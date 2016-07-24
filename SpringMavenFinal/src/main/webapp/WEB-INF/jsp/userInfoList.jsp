<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib uri="http://www.springframework.org/tags/form" prefix="s"%> 
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table align="center">
<tr><td align="center" style="font-weight: bolder;font-size: large; color: #888888">Users List</td><br></tr>
<tr>
	<td align="center">
		<table border="1" style="border-collapse: collapse;" cellspacing="10px" cellpadding="10px">
			<tr style="background-color:#C8C8C8;font-weight: bold;">
					<td >Name</td>
					<td > Email</td>
					<td >Mobile no</td>
					<td >Password</td>
				</tr>
			
			<c:forEach var="i" items="${userList }">
			
				<tr>
					<td><c:out value="${i.name}"></c:out></td>
					<td><c:out value="${i.email}"></c:out></td>
					<td><c:out value="${i.mobileNo}"></c:out></td>
					<td><c:out value="${i.password}"></c:out></td>
				</tr>
			
			</c:forEach>
		
		</table>
	
	</td>
</tr>
</table>
</body>
</html>