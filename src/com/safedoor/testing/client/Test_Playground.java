package com.safedoor.testing.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;

/**
 * A class to create a sandbox for testing the limits of gxt elements.
 * 
 * @author eliot.zuidhof
 *
 */
public class Test_Playground implements EntryPoint {
	
	private CustomerTree tree;
	private LiveDataGrid grid;
	
	/**
	 * The module entry point
	 */
	public void onModuleLoad() {
		//Create the containers
		HorizontalLayoutContainer layout = new HorizontalLayoutContainer();
		
		//Setup the content panels
		ContentPanel customerPanel = new ContentPanel();
		ContentPanel gridPanel = new ContentPanel();
		
		customerPanel.setHeadingText("Customers");
		gridPanel.setHeadingText("Grid Data");
		customerPanel.setWidth(350);
		customerPanel.setHeight(250);
		gridPanel.setWidth(650);
		gridPanel.setHeight(200);
		
		tree = new CustomerTree();
		customerPanel.setWidget(tree.asWidget());
		
		grid = new LiveDataGrid();
		
		//Create the tree data
		for(int i=0;i<200;i++){
			tree.addCustomer("Customer "+(i+1));
		}
		
		layout.add(customerPanel);
		layout.add(grid.asWidget());
		RootPanel.get().add(layout);
	}
	
}
