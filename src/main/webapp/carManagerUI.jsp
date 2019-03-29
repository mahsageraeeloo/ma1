<%@ page import="java.util.Date" %>
<%@ page import="ir.ma.mahsa.business.CarManager" %>
<%@ page import="ir.ma.mahsa.business.Car" %>
<%@ page import="ir.ma.mahsa.business.InstanceRegistry" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Car manager</title>
    <link href="main2.css" rel="stylesheet" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<form method="post" action="manageCar">
    <button type="submit">Add</button>
    <br>
    <label>X : </label>
    <input type="number" name="X" value="1">
    <label>X direction : </label>
    <input type="number" name="XDir" value="1"> <br>
    <label>Y : </label>
    <input type="number" name="Y" value="1">
    <label>Y direction: </label>
    <input type="number" name="YDir" value="1"><br><br>
    <input name="action" type="hidden" value="addCar">
</form>
<form method="post" action="manageCar">
    <button type="submit" name="Remove" value="Remove">Remove</button>
    <br>
    <label>CarID : </label>
    <input type="number" name="CarID"><br><br>
    <input name="action" type="hidden" value="removeCar">
</form>
<form method="post" action="manageCar">
    <button type="submit" name="Start" value="Start" onclick="startRefresh()">Start</button>
    <input name="action" type="hidden" value="start">
</form>
<form method="post" action="manageCar">
    <button type="submit" name="Stop" value="Stop">Stop</button>
    <input name="action" type="hidden" value="stop">
</form>
<br><br>
<div class="container">
    <%
        CarManager cm = InstanceRegistry.lookupSingle(CarManager.class);
        for (Car car : cm.getCarList()) { %>
    <span class="dot"
          style="text-align: center; left: <%=car.getX()%>px; top: <%=car.getY()%>px"><label><%=car.getId()%></label></span>
    <%} %>

</div>
<script lang="javascript">
    function startRefresh() {
        setInterval(function () {
            $.ajax({
                url: "carList", success: function (result) {
                    $('.dot').remove();
                    $ .each(jQuery.parseJSON(result), function (index, item) {
                        var spanElement = document.createElement("span");
                        spanElement.setAttribute("class", "dot");
                        spanElement.innerText = item.id;
                        spanElement.style.setProperty("text-align", "center")
                        spanElement.style.setProperty("left",item.X + "px");
                        spanElement.style.setProperty("top",item.Y + "px");
                         $('div').append(spanElement);
                    });
                }
            });
        }, 1000);
    }
    startRefresh();
</script>
</body>
</html>