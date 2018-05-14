<?php
require('connection.php');


function crypto_rand_secure($min, $max)
{
    $range = $max - $min;
    if ($range < 1) return $min; // not so random...
    $log = ceil(log($range, 2));
    $bytes = (int) ($log / 8) + 1; // length in bytes
    $bits = (int) $log + 1; // length in bits
    $filter = (int) (1 << $bits) - 1; // set all lower bits to 1
    do {
        $rnd = hexdec(bin2hex(openssl_random_pseudo_bytes($bytes)));
        $rnd = $rnd & $filter; // discard irrelevant bits
    } while ($rnd > $range);
    return $min + $rnd;
}
function getToken($length){
     $token = "";
     $codeAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
     //$codeAlphabet.= "abcdefghijklmnopqrstuvwxyz";
     $codeAlphabet.= "0123456789";
     $max = strlen($codeAlphabet); // edited

    for ($i=0; $i < $length; $i++) {
        $token .= $codeAlphabet[crypto_rand_secure(0, $max-1)];
    }

    return $token;
}

$theatername=$_POST['TheaterName'];
$Moviename=$_POST['MovieName'];
$date=$_POST['Date'];
$Noofseat=$_POST['numberOfSeat'];
$show_Name=$_POST['showName'];
$email =$_POST["email"];
$tid = "tid-". getToken(8);

$seat=array();
for($i = 0; $i < $Noofseat; $i++)
{
	$t="s".$i;
	$seat[] = $_POST["$t"];
}



foreach($seat as $s)
{
    $sql = "INSERT INTO `Ticket`(`Tid`, `TheaterName`, `MovieName`, `Date`, `ShowName`, `SeatNo`) values('$tid','$theatername','$Moviename','$date','$show_Name','$s');";
    $result = mysqli_query($conn,$sql);
}
$sql1="INSERT INTO `PersonTicket`(`Tid`, `useremail`) VALUES ('$tid','$email')";
$result1=mysqli_query($conn,$sql1);
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
//echo json_encode($seat);

echo json_encode($response);



?>