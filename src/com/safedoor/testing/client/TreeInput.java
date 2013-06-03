package com.safedoor.testing.client;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * This class allows the user to input new data into the tree
 * 
 * @author eliot.zuidhof
 *
 */
public class TreeInput implements IsWidget {

	private TextField text;
	
	public void addCustomer(String name){
		
	}
	
	@Override
	public Widget asWidget() {
		HorizontalLayoutContainer hlc = new HorizontalLayoutContainer();
		
		//Create a label, text box and button
		Label label = new Label("Customer Name:");
		text = new TextField();
		TextButton button = new TextButton("Add Customer");
		
		//Create a new element in the tree
		button.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				addCustomer(text.getText());
			}
		});
		
		//Add each element to the layout container
		hlc.add(label);
		hlc.add(text);
		hlc.add(button);
		
		return hlc;
	}

}
