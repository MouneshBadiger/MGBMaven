<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<!-- <link rel="stylesheet" href="resources/styles.css" type="text/css" > -->
 <link href="<c:url value="/resources/styles.css" />" rel="stylesheet">
</head>
<body style="width: 100%">
<table border="0" style="border-collapse: collapse;"  width="100%" >
    <tbody><tr>
        <td height="120px"  style="border-bottom-style: solid;" id="header"><tiles:insertAttribute name="header"/></td>
    </tr>
    <tr>
        <td valign="middle"  style="padding-left: 50px ;background-image: url('resources/images/light.jpg');background-size:cover;" height="500px"><tiles:insertAttribute name="body"/></td>
    </tr>
    <tr>
        <td  style="border-top-style: groove;"  height="30px" id="header" ><tiles:insertAttribute name="footer"/></td>
    </tr>
</tbody></table>

</body>
</html>