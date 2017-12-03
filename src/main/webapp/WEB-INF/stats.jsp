<%@ taglib prefix="c" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Stats</title>
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
            <div class="form-stats">
                <s:form action="stats-date-execute">
                    <sx:datetimepicker cssClass="form-control" name="from" label="From (dd-MMM-yyyy)"
                                       displayFormat="dd-MMM-yyyy"/>

                    <sx:datetimepicker cssClass="form-control" name="to" label="From (dd-MMM-yyyy)"
                                       displayFormat="dd-MMM-yyyy"/>
                    <s:submit cssClass="btn btn-primary"/>
                </s:form>
            </div>
            <canvas id="diagram-first" width="400" height="400"></canvas>

            <table id="table-data" class="table table-bordered">
                <thead>
                <tr>
                    <th scope="col">#</th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
    </div>
</div>
<script src="../js/jquery-3.2.1.min.js"></script>

<script src="../js/Chart.min.js"></script>
<script src="../js/common.js"></script>

<script>
    $(document).ready(function () {
        buildGist(JSON.parse('<c:property value="graphJson"/>'.replace(/&quot;/g, '"')));
        buildTable(JSON.parse('<c:property value="tableJson"/>'.replace(/&quot;/g, '"')))
    });
</script>
<SCRIPT>
</SCRIPT>
</body>
</html>
