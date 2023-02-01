<!DOCTYPE HTML>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>DOP-WEB-WAR Home Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/resources/css/b.min.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/resources/css/b-r.min.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/resources/css/t-b.min.css" rel="stylesheet" type="text/css">



<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

</head>
<body>
<hr style="margin:0px;background-color:#003333;border-top:3px solid #003333" />
<div class="container-fluid" style="padding-left:0px;padding-right:0px;">


<div class="row-fluid" style="height:60px;background-color:#fafafa;border-bottom:1px solid #ebebeb;">
<div class="span12">

<layout:block name="header">

<i class="icon-home"></i><a href="/DOP-WS-WAR/DOPContentProviderService?wsdl">Web Service(WSDL)</a>

<!-- <a href="${pageContext.request.contextPath}/ClientServlet">Web Service invoke(Servlet)</a> -->

||
<a href="${pageContext.request.contextPath}/TestServlet">DOP Search(Servlet)</a>
||
<a href="${pageContext.request.contextPath}/Admin/SecurityServlet">Admin Area</a>  
||
<c:choose>
    <c:when test="${not empty pageContext.request.userPrincipal}">
         User: <c:out value="${pageContext.request.userPrincipal.name}" />
         <a href="${pageContext.request.contextPath}/Authentication/Logout">Logout</a> 
    </c:when>    
    <c:otherwise>
       
    </c:otherwise>
</c:choose>

</layout:block>
</div>

</div>


<div class="row-fluid">
<div class="span2" style="padding-left:30px;">

<layout:block name="search_form">

</layout:block>


</div>
<div class="span10">
<blockquote>


<div>
    <layout:block name="contents">
        <h2>Contents will be placed under this</h2>
    </layout:block>
</div>

</blockquote>
</div>
</div>



</div>
</body>
</html>