

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


@WebServlet("/cart_entry")
public class cart_entry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
   		String name="";
   		int quant=Integer.parseInt(request.getParameter("pquant"));
   		int pid=Integer.parseInt(request.getParameter("pid"));


   		int rate=0,quantity=0,temp;
   		try {
   			Class.forName("com.mysql.cj.jdbc.Driver");
   			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Projects","root","amaan9876");
   			Statement statement=connection.createStatement();
   			ResultSet set=statement.executeQuery("select * from product");
   			
   			while(set.next()) {
   				if(set.getInt(1)==pid) {
   					name=set.getString(2);
   					rate=set.getInt(5);
   					quantity=set.getInt(4);
   					break;
   				}
   			}
   			if(quant>quantity) {
   				System.out.println("Data not found");
   				RequestDispatcher rd=request.getRequestDispatcher("display.jsp");
   				rd.forward(request, response);
   				
   			}
   			else {
   				Statement st=connection.createStatement();
   				Statement st1=connection.createStatement();
   				
   				ResultSet set2=st.executeQuery("select * from cart where `Username` like '"+request.getParameter("Username")+"'");
   				int flag=0;
   				while(set2.next()){
   					if(set2.getInt(6)==pid){
   						quant+=set2.getInt(3);
   						flag=1;
   						break;
   					}
   				}
   				if(flag==0){
   					temp=st1.executeUpdate("Insert into cart(`pname`,`quantity`,`total_amt`,`Username`,`pid`) values('"+name+"','"+quant+"','"+(rate*quant)+"','"+request.getParameter("Username")+"','"+pid+"')");
   				}
   				else{
   					if(quant>quantity){
   						System.out.println("Wrong quantity");
   					}
   					else
   						temp=st1.executeUpdate("Update cart set `quantity`='"+quant+"' where `pid`='"+pid+"' and `Username` like '"+request.getParameter("Username")+"'");
   				}
   				System.out.println("Data-added in cart");
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
