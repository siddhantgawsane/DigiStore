<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up</title>
</head>
<body>
	<h1>Register</h1>
 
	<form method="post" action="/signup">
		<table>
			<tr>
				<td>First Name :</td>
				<td><input type="text" style="width: 185px;" maxlength="30"
					name="fname" id="fname" /></span></td>
			</tr>
			<tr>
				<td>Last Name :</td>
				<td><input type="text" style="width: 185px;" maxlength="30"
					name="lname" id="lname" /></span></td>
			</tr>
			<tr>
				<td>Email Id :</td>
				<td><input type="text" style="width: 185px;" maxlength="30"
					name="email" id="email" /></span></td>
			</tr>
			<tr>
				<td>Password :</td>
				<td><input type="text" style="width: 185px;" maxlength="30"
					name="password" id="password" /></span></td>
			</tr>
		</table>
		<input type="submit" class="save" title="Save" value="Save" />
	</form>

</body>
</html>