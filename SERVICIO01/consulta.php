<?php
include('functions.php');
$ART_COD=$_GET["ART_COD"];


if($resultset=getSQLResultSet("SELECT SEG_COD,SEG_FEC,SEG_COR FROM `TB_SEGUIMIENTO` WHERE SEG_ART_COD='$ART_COD'")){
	while ($row = $resultset->fetch_array(MYSQLI_NUM)){
		echo json_encode($row);
	}
}
?>


