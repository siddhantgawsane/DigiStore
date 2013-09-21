<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService"%>
<%  BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add A New Product</title>
</head>
<body>
	<form method="post" action="<%=blobstoreService.createUploadUrl("/addProduct")%>"  enctype="multipart/form-data">
	<table align="center" border="1">
		<tr>
			<td>Brand</td>
			<td><input type="text" style="width: 185px;" maxlength="30"
					name="brand" id="brand" /></td>
		</tr>
		<tr>
			<td>Type</td>
			<td><input type="text" style="width: 185px;" maxlength="30"
					name="type" id="type" /></td>
		</tr>
		<tr>
			<td>Name/Model No.</td>
			<td><input type="text" style="width: 185px;" maxlength="30"
					name="mname" id="mname" /></td>
		</tr>
		<tr>
			<td>Price</td>
			<td><input type="text" style="width: 185px;" maxlength="30"
					name="price" id="price" /></td>
		</tr>
		<tr>
			<td>No. of Units Available</td>
			<td><input type="text" style="width: 185px;" maxlength="30"
					name="nua" id="nua" /></td>
		</tr>
		<tr>
				<td>Profile Picture :</td>
				<td><input type="file" name="mypic" id="mypic" /></td>
			</tr>
	</table>
	<input type="submit" title="Add" value="Add" />
	</form>
</body>
</html>