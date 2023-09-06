<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 9/6/2023
  Time: 10:40 AM
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
<form method="post" action="students?action=editStudent&&id_student=${student.getId()}">
  <table class="table table-hover">
    <tr>
      <th>Student name</th>
      <td>
        <input class="form-control" type="text" name="name" size="45" value="${student.getName()}"/>
      </td>
    </tr>
    <tr>
      <th>Email</th>
      <td>
        <input class="form-control" type="text" name="email" size="45" value="${student.getEmail()}"/>
      </td>
    </tr>
    <tr>
      <th>Date of birth</th>
      <td>
        <input class="form-control" type="date" name="dateOfBirth" size="45" value="${student.getDateOfBirth()}"/>
      </td>
    </tr>
    <tr>
      <th>Address</th>
      <td>
        <input class="form-control" type="text" name="address" size="45" value="${student.getAddress()}"/>
      </td>
    </tr>
    <tr>
      <th>Phone Number</th>
      <td>
        <input class="form-control" type="number" name="phoneNumber" size="45" value="${student.getPhoneNumber()}"/>
      </td>
    </tr>
    <tr>
      <th>Class</th>
      <td>
        <select name="id_class" class="form-control">
          <c:forEach items="${classOfStudents}" var="classOfStudent">
            <option value="${classOfStudent.getId()}">${classOfStudent.getName()}</option>
          </c:forEach>
        </select>
      </td>
    </tr>
    <tr>
      <td colspan="2" align="center">
        <button class="btn btn-outline-dark" type="submit">Save</button>
      </td>
    </tr>
  </table>
</form>
</body>
</html>
