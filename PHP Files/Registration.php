<?php
  require ('connection.php');
$name =$_POST['name'];

$mobile=$_POST['mobile'];
$email = $_POST['email'];
$password = $_POST['password'];



$sql = "INSERT INTO user(Name,MobileNumber,email,password) VALUES('$name','$mobile','$email','$password')";

$result=mysqli_query($conn,$sql);
$response = array();
if($result)
{
    $response["success"] = true;
}
else
{
    $response["success"] = false;
}
mysqli_close($conn);


echo json_encode($response);

?>