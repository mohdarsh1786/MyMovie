<?php


$username= "id5120976_mymovies";
$host = "localhost";
$password = "mymovies";
$database = "id5120976_movie";

$conn = mysqli_connect($host,$username,$password,$database);

$email = $_POST['email'];
$password = $_POST['password'];

//$email = "ajay@gmail.com";
//$password = "ajay";
$sql = "select * from user where email = '$email' and password = '$password'";

$result = mysqli_query($conn,$sql);

$autharray = array();
if(mysqli_num_rows($result) > 0)
{
    $autharray["exist"] = true;
}else
{
    $autharray["exist"] = false;
}

echo json_encode($autharray);


?>