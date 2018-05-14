<?php
require('connection.php');

$theater_name=$_POST['theater_name'];

//$date= $_POST['date'];
$movie_name=$_POST['movie_name'];
$show=$_POST['show'];

$sql1="SELECT * FROM Theater as T WHERE T.TheaterName='$theater_name' and T.MovieName='$movie_name' ";
$result=mysqli_query($conn,$sql1);
/*
$sql="SELECT * FROM Show_time where Theater_Name='$theater_name' and Date='$date' ";
$result=mysqli_query($conn,$sql);
*/
$mresult=array();
 
if($result1=mysqli_fetch_assoc($result))
{
    $theater=array();

    $theater['Time']=$result1[$show];
    $theater['price']=$result1['Price'];
    $mresult[]=$theater;
}

mysqli_close($conn);
echo json_encode(array("seat"=>$mresult));


?>