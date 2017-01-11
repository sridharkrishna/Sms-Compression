package in.ac.iitm.smscompression;

import java.math.BigInteger;

public class DecodeMessage {

	public DecodeMessage() {
		// TODO Auto-generated constructor stub
	}
	
	private static String TAMIL_CODE_FILE		= "/huff_tamil.json";
	private static String HINDI_CODE_FILE 		= "/huff_hindi.json";
	private static String GUJARATI_CODE_FILE	= "/huff_gujarati.json";
	
	private SymbolTable<String, String> st = new SymbolTable<String, String>();
	
	public String decodeData(String code) {		
		return getDecodedString(code);
	}
	
	private String getDecodedString(String code) {
		int KEY = 1;
		int VAL = 0;
		String mHeader = null;
		
		/**
		 * Converting Hex code into binary format
		 */
		StringBuilder binary = new StringBuilder();
		char[] ch = code.toCharArray();
		for(int i = 0; i < ch.length; i++) {
			String hex = String.format("%04x", (int)ch[i]);
			binary.append(hexToBin(hex));
		}
		
		String strToDecode = binary.toString();
		//System.out.println(strToDecode);
		char[] charFromString = strToDecode.toCharArray();
		
		// Checking Header
		StringBuilder hdr = new StringBuilder();
		for(int i = 0; i < 8; i++) {
			hdr.append(charFromString[i]);
		}
		mHeader = hdr.toString();
		
		In in = new In(filePath(mHeader));	
		String[] database = in.readAllLines();	
		
		for(int i = 0; i < database.length; i++) {
			String[] tokens = database[i].split("§");
			String key = tokens[KEY];
			String val = tokens[VAL];
			st.put(key, val);
		}
		
		return decode(strToDecode);
	}
	
	
	private String decode(String code) {
		
		char[] ch = code.toCharArray();
		String temp = "";
		
		StringBuilder mCodeLen 		= new StringBuilder();
		StringBuilder mEncodeStr 	= new StringBuilder();
		StringBuilder mString 		= new StringBuilder();
		
		// Find length of the code
		for(int i = 8; i < 24; i++){
			mCodeLen.append(ch[i]);			
		}
		String s = mCodeLen.toString();
		int len = Integer.parseInt(s, 2);
		
		// Retrieve exact message code based on length
		for(int i = 24; i < len + 24; i++) {
			mEncodeStr.append(ch[i]);
		}
		String mMsg = mEncodeStr.toString();
		char[] mMessage = mMsg.toCharArray();
		
		//System.out.println(mMsg);
		
		int i = 0;
		
		do {
			temp += Character.toString(mMessage[i]);
			if(st.contains(temp) == true) {						
				if(st.get(temp).matches("escape")) {					
					String str = mMsg.substring(i+1, i+17);
					int coded = Integer.parseInt(str,2);
					//System.out.println(str);
					//System.out.println(coded);
					mString.append(Character.toString((char) coded));
					temp = "";
					i=i+17;
					//System.out.println(i);
				} else {
					mString.append(st.get(temp));
					temp = "";
					i++;
				}			
			} else {
				i++;
			}
		} while(i < mMessage.length);
		
		//writeToFile(mString.toString());
		return mString.toString();
	}
	
	private static String filePath(String header) {
		int hdr = Integer.parseInt(header, 2);
		
		if(hdr == 1) {
			return TAMIL_CODE_FILE;
		} else if(hdr == 2) {
			return HINDI_CODE_FILE;
		} else if(hdr == 3) {
			return GUJARATI_CODE_FILE;
		}					
		return null;
	}
	
	/*private void writeToFile(String code) {
		try {
	        FileOutputStream fos = new FileOutputStream("decodedMessage.txt");
	        Writer out = new OutputStreamWriter(fos, "UTF8");
	        out.write(code);
	        out.close();
	    } 
	    catch (IOException e) {
	        e.printStackTrace();
	    }
	}*/
	
	private static String hexToBin(String str) {
		String preBin = new BigInteger(str, 16).toString(2);
	    Integer length = preBin.length();
	    if (length < 16) {
	        for (int i = 0; i < 16 - length; i++) {
	            preBin = "0" + preBin;
	        }
	    }
	    return preBin;
	}


}
