<%@page import="com.fund"%>
<%@page import="com.fundAPI"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/fund.js"></script>
</head>
<body> 

<div class="container"><div class="row"><div class="col-6"> 
		<h1>Fund Management</h1>
<form id="formfund" name="formfund">

 Funder Name: 
 <input id="name" name="name" type="text" 
 class="form-control form-control-sm">
 <br> Fund Amount: 
 <input id="amount" name="amount" type="text" 
 class="form-control form-control-sm">
 <br> Mobile Number: 
 <input id="pnumber" name="pnumber" type="text" 
 class="form-control form-control-sm">
 <br> NIC: 
 <input id="nic" name="nic" type="text" 
 class="form-control form-control-sm">
 <br>
  City: 
 <input id="city" name="city" type="text" 
 class="form-control form-control-sm">
 <br> Fund Description: 
 <input id="desc" name="desc" type="text" 
 class="form-control form-control-sm">
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidfundidSave" 
 name="hidfundidSave" value="">
 
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divfundGrid">
		 <%
		 fund fundObj = new fund(); 
		 		 out.print(fundObj.readfund());
		 %>
</div>
</div> </div> </div> 

</body>
</html>