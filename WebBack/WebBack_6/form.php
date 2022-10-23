<!DOCTYPE html>

<style>
/* Сообщения об ошибках и поля с ошибками выводим с красным бордюром. */
.error {
  border: 2px solid red;
}
    </style>
  </head>
  <body>

<?php
if (!empty($messages)) {
  print('<div id="messages">');
  // Выводим все сообщения.
  foreach ($messages as $message) {
    print($message);
  }
  print('</div>');
}

// Далее выводим форму отмечая элементы с ошибками классом error
// и задавая начальные значения элементов ранее сохраненными.
?>

<head>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
  <meta charset="utf-8">
  <title>Задание 6</title>
  <link rel="stylesheet" href="style.css">
</head>

<body>

  <div id="main-aside-wrapper">
    <div id="cont" class="container">
      <div id="form" class="col-12 order-lg-3 order-sm-2">
      <div id="vhod">
          <?php 
          if (empty($_SESSION['login'])){
          ?>
          <a href="login.php" >Войти</a>
          
          <?php 
          }else { ?><a href="login.php" >Выйти</a><?php } ?>
          
        </div>
        <form action="" method="POST">
          ФИО:
          <label>
            <input name="fio"  <?php if ($errors['fio']) {print 'class="error"';} ?> value="<?php print $values['fio']; ?>" placeholder="Введите ФИО">
          </label>
          <br>
          <br>
          E-mail:
          <label>
            <input type="email" name="email" placeholder="Введите e-mail" <?php if ($errors['email']) {print 'class="error"';} ?> value="<?php print $values['email']; ?>">
          </label>
          <p>Ваш год рождения:</p>
          <label>
            <select name="year">
              <option value="1995" <?php if($values['year'] == 1995) {print 'selected';}?>>1995</option>
              <option value="1996" <?php if($values['year'] == 1996) {print 'selected';}?>>1996</option>
              <option value="1997" <?php if($values['year'] == 1997) {print 'selected';}?>>1997</option>
              <option value="1998" <?php if($values['year'] == 1998) {print 'selected';}?>>1998</option>
              <option value="1999" <?php if($values['year'] == 1999) {print 'selected';}?>>1999</option>
              <option value="2000" <?php if($values['year'] == 2000) {print 'selected';}?>>2000</option>
              <option value="2001" <?php if($values['year'] == 2001) {print 'selected';}?>>2001</option>
              <option value="2002" <?php if($values['year'] == 2002) {print 'selected';}?>>2002</option>
            </select>
          </label>

          <p>Пол:</p>
          <label>
            <input type="radio" name="gender" value="M" <?php if($values['gender'] == 'M') {print 'checked';}?> <?php if ($errors['gender']) {print 'class="error"';} ?> />М
          </label>
          <label>
            <input type="radio" name="gender" value="W" <?php if($values['gender'] == 'W') {print 'checked';}?> <?php if ($errors['gender']) {print 'class="error"';} ?> />Ж
          </label>

          <p>Количество конечностей</p><br />
          <label>
            <input type="radio" name="limbs" value="0" <?php if($values['limbs'] == 0) {print 'checked';}?> <?php if ($errors['limbs']) {print 'class="error"';} ?> />0
          </label>
          <label>
            <input type="radio" name="limbs" value="1" <?php if($values['limbs'] == 1) {print 'checked';}?> <?php if ($errors['limbs']) {print 'class="error"';} ?>/>1
          </label>
          <label>
            <input type="radio" name="limbs" value="2" <?php if($values['limbs'] == 2) {print 'checked';}?> <?php if ($errors['limbs']) {print 'class="error"';} ?>/>2
          </label>
          <label>
            <input type="radio" name="limbs" value="3" <?php if($values['limbs'] == 3) {print 'checked';}?> <?php if ($errors['limbs']) {print 'class="error"';} ?>/>3
          </label>
          <label>
            <input type="radio" name="limbs" value="4" <?php if($values['limbs'] == 4) {print 'checked';}?> <?php if ($errors['limbs']) {print 'class="error"';} ?> />4
          </label>
          <label>
            <input type="radio" name="limbs" value="5" <?php if($values['limbs'] == 5) {print 'checked';}?> <?php if ($errors['limbs']) {print 'class="error"';} ?> />5
          </label>

          <p>Сверхспособности</p>
          <label>
            <select name="power[]" multiple=multiple>
              <option value="1" <?php if ($errors['power']) {print 'class="error"';} ?>
            <?php
              $arr = str_split($values['power']);
              foreach($arr as $el)
                if ($el == 1)
                  print 'selected';
            ?>
              >Бессмертие</option>
              <option value="2" <?php if ($errors['power']) {print 'class="error"';} ?>
              <?php
              $arr = str_split($values['power']);
              foreach($arr as $el)
                if ($el == 2)
                  print 'selected';
            ?>
              > Прохождение сквозь стены</option>
              <option value="3" <?php if ($errors['power']) {print 'class="error"';} ?>
              <?php
              $arr = str_split($values['power']);
              foreach($arr as $el)
                if ($el == 3)
                  print 'selected';
            ?>
              >Левитация</option>
              <option value="4" <?php if ($errors['power']) {print 'class="error"';} ?>
              <?php
              $arr = str_split($values['power']);
              foreach($arr as $el)
                if ($el == 4)
                  print 'selected';
            ?>
              >Web</option>
            </select>
          </label>

          <p id="bio">Биография</p>
          <label>
            <textarea placeholder="Расскажите о себе" name="biography" rows="6" cols="60"  <?php if ($errors['biography']) {print 'class="error"';} ?> ><?php print $values['biography'];?></textarea>
          </label>
          <br>

          <label>
            С контрактом ознакомлен(-a)
            <input type="checkbox" name="ok" >
          </label>
          <br>
          <input type="submit" value="Отправить">
        </form>
      </div>
    </div>
  </div>
</body>

</html>
