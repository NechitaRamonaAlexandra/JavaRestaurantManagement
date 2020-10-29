package pt.hw4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import bussinessLayer.MenuItem;

public class RestaurantSerialization implements Serializable {

	private static final long serialVersionUID = 1L;

	public static void addToMenu(MenuItem m2) throws IOException,ClassNotFoundException{
		FileOutputStream f = new FileOutputStream(new File("menu.txt"));
		ObjectOutputStream o = new ObjectOutputStream(f);
		
		o.writeObject(m2);
		
		o.close();
		f.close();
	}
	
	public static List<MenuItem> getMenu() throws IOException,ClassNotFoundException {
		
		FileInputStream fi = new FileInputStream(new File("menu.txt"));
		ObjectInputStream oi = new ObjectInputStream(fi);
		
		List<MenuItem> theMenu = new ArrayList<MenuItem>();
		while(fi.available() > 0) {
			theMenu.add((MenuItem) oi.readObject());
		}
		oi.close();
		fi.close();
		return theMenu;
	}
		
}