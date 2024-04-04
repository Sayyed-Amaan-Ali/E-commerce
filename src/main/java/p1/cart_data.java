package p1;

public class cart_data {
	String name;
	int pid,quantity,total_amt,Username;
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getUsername() {
		return Username;
	}
	public void setUsername(int username) {
		Username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getTotal_amt() {
		return total_amt;
	}
	public void setTotal_amt(int total_amt) {
		this.total_amt = total_amt;
	}
}	
