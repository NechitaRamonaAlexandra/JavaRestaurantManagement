package bussinessLayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import presentationLayer.AdministratorWindow;
import presentationLayer.ChefWindow;
import presentationLayer.WaiterWindow;
import pt.hw4.FileWriter;
import pt.hw4.RestaurantSerialization;

public class Restaurant extends Observable implements RestaurantProcessing{

	public List<MenuItem> menu = new ArrayList<MenuItem>();
	public Map<Order,List<MenuItem>> orders = new HashMap<Order,List<MenuItem>>();
	private int orderNumber;
	ChefWindow theChef;
	
	public Restaurant(){
		this.orderNumber = 0;
		theChef = new ChefWindow(this.orders);
		try {
			this.menu = RestaurantSerialization.getMenu();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addOrder(Order newOrder, List<MenuItem> chosen)
	{
		orders.put(newOrder, chosen);
	}
	
	public String[][] menuTable(){
		String[][] tableData = new String[20][3] ;
		int i = 0;
		for(MenuItem m : menu) {
			tableData[i][0] = m.getName();
			tableData[i][1] = m.getPrice();
			tableData[i][2] = m.getDescript();
			i++;
		}
		return tableData;
	}
	
	public void createMenuItem(int pric, String name, String descript) throws ClassNotFoundException, IOException {
		MenuItem newItem;
		newItem = new CompositeProduct(name,descript,pric);
			
		menu.add(newItem);
		RestaurantSerialization.addToMenu(newItem);
	}

	public void deleteMenuItem(MenuItem m) {
		for(Iterator<MenuItem> iter = menu.iterator(); iter.hasNext();){
			MenuItem item = iter.next();
			if(item.getClass().toString().equals("class bussinessLayer.BaseProduct") && m.getClass().toString().equals("class bussinessLayer.BaseProduct")){
				if(((BaseProduct)item).price == ((BaseProduct)m).price && ((BaseProduct)item).name.equals(((BaseProduct)m).name) && ((BaseProduct)item).description.equals(((BaseProduct)m).description)){
					iter.remove();
				}
			}
			else if(item.getClass().toString().equals("class bussinessLayer.CompositeProduct") && m.getClass().toString().equals("class bussinessLayer.CompositeProduct")){
				if(((CompositeProduct)item).price == ((CompositeProduct)m).price && ((CompositeProduct)item).name.equals(((CompositeProduct)m).name) && ((CompositeProduct)item).description.equals(((CompositeProduct)m).description)){
					iter.remove();
				}
			}
		}
	}

	public void editMenuItem(MenuItem m, int newPrice) {
		for(Iterator<MenuItem> iter = menu.iterator(); iter.hasNext();){
			MenuItem item = iter.next();
			if(item.getClass().toString().equals("class bussinessLayer.BaseProduct") && m.getClass().toString().equals("class bussinessLayer.BaseProduct")){
				if(((BaseProduct)item).price == ((BaseProduct)m).price && ((BaseProduct)item).name.equals(((BaseProduct)m).name) && ((BaseProduct)item).description.equals(((BaseProduct)m).description)){
					((BaseProduct) item).price = newPrice;
				}
			}
			else if(item.getClass().toString().equals("class bussinessLayer.CompositeProduct") && m.getClass().toString().equals("class bussinessLayer.CompositeProduct")){
				if(((CompositeProduct)item).price == ((CompositeProduct)m).price && ((CompositeProduct)item).name.equals(((CompositeProduct)m).name) && ((CompositeProduct)item).description.equals(((CompositeProduct)m).description)){
					((CompositeProduct)item).price = newPrice;
				}
			}
		}
	}
	
	public void editMenuItem(MenuItem m, String newDescription) {
		for(Iterator<MenuItem> iter = menu.iterator(); iter.hasNext();){
			MenuItem item = iter.next();
			if(item.getClass().toString().equals("class bussinessLayer.BaseProduct") && m.getClass().toString().equals("class bussinessLayer.BaseProduct"))
			{
				if(((BaseProduct)item).price == ((BaseProduct)m).price && ((BaseProduct)item).name.equals(((BaseProduct)m).name) && ((BaseProduct)item).description.equals(((BaseProduct)m).description))
				{
					((BaseProduct) item).description =newDescription;
				}
			}
			else if(item.getClass().toString().equals("class bussinessLayer.CompositeProduct") && m.getClass().toString().equals("class bussinessLayer.CompositeProduct"))
			{
				if(((CompositeProduct)item).price == ((CompositeProduct)m).price && ((CompositeProduct)item).name.equals(((CompositeProduct)m).name) && ((CompositeProduct)item).description.equals(((CompositeProduct)m).description))
				{
					((CompositeProduct)item).description = newDescription;
				}
			}
		}
	}

	public Order createOrder(List<MenuItem> clientOrder) {
		orderNumber++;
		Order newOrder = new Order(orderNumber,4);
		orders.put(newOrder, clientOrder);
		this.theChef.update(this, null);
		return newOrder;
	}

	public int totalDue(Order o) {
		int total = 0;
		//for(Map.Entry<Order, List<MenuItem>> entry : orders.entrySet()){
			//System.out.println(entry.getKey());
			//if(entry.getKey().orderID == o.orderID){
		List<MenuItem> values = this.orders.get(o);
				for(MenuItem item : values){
					total += item.computePrice();
				}
			//}
		//}
		return total;
	}

	public void generateBill(Order o) {
		FileWriter writer = new FileWriter();
		writer.printBill(this.totalDue(o), o.orderID, "Ion Lol");
	}

	public static void main(String[] args)
	{
		Restaurant r = new Restaurant();
		List<MenuItem> anOrder = new ArrayList<MenuItem>();
		MenuItem apa = new BaseProduct(5,"apa plata","apa Bucovina 500 ml");
		MenuItem espr = new BaseProduct(5,"espresso","esspresso arabica 40 ml");
		MenuItem am = new CompositeProduct("americano","espresso arabica(40ml) si apa(100ml)",6);
		MenuItem pui = new BaseProduct(12,"pui","pui la cuptor");
		MenuItem supa = new BaseProduct(5,"supa legume","supa simpla,morcovi,ardei,telina");
		MenuItem supaPui = new CompositeProduct("Supa Pui","Supa traditionala de pui si legume",17);
		((CompositeProduct)am).addPart(apa);
		((CompositeProduct)am).addPart(espr);
		anOrder.add(espr);
		anOrder.add(supaPui);
		r.createOrder(anOrder);
		
		r.menu.add(apa);
		r.menu.add(espr);
		r.menu.add(am);
		r.menu.add(pui);
		r.menu.add(supa);
		r.menu.add(supaPui);
		
		AdministratorWindow ad = new AdministratorWindow(r);
		WaiterWindow w = new WaiterWindow(r);
		
	}

}
