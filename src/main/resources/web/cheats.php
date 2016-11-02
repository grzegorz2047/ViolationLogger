<!DOCTYPE html>
<html lang="pl">
	<head>
	  <title>NCP - CraftGames.pl</title>
	  <link rel='icon' type='image/gif' href='http://i.imgur.com/iZWfi6g.png'>
	  <meta charset="utf-8">
	  <meta name="viewport" content="width=device-width, initial-scale=1">
	  <link rel="stylesheet" href="css/bootstrap.css">
	  <link rel="stylesheet" href="css/style.css">
	  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</head>
	<body>
	<script language="javascript" type="text/javascript">
		setInterval(function(){
			$('#test').load("cheats.php #test");
		}, 10000);
	</script>
		<nav class="navbar navbar-default">
		  <div class="container-fluid">
			<div class="navbar-header">
			  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			  </button>
			  <a class="navbar-brand" href="../index.php">CraftGames.pl</a>
			</div>

			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			  <ul class="nav navbar-nav">
				<li><a href="#">Wszystkie serwery</a></li>
				<li><a href="#">TheWalls</a></li>
				<li><a href="#">SkyWars</a></li>
				<li><a href="#">X-Run</a></li>
				<li><a href="#">Community</a></li>
				<li><a href="newest-violations.php">Najnowsze przewinienia</a></li>
			  </ul>
			  <form class="navbar-form navbar-left" role="search">
				<div class="form-group">
				  <input type="text" class="form-control" placeholder="Search">
				</div>
				<button type="submit" class="btn btn-default">Szukaj</button>
			  </form>
			  <ul class="nav navbar-nav navbar-right">
				<li><a href="#">Login</a></li>
			  </ul>
			</div>
		  </div>
		</nav>
		<div id="test" class="container">
<?php
	$connect = mysql_connect("localhost","user","pass") or die("Nie mozna polaczyc sie z baza: ".mysql_error());
	mysql_select_db("NowaEdycja2016", $connect);
	$sql = "SELECT player, violation, Sum(power) AS 'AllPower' FROM `ViolationLogger` WHERE violation='KillAura' OR violation='Criticals' OR violation='FastBreak' OR violation='Nuker' OR violation='Regen' group by player, violation ORDER BY AllPower DESC";
	$output = mysql_query($sql, $connect);
?>	
			<table class='table table-striped table-hover '>
				<thead>
					<tr>
						<th>Gracz</th>
						<th>Przewinienie</th>
						<th>Siła</th>
					</tr>
				</thead>	
<?php
	while($record = mysql_fetch_array($output)) {
		echo "<tr>";
		echo "<td>".$record['player']."</td>"; 
		echo "<td>".$record['violation']."</td>";
		if($record['AllPower']%100 !=0 && $record['violation']=='KillAura'){
			echo "<td class='warning'>".$record['AllPower']."</td>";
		}else{
			echo "<td>".$record['AllPower']."</td>";
		}
		echo "</tr>";
	}
	mysql_close($connect);
?>		
			</table>
		</div>
		<div class="container">
			<ul class="pagination center">
				<li class="disabled"><a href="#">&laquo;</a></li>
				<li class="active"><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">4</a></li>
				<li><a href="#">5</a></li>
				<li><a href="#">&raquo;</a></li>
			</ul>
		</div>
		<div class="alert alert-dismissible alert-warning">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<h4>Warning!</h4>
			<p>Best check yo self, you're not looking too good. Nulla vitae elit libero, a pharetra augue. Praesent commodo cursus magna, <a href="#" class="alert-link">vel scelerisque nisl consectetur et</a>.</p>
		</div>
		<div class="footer center">
			&copy; 2016, CraftGames.pl
		</div>
	</body>
</html>