<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>

<layout:extends name="base">

<layout:put block="contents" type="replace">


<form method="POST" action="j_security_check">
  User: <input type="text" name="j_username">
  Password: <input type="password" name="j_password">
  
  
  <input type="submit" value="Submit "/>
  
  </form>
  
</layout:put>


</layout:extends>