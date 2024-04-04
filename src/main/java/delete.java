

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

public class delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pid=Integer.parseInt(request.getParameter("product_id"));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Projects","root","amaan9876");
			Statement statement=connection.createStatement();
			int set=statement.executeUpdate("Delete from cart where `pid`='"+pid+"' and `Username` like '"+request.getParameter("Username")+"'");
			System.out.println("data-removed");
			RequestDispatcher rd=request.getRequestDispatcher("display.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
