package in.ac.iitm.smscompression;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import in.ac.iitm.smscompression.model.ClusterObject;

public class EncodeMessage {
	
	public EncodeMessage() {}
	
	private ClusteringData mClusteringData;
	
	public static String msg = "";
	
	private ArrayList<String> strArray = new ArrayList<String>();
	
	private StringBuilder strHdr = new StringBuilder();
	private StringBuilder strLen = new StringBuilder();
	private StringBuilder strMsg = new StringBuilder();
	private StringBuilder encodedMessage = new StringBuilder();
	
	private byte[] mHeader 	= new byte[8];
	private byte[] mLength 	= new byte[16];
	//private byte[] mMessage;//	= new byte[1120];
	
	
	private String TAMIL_CODE_FILE		= "huff_tamil.json";
	private static String HINDI_CODE_FILE 		= "huff_hindi.json";
	private static String GUJARATI_CODE_FILE	= "huff_gujarati.json";
	
	//private static String HEXFORMAT	= "0x";
	
	private static int TAMIL		=	1;
	private static int HINDI		= 	2;
	private static int GUJARATI	=   3;
	
	private int encodeLength = 0;
	
	
	public String encodeData(String strMessage, int flag) {
					
		char[] ch = strMessage.toCharArray();	
		chooseLanguage(flag);
				
		// Header 		
		if(flag == 0) {
			mHeader = mHeaderToBinary(TAMIL);
			for (int i = mHeader.length - 1; i >= 0; i--) {
				strHdr.append(mHeader[i]);
	        }
		} else if(flag == 1) {
			mHeader = mHeaderToBinary(HINDI);
			for (int i = mHeader.length - 1; i >= 0; i--) {
				strHdr.append(mHeader[i]);
	        }
		} else if(flag == 2) {
			mHeader = mHeaderToBinary(GUJARATI);
			for (int i = mHeader.length - 1; i >= 0; i--) {
				strHdr.append(mHeader[i]);
	        }
		}	
				
		// Encode
		strArray = mClusteringData.pickCluster(strMessage, flag);
		for(int i = 0; i < strArray.size(); i++) {
			//System.out.print(strArray.get(i) + " ");
			strMsg.append(getCode(strArray.get(i), flag));
		}
				
		// Length
		encodeLength = strMsg.length();
		mLength = mLengthToBinary(encodeLength);
		for (int i = mLength.length - 1; i >= 0; i--) {
			strLen.append(mLength[i]);
        }
		//System.out.println(strLen);
		
		// wrap the header info to compressed msg
		encodedMessage.append(strHdr);
		encodedMessage.append(strLen);
		encodedMessage.append(strMsg);
				
		// Convert encoded string to chunks;
		msg = encodedMessage.toString();	
	    //System.out.println(msg);
		String temp="";
		String hex = "";
		char outString='0';
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < msg.length();) {
			if((i + 16) < msg.length()) {
				temp = msg.substring(i, i+16);
				hex = Integer.toHexString(Integer.parseInt(temp,2));
				outString = (char) Integer.parseInt(hex,16);
				sb.append(outString);
				i += 16;
			} else {
				String paddingBits = "";
				paddingBits = msg.substring(i,msg.length());
				for(int j = 0; j < (16 - (msg.length() - i)); j++) {
					paddingBits += "0";
				}
				hex = Integer.toHexString(Integer.parseInt(paddingBits,2));
				outString = (char) Integer.parseInt(hex, 16);
				sb.append(outString);
				i += (msg.length() - i);
			}
		}
		//writeToFile(sb.toString());
		return sb.toString();
	}
	
	private String getCode(String strCluster, int flag) {		
		try {
			Map<Character, Map<String, String>> LookUpTable = new HashMap<Character, Map<String, String>>();
			InputStream fis = new FileInputStream(filePath(flag));
			JsonReader jsonReader = Json.createReader(fis);
			JsonArray jsonst = jsonReader.readArray();
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
			
			for(ClusterObject c : clusterObjects) {
				if(LookUpTable.containsKey(c.getBaseUnicodeKey())) {
					(LookUpTable.get(c.getBaseUnicodeKey())).put(c.getKey(), c.getValue());
					
				} else {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put(c.getKey(), c.getValue());
					LookUpTable.put(c.getBaseUnicodeKey(), map);
				}			
			}	
			
			char c = strCluster.charAt(0);
			//System.out.println(strCluster);
			//System.out.println(c +" " +strCluster);
			String str = "";
			
			if(LookUpTable.containsKey(c)) {
				HashMap<String, String> table = new HashMap<String, String>();
				table = (HashMap<String, String>) LookUpTable.get(c);
				str = table.get(strCluster);
				
				byte[] msg = new byte[16];
				int charValue = 0;
				//System.out.println(str);
				if(str != null) {
					return str;
				} else {
					StringBuilder sb = new StringBuilder();
					for(int i = 0; i < strCluster.length(); i++) {
						charValue = Character.codePointAt(strCluster, i);
						//System.out.println(charValue);
						msg = mLengthToBinary(charValue);
						char ch = ("escape").charAt(0);
						HashMap<String, String> temp = new HashMap<String, String>();
						temp = (HashMap<String, String>) LookUpTable.get(ch);
						//System.out.println("@escape" + temp.get("escape"));
						sb.append(temp.get("escape"));
						for (int j = msg.length - 1; j >= 0; j--) {
							sb.append(msg[j]);	
				        }
						//System.out.println(sb);
					}
					//System.out.println(sb);
					return sb.toString();
				}
				
			}else {
				byte[] msgs = new byte[16];
				int charVal = 0;
				StringBuilder sb = new StringBuilder();
				for(int i = 0; i < strCluster.length(); i++) {
					charVal = Character.codePointAt(strCluster, i);
					//System.out.println(charValue);
					msgs = mLengthToBinary(charVal);
					char ch = ("escape").charAt(0);
					HashMap<String, String> temp = new HashMap<String, String>();
					temp = (HashMap<String, String>) LookUpTable.get(ch);
					//System.out.println("@escape" + temp.get("escape"));
					sb.append(temp.get("escape"));
					for (int j = msgs.length - 1; j >= 0; j--) {
						sb.append(msgs[j]);	
			        }
					//System.out.println(sb);
				}
				//System.out.println(sb);
				return sb.toString();
			}
			

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	private String filePath(int unicodeFlag) {		
		if(unicodeFlag == 0) {
			return TAMIL_CODE_FILE;
		} else if(unicodeFlag == 1) {
			return HINDI_CODE_FILE;
		} else if(unicodeFlag == 2) {
			return GUJARATI_CODE_FILE;
		}		
		return null;
	}
	
	/**
	 * Check the Unicode to find its language
	 * @param codePoint
	 * @return
	 */
	private void chooseLanguage(int flag) {		
		
		if(flag == 0){					// Tamil language check	
			mClusteringData = new ClusteringData(Config.Symbols, Config.Vowels, Config.Consonants, null, Config.Matras, null, Config.Halanth, Config.Numbers);
		} else if(flag == 1) {			// Hindi language check
			mClusteringData = new ClusteringData(Config.Symbols, Config.Vowels, Config.Consonants, Config.splConsonants, Config.Matras, Config.splMatras,Config.Halanth, Config.Numbers);
		} else if(flag == 2) {			// Gujarati language check
			mClusteringData = new ClusteringData(Config.Symbols, Config.Vowels, Config.Consonants, Config.splConsonants, Config.Matras, Config.splMatras,Config.Halanth,Config.Numbers);
		} 
		
	}
	
	/*private void writeToFile(String code) {
		try {
	        FileOutputStream fos = new FileOutputStream("encodedMessage.txt");
	        Writer out = new OutputStreamWriter(fos, "UTF8");
	        out.write(code);
	        out.close();
	    } 
	    catch (IOException e) {
	        e.printStackTrace();
	    }
	}*/
	
	/*
     * Java Method to convert decimal to binary
     */
    private static byte[] mHeaderToBinary(int number) {
        byte[] binary = new byte[8];        
        int index = 0;
        int copyOfInput = number;
        while (copyOfInput > 0) {
            binary[index++] = (byte) (copyOfInput % 2);
            copyOfInput = copyOfInput / 2;
        }        
        return binary;
    }
    
    private static byte[] mLengthToBinary(int number) {
        byte[] binary = new byte[16];        
        int index = 0;
        int copyOfInput = number;
        while (copyOfInput > 0) {
            binary[index++] = (byte) (copyOfInput % 2);
            copyOfInput = copyOfInput / 2;
        }        
        return binary;
    }    
}