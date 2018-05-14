<?php
require('connection.php');
$name=$_POST['name'];
$email=$_POST['email'];
$password=$_POST['password'];
//$name="Mohd Arsh";
//$email = "arsh@gmail.com";
//$password = "arsh786";
$sql = "select * from Admin where Name='$name' and Email = '$email' and Password = '$password'";

$result = mysqli_query($conn,$sql);

$Adminarray = array();
if(mysqli_num_rows($result) > 0)
{
    $Adminarray["exist"] = true;
}else
{
    $Adminarray["exist"] = false;
}

echo json_encode($Adminarray);


?>