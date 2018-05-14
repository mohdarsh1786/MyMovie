<?php

require('connection.php');
$email=$_POST['email'];
//$email="ajay";
$sql="select * from Theaters_Details where Email='$email'";

$mresult=mysqli_query($conn,$sql);
$allTheater=array();

while($result = mysqli_fetch_assoc($mresult))
{
    $Theater=array();
     $Theater["name"] = $result["TheaterName"];
     $allTheater[] = $Theater;
    
}
$Theater=array();
     $Theater["name"] = "Film_Name";
     $allTheater[] = $Theater;
$sql1="select * from Movies";
$mresult1=mysqli_query($conn,$sql1);
while($result1 = mysqli_fetch_assoc($mresult1))
{
    
      $Theater=array();
 $Theater["name"] = $result1["MovieName"];
     $allTheater[] = $Theater;
}
mysqli_close($conn);
echo json_encode(array("Theater_Name"=>$allTheater));
?>
