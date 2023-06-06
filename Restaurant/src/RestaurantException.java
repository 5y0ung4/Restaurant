
public class RestaurantException extends Exception{
	private String value;
	
	public RestaurantException(String message, String name){
		super(message);
		this.value = name;
	}
	
	public String getValue() {
		return value;
	}
}
