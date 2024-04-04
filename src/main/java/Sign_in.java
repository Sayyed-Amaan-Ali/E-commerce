

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


@WebServlet("/Sign_in")
public class Sign_in extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Username1=request.getParameter("Username");
		String password=request.getParameter("pwd");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Projects","root","amaan9876");
			Statement statement=connection.createStatement();
			ResultSet set=statement.executeQuery("select * from customer");
			int flag=0;
			while(set.next()) {
				if(set.getString(2).equals(Username1) && set.getString(3).equals(password)) {
					flag=1;
					break;
				}
			}
			if(flag==0) {
				response.sendRedirect("index.html");
				System.out.println("login failed");
			}
			else {
				System.out.println("login successfull");
				RequestDispatcher rd=request.getRequestDispatcher("display.jsp");
				rd.forward(request, response);
				
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
