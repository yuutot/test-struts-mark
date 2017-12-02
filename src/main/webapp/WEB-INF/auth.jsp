<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="c" uri="/struts-tags" %>
<html>
<head>
    <title>Auth</title>
    <s:head />
</head>
<body>
Auth
<c:property value="errorMessage"/>
<s:form action="auth-execute" method="POST" namespace="/">
    <s:textfield name="login"/>
    <s:password name="password"/>
    <s:submit/>
</s:form>

</body>
</html>
