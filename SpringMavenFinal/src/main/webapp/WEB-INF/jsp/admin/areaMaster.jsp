<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="org.springframework.ui.Model"%>
  <%@taglib uri="http://www.springframework.org/tags/form" prefix="s"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment</title>
<script type="text/javascript">
function goBack(){
	window.location.href = "/SpringMavenFinal/home"
}
function deleteEntry(id){
	if(confirm("Are you sure to delete the record")){
		window.location.href = "/SpringMavenFinal/admin/areaMaster/delete?boId="+id;
	}
}
function editEntry(id){
		window.location.href = "/SpringMavenFinal/admin/areaMaster/edit?boId="+id;
}
</script>
</head>
<body style="">
<s:form action="/SpringMavenFinal/admin/areaMaster/add" modelAttribute="areaMasterDto">
<s:hidden path="id"/>
<h3>Area Master</h3>
<table width="100%">

<tr><td style="color: red;"> <c:out value="${error}"></c:out></td></tr>
<tr><td style="color:green;"> <c:out value="${message}"></c:out></td></tr>
<tr>
<td>
<table>
						<tr>
							<td>Name:</td>
							<td><s:input path="name"/> </td>
							<td class="errors"><s:errors path="name"/></td>
						</tr>
						<tr >
							<c:if test="${areaMasterDto.id==0 }">
								<td style="padding-top: 20px"><input type="submit" value="Submit"> </td>
							</c:if>
							<c:if test="${areaMasterDto.id>0 }">
								<td style="padding-top: 20px"><input type="submit" value="Update"> </td>
							</c:if>
							<td style="padding-top: 20px" align="center"><input type="button" onclick="goBack()" value="Close"> </td>
							<td></td>
						</tr>
						
					</table>
		 <c:if test="${not empty areaList }">
		<table border="1" cellpadding="5px" style="border-collapse: collapse;margin-top: 30px" width="100%">
			<tr>
				<th width="20%">Sl No</th>
				<th width="20%">Name</th>
				<th width="10%">Edit</th>
				<th width="10%">Cancel</th>
			</tr>
			<c:forEach var="dto" items="${areaList}" varStatus="st">
			<tr> 
				<td><c:out value="${st.index+1}"/></td>
				<td><c:out value="${dto.name}"/></td>
				<td align="center"><img alt="edit" onclick="editEntry(<c:out value="${dto.id}"/>)" src="<c:url value="/resources/images/edit.png"/>" height="20px" width="20px"></td>
				<td align="center"><img alt="delete" onclick="deleteEntry(<c:out value="${dto.id}"/>)" src="<c:url value="/resources/images/delete.png"/>" height="20px" width="20px"></td>
			</tr>
			</c:forEach>
		</table>
		</c:if>
</td>
</tr>
</table>
</s:form>

</body>
</html>