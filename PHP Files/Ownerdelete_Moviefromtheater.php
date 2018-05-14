<?php
  require ('connection.php');
$theatername=$_POST['Theatername'];
$movie_name =$_POST['movie_name'];
$sql = "DELETE from Theater where MovieName='$movie_name' and TheaterName='$theatername'";

$result=mysqli_query($conn,$sql);
$response = array();
if($result)
{
    $response["success"] = true;
       // mail($email," Rejected by Admin ","Sorry You not able to get verified Please try next time");
}
else
{
    $response["success"] = false;
}
mysqli_close($conn);
echo json_encode($response);
?>