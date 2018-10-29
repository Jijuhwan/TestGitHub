<?php
  $connect = mysqli_connect("192.168.1.48","home","","test") or die("error");
    $username = $_POST['username'];
    $password = $_POST['password'];

    $query_search = "select * from regist where ID = '".$username."' AND PW = '".$password. "'";
    $query_exec = mysqli_query($connect,$query_search) or die(mysqli_error($connect));
        echo "User Found";
    $rows = mysqli_num_rows($query_exec) or die(mysqli_error($connect));

    if($rows == 0) {
        echo "No Such User Found";
    }
    else  {
    }
?>
