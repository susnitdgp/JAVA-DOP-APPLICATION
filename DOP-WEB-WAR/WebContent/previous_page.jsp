<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>

<layout:extends name="base">

<layout:put block="search_form" type="replace">

<form action="TestServlet" method="post">
	<fieldset>
	<legend>Search Criteria</legend>
	
	<b>Section:</b>
	<select id="dop-section" class="form-control">
		<c:forEach items="${sections}" var="item">
		<option value="${item}">${item}</option>
	</c:forEach>
	</select>
	
	<b>Clause:</b> 
	<select class="form-control">
	  <option value="36">36</option>
	  <option value="37">37</option>
	</select>
	<b>Sub Clause:</b>
	<select class="form-control">
	  <option value="A">A</option>
	  <option value="B">B</option>
	</select> 
	
	<b>Roman Clause:</b>
	<select class="form-control">
	  <option value="i">I</option>
	  <option value="ii">II</option>
	  <option value="iii">III</option>
	</select> 
	
	<div class="form-group">
		<label>Search Content</label> 
		<input type="text" name="content" id="content"/>
	</div>
	
	
	</fieldset>
	
	<input type="submit" value="Search Content" class="btn btn-primary"/>
	<input type="reset" Value="Reset" class="btn">
		
	<br/>
	
	Searched String: <b><c:out value="${search_string}" /></b> 

</form>


</layout:put>



<layout:put block="contents" type="replace">

	<H3>Search Result:</H3> 
<ul class="breadcrumb">
	  <li><a href="#">Section-IV</a> <span class="divider">/</span></li>
	  <li><a href="#">Clause-36A</a> <span class="divider">/</span></li>
	  <li class="active">II</li>
	</ul>
	
	<ul class="nav nav-tabs nav-tabs-google">
	  <li class="active">
	    <a href="#">Page Content(Text)</a>
	  </li>
	  <li><a href="#">Page Content(Image)</a></li>
	  <li><a href="#">Remarks Page</a></li>
	  <li><a href="#">Amendment</a></li>
	</ul>
	
	
	<table class="table table-condensed">
	<tr>
	<td>Section</td>
	
	<!--  
	
	<td>Clause</td>
	<td>Sub Clause</td>
	-->
	
	<td>Page No</td>
	<td>Page Text</td>
	<td>Page Content</td>
	</tr>
	
	<tr>
	<td class="span1">${item.dop_section}</td>
	
	<!-- 
	<td>${item.dop_clause}</td>
	<td>${item.dop_subclause}</td>
	
	 -->
	 
	<td class="span1">${item.page_no}</td>
	<td class="span4">
	${item.page_content}
	</td>
	 
	 <td class="span6">
	 <img src="${pageContext.request.contextPath}/ImageServlet?id=${item.id}" width="800" height="600"></img>
	 
	<a href="${pageContext.request.contextPath}/PreviousServlet?page_no=${item.page_no}">Previous Page</a>  
	<a href="${pageContext.request.contextPath}/NextServlet?page_no=${item.page_no}">Next Page</a>
	 
	 
	 </td>
	 
	</tr>
	   
	
	
	</table>

</layout:put>


</layout:extends>



