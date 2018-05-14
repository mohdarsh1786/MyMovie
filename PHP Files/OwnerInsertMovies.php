<?php
 require('connection.php');

$MovieName =$_POST['movie_name'];
$TheaterName =$_POST['theater_name'];
$price=$_POST['price'];
$show1=$_POST['time1'];
$show2=$_POST['time2'];
$show3=$_POST['time3'];
$show4=$_POST['time4'];

$response = array();
$sql3="SELECT * FROM Theater WHERE TheaterName='$TheaterName' and MovieName='$MovieName'";
$result3=mysqli_query($conn,$sql3);
if(mysqli_num_rows($result3))
{
      $response["success"] = "Already";
}
else
{ 
$sql1="SELECT  TheaterCity,Email FROM Theaters_Details WHERE TheaterName ='$TheaterName'";

$result1=mysqli_query($conn,$sql1);
if($mresult = mysqli_fetch_Assoc($result1))
{
    $theatercity=$mresult['TheaterCity'];
    $email=$mresult['Email'];
}
$sql = "INSERT INTO Theater(TheaterName,MovieName,TheaterCity,Price,Email,show1,show2,show3,show4) VALUES ('$TheaterName','$MovieName','$theatercity','$price','$email','$show1','$show2','$show3','$show4')";

$result=mysqli_query($conn,$sql);

if($result)
{
    $response["success"] = "success";
}
else
{
    $response["success"] = "failure";
}
mysqli_close($conn);


echo json_encode($response);
}
?>