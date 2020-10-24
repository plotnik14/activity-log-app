<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
<%--  Not ready yet..  --%>
<%--<form:form method="post" action="processRequest" modelAttribute="reportRequest">--%>
<%--    <table >--%>
<%--        <tr>--%>
<%--            <td>Start</td>--%>
<%--            <td><form:input path="startDate"  /></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>End</td>--%>
<%--            <td><form:input path="endDate" />2020-01-01 00:00:00</td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>Users</td>--%>
<%--            <td><form:input path="userIds" />1,2,3</td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>Period</td>--%>
<%--            <td><form:input path="period" />YEAR</td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td> </td>--%>
<%--            <td><input type="submit" value="Process" /></td>--%>
<%--        </tr>--%>
<%--    </table>--%>
<%--</form:form>--%>

<c:if test="${not empty activityTableHeader}">
    <c:if test="${not empty activityTableRows}">
        <table>
            <tr>
                <c:forEach var="name" items="${activityTableHeader}">
                    <th>${name}</th>
                </c:forEach>
            </tr>
            <c:forEach var="row" items="${activityTableRows}">
                <tr>
                    <td>${row.periodStartDate}</td>
                    <c:forEach var="cell" items="${row.activityCells}">
                        <td>${cell.count}</td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</c:if>
</body>
</html>
