<?php include ('functions.php');
$ART_COD=$_GET['ART_COD'];
$SEG_FEC=date("Y-m-d H:i:s");
$SEG_COR=$_GET['SEG_COR'];

ejecutarSQLCommand("INSERT INTO `TB_SEGUIMIENTO` (SEG_ART_COD,SEG_FEC,SEG_COR) VALUES ('$ART_COD','$SEG_FEC','$SEG_COR');");

 ?>