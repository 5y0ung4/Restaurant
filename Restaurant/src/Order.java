
public class Order extends Menu{
	private int count;
	
	Order(String name, int count, Restaurant r){
		super(name);
		Menu menus[] = r.getMenus();
		for(Menu m : menus) {
			if((m.getName()).equals(name)) {
				this.setPrice(m.getPrice());
				break;
			}
		}
		this.count = count;
	}
	
	void setCount(int count) {
		this.count = count;
	}
	
	int getCount() {
		return count;
	}
}
