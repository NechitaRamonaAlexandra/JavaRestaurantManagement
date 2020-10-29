package bussinessLayer;

import java.io.Serializable;

public interface MenuItem {
	
	public int computePrice();
	
	public String toString();
	public String getName();
	public String getPrice();
	public String getDescript();
}
