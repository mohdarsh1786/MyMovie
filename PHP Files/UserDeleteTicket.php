<?php
  require ('connection.php');

$tid =$_POST['tid'];
$seatno =$_POST['seatno'];
$int = (int)$seatno;
$sql="DELETE from Ticket where Tid='$tid' and SeatNo='$int'";

$result=mysqli_query($conn,$sql);
$response = array();
if($result)
{
    $response["success"] = true;
       
}
else
{
    $response["success"] = false;
}
mysqli_close($conn);


echo json_encode($response);

?>