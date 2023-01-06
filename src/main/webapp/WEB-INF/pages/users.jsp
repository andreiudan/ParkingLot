<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="u" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTamplate pageTitle="Users">
  <h1>Users</h1>
  <div class="container text-center">
    <u:if test="${pageContext.request.isUserInRole('WRITE_USERS')}">
      <a href="${pageContext.request.contextPath}/AddUser" class="btn btn-primary btn-lg">Add user</a>
    </u:if>

    <u:forEach var="user" items="${users}">
      <div class="row">
        <div class="col">
            ${user.username}
        </div>
        <div class="col">
            ${user.email}
        </div>
      </div>
    </u:forEach>
  </div>
</t:pageTamplate>