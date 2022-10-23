<?php


session_start();
header('Content-Type: text/html; charset=UTF-8');

/**
 * Задача 6. Реализовать вход администратора с использованием
 * HTTP-авторизации для просмотра и удаления результатов.
 **/
//
// Пример HTTP-аутентификации.
// PHP хранит логин и пароль в суперглобальном массиве $_SERVER.
// Подробнее см. стр. 26 и 99 в учебном пособии Веб-программирование и веб-сервисы.
/*if (empty($_SERVER['PHP_AUTH_USER']) || empty($_SERVER['PHP_AUTH_PW']) ||
    $_SERVER['PHP_AUTH_USER'] != 'admin' || md5($_SERVER['PHP_AUTH_PW']) != md5('123')) {
  header('HTTP/1.1 401 Unanthorized');
  header('WWW-Authenticate: Basic realm="My site"');
  print('<h1>401 Требуется авторизация</h1>');
  exit();
}*/
if (empty($_SERVER['PHP_AUTH_USER']) || empty($_SERVER['PHP_AUTH_PW'])) {
  header('HTTP/1.1 401 Unanthorized');
  header('WWW-Authenticate: Basic realm="My site"');
}
$usLogin = $_SERVER['PHP_AUTH_USER'];
$usHash = $_SERVER['PHP_AUTH_PW'];

$user = 'u47525';
$pass = '1167408';
$db = new PDO('mysql:host=localhost;dbname=u47525', $user, $pass, array(PDO::ATTR_PERSISTENT => true));

$data = $db->query("SELECT * FROM adm WHERE login ='$usLogin' and hash ='$usHash'");
$result = $data->fetchALL(PDO::FETCH_ASSOC);

if (empty($result[0])) {
  header('HTTP/1.1 401 Unanthorized');
  header('WWW-Authenticate: Basic realm="My site"');
  print('<h1>401 Требуется авторизация</h1>');
  exit();
}

print('Вы успешно авторизовались.');



// *********
// Здесь нужно прочитать отправленные ранее пользователями данные и вывести в таблицу.
// Реализовать просмотр и удаление всех данных.
// *********
$user = 'u47525';
$pass = '1167408';
$db = new PDO('mysql:host=localhost;dbname=u47525', $user, $pass, array(PDO::ATTR_PERSISTENT => true));

$data = $db->query("SELECT * FROM application2");
$result = $data->fetchALL(PDO::FETCH_ASSOC);

$data = $db->query("SELECT * FROM baza");
$result1 = $data->fetchALL(PDO::FETCH_ASSOC);

$data = $db->query("SELECT * FROM spw");
$result2 = $data->fetchALL(PDO::FETCH_ASSOC);

if (isset($_GET['del_id'])) {
  $del = $_GET['del_id'];
  $data2 = $db->query("DELETE FROM spw WHERE id='$del'");
  $data2->execute();
  $data3 = $db->query("DELETE FROM baza WHERE id='$del'");
  $data3->execute();
  $data1 = $db->query("DELETE FROM application2 WHERE id='$del'");
  $data1->execute();
  header('Location: admin2.php');
}

if (!empty($_POST['edit_it'])){
  session_start();
  $_SESSION['uid']=(int)$_POST['edit_it'];
  $_SESSION['login']='hi';
  header('Location: ./');
  }

?>


<head>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
  <meta charset="utf-8">
  <title>Задание 6</title>
  <link rel="stylesheet" href="style.css">
</head>




<body>
  <div class="container-fluid p-0">
    <h2>База данных</h2><br>
    <div class="justify-content-center table-responsive">
      <form action=" " method="post">
      <h4>Введите id для редактирования</h4>
        <input name="edit_it"><br>
        <input type="submit" value="OK" />
      </form>

      <br>

      <table class="table table-bordered">
        <thead class="thead-dark">
          <tr>
            <th scope="col">id</th>
            <th scope="col">ФИО</th>
            <th scope="col">Email</th>
            <th scope="col">Год</th>
            <th scope="col">Пол</th>
            <th scope="col">Конечности</th>
            <th scope="col">Биография</th>
            <th scope="col"> </th>
          </tr>
        </thead>
        <tbody class="s">
          <?php foreach ($result as $value) : ?>
            <tr>
              <th scope="row"><?= $value['id'] ?></th>
              <td><?= $value['fio'] ?></td>
              <td><?= $value['email'] ?></td>
              <td><?= $value['year'] ?></td>
              <td><?= $value['pol'] ?></td>
              <td><?= $value['limb'] ?></td>
              <td><?= $value['biography'] ?></td>
              <td><a class="btn btn-danger s" href='?del_id=<?= $value['id'] ?>'>Удалить</a></td>
            </tr>
          <?php endforeach; ?>
        </tbody>
      </table>


      <table class="table table-bordered">
        <thead>
          <tr>
            <th scope="col">id</th>
            <th scope="col">Логин</th>
            <th scope="col">Пароль</th>
          </tr>
        </thead>

        <tbody>
          <?php foreach ($result1 as $value) : ?>
            <tr>
              <td><?= $value['id'] ?></td>
              <td><?= $value['login'] ?></td>
              <td><?= $value['pass'] ?></td>
            </tr>
          <?php endforeach; ?>

        </tbody>
      </table>


      <h3>1-Бессмертие<br>
        2-Прохождение сквозь стены<br>
        3-Левитация<br>
        4-Web</h3><br>

      <table class="table table-bordered">
        <thead>
          <tr>
            <th scope="col">id Сверхспособности</th>
            <th scope="col">id пользователя</th>
            <th scope="col">Номер сверхспособности</th>
          </tr>
        </thead>

        <tbody>
          <?php foreach ($result2 as $value) : ?>
            <tr>
              <td><?= $value['id_spw'] ?></td>
              <td><?= $value['id'] ?></td>
              <td><?= $value['nom_spw'] ?></td>
            </tr>
          <?php endforeach; ?>

        </tbody>
      </table>







      <table class="table table-bordered">
        <thead>
          <tr>
            <th scope="col">Сверхспособности</th>
            <th scope="col">Бессмертие</th>
            <th scope="col">Прохождение сквозь стены</th>
            <th scope="col">Левитация</th>
            <th scope="col">Web</th>

          </tr>
        </thead>


        <tbody>
          <?php
          $data5 = $db->prepare("SELECT COUNT(nom_spw) FROM spw WHERE nom_spw  = '1'");
          $data5->execute();
          $count = $data5->fetchALL(PDO::FETCH_ASSOC);

          $data6 = $db->prepare("SELECT COUNT(nom_spw) FROM spw WHERE nom_spw  = '2'");
          $data6->execute();
          $count1 = $data6->fetchALL(PDO::FETCH_ASSOC);

          $data7 = $db->prepare("SELECT COUNT(nom_spw) FROM spw WHERE nom_spw  = '3'");
          $data7->execute();
          $count2 = $data7->fetchALL(PDO::FETCH_ASSOC);

          $data8 = $db->prepare("SELECT COUNT(nom_spw) FROM spw WHERE nom_spw  = '4'");
          $data8->execute();
          $count3 = $data8->fetchALL(PDO::FETCH_ASSOC);
          ?>
          <tr>
            <th scope="row">Кол-во</th>
            <td><?= $count[0]["COUNT(nom_spw)"] ?></td>
            <td><?= $count1[0]["COUNT(nom_spw)"] ?></td>
            <td><?= $count2[0]["COUNT(nom_spw)"] ?></td>
            <td><?= $count3[0]["COUNT(nom_spw)"] ?></td>
          </tr>
        </tbody>
      </table>

    </div>
  </div>
</body>
