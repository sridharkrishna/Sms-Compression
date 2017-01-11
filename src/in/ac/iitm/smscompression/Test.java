/**************************************************************************************************************
 Copyright (c) 2017 Prof. Devendra Jalihal & Sridhar Ananthakrishnan

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
************************************************************************************************************/
package in.ac.iitm.smscompression;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import in.ac.iitm.smscompression.model.ClusterKeyValue;
import in.ac.iitm.smscompression.model.ClusterObject;

public class Test {
	
	private static final String JSON_FILE="huff_tamil.json";
	
	Map<Character, List<ClusterKeyValue>> cTable = new HashMap<Character, List<ClusterKeyValue>>();
	
	
	
	public void setClusterMap (char baseUnicodeKey, List<ClusterKeyValue> keyValue) {
		cTable.put(baseUnicodeKey, keyValue);		
	}
	
	public void printMap (char c) {
		System.out.println(c+ " : " + cTable.get(c));
		
		for(ClusterKeyValue kv : cTable.get(c)){
			System.out.println(c+ " : " + kv.getKey());
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Hello Sms Compression.....!");
		HashMap<String, String> KeyValue = new HashMap<String, String>();
		
		Map<Character, Map<String, String>> LookUpTable = new HashMap<Character, Map<String, String>>();
		
		InputStream fis = new FileInputStream(JSON_FILE);
		
		Test test = new Test();
		
		//create JsonReader object
		JsonReader jsonReader = Json.createReader(fis);		
		JsonArray jsonst = jsonReader.readArray();
		
		//we can close IO resource and JsonReader now
		jsonReader.close();
		
		fis.close();
			
		ClusterObject[] clusterObjects = new ClusterObject[jsonst.size()];
		
		for(int i = 0; i < jsonst.size(); i++) {
			JsonObject jObj = jsonst.getJsonObject(i);
			
			String S = jObj.getString("baseUnicodeKey");
			char baseUnicodeKey = S.charAt(0);	
			
			JsonObject keyValueObj = jObj.getJsonObject("keyValuePairs");
			String Key = keyValueObj.getString("Key");
			String Value = keyValueObj.getString("Value");
			
			clusterObjects[i] = new ClusterObject(baseUnicodeKey, Key, Value);			
			
		}
		
		Arrays.sort(clusterObjects, ClusterObject.baseUnicodeKeyComparator);
		
		char baseKey = Character.MIN_VALUE;
		
		
		boolean isSameKey  = false;
		
		List<ClusterKeyValue> keyValue = new ArrayList<ClusterKeyValue>();
		
		for(ClusterObject c : clusterObjects) {
			System.out.println(c.getBaseUnicodeKey());			
			if(LookUpTable.containsKey(c.getBaseUnicodeKey())) {
				(LookUpTable.get(c.getBaseUnicodeKey())).put(c.getKey(), c.getValue());
				System.out.println(true);
				System.out.println(LookUpTable.get(c.getBaseUnicodeKey()));
			} else {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put(c.getKey(), c.getValue());
				LookUpTable.put(c.getBaseUnicodeKey(), map);
				System.out.println(LookUpTable.get(c.getBaseUnicodeKey()));
			}			
		}		
	}

}
