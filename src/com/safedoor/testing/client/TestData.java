package com.safedoor.testing.client;

import java.util.ArrayList;
import java.util.List;

public class TestData {
	
	public static List<Data> getData(){
		List<Data> list = new ArrayList<Data>();
		for(int i=0;i<400;i++){
			list.add(new Data("Name "+(i+1),Math.random()*200,"Note"));
		}		
		return list;
	}
	
}
