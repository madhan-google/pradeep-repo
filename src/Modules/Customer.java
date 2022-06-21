package Modules;

public class Customer {
	String cid;
	String cname;
	String phno;
	double totalSalesAmount;
	public Customer(){
		
	}

	public void setCid(String cid) {
		this.cid = cid;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public void setPhno(String phno) {
		this.phno = phno;
	}
	public String getCid() {
		return this.cid;
	}
	public String getCname() {
		return this.cname;
	}
	public String getPhno() {
		return this.phno;
	}
	
	public double getTotalSalesAmount() {
		return totalSalesAmount;
	}

	public void setTotalSalesAmount(double totalSalesAmount) {
		this.totalSalesAmount = totalSalesAmount;
	}

	@Override
	public String toString() {
		return this.cid+"\t\t"+this.cname+"\t\t\t"+this.phno;
	}
	
}
