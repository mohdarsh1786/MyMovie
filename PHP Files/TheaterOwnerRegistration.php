<?php
  require ('connection.php');
$name =$_POST['name'];

$mobile=$_POST['mobile'];
$email = $_POST['email'];
$password = $_POST['password'];



$sql = "INSERT INTO TheaterOwner(Name,MobileNumber,Email,Password,Isverified) VALUES('$name','$mobile','$email','$password','0')";

$result=mysqli_query($conn,$sql);
$response = array();
if($result)
{
    $response["success"] = true;
    mail($email," Dear User ","Your Information send to admin After getting verification by admin,You can able to login and do all the Functionalies.You will get a seprate Email when verification is done..!");
}
else
{
    $response["success"] = false;
}
mysqli_close($conn);


echo json_encode($response);

?>