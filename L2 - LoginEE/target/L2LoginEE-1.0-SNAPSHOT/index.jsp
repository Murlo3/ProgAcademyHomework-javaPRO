<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Prog Academy</title>
  </head>
  <body>
    <% String login = (String)session.getAttribute("user_login"); %>
    <% String empty = (String)session.getAttribute("empty_field"); %>
    <% String wrong = (String)session.getAttribute("wrong_data"); %>
    <% String age = (String)session.getAttribute("age"); %>
    <% String shortPass = (String)session.getAttribute("short_pass"); %>


    <% if (empty != null && empty.equals("0")) {%>
        <h5>You have not completed one of the fields.</h5>
    <% } %>

    <% if (wrong != null && wrong.equals("0")) {%>
        <h5>You entered the wrong login or password.</h5>
    <% } %>

    <%if (age != null && age.equals("0")) {%>
        <h5>Age verification failed</h5>
    <% } %>

    <%if (shortPass != null && shortPass.equals("0")) {%>
        <h5>You entered a short password</h5>
    <% } %>

    <% if (login == null) { %>
        <form action="/login" method="POST">
            Login: <input type="text" name="login"><br>
            Password: <input type="password" name="password"><br>
            Age: <input type="age" name="age"><br>
            <input type="submit" />
        </form>
    <% } else { %>
        <h1>You are logged in as: <%= login %></h1>
        <br>Click this link to <a href="/login?a=exit">logout</a>
    <% } %>
  </body>
</html>
