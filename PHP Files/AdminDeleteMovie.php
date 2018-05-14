<?php

require('connection.php');


$sql="select Distinct MovieName from Movies";

$mresult=mysqli_query($conn,$sql);
$allMovie=array();

while($result = mysqli_fetch_assoc($mresult))
{
    $Movie=array();
     $Movie["name"] = $result["MovieName"];
     $allMovie[] = $Movie;
    
}

mysqli_close($conn);
echo json_encode(array("Movie_Name"=>$allMovie));
?>
