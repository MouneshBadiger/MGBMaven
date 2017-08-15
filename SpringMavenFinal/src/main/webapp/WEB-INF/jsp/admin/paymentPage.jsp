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
	window.location.href = "userInfo"
}
function deleteEntry(id){
	if(confirm("Are you sure to delete the record")){
		window.location.href = "cancelMonthPayment?boId="+id;
	}
}
</script>
</head>
<body style="">
<s:form action="addMonthlyPayment" modelAttribute="paymentDto">
<h1>Monthly Payment</h1>
<table width="100%">

<tr><td style="color: red;"> <c:out value="${error}"></c:out></td></tr>
<tr><td style="color:green;"> <c:out value="${message}"></c:out></td></tr>
<tr>
<td>
<table>
<tr>
<td>User Name</td>
<td colspan="2"><c:out value="${paymentDto.userName}"></c:out> </td>
</tr>
<tr>
<td>Total Pending amount:</td>
<td colspan="2"><c:out value="${paymentDto.totalAmountPending}"></c:out>
<%-- <s:hidden path="pendingTillMonthMap"/> --%>
<br>
</td>
</tr>
<c:if test="${paymentDto.totalAmountPending==0}">
<td colspan="2"><div style="font-size: x-large;color: green; ">Payment is completed till date.</div> </td>
</c:if>
<c:if test="${paymentDto.totalAmountPending!=0}">
<tr>
<td>Pay From</td>
<td><s:select path="pendingFromMonth">
<s:options items="${paymentDto.pendingFromMonthMap}"/>
</s:select> 
</td>
</tr>
<tr>
<td>Pay Till</td>
<td><s:select path="pendingTillMonth">
<s:options items="${paymentDto.pendingTillMonthMap}"/>
</s:select> 
</td>

</tr>
<tr>
<td  align="center"><input type="submit" value="Add Payment"> </td>
<td  align="center"><input type="button" onclick="goBack()" value="Close"> </td>
</tr>
</c:if>
<c:if test="${paymentDto.totalAmountPending==0}">
<tr>
<td colspan="2" align="center"><input type="button" onclick="goBack()" value="Close"> </td>
</tr>
</c:if>
</table>
<c:if test="${not empty paymentDto.monthDetailsList }">
		<table border="1" cellpadding="5px" style="border-collapse: collapse;margin-top: 30px" width="100%">
		<tr>
			<th width="20%">Sl No</th>
			<th width="20%">Month</th>
			<th width="20%">Payment date</th>
			<th width="20%">Amount</th>
			<th width="20%">Cancel</th>
		</tr>
		<c:forEach var="dto" items="${paymentDto.monthDetailsList }" varStatus="st">
		<tr>
			<td><c:out value="${st.index+1}"/></td>
			<td><c:out value="${dto.paymentMonth}"/></td>
			<td><c:out value="${dto.paymentMadeDate}"/> </td>
			<td><c:out value="${dto.amount}"/></td>
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