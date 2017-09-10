<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib uri="http://www.springframework.org/tags/form" prefix="s"%> 
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>kalikadevi cable networks</title>

</head>
<body>
<table align="center">
<tr><td align="center" style="font-weight: bolder;font-size: large; color: #888888">Subscribers Pending Balance List</td><br></tr>

<tr>
	<td align="center">
		<table id="myTable" border="1" style="border-collapse: collapse;" cellspacing="10px" cellpadding="10px">
		<thead>
			<tr style="background-color:#C8C8C8;font-weight: bold;">
					<th width="5%" >No</th>
					<th width="15%" >Name</th>
					<th width="10%">Mobile no</th>
					<th width="20%">Address</th>
					<th width="10%">Pending Balance</th>
				</tr>
			</thead>
			 <tbody>
			<c:forEach var="i" varStatus="st" items="${userList }">
				<tr>
					<td><c:out value="${st.index+1}"/></td>
					<td><c:out value="${i.name}"/></td>
					<td><c:out value="${i.mobileNo}"/></td>
					<td><c:out value="${i.address}"/></td>
					<td align="center"><c:out value="${i.pendingAmount}"/></td>
					
				</tr>
			</c:forEach>
			 </tbody>
		</table>
	
	</td>
	
</tr>
</table>
</body>
</html>
<script>
    window.print();
</script>