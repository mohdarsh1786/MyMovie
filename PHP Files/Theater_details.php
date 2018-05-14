<?php

      require ('connection.php');
    


$email= $_POST['email'];
$name = $_POST['TheaterName'];
$city=$_POST['TheaterCity'];


$sql = "INSERT INTO Theaters_Details(TheaterName,TheaterCity,Email) VALUES('$name','$city','$email')";

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