<?php
require('connection.php');
$email=$_POST['email'];
$newPassword=$_POST['NewPassword'];
$user=$_POST["user"];
if($user=="user")
{
$sql="Update user SET password='$newPassword' where email='$email'";
}
else if($user=="Theater_Owner")
{$sql="Update TheaterOwner SET password='$newPassword' where Email='$email'"; 
}


$result=mysqli_query($conn,$sql);
$response=array();
if($result){
    $response["success"]=true;
}
else
{
    $response["success"]=false;
}
mysqli_close($conn);


echo json_encode($response);
?>