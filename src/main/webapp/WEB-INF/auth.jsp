<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="c" uri="/struts-tags" %>
<html>
<head>
    <title>Auth</title>
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <s:head/>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="form-wrapper">
            <s:form cssClass="form-auth" action="auth-execute" method="POST" namespace="/">
                <s:textfield label="login" cssClass="form-control" id="login" name="login"/>
                <s:password label="Password" cssClass="form-control" id="password" name="password"/>
                <s:submit cssClass="btn btn-primary"/>
            </s:form>
        </div>

    </div>
</div>
</body>
</html>
