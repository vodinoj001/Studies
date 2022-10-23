<?php
// Отправляем браузеру правильную кодировку,
// файл index.php должен быть в кодировке UTF-8 
header('Content-Type: text/html; charset=UTF-8');

// В суперглобальном массиве $_SERVER PHP сохраняет некторые заголовки запроса HTTP
// и другие сведения о клиненте и сервере, например метод текущего запроса $_SERVER['REQUEST_METHOD'].
if ($_SERVER['REQUEST_METHOD'] == 'GET') {

  $messages = array();

  if (!empty($_COOKIE['save'])) {
    setcookie('save', '', 100000);  //setcookie() задаёт cookie, которое будет передано клиенту вместе с другими HTTP-заголовками. 
    //так как нет time()(отвечает за текущее время и опирается от него),то данные удаляются
    $messages[] = 'Спасибо, результаты сохранены.';
 }

 // Складываем признак ошибок в массив.
  $flag=FALSE;
  $errors = array();
  $errors['name']=!empty($_COOKIE['name_error']);	
  $errors['email']=!empty($_COOKIE['email_error']);
  $errors['date'] = !empty($_COOKIE['date_error']);
  $errors['gender']=!empty($_COOKIE['gender_error']);
  $errors['limbs']=!empty($_COOKIE['limbs_error']);
  $errors['strenght']=!empty($_COOKIE['strenght_error']);
  $errors['comment']=!empty($_COOKIE['comment_error']);
  $errors['check']=!empty($_COOKIE['check_error']);
  

  //проверки на наличие ошибок
  if ($errors['name']) {
    setcookie('name_error', '', 100000);  //в скобках  (Название cookie,значение cookie,время)
    // Выводим сообщение.
    $messages[] = '<div class="error">Заполните имя корректно.</div>';
  }

 if ($errors['email']) {
    setcookie('email_error', '', 100000);
    $messages[] = '<div class="error">Корректно заполните email.</div>';
  }

  if ($errors['date']) {
    setcookie('date_error', '', 100000);
    $messages[] = '<div class="error">Поставьте год.</div>';
  }

 if ($errors['gender']) {
    setcookie('gender_error', '', 100000);
    $messages[] = '<div class="error">Укажите пол.</div>';
  }

 if ($errors['limbs']) {
    setcookie('limbs_error', '', 100000);
    $messages[] = '<div class="error">Укажите количество конечностей.</div>';
  }

 if ($errors['strenght']) {
    setcookie('strenght_error', '', 100000);
    $messages[] = '<div class="error">Укажите суперсилу.</div>';
  }

 if ($errors['comment']) {
    setcookie('comment_error', '', 100000);
    $messages[] = '<div class="error">Напишите биографию.</div>';
  }

 if ($errors['check'])  {
     setcookie('check_error', '', 100000);
     $messages[]= '<div class="error">Примите соглашение.</div>';
 }
 
// Складываем предыдущие значения полей в массив, если есть.
  $values = array();
//если $_COOKIE['name_value'] пустое,то значение будет '' 
//иначе $_COOKIE['name_value']
  $values['name']=empty($_COOKIE['name_value']) ? '' : $_COOKIE['name_value'];
  $values['email']=empty($_COOKIE['email_value']) ? '' : $_COOKIE['email_value'] ;
  $values['date'] = empty($_COOKIE['date_value']) ? '' : $_COOKIE['date_value'];
  $values['gender']=empty($_COOKIE['gender_value']) ? '' : $_COOKIE['gender_value'];
  $values['limbs']=empty($_COOKIE['limbs_value']) ? '' : $_COOKIE['limbs_value'];
  $values['strenght']=empty($_COOKIE['strenght_value']) ? '' : $_COOKIE['strenght_value'];
  $values['comment']=empty($_COOKIE['comment_value']) ? '' : $_COOKIE['comment_value'];
  $values['check']=empty($_COOKIE['check_value']) ? '' : $_COOKIE['check_value'];
  include('form.php');
}
// Иначе, если запрос был методом POST
else {
 // Проверяем ошибки
  $errors = FALSE;

  if (empty($_POST['name']) || !preg_match("/^[a-zA-Z0-9_.+-]+$/",$_POST["name"])) {
     // Выдаем куку на день с флажком об ошибке в поле name
    setcookie('name_error', '1', time() + 24 * 60 * 60);
    $errors = TRUE;
  }

  else {
    setcookie('name_value', $_POST['name'], time() + 30 * 24 * 60 * 60);
  }


  if (empty($_POST['email']) || !preg_match("/^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/",$_POST["email"])){
    setcookie('email_error', '1', time() + 24*60*60);
    $errors=TRUE;
  }

  else{
    setcookie('email_value', $_POST['email'], time()+ 30*24*60*60);
  }


  if (empty($_POST['date'])){
    setcookie('date_error', '1', time() + 24*60*60);
    $errors=TRUE;
  }

  else{
    setcookie('date_value', $_POST['date'], time()+ 30*24*60*60);
  }


  if (empty($_POST['gender'])){
    setcookie('gender_error', '1', time() + 24*60*60);
    $errors=TRUE;
  }

  else{
    setcookie('gender_value', $_POST['gender'], time()+ 30*24*60*60);
  }


  if (empty($_POST['limbs'])){
    setcookie('limbs_error', '1', time() + 24*60*60);
    $errors=TRUE;
  }

  else{
    setcookie('limbs_value', $_POST['limbs'], time()+ 30*24*60*60);
  }


 if (empty($_POST['strenght'])){
     setcookie('strenght_error', '1', time() + 24*60*60);
     $errors=TRUE;
   }
    else{
    $strenght_separated=implode(' , ',$_POST['strenght']);
    setcookie('strenght_value', $strenght_separated, time()+ 30*24*60*60);
    }
    

  if (empty($_POST['comment'])){
    setcookie('comment_error', '1', time() + 24*60*60);
    $errors=TRUE;
  }

  else{
    setcookie('comment_value', $_POST['comment'], time()+ 30*24*60*60);
  }

if(empty($_POST['check'])){
        setcookie('check_error', '1', time()+24*60*60);
        $errors=TRUE;
    }

    else {
        setcookie('check_value', $_POST['check'], time()+30*24*60*60);
    }
    

  if ($errors) {
     // При наличии ошибок перезагружаем страницу и завершаем работу скрипта.
    header('Location: index.php');
    exit();
  }


  else {
    // Удаляем Cookies с признаками ошибок.
    setcookie('name_error', '', 100000);
    setcookie('email_error', '', 100000);
    setcookie('date_error', '', 100000);
    setcookie('gender_error', '', 100000);
    setcookie('limbs_error', '', 100000);
    setcookie('strenght_error', '', 100000);
    setcookie('comment_error', '', 100000);
    setcookie('check_error', '', 100000);
  }

    $user = 'u47525';
    $pass = '1167408';
    $db = new PDO('mysql:host=localhost;dbname=u47525', $user, $pass);

    $name = $_POST['name'];
    $email = $_POST['email'];
    $date = $_POST['date'];
    $gender = $_POST['gender'];
    $limbs = $_POST['limbs'];
    $strenght_separated=implode(' , ',$_POST['strenght']); 
    $comment = $_POST['comment'];
   
    try
    {
        $stmt = $db->prepare("INSERT INTO application1 (name, email, date, gender, limbs, strenght, comment) VALUES (:name, :email, :date, :gender, :limbs, :strenght, :comment)");
        $stmt->bindParam(':name', $name);//Привязка переменных к параметрам подготавливаемого запроса 
        $stmt->bindParam(':email', $email);
        $stmt->bindParam(':date', $date);
        $stmt->bindParam(':gender', $gender);
        $stmt->bindParam(':limbs', $limbs);
        $stmt->bindParam(':strenght', $strenght_separated);
        $stmt->bindParam(':comment', $comment);
        $stmt->execute();
        print ('Результаты отпралены!');
        exit();
    }
    catch(PDOException $e)
    {
        print (': ' . $e->getMessage());
        exit();
    }


// Сохраняем куку с признаком успешного сохранения.
  setcookie('save', '1');
   // Делаем перенаправление.
  header('Location: index.php');
}
?>
