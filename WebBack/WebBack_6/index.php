<?php
/**
 * Реализовать проверку заполнения обязательных полей формы в предыдущей
 * с использованием Cookies, а также заполнение формы по умолчанию ранее
 * введенными значениями.
 */

// Отправляем браузеру правильную кодировку,
// файл index.php должен быть в кодировке UTF-8 без BOM.
header('Content-Type: text/html; charset=UTF-8');

// В суперглобальном массиве $_SERVER PHP сохраняет некторые заголовки запроса HTTP
// и другие сведения о клиненте и сервере, например метод текущего запроса $_SERVER['REQUEST_METHOD'].
if ($_SERVER['REQUEST_METHOD'] == 'GET') {
  // Массив для временного хранения сообщений пользователю.
  $messages = array();

  // В суперглобальном массиве $_COOKIE PHP хранит все имена и значения куки текущего запроса.
  // Выдаем сообщение об успешном сохранении.
  if (!empty($_COOKIE['save'])) {
    // Удаляем куку, указывая время устаревания в прошлом.
    setcookie('save', '', 100000);
    setcookie('login', '', 100000); 
    setcookie('pass', '', 100000); 
    // Если есть параметр save, то выводим сообщение пользователю.
    $messages[] = 'Спасибо, результаты сохранены.';
  }
    if (!empty($_COOKIE['pass'])) {
        $messages[] = sprintf('Вы можете <a href="login.php">войти</a> с логином <strong>%s</strong>
          и паролем <strong>%s</strong> для изменения данных.',
          strip_tags($_COOKIE['login']),
          strip_tags($_COOKIE['pass']));
  }

  // Складываем признак ошибок в массив.
  $errors = array();
  $errors['fio'] = !empty($_COOKIE['fio_error']);
  $errors['email'] = !empty($_COOKIE['email_error']);
  $errors['limbs'] = !empty($_COOKIE['limbs_error']);
  $errors['gender'] = !empty($_COOKIE['gender_error']);
  $errors['power'] = !empty($_COOKIE['power_error']);
  $errors['ok'] = !empty($_COOKIE['ok_error']);
  $errors['biography'] = !empty($_COOKIE['biography_error']);
  // TODO: аналогично все поля.

  // Выдаем сообщения об ошибках.
  if ($errors['fio']) {
    // Удаляем куку, указывая время устаревания в прошлом.
    setcookie('fio_error', '', 100000);
    // Выводим сообщение.
    $messages[] = '<div class="error">Заполните имя корректно.</div>';
  }
  if ($errors['email']) {
    // Удаляем куку, указывая время устаревания в прошлом.
    setcookie('email_error', '', 100000);
    // Выводим сообщение.
    $messages[] = '<div class="error">Заполните email корректно.</div>';
  }

  if ($errors['limbs']) {
    // Удаляем куку, указывая время устаревания в прошлом.
    setcookie('limbs_error', '', 100000);
    // Выводим сообщение.
    $messages[] = '<div class="error">Укажите количество ваших конечностей.</div>';
  }

  if ($errors['gender']) {
    // Удаляем куку, указывая время устаревания в прошлом.
    setcookie('gender_error', '', 100000);
    // Выводим сообщение.
    $messages[] = '<div class="error">Укажите свой пол.</div>';
  }

  if ($errors['power']) {
    // Удаляем куку, указывая время устаревания в прошлом.
    setcookie('power_error', '', 100000);
    // Выводим сообщение.
    $messages[] = '<div class="error">Укажите свои суперспособности.</div>';
  }

  if ($errors['biography']) {
    // Удаляем куку, указывая время устаревания в прошлом.
    setcookie('biography_error', '', 100000);
    // Выводим сообщение.
    $messages[] = '<div class="error">Заполните информацию о себе корректно.</div>';
  }

  if ($errors['ok']) {
    // Удаляем куку, указывая время устаревания в прошлом.
    setcookie('ok_error', '', 100000);
    // Выводим сообщение.
    $messages[] = '<div class="error">Поставьте галочку.</div>';
  }

  // TODO: тут выдать сообщения об ошибках в других полях.

  // Складываем предыдущие значения полей в массив, если есть.
  $values = array();
  $values['fio'] = empty($_COOKIE['fio_value']) ? '' : strip_tags($_COOKIE['fio_value']);
  $values['email'] = empty($_COOKIE['email_value']) ? '' : strip_tags($_COOKIE['email_value']);
  $values['year'] = empty($_COOKIE['year_value']) ? '' : $_COOKIE['year_value'];
  $values['gender'] = empty($_COOKIE['gender_value']) ? '' : $_COOKIE['gender_value'];
  $values['limbs'] = empty($_COOKIE['limbs_value']) ? '' : $_COOKIE['limbs_value'];
  $values['power'] = empty($_COOKIE['power_value']) ? '' : $_COOKIE['power_value'];
  $values['biography'] = empty($_COOKIE['biography_value']) ? '' : strip_tags($_COOKIE['biography_value']);

  // Если нет предыдущих ошибок ввода, есть кука сессии, начали сессию и
  // ранее в сессию записан факт успешного логина.
  $er=false;
  foreach($errors as $el)
    if ($el == true)
      $er = true;
  if (empty($er) && !empty($_COOKIE[session_name()]) && 
  session_start() && !empty($_SESSION['login'])) {
// TODO: загрузить данные пользователя из БД
// и заполнить переменную $values,
// предварительно санитизовав.
  $user = 'u47525';
  $pass = '1167408';
  $db = new PDO('mysql:host=localhost;dbname=u47525', $user, $pass, array(PDO::ATTR_PERSISTENT => true));
  $uid = $_SESSION['uid'];
  $res= $db->query("SELECT fio, email, year, pol, limb, biography FROM application2 WHERE id = $uid");
  foreach($res as $el){
    $values['fio']=strip_tags($el['fio']);
    $values['email']=strip_tags($el['email']);
    $values['year']=strip_tags($el['year']);
    $values['gender']=strip_tags($el['pol']);
    $values['limbs']=strip_tags($el['limb']);
    $values['biography']=strip_tags($el['biography']);
  }
  $res= $db->query("SELECT nom_spw FROM spw WHERE id = $uid");
  $sup = array();
  foreach($res as $el){
    $sup[]=(int)strip_tags($el['nom_spw']);
  }
  $sp = implode('',$sup);
  $values['power'] =$sp;
printf('Вход с логином %s, uid %d', $_SESSION['login'], $_SESSION['uid']);
}
  

  // Включаем содержимое файла form.php.
  // В нем будут доступны переменные $messages, $errors и $values для вывода 
  // сообщений, полей с ранее заполненными данными и признаками ошибок.
  include('form.php');
}
// Иначе, если запрос был методом POST, т.е. нужно проверить данные и сохранить их в XML-файл.
else {
  // Проверяем ошибки.
  setlocale(LC_ALL, "ru_RU.UTF-8");
  $errors = FALSE;
  if (empty($_POST['fio']) || preg_match('/[^(\x7F-\xFF)|(\s)]/', $_POST['fio'])) {
    // Выдаем куку на день с флажком об ошибке в поле fio.
    setcookie('fio_error', '1', time() + 24 * 60 * 60);
    $errors = TRUE;
  }
  else {
    // Сохраняем ранее введенное в форму значение на месяц.
    setcookie('fio_value', $_POST['fio'], time() + 30 * 24 * 60 * 60);
  }


  if (empty($_POST['email'])) {
    // 
    setcookie('email_error', '1', time() + 24 * 60 * 60);
    $errors = TRUE;
  }
  else {
    // Сохраняем ранее введенное в форму значение на месяц.
    setcookie('email_value', $_POST['email'], time() + 30 * 24 * 60 * 60);
  }

  if (empty($_POST['gender'])) {
    // 
    setcookie('gender_error', '1', time() + 24 * 60 * 60);
    $errors = TRUE;
  }
  else {
    // Сохраняем ранее введенное в форму значение на месяц.
    setcookie('gender_value', $_POST['gender'], time() + 30 * 24 * 60 * 60);
  }

  if (empty($_POST['limbs'])) {
    // 
    setcookie('limbs_error', '1', time() + 24 * 60 * 60);
    $errors = TRUE;
  }
  else {
    // Сохраняем ранее введенное в форму значение на месяц.
    setcookie('limbs_value', $_POST['limbs'], time() + 30 * 24 * 60 * 60);
  }

  if (empty($_POST['power'])) {
    // 
    setcookie('power_error', '1', time() + 24 * 60 * 60);
    $errors = TRUE;
  }
  else {
    $sp = implode('',$_POST['power']);
    // Сохраняем ранее введенное в форму значение на месяц.
    setcookie('power_value', $sp, time() + 30 * 24 * 60 * 60);
  }


  if (empty($_POST['biography'])) {
    // 
    setcookie('biography_error', '1', time() + 24 * 60 * 60);
    $errors = TRUE;
  }
  else {
    // Сохраняем ранее введенное в форму значение на месяц.
    setcookie('biography_value', $_POST['biography'], time() + 30 * 24 * 60 * 60);
  }

  if (empty($_POST['ok'])) {
    // 
    setcookie('ok_error', '1', time() + 24 * 60 * 60);
    $errors = TRUE;
  }

  setcookie('year_value', $_POST['year'], time() + 30 * 24 * 60 * 60);
  
// *************
// TODO: тут необходимо проверить правильность заполнения всех остальных полей.
// Сохранить в Cookie признаки ошибок и значения полей.
// *************

  if ($errors) {
    // При наличии ошибок перезагружаем страницу и завершаем работу скрипта.
    header('Location: index.php');
    exit();
  }
  else {
    // Удаляем Cookies с признаками ошибок.
    setcookie('fio_error', '', 100000);
    setcookie('email_error', '', 100000);
    setcookie('gender_error', '', 100000);
    setcookie('limbs_error', '', 100000);
    setcookie('power_error', '', 100000);
    setcookie('biography_error', '', 100000);
    setcookie('ok_error', '', 100000);
    
    // TODO: тут необходимо удалить остальные Cookies.
  }

  $user = 'u47525';
  $pass = '1167408';
  $db = new PDO('mysql:host=localhost;dbname=u47525', $user, $pass, array(PDO::ATTR_PERSISTENT => true));

// Проверяем меняются ли ранее сохраненные данные или отправляются новые.
    if (!empty($_COOKIE[session_name()]) &&
  session_start() && !empty($_SESSION['login'])) {
// TODO: перезаписать данные в БД новыми данными,
// кроме логина и пароля.
try {
  $uid = $_SESSION['uid'];
  $stmt = $db->prepare("UPDATE application2 SET fio = ?, email = ?, year = ?, pol = ?, limb = ?, biography = ? WHERE id = $uid");
  $stmt -> execute([$_POST['fio'],$_POST['email'],$_POST['year'],$_POST['gender'],$_POST['limbs'],$_POST['biography']]);
  
  $db->query("DELETE FROM spw WHERE id = $uid");
  $stmt = $db->prepare("INSERT INTO spw SET id = ?, nom_spw = ?");
  foreach($_POST['power'] as $el)
    $stmt -> execute([$uid,$el]);
}
catch(PDOException $e){
  print('Error : ' . $e->getMessage());
  
  exit();
}
}
  else {
// Генерируем уникальный логин и пароль.
// TODO: сделать механизм генерации, например функциями rand(), uniquid(), md5(), substr().
$login = substr(uniqid(time()),1,8);
$pass = substr(md5($_POST['email']),5,8);
// Сохраняем в Cookies.
setcookie('login', $login);
setcookie('pass', $pass);

// TODO: Сохранение данных формы, логина и хеш md5() пароля в базу данных.
// ...


  // Сохранение в XML-документ.




try {
  $str = implode(',',$_POST['power']);
  
  $stmt = $db->prepare("INSERT INTO application2 SET fio = ?, email = ?, year = ?, pol = ?, limb = ?, biography = ?");
  $stmt -> execute([$_POST['fio'],$_POST['email'],$_POST['year'],$_POST['gender'],$_POST['limbs'],$_POST['biography']]);

  $id = $db->lastInsertId();
  $stmt = $db->prepare("INSERT INTO baza SET id = ?, login = ?, pass = ?");
  $stmt -> execute([$id,$login,md5($pass)]);

  $stmt = $db->prepare("INSERT INTO spw SET id = ?, nom_spw = ?");
  foreach($_POST['power'] as $el)
    $stmt -> execute([$id,$el]);
}
catch(PDOException $e){
  print('Error : ' . $e->getMessage());
  exit();
}
}

  // Сохраняем куку с признаком успешного сохранения.
  setcookie('save', '1');

  // Делаем перенаправление.
  header('Location: index.php');
}
