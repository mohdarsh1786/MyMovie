<?php

require('connection.php');
$Email=$_POST['Email'];
$sql="select Distinct TheaterName from Theater where Email='$Email'";

$mresult=mysqli_query($conn,$sql);
$allTheater=array();

while($result = mysqli_fetch_assoc($mresult))
{
    $city=array();
     $city["name"] = $result["TheaterName"];
     $allTheater[] = $city;
    
}

mysqli_close($conn);
echo json_encode(array("Theater_Name"=>$allTheater));
?>
