package presentationLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import bussinessLayer.BaseProduct;
import bussinessLayer.CompositeProduct;
import bussinessLayer.MenuItem;
import bussinessLayer.Restaurant;

public class AdministratorWindow extends JFrame implements ActionListener{

	private static final long serialVersionUID = -3894372864614036730L;
	JButton createItem;
	JButton editItem ;
	JButton deleteItem ;
	JTextField itemPrice;
	JTextField itemName;
	JTextArea itemDescription;
	Restaurant r;
	String[][] menuList;
	DefaultTableModel table;
	JTable menu;
	
	public AdministratorWindow(Restaurant res)
	{
		JPanel panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		
		this.r = res;
		String[][] menuList = r.menuTable();
		String[] columnNames = {"Name","Price","Description"};
		table = new DefaultTableModel(menuList,columnNames);
		menu = new JTable();
		menu.setModel(table);
		JScrollPane scroll = new JScrollPane();
		menu.add(scroll);
		menu.setBounds(30, 250, 250, 250);
		panel.add(menu);
		
		itemPrice = new JTextField();
		itemPrice.setBounds(140, 122, 161, 25);
		panel.add(itemPrice);
		itemName = new JTextField();
		itemName.setBounds(140, 10, 161, 25);
		panel.add(itemName);
		itemDescription = new JTextArea();
		itemDescription.setBounds(140, 63, 200,55);
		panel.add(itemDescription);
		
		createItem = new JButton("Create item");
		createItem.setBounds(35, 214, 105, 35);
		panel.add(createItem);
		editItem = new JButton("Edit item");
		editItem.setBounds(162, 214, 105, 35);
		panel.add(editItem);
		deleteItem = new JButton("Delete item");
		deleteItem.setBounds(295, 214, 105, 35);
		panel.add(deleteItem);
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setBounds(21, 10, 55, 15);
		panel.add(nameLabel);
		JLabel descriptLabel = new JLabel("Description");
		descriptLabel.setBounds(21, 63, 69, 15);
		panel.add(descriptLabel);
		JLabel priceLabel = new JLabel("Price");
		priceLabel.setBounds(21, 120, 50, 15);
		panel.add(priceLabel);
		
		createItem.addActionListener(this);
		editItem.addActionListener(this);
		deleteItem.addActionListener(this);

		this.add(panel);
		this.setSize(500, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == createItem) {
			int p = Integer.parseInt(itemPrice.getText());
			String n = itemName.getText();
			String d = itemDescription.getText();
			try {
				r.createMenuItem(p, n, d);
				this.menuList = this.r.menuTable();
				String[] columnNames =  {"Name","Price","Description"};
				this.table = new DefaultTableModel(menuList,columnNames);
				this.menu.setModel(table);
				
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		else if(e.getSource() == editItem) {
			String n = itemName.getText();
			MenuItem m = null;
			MenuItem m2 = null;
			for(MenuItem item : r.menu)
			{
				if(item.getName().equals(n))
				{
					m = new CompositeProduct(item.getName(),item.getDescript(),Integer.parseInt(item.getPrice()));
					m2 = new BaseProduct(Integer.parseInt(item.getPrice()),item.getName(),item.getDescript());
				}
			}
			if(itemPrice.getText() != "") {
				r.editMenuItem(m,Integer.parseInt(itemPrice.getText()));
				r.editMenuItem(m2,Integer.parseInt(itemPrice.getText()));
			}
			if(itemDescription.getText() != null)
			{
				r.editMenuItem(m, itemDescription.getText());
				r.editMenuItem(m2, itemDescription.getText());
			}

			this.menuList = this.r.menuTable();
			String[] columnNames =  {"Name","Price","Description"};
			this.table = new DefaultTableModel(menuList,columnNames);
			this.menu.setModel(table);
		}
		else if(e.getSource() == deleteItem) {
			int p = Integer.parseInt(itemPrice.getText());
			String n = itemName.getText();
			String d = itemDescription.getText();
			MenuItem m = new CompositeProduct(n,d,p);
			MenuItem m2 = new BaseProduct(p,n,d);
			r.deleteMenuItem(m2);
			r.deleteMenuItem(m);
			this.menuList = this.r.menuTable();
			String[] columnNames =  {"Name","Price","Description"};
			this.table = new DefaultTableModel(menuList,columnNames);
			this.menu.setModel(table);
			for(MenuItem item : r.menu) {
				if(item.getClass().toString().equals("class bussinessLayer.CompositeProduct")) {
					//System.out.println(((CompositeProduct)item).name +((CompositeProduct)item).description) ;
				}
				else if(item.getClass().toString().equals("class bussinessLayer.BaseProduct")) {
					//System.out.println(((BaseProduct)item).name +((BaseProduct)item).description) ;

				}
			}
		}
		
	}
	

}
