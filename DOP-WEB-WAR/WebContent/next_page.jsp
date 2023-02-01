<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>

<layout:extends name="base">

<layout:put block="search_form" type="replace">

<form action="MetaDataSearchServlet" method="post">
	<fieldset>
	<legend>Search Criteria</legend>
	
	<b>Section:</b>
	<select id="dop-section" name="dop-section" class="form-control">
		<c:forEach items="${sections}" var="item">
		<option value="${item}">${item}</option>
	</c:forEach>
	</select>
	
	<b>Clause:</b> 
	<select id="dop-clause" name="dop-clause"  class="form-control">
	  <option value="N/A">N/A</option>
	  
	</select>
	<b>Sub Clause:</b>
	<select id="dop-subclause" name="dop-subclause" class="form-control">
	  <option value="N/A">N/A</option>
	  
	</select> 
	
	<b>Roman Clause:</b>
	<select id="dop-romanclause" name="dop-romanclause" class="form-control">
	  <option value="N/A">N/A</option>
	  
	</select> 
	
	<!--  
	<div class="form-group">
		<label>Search Content</label> 
		<input type="text" name="content" id="content"/>
	</div>
	-->
	
	</fieldset>
	
	<input type="submit" value="Search" class="btn btn-primary"/>
	<!-- <input type="reset" Value="Reset" class="btn"> -->
		
	<br/>
	
</form>

<hr/>

<form action="TestServlet" method="post">

<fieldset>
	<legend>Search By Content</legend>
	<div class="form-group">
		<label>Search Content</label> 
		<input type="text" name="content" id="content"/>
	</div>
	<input type="submit" value="Search Content" class="btn btn-primary"/>
	
</fieldset>


</form>

Searched String: <b>

	<span class="label label-inverse"><c:out value="${search_string}" /></span>
</b> 


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
	
	<script type="text/javascript">
	
	$( document ).ready(function() {
    
    	console.log( "ready!" );
    	
    	//Section Change Function
    	$("#dop-section").change(function() {
    	
    		var select = $('#dop-clause');
    		select.empty();
    		
    		var select2 = $('#dop-subclause');
	    	select2.empty();
	    	
	    	$('#dop-romanclause').empty();
    	
    		$.ajax({
    	
    		type:'GET',
    		url:'/DOP-RS-WAR/rest/SolrOperation/getClause?section='+$(this).val(),
    		success: function(data){
    			
    			// Parse the returned json data
                var opts = $.parseJSON(data);
               
                console.log(opts[0]);
                $.each(opts, function(i,d){
                
                	console.log(opts[i]);
                	select.append($('<option></option>').val(opts[i]).html(opts[i]));
                	
                	
                });
              
    		}
    	
    	});
    	
    	});
    	
    	//Clause Change function
    	$("#dop-clause").change(function (){
    	
    			var section_val=$("#dop-section").val();
    			var clause_val=$(this).val();
    	
	    		$.ajax({
	    	
	    		type:'GET',
	    		url:'/DOP-RS-WAR/rest/SolrOperation/getSubClause?section='+section_val + "&clause=" + clause_val,
	    		success: function(data){
	    			
	    			// Parse the returned json data
	                var opts2 = $.parseJSON(data);
	                
	                var select2 = $('#dop-subclause');
	    			select2.empty();
	    			
	                console.log(opts2[0]);
	                $.each(opts2, function(i,d){
	                
	                	console.log(opts2[i]);
	                	select2.append($('<option></option>').val(opts2[i]).html(opts2[i]));
	                	
	                	
	                });
	              
	    		}
	    	
	    	});
    	
    	
    	});
    	
    	//SubClause Change Function
    	$("#dop-subclause").change(function (){
    	
    		var section_val=$("#dop-section").val();
    		var clause_val=$("#dop-clause").val();
    		var subclause_val=$(this).val();
    		
    		
    		$.ajax({
	    	
	    		type:'GET',
	    		url:'/DOP-RS-WAR/rest/SolrOperation/getRomanClause?section='+section_val + "&clause=" + clause_val + "&subclause=" + subclause_val,
	    		success: function(data){
	    			
	    			// Parse the returned json data
	                var opts3 = $.parseJSON(data);
	                
	                var select3 = $('#dop-romanclause');
	    			select3.empty();
	    			
	                console.log(opts3[0]);
	                $.each(opts3, function(i,d){
	                
	                	console.log(opts3[i]);
	                	select3.append($('<option></option>').val(opts3[i]).html(opts3[i]));
	                	
	                	
	                });
	              
	    		}
	    	
	    	});
    		
    	
    	});
    	
	
	});
	
	</script>

</layout:put>


</layout:extends>



