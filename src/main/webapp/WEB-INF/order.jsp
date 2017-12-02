<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<html>
<head>
    <title>Order</title>
    <sx:head />
</head>
<body>
<a href="?logout=true">Logout</a>
<a href="<s:url value="order.action"/>">Order</a>
<a href="<s:url value="stats.action"/>">Stats</a>
<s:form action="order-execute">
    <s:select list="employers" name="selectedEmployer" listValue="name" listKey="id" headerKey="-1" headerValue="Выберите работника"/>
    <s:select list="regions" name="selectedRegion" listValue="name" listKey="id" headerKey="-1" headerValue="Выберите регион"/>
    <s:textfield name="total"/>
    <sx:datetimepicker name="date" label="Format (dd-MMM-yyyy)"
                       displayFormat="dd-MMM-yyyy" value="currentDate"/>
    <s:submit/>

</s:form>

</body>
</html>
