<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTamplate pageTitle="AddCarPhoto">
  <h1>Add Car Photo</h1>
  <form class="needs-validation" novalidate method="POST" enctype="multipart/form-data"
        action="${pageContext.request.contextPath}/AddCarPhoto">
      <div class="row">
          <div class="col-md-6 mb-3">
              License Plate: ${car.licensePlate}
          </div>
      </div>
      <div class="row">
        <div class="col-md-6 mb-3">
          <label for="file">Photo</label>
          <input type="file" name="file" id="file" required>
          <div class="invalid-feedback">
              Photo is required.
          </div>
        </div>
      </div>
      <input type="hidden" name="car_id" value="${car.id}">
      <hr class="mb-4">
      <button class="btn btn-primary btn-lg btn-block" type="submit">Save</button>
  </form>
</t:pageTamplate>
