<%--
  Created by IntelliJ IDEA.
  User: macmini2
  Date: 09/04/2020
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.util.*" %>
<html>
<head>
    <title>Zmiana danych urlopu</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="apple-touch-icon" href="apple-touch-icon.png">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="css/main.css">
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">

            <div class="style padding: 25 px">
                <a class="navbar-brand" href="index.html">Strona główna</a>
            </div>

        </div>
    </div>
</nav>

<div class="row form-group"></div>
<div class="row form-group"></div>
<div class="row form-group"></div>

<div class="jumbotron">
    <div class="container">
        <h1>Zmień dane urlopu</h1>

        <form action="LoginServlet" method="get">
            <input type="hidden" name="command" value="MOD"/>
            <input type="hidden" name="id" value="${URLOP.id}"/>
            <input type="hidden" name="email" value="${URLOP.imieNazwisko}"/>
            <div class="form-group">
                <label >Od</label>
                <label>
                    <input type="date"   class="form-control" name="od"/>
                    <script>
                        var today = new Date().toISOString().split('T')[0];
                        document.getElementsByName("od")[0].setAttribute('min', today);
                    </script>
                </label>
            </div>
            <div class="form-group">
                <label >Do</label>
                <label>
                    <input type="date"  class="form-control" name="doU"/>
                    <script>
                        var today = new Date().toISOString().split('T')[0];
                        document.getElementsByName("doU")[0].setAttribute('min', today);
                    </script>
                </label>
            </div>
            <button type="submit" class="btn btn-success">Zmień dane</button>
        </form>
    </div>
</div>

<div class="row form-group"></div>
<div class="row form-group"></div>

<div class="row">
    <div class="container-fluid">

        <div class="col-sm-9">
            <a href="AdminServlet" class="btn btn-lg btn-primary" role="button" aria-disabled="true">Wróć do zestawienia</a>
        </div>
    </div>
</div>

</body>
</html>
