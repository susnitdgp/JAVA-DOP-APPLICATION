<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>

<layout:extends name="base">

<layout:put block="contents" type="replace">

	<h4>Inside Secure Area</h4>
	<h4>User:</h4> <b><c:out value="${username}" /></b> 

</layout:put>

</layout:extends>