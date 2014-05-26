<?php
$atti = json_decode(file_get_contents('./../data/atti.json'), true);
$attiIndex = json_decode(file_get_contents('./../data/atti-index.json'), true);

if(!isset($_GET['id']))
	die("Invalid request");

$atto = null;

$lenAtti = count($atti);
for($i = 0; $i < $lenAtti; $i++) {
	if($atti[$i]['atto'] == $_GET['id']) {
		$atto = $atti[$i];
		break;
	}
}


if($atto == null)
	die("Invalid id requested: ".$_GET['id']);

$attoId = null;
$lenAtti = count($attiIndex);
for ($i = 0; $i < $lenAtti; $i++) { 
	if($attiIndex[$i]['atto'] == $atto['atto']) {
		$attoId = $attiIndex[$i]['id'];
		break;
	}
}

if($atto == null)
	die("Id not found: ".$_GET['id']);


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
	<script type="text/javascript" src="/lib/graph-ui.js" charset="utf-8"></script>
	<script type="text/javascript" src="/app/graph-model-utils.js" charset="utf-8"></script>


	<link rel="stylesheet" href="/css/main.css">
	<link rel="stylesheet" href="/css/footer.css">
	<link rel="stylesheet" href="/css/graph.css">
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
						<li class="active"><a href="/atti/index.php"><span class="glyphicon glyphicon-book"></span>Leggi</a></li>
						<li><a href="/temi/index.php"><span class="glyphicon glyphicon-tag"></span>Temi</a></li>
						<li><a href="/dati/index.html"><span class="glyphicon glyphicon-list-alt"></span>Dati</a></li>
						<li><a href="/info/index.html"><span class="glyphicon glyphicon-info-sign"></span>Info</a></li>
					</ul>
				</div>
				<div class="col-md-9 well admin-content" id="home">
					<div class="row-fluid">
						<div class="col-md-8" id="home">
							<div class="graph" id="graph"></div>
						</div>
						<div class="col-md-4" id="home">
							<table class="table">
								<tr><td class="table-label">titolo</td><td><?php echo $atto['label'] ?></td></tr>
								<tr><td class="table-label">iniziativa</td><td><?php echo $atto['iniziativa'] ?></td></tr>
								<tr><td class="table-label">iri</td><td><a href="<?php echo $atto['atto'] ?>">link</a></td></tr>
								<tr><td class="table-label">contenuto</td><td><?php echo $atto['contenuto'] ?></td></tr>
								<tr><td class="table-label">keywords</td><td><?php print_labels($atto['terms']); ?></td></tr>
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
d3.json(<?php echo "\"/data/atto-".$attoId.".json\"" ?>, function(error, model) {
	graphModel = GraphModelUtils.attoToGraph(model);
	graphUi = new GraphUi(d3.select("#graph"), graphModel, undefined, undefined, {windowResizedEvent: nv.utils.events.windowResize});
	graphUi.nodesSelection.each(function(d) { d3.select(this).classed(d.type, true); });
});
	</script>
</body>
</html>