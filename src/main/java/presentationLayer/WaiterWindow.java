package presentationLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import bussinessLayer.MenuItem;
import bussinessLayer.Order;
import bussinessLayer.Restaurant;

public class WaiterWindow extends JFrame implements ActionListener{

	private static final long serialVersionUID = -3937941201199378712L;

	JButton createOrder = new JButton("Create order");
	JButton computePrice = new JButton("Compute Price");
	JButton generateBill = new JButton("Generate Bill");
	JTable orders ;
	JList<String> menuSelect;
	Restaurant r;
	DefaultTableModel table;
	JTextField totalPrice;
	
	public WaiterWindow(Restaurant res)
	{
		this.r  = res;
		
		String[] combMenu = new String[20];
		for(int i = 0; i < r.menu.size(); i++) {
			combMenu[i] = r.menu.get(i).toString();
		}
		this.menuSelect = new JList<String>(combMenu);
		this.menuSelect.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		menuSelect.setBounds(380,50,200,200);
		
		JPanel panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		
		
		totalPrice = new JTextField();
		totalPrice.setEditable(false);
		totalPrice.setBounds(378, 244, 76, 21);
		
		createOrder.setBounds(48, 294, 110, 25);
		createOrder.addActionListener(this);
		generateBill.setBounds(206, 294, 110, 25);
		generateBill.addActionListener(this);
		computePrice.setBounds(345, 294, 120, 25);
		computePrice.addActionListener(this);
		
		String[] columnNames = {"Order","ItemNo"};
		orders = new JTable();
		JScrollPane scroll = new JScrollPane();
		String[][] orderTable = new String[15][2];
		int i = 0;
		for(Map.Entry<Order, List<MenuItem>> entry : r.orders.entrySet()) {
			orderTable[i][0] = "Order" + entry.getKey().orderID;
			orderTable[i][1] = "Items: " + (entry.getValue().size() -1);
			i++;
		}
		table = new DefaultTableModel(orderTable,columnNames);
		orders.add(scroll);
		orders.setModel(table);
		orders.setBounds(20, 30, 300, 230);
		
		panel.add(totalPrice);
		panel.add(createOrder);
		panel.add(computePrice);
		panel.add(generateBill);
		panel.add(orders);
		panel.add(menuSelect);
		
		this.add(panel);
		this.setSize(600, 381);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == createOrder) {
			
			List<MenuItem> ord = new ArrayList<MenuItem>();
			for(int i : menuSelect.getSelectedIndices()) {
				ord.add(r.menu.get(i));
			}
			r.createOrder(ord);
			String[] columnNames = {"Order","ItemNo"};
			//orders = new JTable();
			String[][] orderTable = new String[15][2];
			int i = 0;
			for(Map.Entry<Order, List<MenuItem>> entry : r.orders.entrySet()) {
				orderTable[i][0] = "Order" + entry.getKey().orderID;
				orderTable[i][1] = "Items: " + (entry.getValue().size());
				i++;
			}
			table = new DefaultTableModel(orderTable,columnNames);
			orders.setModel(table);
			
		}
		else if(e.getSource() == computePrice) {
			char ord = ((String)this.orders.getValueAt(this.orders.getSelectedRow(),0)).charAt(5);
			for(Map.Entry<Order, List<MenuItem>> entry : r.orders.entrySet()) {
				if(entry.getKey().orderID == Character.getNumericValue(ord)) {
					String total = Integer.toString(this.r.totalDue(entry.getKey()));
					this.totalPrice.setText(total);
					break;
				}
			}
		
		}
		else if(e.getSource() == generateBill) {
			char ord = ((String)this.orders.getValueAt(this.orders.getSelectedRow(),0)).charAt(5);
			for(Map.Entry<Order, List<MenuItem>> entry : r.orders.entrySet()) {
				if(entry.getKey().orderID == Character.getNumericValue(ord)) {
					this.r.generateBill(entry.getKey());
					break;
				}
			}
		
		}
	}
}
