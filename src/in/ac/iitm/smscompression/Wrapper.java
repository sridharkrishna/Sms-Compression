package in.ac.iitm.smscompression;

public class Wrapper {
	
	private EncodeMessage 		em = new EncodeMessage();
	//private CalculateMessage 	cm = new CalculateMessage();
	private DecodeMessage 		dm = new DecodeMessage();
	
	public static String mMessage = "";
	
	public Wrapper() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Encode the message using 8bit/16bit stream for SMS
	 * @param strMessage
	 * @return
	 */
	public String encodeString(String strMessage) {	
		char ch[] = strMessage.toCharArray();
		
		int languageFlag = 0;
		
		for(int i = 0; i < ch.length; i++) {
			if(isTamil(ch[i]) != -1) {
				languageFlag = 0;
				//System.out.println("Tamil");
				break;
			} else if(isHindi(ch[i]) != -1) {
				languageFlag = 1;
				//System.out.println("Hindi");
				break;
			} else if(isGujarati(ch[i]) != -1) {
				languageFlag = 2;
				//System.out.println("Gujarati");
				break;
			}
		}
		
		mMessage = em.encodeData(strMessage, languageFlag);
		return mMessage;
	}
	
	/**
	 * Calculate the message length based on the Unicode
	 * @param strMessage
	 * @return
	 */
	public int[] calculateMessageLength(String strMessage) {
		int[] messageLength = new int[7];
		
		int noOfChars 			= strMessage.length();
		int noOfSms	  			= 0;
		int noOfRemainingChars 	= 0;
		int totalNoOfBits		= 0;
		int paddingBitsSz		= 0;
		int otherBits			= 0;
		int avgBitLen			= 0;
		
		//Encoded msg length
		int encodedMessageLength = mMessage.length() * 16;
		
		// No. of SMS generated
		if(encodedMessageLength < 1120) {
			noOfSms = 1;
		} else if(encodedMessageLength > 1120) {
			float val = encodedMessageLength / 1120;
			noOfSms = ((int) val + 1);
		}
		
		// Total no. of bits in the encoding String
		//totalNoOfBits = EncodeMessage.msg.length()-24;
		totalNoOfBits = encodedMessageLength;
		
		// Remaining No. of Bits calculation
		double temp = (double) totalNoOfBits/noOfChars;
		
		//commented by vinodp date:22-03-2016
		/*int remaingBitsInOneSMS = (noOfSms * 1120) - totalNoOfBits;
		double avg = (double) (remaingBitsInOneSMS / temp);
		noOfRemainingChars = (int) avg;*/
		
		
		
		//updated by vinod on date:22-03-2016
		avgBitLen = (int)Math.ceil(temp); // Average Bit length
		noOfRemainingChars = ((noOfSms * 1120)-totalNoOfBits)/avgBitLen;
		//end of updated by vinod
		
		
		// No. of padding bits used
		//paddingBitsSz = encodedMessageLength - totalNoOfBits;
		paddingBitsSz = EncodeMessage.msg.length() % 16;
		
		// Header Bits
		otherBits = 24;
		
		messageLength[0] = noOfSms;
		messageLength[1] = noOfChars;
		messageLength[2] = noOfRemainingChars;
		messageLength[3] = totalNoOfBits;
		messageLength[4] = paddingBitsSz;
		messageLength[5] = otherBits;
		messageLength[6] = avgBitLen;
		
		return messageLength;
	}
	
	/**
	 * Decode the encoded string based on the decoding table
	 * @param strEncodedString
	 * @return
	 */
	public String decodeString(String strEncodedString) {		
		return dm.decodeData(strEncodedString);
	}
	
	private int isTamil(char ch) {
		for(int i = 0; i < Config.Tamil.length; i++) {
			if(Config.Tamil[i] == ch) return i;
		}
		return -1;
	}
	
	private int isHindi(char ch) {
		for(int i = 0; i < Config.Hindi.length; i++) {
			if(Config.Hindi[i] == ch) return i;
		}
		return -1;
	}
	
	private int isGujarati(char ch) {
		for(int i = 0; i < Config.Gujarati.length; i++) {
			if(Config.Gujarati[i] == ch) return i;
		}
		return -1;
	}

}