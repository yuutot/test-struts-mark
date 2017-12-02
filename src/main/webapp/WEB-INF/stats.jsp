<%@ taglib prefix="c" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Stats</title>
    <sx:head />
</head>
<body>
<a href="?logout=true">Logout</a>
<a href="<s:url value="order.action"/>">Order</a>
<a href="<s:url value="stats.action"/>">Stats</a>

<s:form action="stats-date-execute">
    <sx:datetimepicker name="from" label="From (dd-MMM-yyyy)"
                       displayFormat="dd-MMM-yyyy"/>

    <sx:datetimepicker name="to" label="From (dd-MMM-yyyy)"
                       displayFormat="dd-MMM-yyyy"/>
    <s:submit/>
</s:form>
<SCRIPT>
    console.log(JSON.parse('<c:property value="graphJson"/>'.replace(/&quot;/g, '"')));
    console.log(JSON.parse('<c:property value="tableJson"/>'.replace(/&quot;/g, '"')));
</SCRIPT>
</body>
</html>
