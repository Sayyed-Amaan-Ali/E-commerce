package p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class product_data {
	String name,desc,Shopkeeper;
	int id,q,price;
	public int getId() {
		return id;
	}
	public String getShopkeeper() {
		return Shopkeeper;
	}
	public void setShopkeeper(String shopkeeper) {
		Shopkeeper = shopkeeper;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getQ() {
		return q;
	}
	public void setQ(int q) {
		this.q = q;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	public ArrayList<product_data> getProduct_list(String name,String sortString){
		ArrayList<product_data> data=new ArrayList<>();
		if(sortString==null)
			sortString="pname";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Projects","root","amaan9876");
			Statement statement=connection.createStatement();
			if(name.equals("-1")) {
				ResultSet set=statement.executeQuery("select * from product order by "+sortString);
				 while(set.next()){
					product_data obj=new product_data();
					obj.setId(set.getInt(1));
					obj.setName(set.getString(2));
					obj.setDesc(set.getString(3));
					obj.setQ(set.getInt(4));
					obj.setPrice(set.getInt(5));
					obj.setShopkeeper(set.getString(6));
					data.add(obj);
				}
			}
			else {
				ResultSet set=statement.executeQuery("select * from product where `Shopkeeper` like '"+name+"' order by `pname`");
				while(set.next()){
				 	product_data obj=new product_data();
					obj.setId(set.getInt(1));
					obj.setName(set.getString(2));
					obj.setDesc(set.getString(3));
					obj.setQ(set.getInt(4));
					obj.setPrice(set.getInt(5));
					data.add(obj);
			 	}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return data;
	}
	
//	public List<String> getdistinctcolumnitems(String column){
//		List<String> list = null;
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Projects","root","amaan9876");
//			Statement statement=connection.createStatement();
//				ResultSet set=statement.executeQuery("select distinct "+column+" from product ");
//				 while(set.next()){
//					list.add(set.getString(1));
//				}
//		}catch (Exception e) {
//			System.out.println(e);
//		}
//		return list;
//	}
//	
	
}
