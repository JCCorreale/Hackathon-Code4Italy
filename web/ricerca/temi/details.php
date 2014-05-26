<?php
$clusters = json_decode(file_get_contents('./../data/clusters-index.json'), true);

if(!isset($_GET['id']))
	die("Invalid request");

$cluster = null;

$len = count($clusters);
for($i = 0; $i < $len; $i++) {
	if($clusters[$i]['id'] == $_GET['id']) {
		$cluster = $clusters[$i];
		break;
	}
}


if($cluster == null)
	die("Invalid id requested: ".$_GET['id']);

$clusterId = $cluster['id'];


function print_labels($array) {
	$len = count($array);
	for ($i = 0; $i < $len; $i++) { 
		echo "<div class=\"label-wrapper\"><span class=\"label label-primary\">".$array[$i]."</span></div>";
	}
}

?>

<html>
<head>
	<title>Hackathon Code4Italy - Text Miner</title>
	
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- JQUERY -->
	<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.1.min.js"></script>

	<!-- BOOTSTRAP -->
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

	<!-- Optional theme -->
	<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">

	<!-- Latest compiled and minified JavaScript -->
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

	<script type="text/javascript" src="/lib/evt.js" charset="utf-8"></script>
	<script type="text/javascript" src="/lib/d3.v3.js" charset="utf-8"></script>
	<script type="text/javascript" src="/lib/nv.d3.js" charset="utf-8"></script>
	<script type="text/javascript" src="/lib/nv-extensions.js" charset="utf-8"></script>
	<script type="text/javascript" src="/lib/nv-extensions-model.js" charset="utf-8"></script>
	<script type="text/javascript" src="/lib/chart-factory.js" charset="utf-8"></script>


	<link rel="stylesheet" href="/css/main.css">
	<link rel="stylesheet" href="/css/footer.css">
	<link href="/css/nv.d3.css" rel="stylesheet" type="text/css">
	<style>

	#chart svg {
	  height: 400px;
	}

	</style>
</head>
<body>
	<div id="wrap">
		<div class="container">
			<div class="row title">
				<!--<div class="col-md-3 logo"><img src="/img/logo.jpg" alt="Camera dei Deputati" /></div>-->
				<div class="col-md-9 .col-md-offset-3"><h1>Code4Italy @Montecitorio<small class="subtitle">Text Mining</small></h1></div>
			</div>
			<div class="row">
				<div class="col-md-3">
					<ul class="nav nav-pills nav-stacked admin-menu">
						<li><a href="/index.html"><span class="glyphicon glyphicon-home"></span>Home</a></li>
						<li><a href="/atti/index.php"><span class="glyphicon glyphicon-book"></span>Leggi</a></li>
						<li class="active"><a href="/temi/index.php"><span class="glyphicon glyphicon-tag"></span>Temi</a></li>
						<li><a href="/dati/index.html"><span class="glyphicon glyphicon-list-alt"></span>Dati</a></li>
						<li><a href="/info/index.html"><span class="glyphicon glyphicon-info-sign"></span>Info</a></li>
					</ul>
				</div>
				<div class="col-md-9 well admin-content" id="home">
					<div class="row-fluid">
						<div class="col-md-8" id="home">
							<div class="chart" id="chart">
								<svg></svg>
							</div>
							<?php
$len = count($cluster['atti']);
for ($i=0; $i < $len; $i++) {
	echo "<div><a href=\"/atti/details.php?id=".$cluster['atti'][$i]."\">".$cluster['atti'][$i]."</a></div>\n";
}
							?>
						</div>
						<div class="col-md-4" id="home">
							<table class="table">
								<tr><td class="table-label">titolo</td><td>Tema <?php echo $cluster['id'] ?></td></tr>
								<tr><td class="table-label">keywords</td><td><?php print_labels($cluster['terms']); ?></td></tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="footer" class="footer navbar-fixed-bottom">
		<div class="container" class="text-center">
			<div>
				<a href="http://dati.camera.it/it/hackathon/"><img src="/img/code4italy.jpg" class="text-center" /></a>
				<p class="text-center">Text Mining - a data mining project by <b>Thierry Spetebroot</b>, <b>Jean Claude Correale</b>, <b>Alessandro Colace</b></p>
			</div>
		</div>
	</div>

	<script type="text/javascript">
d3.json(<?php echo "\"/data/".$clusterId."-graph.json\"" ?>, function(error, model) {
  model.series.key = "Tema " + <?php echo $clusterId; ?>;
  model.series = [model.series]; // quickfix (missing array in model - json)

  model.xAxisLabels = ["Gen '13", "Feb '13", "Mar '13", "Apr '13", "May '13", "Jun '13", "Jul '13", "Aug '13", "Sep '13", "Oct '13", "Nov '13", "Dec '13"]

  ChartFactory.createLineChart(d3.select("#chart svg"), model);
});
	</script>
</body>
</html>