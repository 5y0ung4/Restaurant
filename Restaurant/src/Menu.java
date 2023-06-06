
public class Menu {
	private String name;
	private int price;
	private int stuck;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public void setStuck(int stuck) {
		this.stuck = stuck;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public int getStuck() {
		return stuck;
	}
	
	Menu(String name, int price, int stuck){
		this.name = name;
		this.price = price;
		this.stuck = stuck;
	}
	
	Menu(String name){
		this.name = name;
	}
	
	private boolean equals (Menu m) {
		if(this.name == m.name) {
			return true;
		} 
		return false;
	}
}
