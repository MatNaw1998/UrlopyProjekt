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
    <title>Panel admina</title>
</head>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<link rel="stylesheet" href="css/main.css">

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
<div class="row form-group"></div>


<h1>Urlopy</h1>

<div class="row form-group"></div>
<div class="row form-group"></div>
<div class="row form-group"></div>

<table class="table table-striped">

    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">name</th>
        <th scope="col">od</th>
        <th scope="col">do</th>
        <th scope="col">ilosc</th>
        <th scope="col">statusU</th>
    </thead>
    <tbody>

    <c:forEach var="uropList" items="${URLOPY_LIST}">

        <%-- definiowanie linkow--%>

        <c:url var="zatwierdz" value="AdminServlet">
            <c:param name="command" value="ZATWIERDZ"></c:param>
            <c:param name="id" value="${uropList.id}"></c:param>
        </c:url>
        <c:url var="odrzuc" value="AdminServlet">
            <c:param name="command" value="ODRZUC"></c:param>
            <c:param name="id" value="${uropList.id}"></c:param>
        </c:url>
        <c:url var="usun" value="AdminServlet">
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
            <td><a href="${zatwierdz}">
                <button type="button" class="btn btn-success">Zatwierdz</button>
            </a>
            </td>
            <td>
            <a href="${odrzuc}">
                <button type="button" class="btn btn-success">Odrzuc</button>
            </a>
            </td>
            <td>
                <a href="${usun}">
                    <button type="button" class="btn btn-success">Usuń</button>
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div class="row form-group"></div>
<div class="row form-group"></div>
<div class="row form-group"></div>

<div class="col-sm-9">
    <p><a class="btn btn-primary btn-info" href="add_phone_form.jsp" role="button">Dodaj żądanie</a></p>
</div>

<div class="row form-group"></div>
<div class="row form-group"></div>
<div class="row form-group"></div>

<div class="row">
    <div class="container-fluid">

        <div class="col-sm-9">
            <a href="index.html" class="btn btn-lg btn-primary" role="button" aria-disabled="true">Wróć do strony
                głównej</a>
        </div>
    </div>
</div>
</body>
</html>
