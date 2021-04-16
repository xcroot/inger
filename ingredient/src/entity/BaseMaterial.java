package entity;

public class BaseMaterial {

	private String material_id;
	private String material_type;
	private String material_name;
	private String material_spec;
	private String uint;
	private double price;
	private String material_attr;
	private String dept;
	private String remark;
	private String operator;
	private String opdate;
	private String filename;
	
	
	public String getMaterial_id() {
		return material_id;
	}
	public BaseMaterial() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BaseMaterial(String materialId, String materialType,
			String materialName, String materialSpec, String uint,
			double price, String materialattr, String dept, String remark,
			String operator, String opdate, String filename) {
		super();
		material_id = materialId;
		material_type = materialType;
		material_name = materialName;
		material_spec = materialSpec;
		this.uint = uint;
		this.price = price;
		material_attr = materialattr;
		this.dept = dept;
		this.remark = remark;
		this.operator = operator;
		this.opdate = opdate;
		this.filename = filename;
	}
	public void setMaterial_id(String materialId) {
		material_id = materialId;
	}
	public String getMaterial_type() {
		return material_type;
	}
	public void setMaterial_type(String materialType) {
		material_type = materialType;
	}
	public String getMaterial_name() {
		return material_name;
	}
	public void setMaterial_name(String materialName) {
		material_name = materialName;
	}
	public String getMaterial_spec() {
		return material_spec;
	}
	public void setMaterial_spec(String materialSpec) {
		material_spec = materialSpec;
	}
	public String getUint() {
		return uint;
	}
	public void setUint(String uint) {
		this.uint = uint;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getMaterial_attr() {
		return material_attr;
	}
	public void setMaterial_attr(String materialattr) {
		material_attr = materialattr;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getOpdate() {
		return opdate;
	}
	public void setOpdate(String opdate) {
		this.opdate = opdate;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}

}
