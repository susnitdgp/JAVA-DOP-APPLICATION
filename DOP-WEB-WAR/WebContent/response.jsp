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
	
	
	</fieldset>
	
	<input type="submit" value="Search" class="btn btn-primary"/>
	<!-- <input type="reset" Value="Reset" class="btn"> -->
		
	<br/>
	
</form>

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

	<ul class="nav nav-tabs nav-tabs-google" id="myTab">
	  <li class="active"><a href="#home"><i class="icon-search"></i> Page Content(Text)</a></li>
	  <li><a href="#profile"><i class="icon-search"></i> Page Content(Image)</a></li>
	  <li><a href="#messages">Remarks Page</a></li>
	  <li><a href="#settings">Office Order(Amendment)</a></li>
	</ul>
 
	<div class="tab-content">
	  <div class="tab-pane active" id="home">
	  
	  <c:forEach items="${items}" var="item">
	  
	  	Section: ${item.dop_section} 
	  	Clause:  ${item.dop_clause}
	  	Sub Clause:  ${item.dop_subclause}
	  	Roman Clause:  ${item.dop_romansubclause}
	  	<pre>
		${item.page_content}
		</pre>
	  </c:forEach>
	  
	  </div>
	  <div class="tab-pane" id="profile">
	  
		  <c:forEach items="${items}" var="item">
		  <img src="ImageServlet?id=${item.page_id}" width="800" height="400"></img>
		  </c:forEach>
	  
	  </div>
	  <div class="tab-pane" id="messages">inside messages</div>
	  <div class="tab-pane" id="settings">inside settings</div>
	</div>
	
	<div class="pagination">
		<ul>
        
        <c:if test="${currentPage != 1}">
            <li class="page-item"><a class="page-link" 
                href="ReadCountries?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">Previous</a>
            </li>
        </c:if>

        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <li class="page-item active"><a class="page-link">
                            ${i} <span class="sr-only">(current)</span></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" 
                        href="ReadCountries?recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage lt noOfPages}">
            <li class="page-item"><a class="page-link" 
                href="ReadCountries?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">Next</a>
            </li>
        </c:if>              
    </ul>
	</div>
	
	 
	<a href="${pageContext.request.contextPath}/PreviousServlet?page_no=${item.page_no}">Previous Page</a>
	<a href="${pageContext.request.contextPath}/NextServlet?page_no=${item.page_no}">Next Page</a>
	
	
	
	<script type="text/javascript">
	
	$( document ).ready(function() {
    
    	console.log( "ready!" );
    	
    	$('#myTab a').click(function (e) {
		  e.preventDefault();
		  $(this).tab('show');
		});
    	
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



