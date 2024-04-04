

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/Admin_login")
public class Admin_login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Admin sign in
		String Username=request.getParameter("shopkeeper_name");
		String password=request.getParameter("admin_pwd");
		//PrintWriter out=response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Projects","root","amaan9876");
			Statement statement=connection.createStatement();
			ResultSet set=statement.executeQuery("select * from admin");
			int flag=0;
			while(set.next()) {
				if(set.getString(2).equals(Username) && set.getString(3).equals(password)) {
					flag=1;
					break;
				}
			}
			if(flag==0) {
				//out.print("Login Failed");
				response.sendRedirect("index.html");
				System.out.println("login failed");
			}
			else {
				RequestDispatcher rDispatcher=request.getRequestDispatcher("productentry.jsp");
				rDispatcher.forward(request, response);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);
	}

}
