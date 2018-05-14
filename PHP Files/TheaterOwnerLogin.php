<?php
require('connection.php');
$email=$_POST['email'];
$password=$_POST['password'];

$sql="SELECT * From TheaterOwner where Email='$email' and Password='$password' and Isverified='1'";

$row=mysqli_query($conn,$sql);
$result=array();

if(mysqli_num_rows($row)>0)
{
   $result["exist"]=true; 
}
else
{
    $result["exist"]=false;
}
echo json_encode($result);
?>