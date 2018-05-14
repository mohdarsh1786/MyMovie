<?php
require('connection.php');
$theater_name=$_POST['theater_name'];
$movie_name=$_POST['movie_name'];
$email=$_POST['email'];
$sql="DELETE FROM Theater WHERE TheaterName='$theater_name' and MovieName='$movie_name' and Email='$email'";
$result=mysqli_query($conn,$sql);
$response=array();
if($result)
{
    $response["success"]=true;
}
else{
    $response["success"]=false;
}

mysqli_close($conn);
echo json_encode($response);
?>
