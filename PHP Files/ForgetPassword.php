<?php
  require ('connection.php');

$email =$_POST['email'];
$OTP=$_POST['OTP'];
$user=$_POST['user'];
if($user=="user")
{
$sql="select * from user where email='$email'";
}
else if($user=="Theater_Owner")
{
  $sql="select * from TheaterOwner where email='$email'";  
}
$result=mysqli_query($conn,$sql);
$response=array();
if(mysqli_num_rows($result))
{
    
$response["success"]=true;
mail($email,"Please Enter Otp"," Your Otp is this ".$OTP);
}
else
{
    $response["success"]=false;
}
mysqli_close($conn);


echo json_encode($response);

?>