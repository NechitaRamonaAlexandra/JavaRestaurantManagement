package bussinessLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CompositeProduct implements MenuItem,Serializable {
	
	private static final long serialVersionUID = 1L;
	public int price;
	public String name;
	public String description;
	public List<MenuItem> parts = new ArrayList<MenuItem>();
	
	public CompositeProduct(String name, String description, int p)
	{
		this.price = p;
		this.name = name;
		this.description = description;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void addPart(MenuItem m)
	{
		parts.add(m);
	}

	public String getPrice() {
		String p = "" + this.price;
		return p;
	}

	public String getDescript() {
		String d = this.description;
		return d;
	}
	
	public int computePrice()
	{
		int price = 0;
		for(MenuItem c : parts){
			price += c.computePrice();
		}
		price += this.price;
		return price;
	}

	public String toString()
	{
		String result = this.name +"  ";
		result += this.description + "  ";
		result += this.price + "  ";
		result += "CONTAINS : ";
		for(MenuItem m : parts){
			result += m.getName() + ",  ";
		}
		
		return result;
	}
}
