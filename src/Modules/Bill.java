package Modules;

public class Bill {
	String bid;
	BillItem[] billItems;
	Customer customer;
	float totalAmount;
	String date;
	int billItemCount;
	public Bill(){
		
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public BillItem[] getBillItems() {
		return billItems;
	}
	public void setBillItems(BillItem[] billItems) {
		this.billItems = billItems;
	}
	
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public int getBillItemCount() {
		return billItemCount;
	}
	public void setBillItemCount(int billItemCount) {
		this.billItemCount = billItemCount;
	}
	@Override
	public String toString() {
		String builder = "";
		builder+=("Bill ID : "+this.bid+"\t\tCustomer ID : "+this.customer.getCid()+"\t\tCustomer Name : "+this.customer.getCname()+"\t\tDate : "+this.date)+("\nItem ID\t\t\tItem Price\t\t\tItem Quantity\n");
		builder+=("*******\t\t\t**********\t\t\t*************\n");
		for(int i=0; i<this.billItemCount; i++) {
			builder+=(billItems[i].getItem().getItemName()+"\t\t\t"+billItems[i].getPrice()+"\t\t\t\t"+billItems[i].quantity+"\t"+(billItems[i].getQuantity()*billItems[i].getPrice())+"\n");
		}
		builder+=("\n*******\t\t\t**********\t\t\t*************\n");
		builder+=("Total Amount : "+this.totalAmount+"\n");
		return builder;
	}
	
	
}
