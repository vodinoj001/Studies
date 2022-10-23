<?php

/**
 * Файл login.php для не авторизованного пользователя выводит форму логина.
 * При отправке формы проверяет логин/пароль и создает сессию,
 * записывает в нее логин и id пользователя.
 * После авторизации пользователь перенаправляется на главную страницу
 * для изменения ранее введенных данных.
 **/

// Отправляем браузеру правильную кодировку,
// файл login.php должен быть в кодировке UTF-8 без BOM.
header('Content-Type: text/html; charset=UTF-8');

// Начинаем сессию.
session_start();
if (empty($_SESSION['token']))
  $_SESSION['token']=bin2hex(random_bytes(64));

// В суперглобальном массиве $_SESSION хранятся переменные сессии.
// Будем сохранять туда логин после успешной авторизации.
if (!empty($_SESSION['login'])) {
  session_destroy();
  // Если есть логин в сессии, то пользователь уже авторизован.
  // TODO: Сделать выход (окончание сессии вызовом session_destroy()
  //при нажатии на кнопку Выход).
  // Делаем перенаправление на форму.
  header('Location: ./');
}
// В суперглобальном массиве $_SERVER PHP сохраняет некторые заголовки запроса HTTP
// и другие сведения о клиненте и сервере, например метод текущего запроса $_SERVER['REQUEST_METHOD'].
if ($_SERVER['REQUEST_METHOD'] == 'GET') {
  $messages = array();

  $errors = array();
  $errors['login'] = !empty($_COOKIE['login_error']);
  $errors['password'] = !empty($_COOKIE['pass_error']);
  $errors['avtor'] = !empty($_COOKIE['avtor_error']);
  
  if ($errors['login']) {
    // Удаляем куку, указывая время устаревания в прошлом.
    setcookie('login_error', '', 100000);
    // Выводим сообщение.
    $messages[] = '<div class="error">Заполните логин.</div>';
  }
  if ($errors['password']) {
    // Удаляем куку, указывая время устаревания в прошлом.
    setcookie('pass_error', '', 100000);
    // Выводим сообщение.
    $messages[] = '<div class="error">Заполните пароль.</div>';
  }
  if ($errors['avtor']) {
    // Удаляем куку, указывая время устаревания в прошлом.
    setcookie('avtor_error', '', 100000);
    // Выводим сообщение.
    $mes = '<div class="error">Неправильный логин и/или пароль.</div>';
  }



?>
<style>
/* Сообщения об ошибках и поля с ошибками выводим с красным бордюром. */
.error {
  border: 2px solid red;
}
    </style>
<?php
if (!empty($messages) && empty($mes)) {
  print('<div id="messages">');
  // Выводим все сообщения.
  foreach ($messages as $message) {
    print($message);
  }
  print('</div>');
} else if (!empty($mes))
    print($mes);
?>
<head>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
  <meta charset="utf-8">
  <title>Авторизация</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>
  <div id="main-aside-wrapper">
        <div id="cont" class="container">
            <div id="form" class="col-12 order-lg-3 order-sm-2">
              <div id="vhod">
                <a href="index.php" ><-Назад</a>
              </div>
                <form action="" method="post">
                    <input type="hidden" name="token" value="<?php print $_SESSION['token']; ?>"/>
                  Логин:
                  <br/>
                    <input name="login" <?php if ($errors['login'] || $errors['avtor']) {print 'class="error"';} ?> /><br/>
                  Пароль:
                  <br/>
                    <input name="password" <?php if ($errors['password'] || $errors['avtor']) {print 'class="error"';} ?>/><br/><br/>
                    <input type="submit" value="Войти" />
                </form>
            </div>
        </div>
    </div>
</body>
<?php
}
// Иначе, если запрос был методом POST, т.е. нужно сделать авторизацию с записью логина в сессию.
else {
    if($_POST['token']===$_SESSION['token']){
  setlocale(LC_ALL, "ru_RU.UTF-8");
  $errors = FALSE;
  if (empty($_POST['login'])) {
    setcookie('login_error', '1', time() + 24 * 60 * 60);
    $errors = TRUE;
  }
  if (empty($_POST['password'])) {
    setcookie('pass_error', '1', time() + 24 * 60 * 60);
    $errors = TRUE;
  }
 
  // TODO: Проверть есть ли такой логин и пароль в базе данных.
  // Выдать сообщение об ошибках.
  $l=$_POST['login'];
  $p=md5($_POST['password']);

  $user = 'u47525';
  $password = '1167408';
  $db = new PDO('mysql:host=localhost;dbname=u47525', $user, $password, array(PDO::ATTR_PERSISTENT => true));

  $sel = $db->query("SELECT login FROM baza WHERE login = $l");
    foreach($sel as $el)
      $login=$el['login'];
  $sel = $db->query("SELECT password FROM baza WHERE login = $l");
    foreach($sel as $el)
      $pas=$el['password'];

  // Если все ок, то авторизуем пользователя.
  if (!empty($login) && !empty($pas) && $p==$pas){
  $_SESSION['login'] = $_POST['login'];
  $p2=$db->quote($p);
  $sel = $db->query("SELECT id FROM baza WHERE login=$l AND password=$p2");
  foreach($sel as $el)
    $id = (int)$el['id'];
  // Записываем ID пользователя.
  $_SESSION['uid'] = $id;
  } 
  else{
    $errors= TRUE;
    setcookie('avtor_error', '1', time() + 24 * 60 * 60);
  }
  if ($errors) {
    header('Location: login.php');
    exit();
  }
  else {
    setcookie('login_error', '', 100000);
    setcookie('pass_error', '', 100000);
    setcookie('avtor_error', '', 100000);
  }
  // Делаем перенаправление.
  header('Location: ./');
}else exit();
}
