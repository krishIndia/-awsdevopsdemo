package com.mfg.json.jsontestdemo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJsonFile {

	public static void main(String[] args) {

		JSONParser parser = new JSONParser();

		try {
			/*String path = System.getenv("/c/Users/Dell/.ssh");
			
			String homeDirectory = System.getProperty("user.home");
			System.out.println("proc :>>>>>" + homeDirectory);
			Process process=Runtime.getRuntime().exec(String.format("cmd.exe C:\\Users\\Dell\\ test1.sh %s", homeDirectory));
			System.out.println("proc 3:>>>>>" + String.valueOf(process));*/
		
            		
			Object object = parser.parse(new FileReader("test.json"));
			
			JSONObject jsonObject = (JSONObject) object;
			
			
			JSONArray tags = (JSONArray) jsonObject.get("Tags");
			for (int i = 0; i < tags.size(); i++) {
				JSONObject obj = (JSONObject) tags.get(i);
				String resourceId = (String) obj.get("ResourceId");
				String value = (String) obj.get("Value");
				System.out.println("Value :" + value);
				System.out.println("ResourceId :" + resourceId);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
}