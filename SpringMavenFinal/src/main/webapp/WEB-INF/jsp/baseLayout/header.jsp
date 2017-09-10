<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="org.springframework.ui.Model" %>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <%@taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <script src="<c:url value="/resources/jquery-1.11.3.min.js" />"></script>
  <script src="<c:url value="/resources/jquery-ui.js" />"></script>
</head>
<body>
<div style=" font-size: xx-large;font-style: oblique;font-stretch: wider;font-family: cursive;color: maroon;" align="center" ><b>KALIKADEVI&nbsp;CABLE &nbsp;NETWORKS</b></div>

<div id='cssmenu'>
<ul>
   <li><a href="<c:url value="/home" />">Home</a></li>
   <li class='active has-sub'><a href='#'>Products</a>
      <ul>
         <li class='has-sub'><a href='#'>Subscriber Details</a>
            <ul>
               <li><a href='<c:url value="/admin/userInfo" />'>List all subscribers</a></li>
                <li><a href='<c:url value="/admin/addSubscriber" />'>Add new subscriber</a></li>
                <li><a href='<c:url value="/admin/paymentYearDetails" />'>View Balance Year wise</a></li>
            </ul>
         </li>
         <li class='has-sub'><a href='#'>Manage</a>
            <ul>
               <li><a href='<c:url value="/admin/openPaymentDefPage" />'>Define Payement</a></li>
                <li><a href='<c:url value="/admin/openAreaMaster" />'>Area Master</a></li>
            </ul>
         </li>
        <!--  <li class='has-sub'><a href='#'>Product 2</a>
            <ul>
               <li><a href='#'>Sub Product</a></li>
               <li><a href='#'>Sub Product</a></li>
            </ul>
         </li> -->
         
         
      </ul>
   </li>
   <li><a href='<c:url value="/about" />'>About</a></li>
   <li><a href='#'>Contact</a></li>
  
	<c:if test="${sessionScope.SPRING_SECURITY_CONTEXT==null }">
		<li><a href='<c:url value="/register" />'>Register</a></li>
        <li><a href='<c:url value="/login" />'>Login</a></li>
         
    </c:if>
 	 <c:if test="${sessionScope.SPRING_SECURITY_CONTEXT!=null }">
 	 <li> <a href='<c:url value="/editProfile"/>'> Edit Profile</a></li>
         <li> <a href='<c:url value="/logout" />'><sec:authentication property="principal.username"/> Logout</a></li>
    </c:if>
</ul>
</div>
</body>
</html>
