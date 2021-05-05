<%--
  Created by IntelliJ IDEA.
  User: macmini2
  Date: 08/04/2020
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.util.*" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">

    <title>Panel pracownika</title>
</head>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<link rel="stylesheet" href="css/main.css">

<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.html">super pracka</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">

            <ul class="navbar-nav">
                <a class="nav-link disabled" aria-current="page" href="user_login.html">Panel Pracownika</a>
                <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Panel Kierownika</a>
                <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Utworz uzytkownika</a>

                <a class="nav-link" href="index.html">Wyloguj</a>
            </ul>
        </div>
    </div>
</nav>

<div class="mx-3">

    <div class="container">

<div class="row form-group"></div>
<div class="row form-group"></div>
<div class="row form-group"></div>
<div class="row form-group"></div>


<h1>Urlopy</h1>

<div class="row form-group"></div>
<div class="row form-group"></div>
<div class="row form-group"></div>

<table class="table table-striped">

    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">email</th>
        <th scope="col">od</th>
        <th scope="col">do</th>
        <th scope="col">ilosc dni</th>
        <th scope="col">status</th>
    </thead>
    <tbody>

    <c:forEach var="uropList" items="${URLOPY_LIST}">


        <c:url var="modyfikacja" value="LoginServlet">
            <c:param name="command" value="LOAD"></c:param>
            <c:param name="id" value="${uropList.id}"></c:param>
        </c:url>
        <c:url var="usun" value="LoginServlet">
            <c:param name="command" value="USUN"></c:param>
            <c:param name="id" value="${uropList.id}"></c:param>
        </c:url>

        <tr>
            <th scope="row"></th>
            <td>${uropList.imieNazwisko}</td>
            <td>${uropList.od}</td>
            <td>${uropList.doU}</td>
            <td>${uropList.iloscDni}</td>
            <td>${uropList.statusU}</td>
            <td><a href="${modyfikacja}">
                <button type="button" class="btn btn-success">Zmodyfikuj</button>
            </a>
            </td>
<%--            <td>--%>
<%--                <a href="${odrzuc}">--%>
<%--                    <button type="button" class="btn btn-success">Odrzuc</button>--%>
<%--                </a>--%>
<%--            </td>--%>
            <td>
                <a href="${usun}">
                    <button name = "usun" type="button" class="btn btn-success">Usuń</button>
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div class="row form-group"></div>
<div class="row form-group"></div>
<div class="row form-group"></div>

<div class="col-sm-6">
    <p><a class="btn btn-primary btn-info" href="add_phone_form.jsp" role="button">Dodaj żądanie</a></p>
    <p class="lead">Dostępne dni :   ${ILOSC_DNI}</p>

</div>

<div class="row form-group"></div>
<div class="row form-group"></div>
<div class="row form-group"></div>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>

</body>
</html>