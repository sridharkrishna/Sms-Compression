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

public class Test {
	
	public static void main(String[] args) {
		Wrapper wrap = new Wrapper();
		String msg = "xyz அரசியல் கட்சிகளின் நன்கொடைக்கு வருமான வரிவிலக்கு அளிப்பதற்கு எதிரான வழக்கை சுப்ரீம் கோர்ட்டு தள்ளுபடி செய்தது.";
		
		System.out.println("Oringinal SMS: " + msg);
		
		System.out.println();
		
		String encodedMsg = wrap.encodeString(msg);		
		System.out.println("Encoded SMS: " + encodedMsg);
		
		System.out.println();	
		
		String decodedMsg = wrap.decodeString(Wrapper.mMessage);
		System.out.println("Decoded SMS: " + decodedMsg);

	}

}
