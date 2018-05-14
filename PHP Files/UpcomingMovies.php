<?php
require('connection.php');

$name=$_POST["name"];
//$poster=$_POST["poster"];
$ActorName=$_POST["actor"];
$Description=$_POST["description"];
$ActressName=$_POST["Actress"];

$sql="INSERT INTO Movies(MovieName,ActorName,Description,ActressName) values('$name','$ActorName','$Description','$ActressName')";

$result=mysqli_query($conn,$sql);
$check=array();
if($result)
{
    $check["Success"]=true;
}
else
{
    $check["Success"]=false;
}
mysqli_close($conn);

echo json_encode($check);

?>