<?php

require('connection.php');

$sql="select Distinct TheaterCity from Theater";

$mresult=mysqli_query($conn,$sql);
$allcity=array();

while($result = mysqli_fetch_assoc($mresult))
{
    $city=array();
     $city["name"] = $result["TheaterCity"];
     $allcity[] = $city;
    
}

mysqli_close($conn);
echo json_encode(array("City_Name"=>$allcity));
?>
