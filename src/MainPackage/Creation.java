package MainPackage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import Modules.*;
public class Creation {
	public static final Scanner sn = new Scanner(System.in);
	public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static int bno = 100, cno = 100, ino = 100;
	
	public Item createItem() throws Exception {
		Item item = new Item();
		// setting item id by default
		item.setId("I"+ino);
		ino++;
		
		print("\nEnter Item Name : ");
		item.setItemName(br.readLine());
		
		print("\nEnter Item Price : ");
		item.setPrice(sn.nextFloat());
		
		print("\nEnter stock count : ");
		int sCount = sn.nextInt();
		item.setStock(sCount);
		
		print("\n"+item); // printing item
		return item;
	}
	
	public Customer createCustomer() throws Exception {
		Customer customer = new Customer();
		// setting customer id by default
		customer.setCid("C"+cno);
		cno++;
		
		print("\nEnter Customer Name : ");
		customer.setCname(br.readLine());
		
		print("\nEnter Customer Phone Number : ");
		customer.setPhno(br.readLine());
		print("Customer ID\t\tCustomer Name\t\t\tCustomer Phonenumber\n"+
				"***********\t\t*************\t\t\t********************\n");
		print("\n"+customer); // printing customer
		return customer;
	}
	
	public Bill createBill(Item[] items, int n, Customer customer) throws Exception {
		int nBillItems = 0;
		float totalAmount = 0.0f;
		BillItem[] billItems = new BillItem[100];
		Bill bill = new Bill();
		
		// setting bill id
		bill.setBid("B"+bno);
		bno++;
		
		//getting date
		print("\nEnter the date (dd-mm-yyyy) : ");
		String date = br.readLine();
		bill.setDate(date);
		
		//getting number of items
		print("\nenter the number of items : ");
		int no = sn.nextInt();
		for(int i=0; i<no; i++) {
			print("\nEnter the item id : ");
			String id = br.readLine();
			Item tempItem = getItem(id, items, n);
			if(tempItem!=null) {
				BillItem billItem = new BillItem();
				
				//setting item properties to bill item
				billItem.setItem(tempItem);
				billItem.setPrice(tempItem.getPrice());
				print("\nEnter quantity : ");
				int q = sn.nextInt();
				if(q>tempItem.getStock()) { // checking stock count
					print("\n only "+tempItem.getStock()+" is avaliable. want to buy (y/n) ");
					char ch = sn.next().charAt(0);
					if(ch=='Y'||ch=='y') {
						q = tempItem.getStock(); // setting remaining stock count to q
						tempItem.setStock(0); // setting stock count to zero
					}else {
						continue;
					}
				}else {
					tempItem.setStock(tempItem.getStock()-q);
				}
				// calculating total amount
				print("\nItem price : "+tempItem.getPrice()+". want to change item price (y/n) ");
				char ch = sn.next().charAt(0);
				if(ch=='y'||ch=='Y') {
					print("\nEnter item price : ");
					float amt = sn.nextFloat();
					totalAmount += (q*amt);
				}else {
					totalAmount += (q*tempItem.getPrice());
				}
				billItem.setQuantity(q);
				billItems[nBillItems++] = billItem;
			}else {
				print("\nItem not found or out of stock");
//				return;
			}
		}
		// setting customer's total sale amount
		if(nBillItems!=0) {
			customer.setTotalSalesAmount(customer.getTotalSalesAmount()+totalAmount);
			
			bill.setBillItemCount(nBillItems);
			bill.setBillItems(billItems);
			bill.setTotalAmount(totalAmount);
			bill.setCustomer(customer);
			
			print("\n"+bill); // printing final bill
			return bill;
		}else {
			return null;
		}
	}
	public Item getItem(String id, Item[] items, int n) { // check
		for(int i=0; i<n; i++) {
			if(items[i].getId().equals(id)) {
				if(items[i]!=null&&items[i].getStock()!=0) {					
					return items[i];
				}
			}
		}
		return null;
	}
	
	public static int Int() {
		return sn.nextInt();
	}
	public static void print(String ss) {
		System.out.print(ss);
	}
}
