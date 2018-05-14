<?php

require('connection.php');
$Theater_name =$_POST['Theater_name'];
//$city = "Allahabad";

$sql="SELECT * FROM Theater WHERE TheaterName='$Theater_name'";


$mresult=mysqli_query($conn,$sql);
$allmovies = array();
while($result = mysqli_fetch_Assoc($mresult))
{
    $movie = array();
    $movie["name"] = $result["MovieName"];
    
    $allmovies[] = $movie;
}

mysqli_close($conn);
echo json_encode(array("movie"=>$allmovies));

?>

