package bean;

import entity.BaseMaterial;

public class BaseMaterialItem {
	private BaseMaterial material;
	private int qty;
	public BaseMaterial getMaterial() {
		return material;
	}
	public void setMaterial(BaseMaterial material) {
		this.material = material;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
}
