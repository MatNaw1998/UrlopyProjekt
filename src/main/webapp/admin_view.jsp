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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">

    <title>Hello, world!</title>
</head>
<body>

<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow">
    <h5 class="my-0 mr-md-auto font-weight-normal">Super Pracka</h5>
    <nav class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-dark" href="employees.html">Pracownicy</a>
        <a class="p-2 text-dark" href="admin_view.jsp">Urlopy</a>
    </nav>
    <a class="btn btn-outline-primary" href="index.html">Wyloguj</a>
</div>

<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
    <h1 class="display-4">Panel Admina</h1>
    <p class="lead">szybko przeglądaj, odrzucaj i zatwierdzaj propozycje urlopów Twoich podwładnych</p>
</div>


<div class="container">
    <div class="card-deck mb-3 text-center">
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Panel urlopów</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDarkDropdown" aria-controls="navbarNavDarkDropdown" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavDarkDropdown">
                    <ul class="navbar-nav">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Wbierz akcję
                            </a>
                            <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenuLink">
                                <li><a class="dropdown-item" href="#">Do zatwierdzenia</a></li>
                                <li><a class="dropdown-item" href="#">Zatwierdzone</a></li>
                                <li><a class="dropdown-item" href="#">Odrzucne</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>



    </div>

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

</div>


<!-- Optional JavaScript; choose one of the two! -->

<!-- Option 1: Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>

<!-- Option 2: Separate Popper and Bootstrap JS -->
<!--
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js" integrity="sha384-SR1sx49pcuLnqZUnnPwx6FCym0wLsk5JZuNx2bPPENzswTNFaQU1RDvt3wT4gWFG" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js" integrity="sha384-j0CNLUeiqtyaRmlzUHCPZ+Gy5fQu0dQ6eZ/xAww941Ai1SxSY+0EQqNXNE6DZiVc" crossorigin="anonymous"></script>
-->
</body>
</html>