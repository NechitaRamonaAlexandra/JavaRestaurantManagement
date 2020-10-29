package pt.hw4;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class FileWriter {
	//public PrintWriter print;
	
	public void printBill(int amount, int orderNo, String waiterName)
	{
		try {
			PrintWriter writer = new PrintWriter("billNo" + orderNo +".txt","UTF-8");
			writer.println("Thank you for ordering from us!!!");
			writer.println("Server: " + waiterName);
			writer.println("Order: " + orderNo);
			writer.println("TOTAL DUE :          " + amount);
			writer.println("We hope we can see you again soon!!!");
			writer.println("HAVE A NICE DAY U+1F600");
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
}
