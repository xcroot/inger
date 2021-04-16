package bean;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private List<CartItem> items = new ArrayList<CartItem>();

	public boolean add(CartItem c) {
		for (int i = 0; i < items.size(); i++) {
			CartItem item = items.get(i);
			if (c.getC().getId() == item.getC().getId()) {
				return false;
			}
		}
		items.add(c);
		return true;
	}

	public List<CartItem> list() {
		return items;
	}

	public double cost() {
		double sum = 0;
		for (int i = 0; i < items.size(); i++) {
			CartItem item = items.get(i);
			sum += item.getC().getPrice() * item.getQty();
		}
		return sum;
	}

	public void delete(long id) {
		for (int i = 0; i < items.size(); i++) {
			CartItem item = items.get(i);
			if (item.getC().getId() == id) {
				items.remove(item);
				return;
			}
		}
	}

	public void modify(long id, int qty) {
		for (int i = 0; i < items.size(); i++) {
			CartItem item = items.get(i);
			if (item.getC().getId() == id) {
				item.setQty(qty);
				return;
			}
		}
	}
	
	public void clear(){
		items.clear();
	}
	
	
}
