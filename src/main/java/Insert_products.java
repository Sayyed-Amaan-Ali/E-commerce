

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


@WebServlet("/Insert_products")
public class Insert_products extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String Shopkeeper=request.getParameter("shopkeeper_name");
		System.out.println(Shopkeeper);
		String name=request.getParameter("pname");
		String desc=request.getParameter("pdesc");
		int quant=Integer.parseInt(request.getParameter("pquant"));
		int rate=Integer.parseInt(request.getParameter("prate"));
		int id=-1;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Projects","root","amaan9876");
			Statement statement=connection.createStatement();
			ResultSet set=statement.executeQuery("select * from product where `Shopkeeper` like '"+Shopkeeper+"'");
			int flag=0;
			while(set.next()) {
				if(set.getString(2).equals(name)) {
					id=set.getInt(1);
					flag=1;
					break;
				}
			}
			if(flag==0) {
				int temp=statement.executeUpdate("Insert into product(`pname`,`pdesc`,`quantity`,`price`,`Shopkeeper`) values('"+name+"','"+desc+"','"+quant+"','"+rate+"','"+Shopkeeper+"')");
				System.out.println("Data-Added");
			}
			else {
				int temp=statement.executeUpdate("Update product set `quantity`='"+(set.getInt(4)+quant)+"' where `id`='"+id+"'");
				System.out.println("Data-Updated");
			}
			RequestDispatcher rDispatcher=request.getRequestDispatcher("productentry.jsp");
			rDispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println(e);
		}

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
