<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<form action = "addPerson">
    <input type="text" name = "id"><br>
    <input type="text" name = "name"><br>
    <input type="text" name = "location"><br>
    <input type="submit">
</form><br><br>

<form action = "updatePerson">
    <input type="text" name = "id"><br>
    <input type="text" name = "name"><br>
    <input type="text" name = "location"><br>
    <input type="submit">
</form><br><br>

<form action = "deletePerson">
    <input type="text" name = "id"><br>
    <input type="submit">
</form><br><br>

<form action = "searchPerson">
    <input type="text" name = "id"><br>
    <input type="submit">
</form>

</body>
</html>