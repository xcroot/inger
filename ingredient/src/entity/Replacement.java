package entity;

public class Replacement {
	private String purchaseId;
	private String materialId;
	private String materialName;
	private String sort;
	private String materialSpec;
	private String unit;
	private int num;
	private String materialProvider;
	private String equipmentUse;
	private String reason;
	private String opdate;
	private String deptNname;
	private String worktypeName;
	private String operator;
	private String workTypeBelong;
	private String remark;
	private String status;//非数据库字段
	
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getMaterialSpec() {
		return materialSpec;
	}
	public void setMaterialSpec(String materialSpec) {
		this.materialSpec = materialSpec;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getMaterialProvider() {
		return materialProvider;
	}
	public void setMaterialProvider(String materialProvider) {
		this.materialProvider = materialProvider;
	}
	public String getEquipmentUse() {
		return equipmentUse;
	}
	public void setEquipmentUse(String equipmentUse) {
		this.equipmentUse = equipmentUse;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getOpdate() {
		return opdate;
	}
	public void setOpdate(String opdate) {
		this.opdate = opdate;
	}
	public String getDeptNname() {
		return deptNname;
	}
	public void setDeptNname(String deptNname) {
		this.deptNname = deptNname;
	}
	public String getWorktypeName() {
		return worktypeName;
	}
	public void setWorktypeName(String worktypeName) {
		this.worktypeName = worktypeName;
	}
	
	public String getWorkTypeBelong() {
		return workTypeBelong;
	}
	public void setWorkTypeBelong(String workTypeBelong) {
		this.workTypeBelong = workTypeBelong;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
