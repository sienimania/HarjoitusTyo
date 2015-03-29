<?php
$adata = $_POST['d0'];
$bdata = $_POST['d1'];
if ($adata == "Looppi")
    {
        echo "Looping back information: ".$bdata;
    }
	else
	{
		echo "Command Line Error!!!";
	}
?>