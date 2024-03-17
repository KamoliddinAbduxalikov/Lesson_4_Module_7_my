<%@ page import="java.util.UUID" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="uuid" value="${UUID.randomUUID().toString}"/>
<p>
    <c:out value="${uuid}"/>
</p>

<c:if test="${12 ge 12}">
    <p>
        12 >= 12
    </p>
</c:if>

<c:choose>
    <c:when test="${1 < 0}">
        <p>
            1 < 0
        </p>
    </c:when>
    <c:when test="${1 > 1}">
        <p>
            1 > 1
        </p>
    </c:when>
    <c:otherwise>
        <p>
            1 =
        </p>
    </c:otherwise>
</c:choose>
</body>
</html>
