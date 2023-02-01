<!DOCTYPE HTML>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Image Viewer</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

<div style="border:1px solid blue;">

<c:forEach items="${list_of_notesheet}" var="note">
    <c:out value="${note.page_no}"/>
	<img src="<c:out value="${note.page_content}"/>"/>
	<hr>
</c:forEach>
</div>


</body>
</html>