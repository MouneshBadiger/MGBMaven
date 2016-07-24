<?php
mysql_connect("localhost","root","root");
mysql_select_db("mgb_gb");
$sql=mysql_query("select * from user_login");
while($row=mysql_fetch_assoc($sql))
$output[]=$row;
print(json_encode($output));
mysql_close();
?>
