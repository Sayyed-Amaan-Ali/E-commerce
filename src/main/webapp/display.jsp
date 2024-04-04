<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="jakarta.el.ValueReference"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="p1.product_data" %>
<%@page import="p1.cart_data" %>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
<link rel="stylesheet" href="CSS/display_style.css">
</head>
<body>
<div class="container">
<h2> <%out.print(request.getParameter("Username")); %>!! Welcome to Our Shop</h2>

<div class="cartbutton"><button id="cartbtn" onclick="showCart()">View Cart</button></div>

<!-- Sorting-Menu -->
<div class="dropdown">
    <button class="dropbtn" id="dropdownBtn">Sort by</button>
    <div class="dropdown-content" id="dropdownContent">
    	<form action="display.jsp" method="post">
    		<input type="hidden" value=<%=request.getParameter("Username")%> name="Username">
			<input type="hidden" value="pname" name="Sortby">
			<input type="submit" value="Name">
    	</form>
    	<form action="display.jsp" method="post">
    		<input type="hidden" value=<%=request.getParameter("Username")%> name="Username">
			<input type="hidden" value="price" name="Sortby">
			<input type="submit" value="Price">
    	</form>
    </div>
</div>

<!-- Filter-Menu -->

<table border="2">
<thead>
<tr>
<td>S.No.</td>
<td>Name</td>
<td>Description</td>
<td>Quantity</td>
<td>Price</td>
<td>Shop-Name</td>
<td>Action</td>
</tr>
</thead>
<tbody>
	<% 
		ArrayList<product_data> data=new product_data().getProduct_list("-1",request.getParameter("Sortby"));
		for(int i=0;i<data.size();i++){ %>
		<tr>
			<td><% out.println(i+1);//out.println(data.get(i).getId()); %></td>
			<td><% out.println(data.get(i).getName()); %></td>
			<td><% out.println(data.get(i).getDesc()); %></td>
			<td><% out.println(data.get(i).getQ()); %></td>
			<td><% out.println(data.get(i).getPrice()); %></td>
			<td><% out.println(data.get(i).getShopkeeper()); %></td>
			<td>
				<form action="cart_entry" method="post">
					<input type="hidden" value=<%=request.getParameter("Username")%> name="Username">
					<input type="hidden" value=<%=data.get(i).getId()%> name="pid">
					<input type="number" class="input-quantity" name="pquant" value="1" min="1"  max=<%=data.get(i).getQ()%>/>
                    <button class="btn">Add to Cart</button>
				</form>
			</td>
		</tr>
	<% } %>
</tbody>
</table>
</div>
<div class="modal" id="cartModal">
<div class="modal-content">
<span class="close">&times;</span>
<h3>Your Cart</h3>
<%
ArrayList<cart_data> bill_data=new ArrayList<>();

try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Projects","root","amaan9876");
	Statement statement=connection.createStatement();
	ResultSet set=statement.executeQuery("select * from cart where `Username` like '"+request.getParameter("Username")+"'");
	while(set.next()){
		cart_data obj=new cart_data();
		obj.setPid(set.getInt(6));
		obj.setName(set.getString(2));
		obj.setQuantity(set.getInt(3));
		obj.setTotal_amt(set.getInt(4));
		bill_data.add(obj);
	}
} catch (Exception e) {
	System.out.println(e);
}
%>
<table>
<thead>
<tr>
<td>P_ID</td>
<td>Name</td>
<td>Quantity</td>
<td>Price</td>
</tr>
</thead>
<tbody>
	<%int sum=0;for(int i=0;i<bill_data.size();i++){ %>
		<tr>
			<td><% out.println(bill_data.get(i).getPid()); %></td>
			<td><% out.println(bill_data.get(i).getName()); %></td>
			<td><% out.println(bill_data.get(i).getQuantity()); %></td>
			<td><% out.println(bill_data.get(i).getTotal_amt());sum+=bill_data.get(i).getTotal_amt(); %></td>
			<td>
				<form action="delete" method="post">
					<input type="hidden" value=<%=request.getParameter("Username")%> name="Username">
					<input type="hidden" value=<%=bill_data.get(i).getPid()%> name="product_id">
					<input type="submit" value="Remove">
				</form>
			</td>
		</tr>
		<% } %>
	<tr>
		<td></td>
		<td>Total Amount</td>
		<td><%out.println(sum); %></td>
	</tr>
</tbody>
</table>
<form action="checkout.jsp" method="post">
<input type="hidden" value=<%=request.getParameter("Username")%> name="Username">
<input type="submit" value="check-out">
</form>
</div>
</div>

<script src="JS/display_script.js"></script>
</body>
</html>