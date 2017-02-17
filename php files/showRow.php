<?php

if($_SERVER["REQUEST_METHOD"]=="POST"){
	include 'connection.php';
	showRow();
}

function showRow()
{
	global $connect;
	$query = "Select * From kajal;";
	$result = mysqli_query($connect,$query);
	$number_of_rows = mysqli_num_rows($result);
	$temp_array = array();

	if($number_of_rows > 0){
		while($row = mysqli_fetch_assoc($result)){
			$temp_array[] = $row;
}
}
	header('Connect-Type: application/json');
	echo json_encode(array("values"=>$temp_array));
	mysqli_close($connect);
}
?>