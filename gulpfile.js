var 
  es 		= require('event-stream'),

  gulp 		= require('gulp'),
  fn		= require('gulp-fn'),
  util		= require('gulp-util'),
  clean		= require('gulp-clean'),

  prompt 	= require('gulp-prompt'),
  ftp		= require('gulp-ftp'),
  
  uglify 	= require('gulp-uglify'),

  debug 	= require('gulp-debug');

var all = '**/*';

var 
  path_utils = "./web/utils/",

  path_sparqlJsonConverter = path_utils + "commonjs-sparql-json-converter", // converts the JSON from the Virtuoso SPARQL Endpoint into a compact JSON
  path_dataMerger = path_utils + "commonjs-dummy-json-merger", // combines atti-index with atti-info into atti.json
  path_attiTermsSplitter = path_utils + "commonjs-atti-terms-splitter"; // splits the terms into multiple json file (one for each atto) in order to be visualized using the d3 graph


var 
  path_data_input 	= "./it.camera.hackathon.textmining/output/json/",
  path_data_output 	= "data/", // dati rispetto alla root del sito
  path_tmp			= "./tmp/",
  path_website		= "./web/deploy/",

  path_local_data_output  = path_website + path_data_output,
  path_remote_data_output = "/" + path_data_output,

  file_data_attiInfo = "./web/deploy/data/atti-info.json",
  file_data_attiTerms = path_data_input + "atti-terms.json",
  file_data_clusterIndex = path_data_input + "clusters/clusters-index.json",
  file_data_clusterGraph = path_data_input + "clusters/*-graph.json";


var 
  ftp_host 	= "ftp.code4italy.altervista.org",
  ftp_user 	= "code4italy",
  ftp_password = undefined; // ASKS FTP PASSWORD EVERY TIME IF NULL OR UNDEFINED


var 
  attiDataMerger 	= require(path_dataMerger),
  attiTermsSplitter = require(path_attiTermsSplitter);

// #TODO BUILD data from Virtuoso SPARQL endpoint (in order to generate atti-info.json)


function buildDataAtti() {
	data_attiInfo = require(file_data_attiInfo);
	data_attiTerms = require(file_data_attiTerms);
	
	attiDataMerger(data_attiInfo, data_attiTerms, path_tmp);
}
buildDataAtti();

function buildDataAtto() {
	data_attiTerms = require(file_data_attiTerms);

	attiTermsSplitter(data_attiTerms, path_tmp);
}

// creates atti.json
gulp.task('build-data-Atti', function() {
	buildDataAtti();
});

// creates atto-index.json & atto-*.json
gulp.task('build-data-Atto', function() {
	buildDataAtto();
});

gulp.task('build-atti-data', function() {
	buildDataAtti();
	buildDataAtto();
});

gulp.task('build-cluster-data', function() {
	return gulp.src([file_data_clusterIndex, file_data_clusterGraph])
	  .pipe(gulp.dest(path_tmp)); // copy all requested data in the build folder
})


gulp.task('build-data-uncleaned', ['build-atti-data', 'build-cluster-data'], function() {
	gulp.src(path_tmp + all)
	  .pipe(gulp.dest(path_local_data_output));
});

gulp.task('build-data', ['build-data-uncleaned'], function() {
	gulp.src(path_tmp + all)
	  .pipe(clean()); // cleans tmp folder
});

gulp.task('build-data-and-ask-ftp-password', ['build-data'], function() {
	if(ftp_password != null)
		return;

	return gulp.src("")
	  .pipe(prompt.prompt({
			type: 'password',
			name: 'pass',
			message: 'Please enter your ftp password'
		}, function(res){
			ftp_password = res.pass;
		}));
})

// DEPLOY ONLY THE DATA
gulp.task('deploy-data', ['build-data-and-ask-ftp-password'], function() {
	if(ftp_password == null)
		return;

	return gulp.src(path_local_data_output + all)
	  .pipe(ftp({
			host: ftp_host,
			user: ftp_user,
			pass: ftp_password,
			remotePath: path_remote_data_output
		}));
});

// DEPLOY THE WHOLE WEBSITE
gulp.task('deploy-all', ['build-data-and-ask-ftp-password'], function() {
	if(ftp_password == null)
		return;

	return gulp.src(path_website + all)
	  .pipe(ftp({
            host: ftp_host,
            user: ftp_user,
            pass: ftp_password
        }));
});
