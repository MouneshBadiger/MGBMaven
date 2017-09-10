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
		window.location.href = "/SpringMavenFinal/admin/paymentDef/delete?boId="+id;
	}
}
function editEntry(id){
		window.location.href = "/SpringMavenFinal/admin/paymentDef/edit?boId="+id;
}
</script>
</head>
<body style="">
<s:form action="/SpringMavenFinal/admin/paymentDef/add" modelAttribute="paymentDefDTO">
<s:hidden path="id"/>
<h1>Monthly Defintion</h1>
<table width="100%">

<tr><td style="color: red;"> <c:out value="${error}"></c:out></td></tr>
<tr><td style="color:green;"> <c:out value="${message}"></c:out></td></tr>
<tr>
<td>
<table>
						<tr>
							<td>Month</td>
							<td><s:select path="defMonth">
										<s:option value="0">January</s:option>
										<s:option value="1">February</s:option>
										<s:option value="2">March</s:option>
										<s:option value="3">April</s:option>
										<s:option value="4">May</s:option>
										<s:option value="5">June</s:option>
										<s:option value="6">July</s:option>
										<s:option value="7">August</s:option>
										<s:option value="8">September</s:option>
										<s:option value="9">October</s:option>
										<s:option value="10">November</s:option>
										<s:option value="11">December</s:option>
										
								</s:select></td>
								<td class="errors"><s:errors path="defMonth"/></td>
						</tr>
						<tr>
							<td>Year</td>
							<td><s:select path="defYear">
										<s:option value="2014">2014</s:option>
										<s:option value="2015">2015</s:option>
										<s:option value="2016">2016</s:option>
										<s:option value="2017">2017</s:option>
										<s:option value="2018">2018</s:option>
										<s:option value="2019">2019</s:option>
										<s:option value="2020">2020</s:option>
										<s:option value="2021">2021</s:option>
										
								</s:select></td>
								<td class="errors"><s:errors path="defYear"/></td>
						</tr>
						<tr>
							<td>Amount</td>
							<td><s:input path="amount"/> </td>
							<td class="errors"><s:errors path="amount"/></td>
						</tr>
						<tr>
							<c:if test="${paymentDefDTO.id==0 }">
								<td><input type="submit" value="Submit"> </td>
							</c:if>
							<c:if test="${paymentDefDTO.id>0 }">
								<td><input type="submit" value="Update"> </td>
							</c:if>
							<td  align="center"><input type="button" onclick="goBack()" value="Close"> </td>
							<td></td>
						</tr>
						
					</table>
		 <c:if test="${not empty defList }">
		<table border="1" cellpadding="5px" style="border-collapse: collapse;margin-top: 30px" width="100%">
			<tr>
				<th width="20%">Sl No</th>
				<th width="20%">Month</th>
				<th width="20%">Year</th>
				<th width="20%">Amount</th>
				<th width="10%">Edit</th>
				<th width="10%">Cancel</th>
			</tr>
			<c:forEach var="dto" items="${defList}" varStatus="st">
			<tr> 
				<td><c:out value="${st.index+1}"/></td>
				<td><c:out value="${dto.defMonth}"/></td>
				<td><c:out value="${dto.defYear}"/> </td>
				<td><c:out value="${dto.amount}"/></td>
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