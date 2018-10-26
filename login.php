<?php

  $connect = @mysqli_connect("192.168.1.75","home","1234") or die("error");
  $dbname = "android_test(test)";
  $dbconn = mysqli_select_db($dbname,$connect);
 $username = $_POST['username'];

    $query_search = "select * from regist where ID = '".$username."' AND PW = '".$password. "'";
    $password = $_POST['password'];
    $query_exec = mysqli_query($query_search) or die(mysqli_error());
        echo "User Found";
    $rows = mysqli_num_rows($query_exec);

    if($rows == 0) {
        echo "No Such User Found";
    }
    else  {
    }

?>