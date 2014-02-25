<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h3>Malt Records</h3>
<c:if  test="${!empty maltRecordList}">
    <table class="data">
        <tr>
            <th>Name</th>
            <th>Amount</th>
            <th>Type</th>
        </tr>
        <c:forEach items="${maltRecordList}" var="malt">
            <tr>
                <td>${malt.name}</td>
                <td>${malt.amount} ${malt.unit}</td>
                <td>${malt.type}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>