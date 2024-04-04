<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Set"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="p1.product_data" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Adding Products</title>
<link rel="stylesheet" href="CSS/productentry_style.css">

</head>
<body>
<h1>Welcome <%out.println(request.getParameter("shopkeeper_name"));%>!!</h1>

<!--  DISPLAY -->

<!-- Displaying products available in shop -->
<table border="2">
<thead>
<tr>
<td>Name</td>
<td>Description</td>
<td>Quantity</td>
<td>Price</td>
</tr>
</thead>
<tbody>
	<% 	ArrayList<product_data> data=new product_data().getProduct_list(request.getParameter("shopkeeper_name"),null);
		for(int i=0;i<data.size();i++){ %>
		<tr>
			<td><% out.println(data.get(i).getName()); %></td>
			<td><% out.println(data.get(i).getDesc()); %></td>
			<td><% out.println(data.get(i).getQ()); %></td>
			<td><% out.println(data.get(i).getPrice()); %></td>
			<form action="Shopkeeper_delete" method="post" onsubmit="return confirmRemove();">
			<td><input type="submit" value="remove">
				<input type="hidden" value='<%=request.getParameter("shopkeeper_name")%>' name="shopkeeper_name">
				<input type="hidden" value='<%=data.get(i).getId()%>' name="pid">
			</td>
			</form>
		</tr>
	<% } %>
</tbody>
</table>

<!-- Adding Product to shop -->
<div class="form-container">
<h2>Add-Products to your Shop</h2>
<form action="Insert_products" method="post">
Product Name 		<input type="text" name="pname"><br>
Product Description <input type="text" name="pdesc"><br>
Product Quantity 	<input type="text" name="pquant"><br>
Product Rate 		<input type="text" name="prate"><br>
<input type="hidden" value='<%=request.getParameter("shopkeeper_name")%>' name="shopkeeper_name">
<input type="submit" value="ADD"><br>
</form>
 </div>
 <script>
    function confirmRemove() {
        return confirm('Are you sure you want to remove this item?');
    }
</script>
 </body>
</html>


