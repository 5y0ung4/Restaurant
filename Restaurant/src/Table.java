
public class Table {
	private String id;
	private int capacity;
	private boolean available;
	private Order orders[] = new Order[30];
	
	private int orderAdded = 0;
	
	public String getId() {
		return id;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public boolean getAvailable() {
		return available;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	public void addOrder(Order o) {
		for (int i = 0; i < orderAdded; i++) {
			if(o.getName() == orders[i].getName()) {
				orders[i].setCount(orders[i].getCount() + o.getCount());
				return;
			}
		}
		orders[orderAdded] = o;
		orderAdded++;
	}
	
	public Order[] getOrders() {
		return orders;
	}
	
	Table(String id){
		this.id = id;
	}
	
	Table(String id, int capacity, boolean available){
		this.id = id;
		this.capacity = capacity;
		this.available = available;
	}
	
	boolean equals (Table t) {
		if(this.id == t.id) {
			return true;
		} 
		return false;
	}
	
	public int checkOut() {
		int i = 0,j = 0;
		for(Order o : orders) {
			if(j >= orderAdded) {
				break;
			}
			i += o.getPrice() * o.getCount();
			j++;
		}
		orderAdded = 0;
		orders = new Order[30];
		return(i);
	}
}
