package com.yuhannci.erp.model;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Set;
import java.util.AbstractMap.SimpleEntry;

import com.yuhannci.erp.model.db.JobOrder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldExtractor{
	
	// 현재 instance 의 값을 리스트화 
	public HashMap<String, String> extractFields(){
		return extractFields(null);
	}
	public HashMap<String, String> extractFields(Set<String> filters){
		HashMap<String, String> result =  new HashMap<String, String>();
		Field[] fields = this.getClass().getDeclaredFields();
		Object value = null;
		for(Field field : fields){
			field.setAccessible(true);
	
			// log.info("checking field [" + field.getName() + "] ==> " + filters.contains(field.getName()));
			if(filters!=null && !filters.contains(field.getName()))
				continue;
			
			//Class type = field.getType();
			try{
				value = field.get( this);
			}catch(Exception e){
				value = null;
			}
			
			if(value == null){
				result.put(field.getName(), "");
				continue;
			}
			
			// always not null
			result.put(field.getName(), String.valueOf(value) );				

		}
		return result;
	}
	// 원래 값에서 변경된 것을 찾아서 반환
	// 반환 : (필드이름) - (원래값/변경된 값)
	public HashMap<String, SimpleEntry<String, String>> compare(HashMap<String, String> org, Set<String> filters){
		
		HashMap<String, String> mine = this.extractFields(filters);		
		HashMap<String, SimpleEntry<String, String>> changed = new HashMap<>();
		
		for(String key : mine.keySet()){
			
			String org_value = org.get(key);
			String new_value = mine.get(key);
			if(!org_value.equals(new_value))		
				changed.put(key, new SimpleEntry<String, String>(org_value, new_value));			
		}
		
		return changed;
	}
}
