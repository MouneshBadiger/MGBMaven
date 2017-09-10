<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib uri="http://www.springframework.org/tags/form" prefix="s"%> 
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kalikadevi cable networks</title>
<script type="text/javascript">
function filterRecordsOnArea(id){
	window.location.href = "/SpringMavenFinal/admin/paymentYearDetails?areaId="+id;
	
}
function generateYearReport(){
	
	var url= "/SpringMavenFinal/admin/paymentYearDetails?areaId="+$('#name').val()+"&printYearRep=true";
	var win = window.open(url, '_blank');
}
function goBack(){
	window.location.href = "/SpringMavenFinal/home"
}
</script>

</head>
<body>
<s:form action="" modelAttribute="areaMasterDto">
<table align="center">
<tr><td align="center" style="font-weight: bolder;font-size: large; color: #888888">Subscribers Pending Balance List</td><br></tr>

<tr>
	<td align="center">
		<table align="right" style="padding-right: 50px;">
		<tr>
			<td align="right">Area</td>
			<td  align="right">
				<s:select path="name" onchange="filterRecordsOnArea(this.value);">
				<s:option value="">--select--</s:option>
				<s:option value="0">All</s:option>
					<s:options items="${areaMap}"/>
				</s:select> 
			</td>
		</tr>
		</table>
		<c:if test="${not empty subsList }">
		<table id="myTable" border="1" style="border-collapse: collapse;" cellspacing="10px" cellpadding="10px" width="100%">
		<thead>
			<tr style="background-color:#C8C8C8;font-weight: bold;">
					<th width="5%" >No</th>
					<th  width="30%">Name</th>
					<!-- <th width="5%">Mobile no</th>
					<th width="5%">Address</th> -->
					<th width="5%">Pre Bal</th>
					<th>Jan</th>
					<th>Feb</th>
					<th>Mar</th>
					<th>Apr</th>
					<th>May</th>
					<th>Jun</th>
					<th>Jul</th>
					<th>Aug</th>
					<th>Sep</th>
					<th>Oct</th>
					<th>Nov</th>
					<th>Dec</th>
					<th>Tot Bal</th>
				</tr>
			</thead>
			 <tbody>
			<c:forEach var="i" varStatus="st" items="${subsList}">
				<tr>
					<td><c:out value="${st.index+1}"/></td>
					<td><c:out value="${i.subscriberName}"/></td>
					<%-- <td><c:out value="${i.phoneNo}"/></td>
					<td><c:out value="${i.address}"/></td> --%>
					<td><c:out value="${i.preBalance}"/></td>
					<c:forEach var="m" varStatus="st1" items="${i.monthList }">
					 <td align="center"><b><c:out  value="${m.amount}"/></b><br><div style="font-size:8px;font-family: sans-serif;"><c:out value="${m.paymentMadeDate}"/></div></td>
					</c:forEach>
					<td><b><c:out value="${i.totalBalance}"/></b></td>
				</tr>
			</c:forEach>
			 </tbody>
		</table>
		<table style="margin-top: 10px;">
		<tr>
			<td align="center" colspan="9">
				<input type="button" value="close" onclick="goBack();">
				&nbsp;&nbsp;&nbsp;
				<input type="button" value="Balance Report" onclick="generateYearReport();"> 
			</td>
		</tr>
		</table>
	</c:if>
	</td>
	
</tr>
</table>
</s:form>
</body>
</html>
