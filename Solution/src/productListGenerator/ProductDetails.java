package productListGenerator;

public class ProductDetails {
	
	private int id,productCode;
	private String vendorName,productName,unit;
	private double weight,price;

	public ProductDetails(int id,  String vendorName, String productName,int productCode, String unit, double weight,
			double price) {
		super();
		this.id = id;
		this.productCode = productCode;
		this.vendorName = vendorName;
		this.productName = productName;
		this.unit = unit;
		this.weight = weight;
		this.price = price;
	}

	//get and set methods for all the list heads used as key in HashMap

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProductCode() {
		return productCode;
	}
	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
