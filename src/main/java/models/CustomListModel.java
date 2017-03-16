package models;

import java.util.List;

import javax.swing.AbstractListModel;

public class CustomListModel<T> extends AbstractListModel<T> {

	private List<T> items;

	public CustomListModel(List<T> items) {
		super();
		this.items = items;
	}

	public void update(List<T> items) {
		this.items = items;
	}

	@Override
	public T getElementAt(int index) {

		return items.get(index);
	}

	@Override
	public int getSize() {
		return items.size();
	}

}
