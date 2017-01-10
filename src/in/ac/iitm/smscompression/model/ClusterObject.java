package in.ac.iitm.smscompression.model;

import java.util.Comparator;

public class ClusterObject implements Comparable<ClusterObject>{
	
	private char baseUnicodeKey;
	
	private String Key;
	
	private String Value;

	public ClusterObject(char baseUnicodeKey, String Key, String Value) {
		super();
		
		this.baseUnicodeKey = baseUnicodeKey;
		
		this.Key = Key;
		
		this.Value = Value;
	}

	public char getBaseUnicodeKey() {
		return baseUnicodeKey;
	}

	public void setBaseUnicodeKey(char baseUnicodeKey) {
		this.baseUnicodeKey = baseUnicodeKey;
	}

	public String getKey() {
		return Key;
	}

	public void setKey(String key) {
		Key = key;
	}

	public String getValue() {
		return Value;
	}

	public void setValue(String value) {
		Value = value;
	}

	public static Comparator<ClusterObject> baseUnicodeKeyComparator = new Comparator<ClusterObject>() {
		public int compare(ClusterObject c1, ClusterObject c2) {
			Character baseUnicode1 = c1.getBaseUnicodeKey();
			Character baseUnicode2 = c2.getBaseUnicodeKey();
			
			return baseUnicode1.compareTo(baseUnicode2);
		}
	};

	@Override
	public int compareTo(ClusterObject o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
