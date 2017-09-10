<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib uri="http://www.springframework.org/tags/form" prefix="s"%> 
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="<c:url value="/resources/jquery-1.11.3.min.js" />"></script>
  <script src="<c:url value="/resources/jquery-ui.js" />"></script>

<link href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css" rel="stylesheet">
<script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
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
function generateBalanceReport(){
	var url = "userInfo?generateReport=true"
		var win = window.open(url, '_blank');
}
</script>

</head>
<body >
<table  align="center" >
<tr><td align="center" style="font-weight: bolder;font-size: large; color: #888888">Users List</td><br></tr>
<tr><td style="color: red;"> <c:out value="${error}"></c:out></td></tr>
<tr><td style="color:green;"> <c:out value="${message}"></c:out></td></tr>
<tr>
	<td align="center">
		<table  id="myTable"  border="1" style="border-collapse: collapse;" cellspacing="10px" cellpadding="10px">
		<thead>
			<tr style="background-color:#C8C8C8;font-weight: bold;">
					<th width="5%" >No</th>
					<th width="15%" >Name</th>
					<!-- <th width="10%"> Email</th> -->
					<th width="10%">Mobile no</th>
					<th width="10%">Address</th>
					<th width="10%">Sub Date</th>
					<th width="10%">Balance</th>
					<th width="10%">Payment</th>
					<th width="10%">Edit</th>
					<th width="10%">Delete</th>
				</tr>
			</thead>
			 <tbody>
			<c:forEach var="i" varStatus="st" items="${userList }">
			
				<tr>
					<td><c:out value="${st.index+1}"/></td>
					<td><c:out value="${i.name}"/></td>
					<%-- <td><c:out value="${i.email}"/></td> --%>
					<td><c:out value="${i.mobileNo}"/></td>
					<td><c:out value="${i.address}"/></td>
					<td><fmt:formatDate type="date" value="${i.subscriberDetails.subscribedDate}" pattern="dd/MM/yyyy" /></td>
					<td align="center"><c:out value="${i.pendingAmount}"/></td>
					<td align="center"><img alt="make payment" onclick="makePayment(<c:out value="${i.id}"/>)" src="<c:url value="/resources/images/makePayment.png"/>" height="20px" width="20px"></td>
					<td align="center"><a href="openEditSubscriber?userId=<c:out value="${i.id}"/>"><img  alt="edit" src="<c:url value="/resources/images/edit.png"/>" height="20px" width="20px"> </a></td>
					<td align="center"><img alt="delete" onclick="deleteEntry(<c:out value="${i.id}"/>)" src="<c:url value="/resources/images/delete.png"/>" height="20px" width="20px"></td>
				</tr>
			</c:forEach>
			 </tbody>
			
		
		</table>
		<table>
			<tr>
				<td align="center" colspan="9">
				<input type="button" value="close" onclick="goBack();">
				&nbsp;&nbsp;&nbsp;
				<input type="button" value="Balance Report" onclick="generateBalanceReport();"> 
				 </td>
			</tr>
		</table>
	
	</td>
	
</tr>
</table>
</body>
</html>
<script>
$(document).ready(function(){
    $('#myTable').DataTable();
});
</script>