<?php
  require ('connection.php');

$email = $_POST['email'];
$sql = "DELETE from TheaterOwner where Email='$email'";

$result=mysqli_query($conn,$sql);
$response = array();
if($result)
{
    $response["success"] = true;
         mail($email," Rejected by Admin ","Sorry You not able to get verified Please try next time");
}
else
{
    $response["success"] = false;
}
mysqli_close($conn);


echo json_encode($response);

?>