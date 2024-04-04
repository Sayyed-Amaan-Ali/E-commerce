

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


@WebServlet("/User_registration")
public class User_registration extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String Username=request.getParameter("name");
		String password=request.getParameter("password");
		String add=request.getParameter("add");
		String gen=request.getParameter("gen");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Projects","root","amaan9876");
			Statement statement=connection.createStatement();
			ResultSet set=statement.executeQuery("select * from customer");
			int flag=0;
			while(set.next()) {
				if(set.getString(2).equals(Username)) {
					flag=1;
					break;
				}
			}
			if(flag==0) {
				int temp=statement.executeUpdate("insert into customer(`name`,`password`,`address`,`gender`) values('"+Username+"','"+password+"','"+add+"','"+gen+"')");
				response.sendRedirect("index.html");
				System.out.println("account created successfully");
			}
			else {
				System.out.println("Duplicate entry");
				response.sendRedirect("index.html");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
