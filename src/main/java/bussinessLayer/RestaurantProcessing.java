package bussinessLayer;

import java.io.IOException;
import java.util.List;

public interface RestaurantProcessing {
	
	public void createMenuItem(int pric, String name, String descript) throws ClassNotFoundException, IOException;
	public void deleteMenuItem(MenuItem m);
	public void editMenuItem(MenuItem m,int newPrice);
	public void editMenuItem(MenuItem m,String newDescription);
	
	
	public Order createOrder(List<MenuItem> clientOrder);
	public int totalDue(Order o);
	public void generateBill(Order o);
	
	

}
