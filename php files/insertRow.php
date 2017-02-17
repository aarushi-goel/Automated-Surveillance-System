<?php

if($_SERVER["REQUEST_METHOD"]=="POST"){
	require 'connection.php';
	createStudent();
}

function createstudent()
{
	global $connect;
	
	$phone_number = $_POST["phone_number"];
	
	$Guard_Name = $_POST["Guard_Name"];

	$query = "Insert into kajal(phone_number,Guard_Name) values ('$phone_number','$Guard_Name');";

	mysqli_query($connect,$query) or die (mysqli_error($connect));
	mysqli_close($connect);

}

?>