<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Car manager</title>
    <link href="main2.css" rel="stylesheet" type="text/css">
</head>
<body>
<form method="post" action="manageCar">
    <button type = "submit">Add</button><br>
    <label>X : </label>
    <input type= "number" name="X">
    <label>X direction : </label>
    <input type="number" name="XDir"> <br>
    <label>Y : </label>
    <input type="number" name="Y">
    <label>Y direction: </label>
    <input type="number" name="YDir"><br><br>
    <input name="action" type="hidden" value="addCar">
</form>
<form method="post" action="manageCar">
    <button type = "submit" name="Remove" value="Remove">Remove</button><br>
    <label>CarID : </label>
    <input type="number" name="CarID"><br><br>
    <input name="action" type="hidden" value="removeCar">
</form>
<form method="post" action="manageCar">
    <button type = "submit" name="Start" value="Start">Start</button>
    <input name="action" type="hidden" value="start">
</form>
<form method="post" action="manageCar">
    <button type = "submit" name="Stop" value="Stop">Stop</button>
    <input name="action" type="hidden" value="stop">
</form>
    <br><br>
    <div class="container">
        <span class="dot" style="text-align: center"><label>1</label></span>
    </div>
</body>
</html>