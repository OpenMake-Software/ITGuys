<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>IT Guys (${env})</title>
<style type="text/css">
#rt-logo {background: url(/images/omlogo.png) 50% 0 no-repeat !important;}
#rt-logo {width: 156px;height: 30px;}
</style>
</head>
<body>
<div style="background-color:#02638b;height:53px;width:100%">
<a href="/"><img style="position:absolute; top:20px; left:20px;" src="images/omlogo.png"></a>
</div>
<H1>Important People in IT</H1>
<h2>Version ${version} - Build ${buildno}</h2>
<table border="1">
<tr>
<td style="background-color:#02638b;color:white"><I>First Name</I></td>
<td style="background-color:#02638b;color:white"><I>Surname</I></td>
<td style="background-color:#02638b;color:white"><I>Date of Birth</I></td>
<td style="background-color:#02638b;color:white"><I>Achievement</I></td>
</tr>
<c:forEach items="${people}" var="person">
<tr><td>${person.firstName}</td><td>${person.surname}</td><td>${person.dob}</td><td>${person.achievement}</td></tr>
</c:forEach>
</table>
</body>
</html>
