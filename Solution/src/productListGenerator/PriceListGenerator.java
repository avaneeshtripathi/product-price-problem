package productListGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PriceListGenerator {
	public static void main(String args[]) throws IOException{
		
		//HashMap to store product list with Product Code and corresponding Id - Price List from CSV File
		HashMap<Integer,HashMap<Integer,Double>> productCodeList = ReadCSV.readCSV();
		
		//Product listing for minimum prices
		System.out.println("Cheapest prices for all products:");
		System.out.println("Id\t"+"Vendor\t\t"+"Product Name\t\t\t"+"Product code\t"+"Unit\t"+"Weight\t"+"Price");
		
		//Iterator to iterate the HashMap for each product code 
		Iterator it = productCodeList.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        
	        //gets the minimum price for the current product code 
	        Double minPrice = Collections.min(productCodeList.get(pair.getKey()).values());
	        
	        //function to get the corresponding Id(s) for minimum price of a product code
	        List<Integer> minPriceIds = minMaxFunction(productCodeList.get(pair.getKey()), minPrice);
	        
	        showList(minPriceIds); //displays the list
	    }
	    
	    //Product listing for maximum prices
	    System.out.println("\n\n\nExpensive prices for all products:");
		System.out.println("Id\t"+"Vendor\t\t"+"Product Name\t\t\t"+"Product code\t"+"Unit\t"+"Weight\t"+"Price");
	    it = productCodeList.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        
	        //gets the maximum price for the current product code
	        Double maxPrice = Collections.max(productCodeList.get(pair.getKey()).values());
	        
	        //function to get the corresponding Id(s) for maximum price of a product code
	        List<Integer> maxPriceIds = minMaxFunction(productCodeList.get(pair.getKey()), maxPrice);
	        showList(maxPriceIds);
	    }
	    
	    //Product listing for minimum prices with given product codes
	    System.out.println("\n\n\nCheapest prices for products [3736, 4356, 3732, 3746, 3759, 3719, 3740, 4341]");
		System.out.println("Id\t"+"Vendor\t\t"+"Product Name\t\t\t"+"Product code\t"+"Unit\t"+"Weight\t"+"Price");
		List<Integer> productIds = new ArrayList<Integer>();
		productIds = Arrays.asList(3736, 4356,3732, 3746, 3759, 3719, 3740, 4341);
		for(int repeatProductId=0;repeatProductId<productIds.size();repeatProductId++){
			Double minPrice = Collections.min(productCodeList.get(productIds.get(repeatProductId)).values());
			List<Integer> minPriceIds = minMaxFunction(productCodeList.get(productIds.get(repeatProductId)), minPrice);
	        showList(minPriceIds);
		}
	}
	
	public static List<Integer> minMaxFunction( HashMap<Integer, Double> productIdList, Double price){
		List<Integer> priceIds = new ArrayList<Integer>();
		
		//Iterator to traverse each id-price pair for a product code
		Iterator it = productIdList.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        
	        //checks if the price is equals to minimum or maximum price and returns the list of Ids for that price
	        if(pair.getValue().toString().equals(price.toString())) {
	        	priceIds.add( (Integer) pair.getKey());
		    }
	    }
	    return priceIds;
	}
	
	public static void showList(List<Integer> productData){
		// displays the output list
		for(int repeat=0;repeat<productData.size();repeat++){
			int id=ReadCSV.productInfoList.get(productData.get(repeat)-1).getId();
			String productName=ReadCSV.productInfoList.get(productData.get(repeat)-1).getProductName();
			String vendorName=ReadCSV.productInfoList.get(productData.get(repeat)-1).getVendorName();
			int productCode=ReadCSV.productInfoList.get(productData.get(repeat)-1).getProductCode();
			String unit=ReadCSV.productInfoList.get(productData.get(repeat)-1).getUnit();
			Double weight=ReadCSV.productInfoList.get(productData.get(repeat)-1).getWeight();
			Double price=ReadCSV.productInfoList.get(productData.get(repeat)-1).getPrice();
			System.out.println(id+"\t"+vendorName+"\t\t"+productName+"\t\t\t"+productCode+"\t\t"+unit+"\t"+weight+"\t"+price);
		}
	}
}
