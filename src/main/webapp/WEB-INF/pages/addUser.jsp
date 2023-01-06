<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTamplate pageTitle="AddUser">

  <form class="needs-validation" novalidate method="post" action="${pageContext.request.contextPath}/AddUser">
    <div class="row">
      <div class="col-md-6 mb-3">
        <label for="username">Username</label>
        <input type="text" class="form-control" id="username" name="username" placeholder="" value=""
               required>
        <div class="invalid-feedback">
          Username is required.
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-md-6 mb-3">
        <label for="email">Email</label>
        <input type="text" class="form-control" id="email" name="email" placeholder="" value=""
               required>
        <div class="invalid-feedback">
          Email is required.
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-md-6 mb-3">
        <label for="password">Password</label>
        <input type="text" class="form-control" id="password" name="password" placeholder="" value=""
               required>
        <div class="invalid-feedback">
          Password is required.
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-md-6 mb-3">
        <label for="user_groups">Groups</label>
        <select class="custom-select d-block w-100" id="user_groups" name="user_groups" multiple>
          <c:forEach var="user_group" items="${userGroups}" varStatus="status">
            <option value="${user_group}">${user_group}</option>
          </c:forEach>
        </select>
      </div>
    </div>
    <button class="btn btn-primary btn-lg" type="submit">Save</button>
  </form>
</t:pageTamplate>
