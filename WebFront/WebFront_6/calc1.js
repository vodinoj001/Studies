function calc() {
  let price = Number(document.getElementById("price").value);
  let n = Number(document.getElementById("n").value);
  let result = price * n;
  if (Number.isNaN(result)) {
  document.getElementById("result").innerHTML = "Неверные данные!";
  }
  else {
  document.getElementById("result").innerHTML = result;
  }
  document.getElementById("price").value = "";
  document.getElementById("n").value = "";
  }