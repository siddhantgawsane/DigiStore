<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><%@page
 language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<html>
<body>
<% 
HttpServletResponse hsr = (HttpServletResponse) response;
hsr.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
hsr.setHeader("Pragma", "no-cache"); // HTTP 1.0.
hsr.setHeader("Expires","0"); 
hsr.setDateHeader("Expires", -1); // Proxies.

if(session.getAttribute("userobj")!=null){
	response.sendRedirect("home");
}

%>
	<h1>Login</h1>
 
	<form method="post" action="signin">
		<table align="center" border="1">
			<tr>
				<td>Employee Id :</td>
				<td><input type="text" style="width: 185px;" maxlength="30"
					name="empid" id="empid" /></span></td>
			</tr>
			<tr>
				<td>Password :</td>
				<td><input type="text" style="width: 185px;" maxlength="30"
					name="password" id="password" /></span></td>
			</tr>
		</table>
		<input type="submit" class="save" title="Login" value="Login" />
	</form>
	New Employee? <a href = "/signup">Sign Up!</a>
 
</body>
</html>