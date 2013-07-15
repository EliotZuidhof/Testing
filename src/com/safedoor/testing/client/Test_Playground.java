package com.safedoor.testing.client;

import java.beans.EventSetDescriptor;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

/**
 * Test Playground in Gxt
 * 
 * @author eliot.zuidhof
 *
 */
public class Test_Playground implements EntryPoint {
	
	/**
	 * The module entry point
	 */
	public void onModuleLoad() {
		RootPanel.get().add(new DynamicView().asWidget());
	}
	
}
