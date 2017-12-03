<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<html>
<head>
    <title>Order</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/style.css">
    <sx:head/>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3">

            <div class="list-group">
                <a href="<s:url value="order.action"/>" class="list-group-item">Order</a>
                <a href="<s:url value="stats.action"/>" class="list-group-item">Stats</a>
                <a href="?logout=true" class="list-group-item">Logout</a>
            </div>
        </div>
        <div class="offset-1 col-sm-6">
            <s:form cssClass="form-order" action="order-execute">
            <div class="form-group select-c">
                <label class="col-form-label" for="empl">Select employer</label>
                <s:select id="empl" cssClass="col-form-label" list="employers" name="selectedEmployer"
                          listValue="name" listKey="id" headerKey="-1" headerValue="Выберите работника"/>
            </div>
            <div class="form-group select-c">
                <label class="col-form-label" for="reg">Select region</label>
                <s:select id="reg" cssClass="custom-select" list="regions" name="selectedRegion" listValue="name"
                          listKey="id" headerKey="-1" headerValue="Выберите регион"/>
            </div>
            <div class="form-group">
                <label class="col-form-label" for="price_id">Price</label>
                <s:textfield id="price_id" cssClass="form-control" name="total"/>
            </div>
            <div class="form-group">
                <div class='input-group date'>
                    <sx:datetimepicker cssClass="form-control" name="date" label="Format (dd-MMM-yyyy)"
                                       displayFormat="dd-MMM-yyyy" value="currentDate"/>
                </div>
                <s:submit class="btn btn-primary"/>

                </s:form>
            </div>
        </div>
    </div>
</div>
</body>
<script src="../js/jquery-3.2.1.min.js"></script>
</html>
