package models;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class CustomComboboxModel<T> extends AbstractListModel<T> implements ComboBoxModel<T> {

	private List<T> items;
	private T selectedItem;

	public CustomComboboxModel(List<T> items) {
		this.items = items;
		if (items != null && !items.isEmpty()) {
			selectedItem = items.get(0);
		}
	}

	public void setItems(List<T> items) {
		this.items = items;
		if (items != null && !items.isEmpty()) {
			selectedItem = items.get(0);
		}
	}

	@Override
	public T getElementAt(int index) {
		return items.get(index);
	}

	@Override
	public int getSize() {
		return items.size();
	}

	@Override
	public Object getSelectedItem() {
		return selectedItem;
	}

	@Override
	public void setSelectedItem(Object anItem) {
		selectedItem = (T) anItem;

	}

}
