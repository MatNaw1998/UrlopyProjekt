<%--
  Created by IntelliJ IDEA.
  User: macmini2
  Date: 08/04/2020
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>Admin log panel</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title></title>
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
                <a class="navbar-brand" href="index.html">Strona Główna</a>
            </div>

        </div>
    </div>
</nav>

<div class="row form-group"></div>
<div class="row form-group"></div>
<div class="row form-group"></div>

<div class="jumbotron">
    <div class="container">
        <h1>Wpisz dane nowego żądania</h1>

        <form action="AdminUrlopyServlet" method="get">
            <input type="hidden" name="command" value="ADD">
            <div class="form-group">
                <label>name</label>
                <label>
                    <input type="text" class="form-control" name="imieNazwisko"/>
                </label>
            </div>
            <div class="form-group">
                <label> od </label>
                <label>
                    <input type="date"   class="form-control" name="od"/>
                    <script>
                        var today = new Date().toISOString().split('T')[0];
                        document.getElementsByName("od")[0].setAttribute('min', today);
                    </script>
                </label>
            </div>
            <div class="form-group">
                <label>do</label>
                <label>
                    <input type="date"  class="form-control" name="doU"/>
                    <script>
                        var today = new Date().toISOString().split('T')[0];
                        document.getElementsByName("doU")[0].setAttribute('min', today);
                    </script>
                </label>
            </div>
            <button type="submit" class="btn btn-info">Dodaj</button>
        </form>
    </div>
</div>

<div class="row form-group"></div>
<div class="row form-group"></div>

<div class="row">
    <div class="container-fluid">

        <div class="col-sm-9">
            <a href="AdminUrlopyServlet" class="btn btn-lg btn-primary" role="button" aria-disabled="true">Wróć do zestawienia</a>
        </div>
    </div>
</div>

</body>
