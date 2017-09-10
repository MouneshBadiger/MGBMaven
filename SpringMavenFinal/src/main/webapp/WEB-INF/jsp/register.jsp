<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib uri="http://www.springframework.org/tags/form" prefix="s"%> 
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.min.css" />">
  <script src="<c:url value="/resources/jquery-1.11.3.min.js" />"></script>
  <script src="<c:url value="/resources/jquery-ui.js" />"></script>
  
<script>
  $(function() {
    $( "#datepicker" ).datepicker({ dateFormat: 'dd/mm/yy' });
  });
  function returnNull(){
	  return 0;
  }
</script>
<title>Register</title>
<style type="text/css">
.errors{
color:#880000  ;
}
</style>
</head>
<body >
<table width="100%">
	<tr>
		<td valign="middle">
			<div id="body" >
			
				<s:form action="registration" modelAttribute="register" method="post"> 
				<s:hidden path="id"/>
				<table >
					<tr><td colspan="2" style="color: red;"> <c:out value="${error}"></c:out></td></tr>
					
					<tr><td >Name:</td><td><s:input path="name" /></td><td class="errors"><s:errors path="name"/></td></tr>
					<tr><td>Email:</td><td><s:input path="email" /></td><td class="errors" ><s:errors path="email"/></td></tr>
					<tr><td>Mobile No:</td><td><s:input path="mobileNo" /></td><td class="errors"><s:errors path="mobileNo"/></td></tr>
					<tr><td>Password:</td><td><s:password path="password" /></td><td class="errors"><s:errors path="password"/></td></tr>
					<tr><td>Re Enter Password:</td><td><s:password path="rePassword" /></td><td class="errors"><s:errors path="rePassword"/></td></tr>
					<%-- <tr><td>Date Of Birth:</td><td><s:input path="dob" id="datepicker" onkeyup="returnNull()"/></td><td class="errors"><s:errors path="dob"/></td></tr> --%>
					<tr><td>Address:</td><td><s:input path="address" /></td><td class="errors"><s:errors path="address"/></td></tr>
					<tr><td>Gender:</td><td>Male<s:radiobutton path="gender"  value="male"/> Female<s:radiobutton path="gender" value="female"/> </td><td class="errors"><s:errors path="gender"/></td></tr>
					<!-- <tr id="spPass"><td>special password</td><td><input type="text"></td></tr> -->
					
					<!-- <tr><td>Upload files1<input type="file" name="file2" multiple="multiple" ></td></tr> -->
					
					<tr>
						<td align="right" colspan="3">
						<c:if test="${register.id==0 }">
						<input type="submit" value="Submit"/> 
						</c:if>
						<c:if test="${register.id>0 }">
						<input type="submit" value="Update"/> 
						</c:if>
						</td>
					</tr>
					
   					<%--  <c:set var="count" value="0"  ></c:set>
   					 <c:forEach var="i" items="${register.toList}" varStatus="status">
   					 	<tr><td colspan="2">
							<input type="text" name="toList['${status.index}'].name" id="${status.index}" value='<c:out value="${register.toList[status.index].name}"></c:out>'>
							<input type="checkbox" name="toList['${status.index}'].isChecked" id="checkId"  value='<c:out value="${register.toList[status.index].isChecked}"></c:out>'>
							
							
							
	   					 </td></tr>
   					 </c:forEach> --%>
					
				</table>
				</s:form>
			</div>
		</td>
		<td >
			<div ><img alt="welcome" src="resources/images/po2.png"></div>
		</td>
	</tr>
</table>

</body>
</html>