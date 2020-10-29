package bussinessLayer;

import java.util.Date;
import java.util.Objects;

public class Order {
	public int orderID;
	public Date today;
	public int table;
	
	public Order(int id, int t)
	{
		this.orderID = id;
		this.today = new Date();
		this.table = t;
	}
	
	public int hashCode(Order o)
	{	
		if(o == null)
			return 0;
		int hash = Objects.hash(o.hashCode());
		
		return hash;
	}

	public boolean onSameDay(Order o)
	{
		if(this.today.compareTo(o.today) == 0)
			return true;
		
		return false;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(this.orderID != ((Order)o).orderID)// && this.table == o.table && this.onSameDay(o))
			return false;
		return true;
	}
}
