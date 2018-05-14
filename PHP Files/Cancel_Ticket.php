<?php
require('connection.php');
$email='arsh@gmail.com';//$_POST['email'];
$sql="SELECT t.Tid,t.MovieName,t.TheaterName,t.ShowName,t.SeatNo,t.Date FROM Ticket as t,PersonTicket as p WHERE t.Tid=p.Tid and p.useremail='$email'";
$result=mysqli_query($conn,$sql);
$resultarray=array();
while($mresult=mysqli_fetch_assoc($result))
{$response=array();
    
$response["Tid"]=$mresult["Tid"];
$response["MovieName"]=$mresult["MovieName"];
$response["TheaterName"]=$mresult["TheaterName"];
$TheaterName=$mresult["TheaterName"];
$Moviename=$mresult["MovieName"];
$showname=$mresult["ShowName"];
$response["SeatNo"]=$mresult["SeatNo"];
$response["Date"]=$mresult["Date"];
  $sql2="SELECT $showname FROM Theater WHERE TheaterName='$TheaterName' and MovieName='$Moviename'";
  $result2=mysqli_query($conn,$sql2);
  
  if($showresult=mysqli_fetch_assoc($result2))
  {
      $response["ShowTime"]=$showresult[$showname];
       
  }
        
$resultarray[]=$response;
}
mysqli_close($conn); 
echo json_encode(array("Ticket_info"=>$resultarray));

//echo json_encode(array("City_Name"=>$allcity));

?>
