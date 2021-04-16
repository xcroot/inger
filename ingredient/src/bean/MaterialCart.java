package bean;

import java.util.ArrayList;
import java.util.List;



public class MaterialCart {
	
	private List<BaseMaterialItem> items = new ArrayList<BaseMaterialItem>();
	public List<BaseMaterialItem> list() {
		return items;
	}

	public void setItems(List<BaseMaterialItem> items) {
		this.items = items;
	}

	public boolean add(BaseMaterialItem m){
		for (int i = 0; i < items.size(); i++) {
			BaseMaterialItem item = items.get(i);
			if (m.getMaterial().getMaterial_id().equals(item.getMaterial().getMaterial_id())) {
				return false;
			}
		}
		items.add(m);
		return true;
	}
	
	public double cost() {
		double sum = 0 ;
		for (int i = 0; i < items.size(); i++) {
			BaseMaterialItem item = items.get(i);
			sum += item.getMaterial().getPrice()*item.getQty();
		}
		return sum;
	}
	public void modify(String id, int qty){
		for (int i = 0; i < items.size(); i++) {
			BaseMaterialItem item = items.get(i);
			if (item.getMaterial().getMaterial_id().equals(id)){
				item.setQty(qty);
			}
		}
	}
	public void delete(String id){
		for (int i = 0; i < items.size(); i++) {
			BaseMaterialItem item = items.get(i);
			if(item.getMaterial().getMaterial_id().equals(id)){
				items.remove(item);
				return;
			}
			
		}
	}
	public void clear(){
		items.clear();
	}
}
