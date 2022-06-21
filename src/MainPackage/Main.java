package MainPackage;
import java.io.*;
import java.util.*;
import Modules.Item;
import Modules.Customer;
import Modules.Bill;
public class Main {
	public static int menus() {
		print("\n\n*********WELCOME TO ZOHOMARKET**********");
		print("\n1.Item Creation");
		print("\n2.Customer Creation");
		print("\n3.Invoice / Bill Creation");
		print("\n4.Bill List");
		print("\n5.Bill based on customer");
		print("\n6.Item List");
		print("\n7.Customer List");
		print("\n8.Bill details by bill no");
		print("\n9.Total sales to a customer");
		print("\n10.Exit");
		print("\n11. item price update");
		int ch = Int(); // getting choice from user based on above menus
		return ch;
	}
	
	//displaying bills
	public void showBillList(Bill[] bills, int n) {
		for(int i=0; i<n; i++) {
			print("\n"+bills[i]);
		}
	}
	
	// displaying items
	public void showItemList(Item[] items, int n) {
		print("\nItem ID\t\tItem Name\t\t\tItem Price");
		print("\n*******\t\t*********\t\t\t**********\n");
		for(int i=0; i<n; i++) {
			print("\n"+items[i]);
			if(items[i].getStock()==0) {
				print("\tout of stock! ");
			}else {
				print("\t"+items[i].getStock());
			}
		}
	}
	
	// displaying customers
	public void showCustomerList(Customer[] customers, int n) {
//		for(int i=0; i<n; i++) {
//			for(int j=i+1; j<n; j++) {
//				if(customers[i].getTotalSalesAmount()<customers[i].getTotalSalesAmount()) {
//					Customer temp = customers[i];
//					customers[i] = customers[j];
//					customers[j] = temp;
//				}
//			}
//		}
		print("\nCustomer ID\tCustomer Name\t\tCustomer Phonenumber");
		print("\n***********\t*************\t\t********************");
		for(int i=0; i<n; i++) {
			print("\n"+customers[i]);
		}
	}
	
	// check customer which exist or not
	public boolean checkCustomer(String cid, Customer[] customers, int n) {
		for(int i=0; i<n; i++) {
			if(cid.equals(customers[i].getCid())) {
				return true;
			}
		}
		return false;
	}
	
	// getting customer details
	public Customer getCustomer(String cid, Customer[] customers, int n) {
		for(int i=0; i<n; i++) {
			if(cid.equals(customers[i].getCid())) {
				return customers[i];
			}
		}
		return null;
	}
	
	public static final Scanner sn = new Scanner(System.in);
	public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		Main main = new Main();
		//
		Item[] items = new Item[100];
		Bill[] bills = new Bill[100];
		Customer[] customers = new Customer[100];
		int nItems=0, nBills=0, nCustomers=0, i=0;
		//
		Creation creation = new Creation();
		while(true) {
			int ch = menus();
			switch(ch) {
				case 1: // create item
					items[nItems++] = creation.createItem(); // append item instance after item is created
					print("\nItem Created Successfully");
					break;
					
				case 2: // create customer
					customers[nCustomers++] = creation.createCustomer(); // append customer instance after customer is created
					print("\nCustomer created Successfully");
					break;
					
				case 3: // create bill
					print("\nEnter the customer ID : ");
					String cid = br.readLine();
					Customer customer = main.getCustomer(cid, customers, nCustomers);
					// get customer instance by given customer id
					// customer instance will be passed to creatBill()
					// set customer instance to bill as a field during bill creation
					// beacuse when displaying bills we should also print customer details
					if(customer!=null) {
						main.showItemList(items, nItems);
						Bill bill = creation.createBill(items, nItems, customer);
						if(bill!=null) {							
							bills[nBills++] = bill; // append bill instance after bill is created
						}
					}else {
						// if customer is not found. asking to create a customer 
						print("\nsorry!, customer not found. want to create a customer (y/n)");
						char choice = sn.next().charAt(0);
						if(choice=='y'||choice=='Y') {
							customers[nCustomers++] = creation.createCustomer();
						}
					}
					break;
					
				case 4: // bill list
					if(nBills!=0) {
						main.showBillList(bills, nBills);
					}else {
						print("\nno bills");
					}
					break;
					
				case 5: // bill based on customer
					print("\nEnter Customer ID : ");
					cid = br.readLine();
					if(main.checkCustomer(cid, customers, nCustomers)) {
						for(i=0; i<nBills; i++) {
							if(cid.equals(bills[i].getCustomer().getCid())) { // getting customer id from customer in bills
								print("\n"+bills[i]);
							}
						}
					}else {
						print("\nsorry!, customer not found. Try Again.");
					}
					break;
					
				case 6: // item list
					if(nItems!=0) {
						main.showItemList(items, nItems);
					}else {
						// asking to create new item
						print("\nno items records. want to create a item (y/n) ");
						char choice = sn.next().charAt(0);
						if(choice=='y'||choice=='Y') {
							items[nItems++] = creation.createItem(); // append item instance after item is created
						}
					}
					break;
					
				case 7: // customer list
					if(nCustomers!=0) {
						main.showCustomerList(customers, nCustomers);
					}else {
						// if no customer record found
						// asking to create a new customer record
						print("\nno customers record. want to create a customer (y/n)");
						char choice = sn.next().charAt(0);
						if(choice=='y'||choice=='Y') {
							customers[nCustomers++] = creation.createCustomer();
						}
					}
					break;
					
				case 8: // bill details by bill no
					print("\nEnter bill id : ");
					String bid = br.readLine();
					boolean found = false;
					for(i=0; i<nBills; i++) {
						if(bid.equals(bills[i].getBid())) {
							print("\n"+bills[i]);
							found = true;
							break;
						}
					}
					if(!found) print("\nsorry! bill not found"); 
					break;
					
				case 9: // sales amount to a customer
					print("\nEnter customer ID : ");
					cid = br.readLine();
					// checking the given customer is exist or not
					if(main.checkCustomer(cid, customers, nCustomers)) {
						float totalSalesAmount = 0.0f;
						for(i=0; i<nBills; i++) {
							if(cid.equals(bills[i].getCustomer().getCid())) {
								totalSalesAmount += bills[i].getTotalAmount();
							}
						}
						print("\n Total sales amount : "+totalSalesAmount);
					}else {
						print("\nsorry!, customer not found. Try Again");
					}
					break;
					
				case 10:
					print("************THANK YOU**************");
					System.exit(0);
					break;
				case 11:
					print("\nenter item id : ");
					String id = sn.next();
					for(int j=0; j<nItems; j++) {
						if(id.equals(items[i].getId())) {
							items[i].setPrice(items[i].getPrice()*3);
							break;
						}
					}
					break;
				default:
					print("\nInvalid option");
			}
		}
	}
	public static int Int() {
		return sn.nextInt();
	}
	public static void print(String ss) {
		System.out.print(ss);
	}
}
