
<?php
/*
* Following code will create a new product row
* All product details are read from HTTP Post Request
*/



// array for JSON response

$response = array();

// check for required fields

//echo "aarushigoel";

if (isset($_POST['Guard_Name']) && isset($_POST['phone_number']))
	{
 $Guard_Name = $_POST['Guard_Name'];
$phone_number = $_POST['phone_number'];

//$aScoreData = json_decode($phone_number, true);
// $aScoreData2 = json_decode($Guard_Name, true);




//echo $aScoreData;
//echo $aScoreData2;
	//$phone_number = '887317459';
       // $Guard_Lattitude = '123.56';
	//$Guard_longitude = '134.78';
	//$User_Lat = '234.65';
	//$User_long = '564.87';
	//$Distance = '45';
	//$Guard_Name = 'nishal Sir';
	// include db connect class

	require_once __DIR__ . '/dataconnect.php';
//require("dataconnect.php");
	// connecting to db
	//echo "aarushi";
	$db = new DB_CONNECT();
	//print_r($db);
	// mysql inserting a new row
	

	try
		{
	$query = "INSERT INTO kajal(Guard_Name, phone_number) VALUES('$Guard_Name', '$phone_number')";
	
	$result = mysql_query($query);
	//echo $query;
	//echo $result;
	}catch(Exception $e)
	{
	//echo $e->getMessage(); 		
			}
	// check if row inserted or not

	if ($result)
		{

		// successfully inserted into database

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


