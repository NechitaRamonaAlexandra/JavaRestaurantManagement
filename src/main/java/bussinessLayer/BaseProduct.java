package bussinessLayer;

import java.io.Serializable;

public class BaseProduct implements MenuItem,Serializable {

	private static final long serialVersionUID = 1L;
	public int price;
	public String name;
	public String description;
	
	public BaseProduct(int p, String n, String d)
	{
		this.price = p;
		this.name = n;
		this.description = d;
	}
	
	public String getName() {
		return this.name;
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
		return this.price;
	}

	public String toString()
	{
		String result;
		result = this.name + "  ";
		result += this.description + "  ";
		result += this.price + "  ";
		
		return result;
	}

}
