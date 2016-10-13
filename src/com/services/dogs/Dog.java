package com.services.dogs;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;


public class Dog {
	
	private Integer id;
	private String name;
	private String breed;
	
	public Dog(String jsonStr) throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		Map m = mapper.readValue(jsonStr, Map.class);
		
		//Refactor this to use reflection
	    for(Object key: m.keySet())
	    {
	    	switch(key.toString()){
	    	case "id":
	    		this.id = Integer.parseInt(m.get(key).toString());
	    		break;
	    	case "name":
	    		this.name = m.get(key).toString();
	    		break;
	    	case "breed":
	    		this.breed = m.get(key).toString();
	    		break;
	    	default:
	    		throw new IOException("ERROR, INVALID FIELD SPECIFIED");
	    	
	    	}
	   	}
	}
	
	public Dog(Integer id, String name, String breed){
		
		this.id = id;
		this.name = name;
		this.breed = breed;
	}
	
	//Return a JSON representation of the object.   Do not name this getXXX as it will cause a Jackson issue
	public String generateJson() throws JsonGenerationException, JsonMappingException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);

	    String dogJson = mapper.writeValueAsString(this);
	    
	    return dogJson;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
}
