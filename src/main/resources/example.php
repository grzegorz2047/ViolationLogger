<?php
	echo "<html>";
	echo "<head></head><title>NCP - CraftGames.pl</title>
<link rel='icon' type='image/gif' href='http://i.imgur.com/iZWfi6g.png'>";
	echo "<body>";
	$connect = mysql_connect("localhost","ViolationLogger","password") or die("Nie mozna polaczyc sie z baza: ".mysql_error());
	mysql_select_db("NowaEdycja2016", $connect);
	$sql = "SELECT player, violation, Sum(power) AS 'AllPower' FROM `ViolationLogger` group by player, violation ORDER BY AllPower DESC";
	$output = mysql_query($sql, $connect);
	echo "<table border=1 cellpadding=7 cellspacing=0>
		<tr>
		<th>Player</th>
		<th>Violation</th>
		<th>Power</th>
		</tr>";
	while($record = mysql_fetch_array($output)) {
		echo "<tr>";
		echo "<td>".$record['player']."</td>";
		echo "<td>".$record['violation']."</td>";
		echo "<td>".$record['AllPower']."</td>";
		echo "</tr>";
	}
	mysql_close($connect);
echo "</body>";
echo "</html>";
?>