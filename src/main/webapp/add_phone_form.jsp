
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin log panel</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">

</head>
<body>


<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.html">super pracka</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">

            <ul class="navbar-nav">
                <a class="nav-link" aria-current="page" href="user_view.jsp">Panel Pracownika</a>
                <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Panel Kierownika</a>
                <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Utworz uzytkownika</a>

                <a class="nav-link" href="index.html">Wyloguj</a>
            </ul>
        </div>
    </div>
</nav>


<div class="mx-3">
    <div class="container">
        <h1>Wpisz dane nowego żądania</h1>

        <form action="LoginServlet" method="get">
            <input type="hidden" name="command" value="ADD">
            <div class="col-sm-6">
                <label> od </label>
                <label>
                    <input type="date"   class="form-control" name="od"/>
                    <script>
                        var today = new Date().toISOString().split('T')[0];
                        document.getElementsByName("od")[0].setAttribute('min', today);
                    </script>
                </label>
            </div>
            <div class="col-sm-6">
                <label>do</label>
                <label>
                    <input type="date"  class="form-control" name="doU"/>
                    <script>
                        var today = new Date().toISOString().split('T')[0];
                        document.getElementsByName("doU")[0].setAttribute('min', today);
                    </script>
                </label>
            </div>
            <div class="col-sm-6">
                <br>
                <button name = "dodajurlop" type="submit" class="btn btn-info">Dodaj</button>
            </div>
        </form>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>


</body>
</html>