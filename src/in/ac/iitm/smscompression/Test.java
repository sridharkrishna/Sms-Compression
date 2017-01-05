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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;

import org.glassfish.json.JsonProviderImpl;

import in.ac.iitm.smscompression.model.ClusterKeyValue;
import in.ac.iitm.smscompression.model.ClusterTable;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello Sms Compression.....!");
		
		
		
		ClusterTable clust = new ClusterTable();
		
		ClusterKeyValue ckv = new ClusterKeyValue();
		ckv.setKey("த");
		ckv.setValue("000110");
		
		
		List<ClusterKeyValue> lkv = new ArrayList<ClusterKeyValue>();
		
		lkv.add(ckv);
		clust.setKeyValuePairs(lkv);
		
		clust.setBaseKeyUnicode((char) 0xba4);
		
		JsonObjectBuilder jObj = Json.createObjectBuilder();
		
		//JsonObjectBuilder baseUnicodeKey = Json.createObjectBuilder();
		JsonArrayBuilder keyValuePairs = Json.createArrayBuilder();
		
		
		for(ClusterKeyValue keyValue: clust.getKeyValuePairs()) {
			keyValuePairs.add(Json.createObjectBuilder()
					.add("Key", keyValue.getKey())
					.add("Value", keyValue.getValue()));
		}
	
		
		
		jObj.add("baseUnicodeKey", (char) clust.getBaseKeyUnicode());
		jObj.add("keyValuePairs", keyValuePairs.build());
		
		JsonObject cluster = jObj.build();
		
		System.out.print(cluster);
	}

}
