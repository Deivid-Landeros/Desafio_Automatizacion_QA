package ips.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import ips.utils.logs.Log;


import java.io.FileReader;



public class JSONReadFromFile {
	
	
	/**
	* Permite obtener los valores de los atributos en utils.json
	* @param atributo nombre del atributo que se quiere obtener.
	* @return Devuelve un string con el valor del atributo.
	*/
	public static String readJson(String atributo) {
	    try {
	        // Create a JSON parser
	          JSONParser parser = new JSONParser();
	          // Read the JSON file and parse it
	          Object obj = parser.parse(new FileReader("src/test/resources/utils.json"));
	          // Convert the parsed object to a JSONObject
	          JSONObject jsonObject = (JSONObject) obj;

	          // Get the value of the "name" field
	          String atr = (String) jsonObject.get(atributo);

	          return atr;
	        } catch (Exception e) {
	          e.printStackTrace();
	          Log.error(e.toString());
	          return null;
	        }
	    
	}
	
	/**
	* Permite obtener los valores de los atributos en utils.json
	* @param atributoPadre nombre del atributo padre que se quiere obtener (Ejemplo CP001).
	* @param atributoHijo nombre del atributo hijo que se quiere obtener (Ejemplo rutbeneficiario).
	* @return Devuelve un string con el valor del atributo.
	*/
	public static String readJson(String atributoPadre, String atributoHijo) {
	    try {
	        // Create a JSON parser
	          JSONParser parser = new JSONParser();
	          // Read the JSON file and parse it
	          Object obj = parser.parse(new FileReader("src/test/resources/utils.json"));
	          // Convert the parsed object to a JSONObject
	          JSONObject jsonObject = (JSONObject) obj;

	          // Get the value of the "skills" field
	          JSONObject casos = (JSONObject) jsonObject.get(atributoPadre);
	          String atr =(String) casos.get(atributoHijo);

	          return atr;
	        } catch (Exception e) {
	          e.printStackTrace();
	          return null;
	        }
	    
	}

}
