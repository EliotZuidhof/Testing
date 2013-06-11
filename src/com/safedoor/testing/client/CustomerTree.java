package com.safedoor.testing.client;

import java.util.Date;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.NorthSouthContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.tree.Tree;

/**
 * The customer tree displays customers in an hierarchy.
 * The customers children are facilities.
 * each facility has 4 children:
 * 
 * 1. Followup
 * 2. Facility Name
 * 3. Asset List
 * 4. Schedule 
 * @author eliot.zuidhof
 */
public class CustomerTree implements IsWidget {
	
	//A string array constant holding values for facility nodes
	private static String NODES[] = {"Followup","Facility Name","Asset List","Schedule"};
	//The tree object
	private Tree<BaseDto, String> tree;
	//The text field
	private TextField text;
	
	//Create the KeyProvider class
	class KeyProvider implements ModelKeyProvider<BaseDto>{
		@Override
		public String getKey(BaseDto item) {
			//return the BaseDto id as a string
			return item.getId().toString();
		}
	}
	
	/**
	 * Return the CustomerTree Widget
	 */
	@Override
	public Widget asWidget() {
		//Create a container to hold the tree and input
		NorthSouthContainer nsc = new NorthSouthContainer();
		//Create a TreeStore object with the KeyProvider class
		TreeStore<BaseDto> store = new TreeStore<BaseDto>(new KeyProvider());
		
		//Create the tree and implement ValueProvider methods
		tree = new Tree<BaseDto, String>(store, new ValueProvider<BaseDto, String>(){
			//Return the BaseDto name
			@Override
			public String getValue(BaseDto object) {
				return object.getName();
			}
			
			//Set the BaseDto name
			@Override
			public void setValue(BaseDto object, String value) {
				object.setName(value);				
			}
			
			//return the path
			@Override
			public String getPath() {
				return "name";
			}
		});
		
		tree.setHeight(200);
		
		nsc.setNorthWidget(tree);
		nsc.setSouthWidget(inputWidget());
		
		return nsc;
	}
	
	/**
	 * Creates the input box
	 * 
	 * @return The input widget
	 */
	private Widget inputWidget(){
		HorizontalLayoutContainer hlc = new HorizontalLayoutContainer();
		
		//Create a label, text box and button
		Label label = new Label("Customer Name:");
		text = new TextField();
		TextButton button = new TextButton("Add Customer");
		
		//Create a new element in the tree
		button.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				Date date = new Date();
				int start = (int) date.getTime();
				addCustomer(text.getText());
				int end = (int) date.getTime();
				System.out.println("Time taken: "+(end-start)+" ms");
			}
		});
		
		//Add each element to the layout container
		hlc.add(label);
		hlc.add(text);
		hlc.add(button);
		
		return hlc;
	}
	
	/**
	 * Adds a new customer to the tree. Each customer owns 4 facilities and each facility has 4 nodes:
	 * follow-up, facility name, asset list, schedule
	 * @param name The customer name
	 */
	public void addCustomer(String name){
		//get the TreeStore object
		TreeStore<BaseDto> store = tree.getStore();
		//create the customer in the tree
		BaseDto customer = new BaseDto(name);
		store.add(customer);
		
		for(int i=0;i<4;i++){
			//create 4 facilities per customer 
			BaseDto facility = new BaseDto("Facility "+(i+1));
			store.add(customer, facility);
			
			for(int j=0;j<4;j++){
				//create 4 nodes per facility
				BaseDto node = new BaseDto(CustomerTree.NODES[j]);
				store.add(facility,node);
				
			}//end for (j)
		}//end for (i)
	}

}
