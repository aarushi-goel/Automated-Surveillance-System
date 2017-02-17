<?php

// include db connect class

	require_once __DIR__ . '/dataconnect.php';
//require("dataconnect.php");
	// connecting to db
	echo "aarushi";
	$db = new DB_CONNECT();
	print_r($db);

function getDistance($latitude1, $longitude1, $latitude2, $longitude2)
 {
	
//$latitude1 = '452.21';
//$longitude1 = '42.81';
//$latitude2 = '65.21';
//$longitude2 = '23.28';
$earth_radius = 6371;
	
	
$dLat = deg2rad($latitude2 - $latitude1);
	
$dLon = deg2rad($longitude2 - $longitude1);
	
	
$a = sin($dLat/2) * sin($dLat/2) + cos(deg2rad($latitude1)) * cos(deg2rad($latitude2)) * sin($dLon/2) * sin($dLon/2);

				$c = 2 * asin(sqrt($a));
	
$d = $earth_radius * $c;


	
echo $d;

}







/*
$sa= Array(); 
while ($row = mysql_fetch_array($result2, MYSQL_ASSOC)) { 
	$sa["phone_number"] = $row['phone_number']; 
	$sa["Guard_Name"] = $row['Guard_Name'];
}
//echo $sa[0];
//echo $sa[1];


// echo json_encode($sa); 
echo json_encode(array('closest'=>$sa)); 
  
//echo json_encode($sa); 

*/

getDistance(452.21,42.81,65.21,23.28);






function getDistance($Guard_Lattitude, $Guard_Longitude, $User_Lat, $User_long)
 {
	
//$latitude1 = '452.21';
//$longitude1 = '42.81';
//$latitude2 = '65.21';
//$longitude2 = '23.28';
$earth_radius = 6371;
	
	
$dLat = deg2rad($User_Lat - $Guard_Lattitude);
	
$dLon = deg2rad($User_long - $Guard_Longitude);
	
	
$a = sin($dLat/2) * sin($dLat/2) + cos(deg2rad($Guard_Lattitude)) * cos(deg2rad($User_Lat) * sin($dLon/2) * sin($dLon/2);

				$c = 2 * asin(sqrt($a));
	
$d = $earth_radius * $c;


	
//echo $d;

}


getDistance($Guard_Lattitude, $Guard_Longitude, $User_Lat, $User_long);

$sql2 =  "UPDATE kajal SET Distance = '$d' WHERE phone_number = '$phone_number'";
$result2 = mysql_query($sql);


//$Guard_Lattitude = '123.56';
//$Guard_Longitude = '134.78';
//$phone_number = '1234569870';




$myQuery = "SELECT phone_number FROM kajal WHERE Guard_Name= 'vishal sir'";
$re = mysql_query($myQuery);
if($re)
{
	$result2 = mysql_fetch_assoc($re);
	print_r($result2);
}
else
echo "not found";            
?>

code ki zaroorat nahi hai
implementtion mei
sirf theory likh de



loudspeaker

implementation ke baad hi karenge hum agar time bacha toh

tujhse nahi ho raha toh hu
m kaise karenge


hum kaise karein
varun sir ko error dikha do
whatsapp pe


implementation mei
 code is not required
   




report ka poora kaam tera
 implementation ka humara

50 ke aas pass

plagarism wali cheez ko consider mat kar abhi
