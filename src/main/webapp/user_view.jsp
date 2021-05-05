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
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">

    <title>Rejestracja</title>
</head>
<body >

<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow">
    <h5 class="my-0 mr-md-auto font-weight-normal">Super Pracka</h5>
    <nav class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-dark" href="user_login.html">Panel Pracownika</a>
        <a class="p-2 text-dark" href="admin_login.html">Panel Kierownika</a>

    </nav>
    <a class="btn btn-outline-primary" href="create_user.html">Utworz uzytkownika</a>
</div>



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
        <th scope="col">name</th>
        <th scope="col">od</th>
        <th scope="col">do</th>
        <th scope="col">ilosc</th>
        <th scope="col">statusU</th>
    </thead>
    <tbody>

    <c:forEach var="uropList" items="${URLOPY_LIST}">

        <%-- definiowanie linkow--%>

<%--        <c:url var="zatwierdz" value="AdminServlet">--%>
<%--            <c:param name="command" value="ZATWIERDZ"></c:param>--%>
<%--            <c:param name="id" value="${uropList.id}"></c:param>--%>
<%--        </c:url>--%>
        <c:url var="modyfikacja" value="LoginServlet">
            <c:param name="command" value="LOAD"></c:param>
            <c:param name="id" value="${uropList.id}"></c:param>
        </c:url>
        <c:url var="usun" value="LoginServlet">
            <c:param name="command" value="USUN"></c:param>--%>
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

<div class="col-sm-9">
    <p><a class="btn btn-primary btn-info" href="add_phone_form.jsp" role="button">Dodaj żądanie</a></p>
    <p class="lead">Dostępne dni:</p>

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
    </div>
</div>