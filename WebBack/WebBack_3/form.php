

<!DOCTYPE html>

<head>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
  <meta charset="utf-8">
  <title>3 Задание</title>
  <link rel="stylesheet" href="style.css">
</head>

<body>
  <header>
    Форма
  </header>
  <div id="main-aside-wrapper">
    <div id="cont" class="container">
      <div id="form" class="col-12 order-lg-3 order-sm-2">
        <form action="" method="POST">
          ФИО:
          <label>
            <input name="name" placeholder="Введите ФИО">
          </label>
          <br>
          <br>
          E-mail:
          <label>
            <input type="email" name="mail" placeholder="Введите e-mail">
          </label>
          <p>Ваш год рождения:</p>
          <label>
            <select name="year">
              <option value="1990">1990</option>
              <option value="1991">1991</option>
              <option value="1992">1992</option>
              <option value="1993">1993</option>
              <option value="1994">1994</option>
              <option value="1995">1995</option>
              <option value="1996">1996</option>
              <option value="1997">1997</option>
              <option value="1998">1998</option>
              <option value="1999">1999</option>
              <option value="2000">2000</option>
              <option value="2001" selected="selected">2001</option>
              <option value="2002">2002</option>
            </select>
          </label>

          <p>Пол:</p>
          <label>
            <input type="radio" name="sex" value="M">М   
          </label>
          <label>
            <input type="radio" name="sex" checked="checked" value="F" />Ж   
          </label>

          <p>Количество конечностей</p><br />
          <label>
            <input type="radio" name="countlimbs" value="0" />0   
          </label>
          <label>
            <input type="radio" name="countlimbs" value="1" />1   
          </label>
          <label>
            <input type="radio" name="countlimbs" value="2" />2   
          </label>
          <label>
            <input type="radio" name="countlimbs" value="3" />3   
          </label>
          <label>
            <input type="radio" checked="checked" name="countlimbs" value="4" />4   
          </label>
          <label>
            <input type="radio" name="countlimbs" value="5" />5   
          </label>

          <p>Сверхспособности</p>
          <label>
            <select name="super[]" multiple=multiple>
              <option value="Immortal">Бессмертие</option>
              <option value="Through the walls"> Прохождение сквозь стены</option>
              <option value="Levitation">Левитация</option>
            </select>
          </label>

          <p id="biography">Биография</p>
          <label>
            <textarea placeholder="Расскажите о себе" name="biography" rows="6" cols="60"></textarea>
          </label>
          <br>

          <label>
            С контрактом ознакомлен(-а)
            <input type="checkbox" name="check1" checked="checked">
          </label>
          <br>
          <input type="submit" value="Отправить">
        </form>
      </div>
    </div>
  </div>
<footer>
</footer>
</body>
</html>