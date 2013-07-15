package com.safedoor.testing.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.Radio;
import com.sencha.gxt.widget.core.client.form.TextField;

public class DynamicView implements IsWidget{
	
	private final ContentPanel cPanel;
	private final VerticalPanel vPanel;
	private final TextButton eventButton;
	
	//example class used for combo box properties
	public static class Combo{
		private static Integer ID = 0;
		
		private String name;
		private Integer id;
		
		public Combo(String name){
			this.id = ID++;
			this.name = name;			
		}
		
		public static List<Combo> getData(){
			List<Combo> list = new ArrayList<Combo>();
			for(int i=0;i<5;i++){
				Combo combo = new Combo("Combo Item "+i);
				list.add(combo);
			}
			return list;
		}
		
		//Condensed getters/setters
		public void setName(String name){this.name = name;}
		public String getName(){return this.name;}
		public void setId(Integer id){this.id = id;}
		public Integer getId(){return this.id;}
	}
	
	//Property access interface for combobox items
	interface ComboProperties extends PropertyAccess<Combo>{
		ModelKeyProvider<Combo> id();
		LabelProvider<Combo> name();
	}
	
	
	//Initialize the widget components here
	public DynamicView(){
		cPanel = new ContentPanel();
		vPanel = new VerticalPanel();
		eventButton = new TextButton("Add Element");
		
		VerticalPanel vPanel2 = new VerticalPanel(); 
		
		cPanel.setHeadingText("Dynamic View");
		cPanel.setHeight(600);
		cPanel.setWidth(400);
		cPanel.add(vPanel2);
		
		vPanel2.add(eventButton);
		vPanel2.add(vPanel);
		vPanel.setSpacing(10);
		
		eventButton.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				vPanel.add(randElement());
			}
		});
		
	}
	
	
	/*
	 * Creates a random widget and adds it to the form
	 */
	@SuppressWarnings("unchecked")
	private Widget randElement(){
		int rand = (int) Math.round(Math.random()*3);
		
		switch(rand){
		case 0:
			return new TextField();
		case 1:
			HorizontalPanel panel = new HorizontalPanel();
			ToggleGroup group = new ToggleGroup();
			for(int i=0;i<3;i++){
				Radio radio = new Radio();
				radio.setBoxLabel("Radio "+(i+1));
				group.add(radio);
				panel.add(radio);
			}
			return panel;
		case 2:
			HorizontalPanel panel1 = new HorizontalPanel();
			for(int i=0;i<3;i++){
				CheckBox cb = new CheckBox();
				cb.setBoxLabel("Check "+(i+1));
				panel1.add(cb);
			}
			return panel1;
		case 3:
			ComboProperties props = GWT.create(ComboProperties.class);
			ListStore<Combo> comboStore = new ListStore<Combo>(props.id());
			comboStore.addAll(Combo.getData());
			
			ComboBox<Combo> combo = new ComboBox<Combo>(comboStore, props.name());
			combo.setEmptyText("Pick an option...");
			return combo;
		default:
			return new Widget();
		}
	}

	@Override
	public Widget asWidget() {
		return this.cPanel;
	}
	
	
}
