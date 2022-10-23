<?php

header('Content-Type:text/html;charset=UTF-8');
if ($_SERVER['REQUEST_METHOD'] == 'GET') {
  if (!empty($_GET['save'])) {
    echo('Ваши результаты успешно отправлены');
  }
  include('form.php');
  exit();
}


$errors = FALSE;
$name = $_POST["name"];
$mail = $_POST["mail"];
$year = $_POST["year"];
$sex =	$_POST["sex"];
$flag=FALSE;
$countlimbs = $_POST["countlimbs"];
$biography = $_POST["biography"];	
$check1 = $_POST["check1"];

if (empty($name)) {
  print	"Ваше имя:____<br/>";
  $errors = TRUE;
}else if(!preg_match("#^[aA-zZ0-9\-_]+$#",$_POST["name"])){
	print('Символы введены некорректно!<br/>');
	$errors=TRUE;
}
if (empty($mail)){
	print "Электронный адрес:____<br/>";
	$errors = TRUE;}
else if(!preg_match("/^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/",$_POST["mail"])){
	print('Вы не заполнили email!<br/>');
	$errors=TRUE;
}
if	(empty($year)){
	print "Выберите год Вашего рождения:<br/>";
	$errors = TRUE;
}
if	(empty($_POST["sex"])){
	print "укажите пол:<br/>";
	$errors	= TRUE;
}
if	(empty($_POST["countlimbs"])){
	print "Количество конечностей:____<br/>";
	$errors	= TRUE;	
}

$Super = $_POST["super"];

  if(!isset($Super))
  {
    print("<p>Способности не выбраны!</p>\n");
  }
  else
  {	for($i=0; $i < count($Super); $i++)
    {
		if($Super[$i]=="no")
            $flag=TRUE;
    }
  }
 if($flag){
	 for($t=0;$t<count($Super);$t++){
		 if($Super[$t]!="no")unset($Super[$t]);
	 }
 }
 $super_separated=implode(' ',$Super);//объединяем выбранное в одну строку

if	(empty($_POST["biography"])){
	print "Заполните поле биографии!<br/>";
	$errors	= TRUE;
}
if	(empty($_POST["check1"])){
	print "Нет соглашения со всеми условиями!<br/>";
	$errors	= TRUE;	
}

if($errors){
	exit();
}
$user = 'u47525';
$pass = '1167408';
$db = new PDO('mysql:host=localhost;dbname=u47525', $user, $pass,
array(PDO::ATTR_PERSISTENT => true));
try {
 $stmt = $db->prepare("INSERT INTO application (name, mail, year, sex, countlimbs, super, biography, check1) 
 VALUES (:name, :mail, :year, :sex, :countlimbs, :super, :biography, :check1)");
$stmt->bindParam(':name', $name_db);
$stmt->bindParam(':mail', $mail_db);
$stmt->bindParam(':year', $year_db);
$stmt->bindParam(':sex', $sex_db);
$stmt->bindParam(':countlimbs', $limb_db);
$stmt->bindParam(':super', $super_db);
$stmt->bindParam(':biography', $bio_db);
$stmt->bindParam(':check1', $check1_db);
$name_db=$_POST["name"];
$mail_db=$_POST["mail"];
$year_db=$_POST["year"];
$sex_db=$_POST["sex"];
$limb_db=$_POST["countlimbs"];
$super_db=$super_separated;
$bio_db=$_POST["biography"];
$check1_db=$_POST["check1"];
$stmt->execute();
}
catch(PDOException $e){
  print('Error : ' . $e->getMessage());
  exit();
}
header('Location: ?save=1');
?>
