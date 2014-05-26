<?php
$content = file_get_contents('./../data/clusters-index.json');
$clusters = json_decode($content, true);
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
	<script type="text/javascript" src="/lib/jquery.tagcloud.js" charset="utf-8"></script>

	<script type="text/javascript" src="/lib/selectize.js" charset="utf-8"></script>
	<link rel="stylesheet" href="/css/selectize.css">

	<link rel="stylesheet" href="/css/main.css">
	<link rel="stylesheet" href="/css/footer.css">
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
					<div class="search">
						<div class="control-group">
							<select id="select" class="searchbar" placeholder="Ricerca..."></select>
						</div>
						<script>
						$('#select').selectize({
							persist: false,
							maxItems: null,
							valueField: 'label',
							labelField: 'label',
							searchField: ['label'],
							sortField: [
								{field: 'label', direction: 'asc'}
							],
							options: [
							<?php
$terms = array();

$lenClusters = count($clusters);
for($i = 0; $i < $lenClusters; $i++) {
	$cluster = $clusters[$i];
	$len = count($cluster["terms"]);
	for($j = 0; $j < $len; $j++) {
		$term = ucfirst($cluster["terms"][$j]);
		if(!in_array($term, $terms))
			array_push($terms, $term);
	}

}

$len = count($terms);
if($len > 0)
	echo "{ label: \"".$terms[0]."\"}";

for($j = 1; $j < $len; $j++) {
	echo ", { label: \"".$terms[$j]."\"}";
}

							?>

							],
							render: {
								item: function(item, escape) {
									return '<div>' + item.label + '</div>';
								},
								option: function(item, escape)  {
									return '<div>' + item.label + '</div>';
								}
							},
							onChange: function(values) {
								if(values == null || values.length == 0) {
									d3.selectAll('.law').classed('hidden', false);
									return;
								}

								d3.selectAll('.law').classed('hidden', true);

								for (var i = values.length - 1; i >= 0; i--) {
									var value = values[i];

									d3.selectAll('.law.'+value).classed('hidden', false);
								};
							}
						});
						</script>
					</div>
					<div class="laws">
<?php
$lenClusters = count($clusters);
for($i = 0; $i < $lenClusters; $i++) {
	$cluster = $clusters[$i];

						echo '<div class="law panel panel-default';

						$len = count($cluster["terms"]);
						for($j = 0; $j < $len; $j++)
							echo " ".$cluster["terms"][$j];

						echo '">';
?>
							<div class="panel-body">
								<?php echo '<a href="/temi/details.php?id='.$cluster["id"].'">' ?><h4><span class="glyphicon glyphicon-share"></span>Tema <?php echo $cluster['id']; ?></h4></a>
								<div class="tagcloud">
<?php
	$len = count($cluster["terms"]);
	for($j = 0; $j < $len; $j++)
		echo "<a href=\"\" rel=\"".(rand()&9 + 1)."\">".$cluster["terms"][$j]."</a> ";
?>
								</div>
							</div>
						</div>
							
<?php
}
?>
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
$.fn.tagcloud.defaults = {
  size: {start: 14, end: 18, unit: 'pt'},
  color: {start: '#cde', end: '#f52'}
};

$(function() {
  $('#home .tagcloud a').tagcloud();
});
	</script>
</body>
</html>