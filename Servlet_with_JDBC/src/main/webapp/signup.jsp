<%--
  Created by IntelliJ IDEA.
  User: vasur
  Date: 2020-09-29
  Time: 12:57 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<!-- This is a bad practice to rewrite same HTML code again )-->
<!-- Make separate folders for JS, CSS and all JSP pages ) -->
<!-- Re-write all of them without repeating code (Hint: use include directive [Tutorial-3] in JSP) -->
<head>
    <title>Sign Up</title>
    <link href='https://fonts.googleapis.com/css?family=Nunito:400,300' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="main.css">
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <form action="StoreServlet" method="post">
            <h1> Sign Up </h1>

            <fieldset>

                <legend><span class="number">👨🏻‍🎓</span> Your Info</legend>

                <label for="name">Username:</label>
                <input type="text" id="username" name="username">

                <label for="name">First Name:</label>
                <input type="text" id="first_name" name="first_name">

                <label for="name">Last Name:</label>
                <input type="text" id="last_name" name="last_name">

                <label for="password">Password:</label>
                <input type="password" id="password" name="password">

                <label>Gender:</label>
                <input type="radio" id="male" value="Male" name="gender"><label for="Male" class="light">Male</label><br>
                <input type="radio" id="female" value="Female" name="gender"><label for="Female" class="light">Female</label>

            </fieldset>
            <button type="submit">Sign Up</button>

        </form>
    </div>
</div>

</body>
</html>
