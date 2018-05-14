<?php
  require ('connection.php');

$email = $_POST['email'];
$sql = "UPDATE TheaterOwner set Isverified='1' where Email='$email'";

$result=mysqli_query($conn,$sql);
$response = array();
if($result)
{
    $response["success"] = true;
    mail($email," Verified by Admin ","You are verified !!! Now you can able to add Theater and Movies");
}
else
{
    $response["success"] = false;
}
mysqli_close($conn);


echo json_encode($response);

?>