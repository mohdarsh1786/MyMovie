<?php

require('connection.php');
$movie_Name = $_POST['movie_name'];
$city=$_POST['city_name'];
//$date =$_POST['date'];
$sql="SELECT * FROM Theater WHERE MovieName='$movie_Name' and TheaterCity='$city'";


$mresult=mysqli_query($conn,$sql);
$allTheater = array();
while($result = mysqli_fetch_Assoc($mresult))
{
    $Theater = array();
    $Theater["name"] = $result["TheaterName"];
  $Theater["show1"] = $result["show1"];
    $Theater["show2"] = $result["show2"];
    $Theater["show3"] = $result["show3"];
    $Theater["show4"] = $result["show4"];
    $allTheater[] = $Theater;
}

mysqli_close($conn);
echo json_encode(array("Theater"=>$allTheater));
//echo json_encode($allTheater);

?>

