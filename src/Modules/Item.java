package Modules;

public class Item {
	String Id;
	String itemName;
	float price;
	int stock;
	public Item(){
	}
	
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public void setId(String Id) {
		this.Id = Id;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getId() {
		return this.Id;
	}
	public String getItemName() {
		return this.itemName;
	}
	public float getPrice() {
		return this.price;
	}
	@Override
	public String toString() {
		return this.Id+"\t\t"+this.itemName+"\t\t\t\t"+this.price;
	}
	
}
