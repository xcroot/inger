package entity;

public class Computer {
	private long id;
	private String model;
	private String picName;
	private String prodDesc;
	private double price;
	public Computer(String model, String picName,
			String prodDesc, double price) {
		super();
		this.model = model;
		this.picName = picName;
		this.prodDesc = prodDesc;
		this.price = price;
	}
	public Computer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getProdDesc() {
		return prodDesc;
	}
	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}
	
}
