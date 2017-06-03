package productListGenerator;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class ReadCSV {
	static List<ProductDetails> productInfoList;
	public static HashMap readCSV() throws IOException{
		int csvIterator=0;
		ProductDetails productDetails;
		HashMap<Integer,HashMap<Integer,Double>> productCodeList=new HashMap<Integer,HashMap<Integer,Double>>();
		productInfoList=new ArrayList<ProductDetails>();
		String fileLocation=new File(".").getCanonicalPath()+"\\resources\\products.csv";
		Reader csvReader = new FileReader(fileLocation);
		Iterable<CSVRecord> recordsIterator = CSVFormat.EXCEL.parse(csvReader);
		
		for (CSVRecord record : recordsIterator) {	   
			if(csvIterator==0){ //to neglect the first heading row
				csvIterator++;
			}
			else{
				productDetails=new ProductDetails(Integer.parseInt(record.get(0)),record.get(1),record.get(2),Integer.parseInt(record.get(3)),record.get(4),Double.parseDouble(record.get(5)),Double.parseDouble(record.get(6)));
				productInfoList.add(productDetails);
				csvIterator++;
				int productCode=Integer.parseInt(record.get(3));
				Double price=Double.parseDouble(record.get(6));
				String unit = record.get(4);
				Double weight = Double.parseDouble(record.get(5));
				
				//calculates per Kg price in double and formats up to two decimal places
				Double productPrice = decimalFormatter(getPerKgPrice(unit, weight, price));
				
				int serialNo=Integer.parseInt(record.get(0));
				
				//Inserts a new entry in HashMap (productCodeList) if product code is not present in HashMap
				if(productCodeList.get(productCode)== null){
					HashMap<Integer,Double> temp=new HashMap<>();
					temp.put(serialNo, productPrice);
					productCodeList.put(productCode,temp);
				} else { //Adds to existing product code in HashMap (productCodeList) if product code is already present
					HashMap<Integer,Double> dataField=productCodeList.get(productCode);
					HashMap<Integer,Double> temp=new HashMap<>();
					temp.put(serialNo, productPrice);
					dataField.putAll(temp);
					productCodeList.put(productCode, dataField);
				}
			}
		}
		return productCodeList;
	}
	
	//per Kg price calculator
	public static Double getPerKgPrice( String unit, Double weight, Double price){
		weight = unit.toLowerCase().equals("grams") ? (weight / 1000) : weight;
		return (price / weight);
	}
	
	//decimal number formatter up to two places after decimal
	public static Double decimalFormatter (Double number) {
		DecimalFormat df = new DecimalFormat("#.00");
		return Double.parseDouble(df.format(number)) ;
	}
}
