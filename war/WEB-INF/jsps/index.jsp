<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
	<%@ page import="adaptavant.digistore.Products" %>
	<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="javax.servlet.http.HttpServletResponse"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to DigiStore</title>
</head>
<body>
<%
if(session.getAttribute("empid")!=null)
{
	%>
		<div align="center">
		Signed in as Employee No : <%=session.getAttribute("empid") %> Not you? 
		<a href="/signout">signout</a><br><br><br>
		<input type="button" value="add new" onclick="location.href='/addProduct';"/>
		<input type="button" value="update" onclick="location.href='/updateProduct';"/>
		<!-- <input type="button" value="delete" onclick="location.href='/deleteProduct';"/>
		-->
		</div><br><br><br>
	<%
}
else
{
	%>
	<p align="right"><a href="/signin">signin</a></p>
	<%
}
%>	
<table border="1" align="center">
	<tr>
		<th>Picture</th>
		<th>Brand Name</th>
		<th>Type</th>
		<th>Model Name/Number</th>
		<th>Units Available</th>
		<th>Price per Unit</th>
		<th>Buy</th>
	</tr>
<%
List<Products> l = (List<Products>)request.getAttribute("ProductsList");
if(l!=null)
for(Products p : l) 
{
%>		<tr>
			<td><img src="/serve?blob-key=<%= p.getBlobKey() %>" height="50" width="50"/></td>
	 		<td><%= p.getBrand() %></td>
			<td><%= p.getType() %></td>
			<td><%= p.getName() %></td>
			<td><%= p.getUnitsAvailable() %></td>
			<td><%= p.getPrice() %></td>			
			<td><input value = "Buy" type = button onclick="location.href='/sell?pid=<%=p.getProductId()%>'"></td>
		</tr>
<% 
} 
%>
	</table>
</body>
</html>