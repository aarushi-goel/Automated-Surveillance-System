<?php

	

// array for JSON response

$response = array();

// check for required fields


function getDistance($Guard_Lattitude, $Guard_Longitude, $User_Lat, $User_long)
{
	
 static $d = 0;
$earth_radius = 6371;
	
	
$dLat = deg2rad($User_Lat - $Guard_Lattitude);
	
$dLon = deg2rad($User_long - $Guard_Longitude);
	
	
$a = sin($dLat/2) * sin($dLat/2) + cos(deg2rad($Guard_Lattitude)) * cos(deg2rad($User_Lat)) * sin($dLon/2) * sin($dLon/2);

$c = 2 * asin(sqrt($a));
	
$d = $earth_radius * ($c);

return $d;
}


if (isset($_POST['Guard_Lattitude']) && isset($_POST['Guard_Longitude']) && isset($_POST['phone_number'])){	
					 $Guard_Lattitude = $_POST['Guard_Lattitude'];
					$Guard_Longitude = $_POST['Guard_Longitude'];
						$phone_number = $_POST['phone_number'];
 	 	
					require_once __DIR__ . '/dataconnect.php';
		$db = new DB_CONNECT();
		$disc= getDistance($Guard_Lattitude, $Guard_Longitude, 0.0, 0.0);


	try
		{

			$sql =  "UPDATE kajal SET Guard_Lattitude = '$Guard_Lattitude' , Guard_Longitude = '$Guard_Longitude' , Distance = '$disc' WHERE phone_number = '$phone_number'";
			$result = mysql_query($sql);
	

	
	}catch(Exception $e)
	{
	//echo $e->getMessage(); 		
	}
	// check if row inserted or not

	if ($result){

		
		$response["success"] = 1;
		$response["message"] = "Product successfully created.";

		// echoing JSON response

		echo json_encode($response);
		}
	
	  else  
		{

		// failed to insert row

		$response["success"] = 0;
		$response["message"] = "Oops! An error occurred.";

		// echoing JSON response

		echo json_encode($response);
		}
	
	}
  else
	{

	// required field is missing

	$response["success"] = 0;
	$response["message"] = "Required field(s) is missing";

	// echoing JSON response

	echo json_encode($response);
	}


?>