package com.jeeyong.kakaobooks.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonUtils {

	public static String makeJson( Map<String, Object> data ) {
		
		//
		String returnValue = null;
		
		//
		ObjectMapper mapper = new ObjectMapper();
		
		//
		try {
			
			returnValue = mapper.writeValueAsString( data );
		} catch( Exception e ) {
			e.printStackTrace();
		}
		
		//
		return returnValue;
	}
	public static String makeJson( Hashtable data ) {
		
		//
		String returnValue = null;
		
		//
		ObjectMapper mapper = new ObjectMapper();
		
		//
		try {
			
			returnValue = mapper.writeValueAsString( data );
		} catch( Exception e ) {
			e.printStackTrace();
		}
		
		//
		return returnValue;
	}
	public static String makeJson( List data ) {

		//
		String returnValue = null;
		
		//
		ObjectMapper mapper = new ObjectMapper();
		
		//
		try {
			
			returnValue = mapper.writeValueAsString( data );
		} catch( Exception e ) {
			e.printStackTrace();
		}
		
		//
		return returnValue;
	}
	public static Map<String, String> readJsonToStringString( String strJson ) {

		//
		strJson = makeJsonStr( strJson );
		
		//
		HashMap<String, String> returnValue = null;

		//
		ObjectMapper mapper = null;
		try {
			
			mapper = new ObjectMapper();
			returnValue = new HashMap<String, String>();
			
			returnValue = mapper.readValue( strJson, new TypeReference<HashMap<String, String>>(){});
		} catch( Exception e ) {
			e.printStackTrace();
		}
		
		//
		return returnValue;
	}
	public static ArrayList readJsonToArrayList( String strJson ) {
		
		//
		strJson = makeJsonStr( strJson );
		
		//
		ArrayList returnValue = null;

		//
		ObjectMapper mapper = null;
		try {
			
			mapper = new ObjectMapper();
			returnValue = new ArrayList();
			
			returnValue = mapper.readValue( strJson, new TypeReference<ArrayList>(){});
		} catch( Exception e ) {
			e.printStackTrace();
		}
		
		//
		return returnValue;
	}
	public static Map<String, Object> readJsonToStringObject( String strJson ) {
		
		//
		strJson = makeJsonStr( strJson );

		//
		HashMap<String, Object> returnValue = null;

		//
		ObjectMapper mapper = null;
		try {
			
			mapper = new ObjectMapper();
			returnValue = new HashMap<String, Object>();
			
			returnValue = mapper.readValue( strJson, new TypeReference<HashMap<String, Object>>(){});
		} catch( Exception e ) {
			e.printStackTrace();
		}
		
		//
		return returnValue;
	}
	public static ObjectMapper getObjectMapper() throws Exception {
				
		//
		ObjectMapper returnValue = new ObjectMapper();
		returnValue.configure( DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false );
		return returnValue;
	}
	
	private static String makeJsonStr( String source ) {

		source = source.replace( "\"[", "[" );
		source = source.replace( "]\"", "]" );
		source = source.replace( "\\\"", "\"" );
		
		return source;
	}
	
	public static Map<String, String> readJsonToStringStringUnparse( String strJson ) {

		
		//
		HashMap<String, String> returnValue = null;

		//
		ObjectMapper mapper = null;
		try {
			
			mapper = new ObjectMapper();
			returnValue = new HashMap<String, String>();
			
			returnValue = mapper.readValue( strJson, new TypeReference<HashMap<String, String>>(){});
		} catch( Exception e ) {
			e.printStackTrace();
		}
		
		//
		return returnValue;
	}
	public static ArrayList readJsonToArrayListUnparse( String strJson ) {
		
		
		//
		ArrayList returnValue = null;

		//
		ObjectMapper mapper = null;
		try {
			
			mapper = new ObjectMapper();
			returnValue = new ArrayList();
			
			returnValue = mapper.readValue( strJson, new TypeReference<ArrayList>(){});
		} catch( Exception e ) {
			e.printStackTrace();
		}
		
		//
		return returnValue;
	}
	public static Map<String, Object> readJsonToStringObjectUnparse( String strJson ) {
		

		//
		HashMap<String, Object> returnValue = null;

		//
		ObjectMapper mapper = null;
		try {
			
			mapper = new ObjectMapper();
			returnValue = new HashMap<String, Object>();
			
			returnValue = mapper.readValue( strJson, new TypeReference<HashMap<String, Object>>(){});
		} catch( Exception e ) {
			e.printStackTrace();
		}
		
		//
		return returnValue;
	}
}
