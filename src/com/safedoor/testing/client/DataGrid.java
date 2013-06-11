package com.safedoor.testing.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

/**
 * A grid to hold large amounts of records
 * 
 * @author eliot.zuidhof
 *
 */
public class DataGrid implements IsWidget {

	public static int RECORDS = 400;
	
	//Data provider interface
	interface Properties extends PropertyAccess<Data>{
		ValueProvider<Data, String> name();
		ValueProvider<Data, String> symbol();
		ValueProvider<Data, String> date();
		ValueProvider<Data, String> notes();
		ValueProvider<Data, Integer> id();
		ValueProvider<Data, Double> price();
		ValueProvider<Data, Double> change();
		ValueProvider<Data, Boolean> available();
		ValueProvider<Data, Boolean> checked();
		ValueProvider<Data, Boolean> compliant();
		@Path("name")
		ModelKeyProvider<Data> key();
	}
	
	/**
	 * Creates the grid widget
	 */
	@Override
	public Widget asWidget() {
		//create the property access
		Properties properties = GWT.create(Properties.class);
		
		//Create colum configurations
		ColumnConfig<Data, String> nameCol = new ColumnConfig<Data, String>(properties.name(),60,"Name");
		ColumnConfig<Data, String> symbolCol = new ColumnConfig<Data, String>(properties.symbol(),50,"Symbol");
		ColumnConfig<Data, String> dateCol = new ColumnConfig<Data, String>(properties.date(),150,"Date");
		ColumnConfig<Data, String> notesCol = new ColumnConfig<Data, String>(properties.notes(),50,"Notes");
		ColumnConfig<Data, Integer> idCol = new ColumnConfig<Data, Integer>(properties.id(),30,"Id");
		ColumnConfig<Data, Double> priceCol = new ColumnConfig<Data, Double>(properties.price(),50,"Price");
		ColumnConfig<Data, Double> changeCol = new ColumnConfig<Data, Double>(properties.change(),50,"Change");
		ColumnConfig<Data, Boolean> availableCol = new ColumnConfig<Data, Boolean>(properties.available(),60,"Available");
		ColumnConfig<Data, Boolean> checkedCol = new ColumnConfig<Data, Boolean>(properties.checked(),50,"Checked");
		ColumnConfig<Data, Boolean> compliantCol = new ColumnConfig<Data, Boolean>(properties.compliant(),60,"Compliant");
		
		//Add column config data to a list
		List<ColumnConfig<Data,?>> list = new ArrayList<ColumnConfig<Data,?>>();
		list.add(nameCol);
		list.add(symbolCol);
		list.add(dateCol);
		list.add(notesCol);
		list.add(idCol);
		list.add(priceCol);
		list.add(changeCol);
		list.add(availableCol);
		list.add(checkedCol);
		list.add(compliantCol);
		//create a column model and put the column config data into it
		ColumnModel<Data> cm = new ColumnModel<Data>(list);
		
		//create the liststore for the grid data
		ListStore<Data> store = new ListStore<Data>(properties.key());
		store.addAll(new TestData(RECORDS).getData());
		
		Grid<Data> grid = new Grid<Data>(store,cm);
		
		return grid;
	}

}
