<?php

//include('conn.php');
require('connection.php');

$TheaterName=$_POST["TheaterName"];
$MovieName = $_POST["MovieName"];
$show_id=$_POST["showName"];
$date=$_POST["show_date"];
$sql = "SELECT * FROM Ticket  WHERE TheaterName= '$TheaterName' AND MovieName= '$MovieName' AND ShowName='$show_id' and Date='$date' ORDER BY Date ASC";
$res = mysqli_query($conn,$sql);
$r = array();
if(mysqli_num_rows($res) > 0)
{
	while($row = mysqli_fetch_assoc($res))
	{
		$r[] = $row;
	}
}

echo json_encode($r);

?>