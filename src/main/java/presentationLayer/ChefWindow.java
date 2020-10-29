package presentationLayer;

import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import bussinessLayer.MenuItem;
import bussinessLayer.Order;
import bussinessLayer.Restaurant;

public class ChefWindow extends JFrame implements Observer{

	private static final long serialVersionUID = -3968871698471533285L;
	private int height = 400, width = 300;
	JList<String> orders;
	JPanel panel;
	
	public ChefWindow(Map<Order,List<MenuItem>> ord)
	{
		this.panel = new JPanel();
		String[] theOrders = new String[20];
		int i = 0;
		for(Map.Entry<Order, List<MenuItem>> entry : ord.entrySet()) {
			theOrders[i] = "Order " + entry.getKey().orderID + " with: ";
			for(MenuItem m: entry.getValue()) {
				theOrders[i] += m.getName() + " + ";
			}
			i++;
		}
		orders = new JList<String>(theOrders);
		
		orders.setAlignmentX(CENTER_ALIGNMENT);
		orders.setAlignmentY(CENTER_ALIGNMENT);
		
		panel.add(orders);
		this.add(panel);
		this.setTitle("Chef");
		this.setSize(width, height);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}


	//@Override
	public void update(Observable o, Object arg) {
		panel.removeAll();
		panel.revalidate();
		System.out.println("aaaaaaaaaaa");
		String[] theOrders = new String[20];
		int i = 0;
		for(Map.Entry<Order, List<MenuItem>> entry : ((Restaurant)o).orders.entrySet()) {
			theOrders[i] = "Order " + entry.getKey().orderID + " with: ";
			for(MenuItem m: entry.getValue()) {
				theOrders[i] += m.getName() + " + ";
			}
			i++;
		}
		orders = new JList<String>(theOrders);
		panel.add(orders);
		panel.repaint();
		panel.revalidate();
	}

}
