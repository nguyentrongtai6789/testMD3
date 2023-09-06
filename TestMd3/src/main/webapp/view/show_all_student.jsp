<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 9/6/2023
  Time: 9:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
            <form class="d-flex" role="search" action="students?action=search" style="width: 100%" METHOD="post">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="nameSearch">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
    </div>
</nav>
<table class="table table-hover">
    <tr>
        <th>#</th>
        <th>Name</th>
        <th>DateOfBirth</th>
        <th>Email</th>
        <th>Address</th>
        <th>PhoneNumber</th>
        <th>Classroom</th>
        <td>Action</td>
        <td></td>
    </tr>
    <c:forEach var="student" items="${studentList}" varStatus="loop">
        <tr>
            <td><c:out value="${loop.index + 1}"/></td>
            <td><c:out value="${student.getName()}"/></td>
            <td><c:out value="${student.getDateOfBirth()}"/></td>
            <td><c:out value="${student.getEmail()}"/></td>
            <td><c:out value="${student.getAddress()}"/></td>
            <td><c:out value="${student.getPhoneNumber()}"/></td>
            <td><c:out value="${student.getClassOfStudent().getName()}"/></td>
            <td>
                <a href="students?action=editStudent&&id_student=${student.getId()}">
                    <button class="btn btn-outline-dark" type="button">Edit</button>
                </a>
                <a href="students?action=deleteStudent&&id_student=${student.getId()}">
                    <button class="btn btn-outline-danger" type="button">Delete</button>
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
<div style="text-align: center">
    <a href="students?action=createStudent"><button class="btn btn-outline-dark" >Add student</button></a>
</div>
</body>
</html>
