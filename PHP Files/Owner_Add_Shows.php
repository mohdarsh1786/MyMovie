<?php
  require('connection.php');
  
  $showDate=$_POST['Show_date'];
  $movieName=$_POST['movie_name'];
  $theaterName=$_POST['theater_name'];
  $time1=$_POST['ShowTime1'];
  $seat1=$_POST['NUmberofSeat1'];
  $time2=$_POST['ShowTime2'];
  $seat2=$_POST['NUmberofSeat2'];
  $time3=$_POST['ShowTime3'];
  $seat3=$_POST['NUmberofSeat3'];
  $time4=$_POST['ShowTime4'];
  $seat4=$_POST['NUmberofSeat4'];
  
  $sql="INSERT INTO Show_time(Date,MovieName,Theater_Name,show1,show1_seat,show2,show2_seat,show3,show3_seat,show4,show4_seat) values('$showDate','$movieName','$theaterName','$time1','$seat1','$time2','$seat2','$time3','$seat3','$time4','$seat4')";
  $result=mysqli_query($conn,$sql);
  $response=array();
  if($result)
  {
      $response["success"]=true;
  }
  else
  {
      $response["success"]=false;
  }
  mysqli_close($conn);
  echo json_encode($response);
 
  ?>