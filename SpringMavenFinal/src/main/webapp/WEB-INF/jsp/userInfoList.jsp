<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib uri="http://www.springframework.org/tags/form" prefix="s"%> 
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function goBack(){
	window.location.href = "/SpringMavenFinal/home"
}
function deleteEntry(id){
	if(confirm("Are you sure to delete the record")){
		window.location.href = "deleteSubscriber?userId="+id;
	}
	
}
function makePayment(id){
	window.location.href = "openPaymentPage?userId="+id;
}
</script>

</head>
<body>
<table align="center">
<tr><td align="center" style="font-weight: bolder;font-size: large; color: #888888">Users List</td><br></tr>
<tr><td style="color: red;"> <c:out value="${error}"></c:out></td></tr>
<tr><td style="color:green;"> <c:out value="${message}"></c:out></td></tr>
<tr>
	<td align="center">
		<table border="1" style="border-collapse: collapse;" cellspacing="10px" cellpadding="10px">
			<tr style="background-color:#C8C8C8;font-weight: bold;">
					<th >Name</th>
					<th > Email</th>
					<th >Mobile no</th>
					<th >Address</th>
					<th >Pending Balance</th>
					<th >Add Payment</th>
					<th >Edit Details</th>
					<th >Delete</th>
				</tr>
			
			<c:forEach var="i" items="${userList }">
			
				<tr>
					<td><c:out value="${i.name}"/></td>
					<td><c:out value="${i.email}"/></td>
					<td><c:out value="${i.mobileNo}"/></td>
					<td><c:out value="${i.address}"/></td>
					<td><c:out value="${i.pendingAmount}"/></td>
					<td><img alt="make payment" onclick="makePayment(<c:out value="${i.id}"/>)" src="<c:url value="/resources/images/makePayment.png"/>" height="20px" width="80px"></td>
					<td align="center"><a href="openEditSubscriber?userId=<c:out value="${i.id}"/>"><img  alt="edit" src="<c:url value="/resources/images/edit.png"/>" height="20px" width="20px"> </a></td>
					<td align="center"><img alt="delete" onclick="deleteEntry(<c:out value="${i.id}"/>)" src="<c:url value="/resources/images/delete.png"/>" height="20px" width="20px"></td>
				</tr>
			
			</c:forEach>
			<tr><td align="center" colspan="8"><input type="button" value="close" onclick="goBack();"> </td></tr>
		
		</table>
	
	</td>
	
</tr>
</table>
</body>
</html>