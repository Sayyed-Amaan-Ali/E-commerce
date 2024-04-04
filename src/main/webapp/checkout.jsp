<%@page import="java.util.ArrayList"%>
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
<title>Bill generation</title>
</head>
<h1>Thanks for Shopping with us!!!!</h1>
<body>
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
<td>Name</td>
<td>Quantity</td>
<td>Price</td>
</tr>
</thead>
<tbody>
	<%int sum=0;for(int i=0;i<bill_data.size();i++){ %>
		<tr>
			<td><% out.println(bill_data.get(i).getName()); %></td>
			<td><% out.println(bill_data.get(i).getQuantity()); %></td>
			<td><% out.println(bill_data.get(i).getTotal_amt());sum+=bill_data.get(i).getTotal_amt(); %></td>
		</tr>
		<% } %>
	<tr>
		<td></td>
		<td>Total Amount</td>
		<td><%out.println(sum); %></td>
	</tr>
</tbody>
</table>

<% 
int quantity;
try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Projects","root","amaan9876");
	Statement statement=connection.createStatement();
	for(int i=0;i<bill_data.size();i++){
		int pid=bill_data.get(i).getPid();
		int quant=bill_data.get(i).getQuantity();
		ResultSet set=statement.executeQuery("select * from product");
		int flag=0;
		while(set.next()) {
			if(set.getInt(1)==pid) {
				quantity=set.getInt(4);
				Statement st=statement;
				int temp;
				if((quantity-quant)==0)
					 temp=st.executeUpdate("delete from product where `id`='"+pid+"'");
				else 
					temp=st.executeUpdate("Update product set `quantity`='"+(quantity-quant)+"' where `id`='"+pid+"'");
				flag=1;
				break;
			}
		}
	}
	int set=statement.executeUpdate("Delete from cart where `Username` like '"+request.getParameter("Username")+"'");
} catch (Exception e) {
	System.out.println(e);
}
%>
<form action="display.jsp" method="post">
<input type="hidden" value=<%=request.getParameter("Username")%> name="Username">
<input type="submit" value="continue shopping">
</form>
<form action="index.html" method="post">
<input type="submit" value="Logout">
</form>
</body>
</html>