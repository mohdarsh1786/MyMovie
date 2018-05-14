<?php
require('connection.php');
$theaterName=$_POST['theater_name'];
$movieName=$_POST['movie_name'];
$price=$_POST['price'];
$sql="UPDATE Theater SET Price='$price' where TheaterName='$theaterName' and MovieName='$movieName'";

$result=mysqli_query($conn,$sql);

$response=array();
if($result)
{
    $response["success"]=true;
}
else
{
    $response["success"]=false;
}
mysqli_close($conn);
echo json_encode($response);
?>