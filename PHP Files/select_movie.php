<?php

require('connection.php');
$city = $_POST['city'];
//$city = "Allahabad";
$sql="SELECT * FROM Movies WHERE MovieName IN(SELECT MovieName FROM Theater WHERE TheaterCity='$city')";
//$sql="SELECT * FROM Theater WHERE TheaterCity='$city'";


$mresult=mysqli_query($conn,$sql);
$allmovies = array();
while($result = mysqli_fetch_Assoc($mresult))
{
    $movie = array();
    $movie["name"] = $result["MovieName"];
    $movie["ActorName"] = $result["ActorName"];
    $movie["ActressName"] = $result["ActressName"];
    $movie["description"] = $result["Description"];
    $allmovies[] = $movie;
}

mysqli_close($conn);
echo json_encode(array("movie"=>$allmovies));

?>

