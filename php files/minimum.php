<?php


require_once __DIR__ . '/dataconnect.php';
$db = new DB_CONNECT();

$response = array();

$result = mysql_query("SELECT Distance FROM kajal"); 
$storeArray = Array(); 
while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) { 
	$storeArray[] = $row['Distance']; 
}

$arrlength = count($storeArray);

$mini = $storeArray[0];

for($x = 1 ; $x < $arrlength ; $x++) {
	
	if( $mini > $storeArray[$x] ) {
		$mini = $storeArray[$x];
		}
}
//echo $mini;

$result2 = mysql_query("SELECT phone_number FROM kajal WHERE Distance = $mini");


if (!empty($result2)) {
        // check for empty result
        if (mysql_num_rows($result2) > 0) {
 
            $result2 = mysql_fetch_array($result2);
		$product = array();
		$product["phone_number"] = $result2["phone_number"];
		//$product["Guard_Name"] = $result2["Guard_Name"];

$response["success"] = 1;
 
            // user node
            $response["product"] = array();
 
            array_push($response["product"], $product);
 
            // echoing JSON response
            echo json_encode($response);
        } else {
            // no product found
            $response["success"] = 0;
            $response["message"] = "No product found";
 
            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // no product found
        $response["success"] = 0;
        $response["message"] = "No product found";
 
        // echo no users JSON
        echo json_encode($response);
    }


?>
