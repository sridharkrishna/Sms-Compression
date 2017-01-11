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


public class Config {
	
	
	static char[] Symbols		= {'\u0020','ॐ','ௐ'};
	
	static char[] Vowels		= {
									// Hindi
									'अ','आ','इ','ई','उ','ऊ','ए','ऐ','ओ','औ','ऒ','ऍ','ऎ','ऋ','ॠ','ऌ','ॡ',										
									// Gujarati
									'અ','આ','ઇ','ઈ','ઉ','ઊ','ઍ','એ','ઐ','ઑ','ઓ','ઔ','ઋ','ૠ','ઌ','ૡ',
									// Tamil
									'அ','ஆ','இ','ஈ','உ','ஊ','எ','ஏ','ஐ','ஒ','ஓ','ஔ'
								   };
	
	
	static char[] Consonants	= {
									// Hindi
									'क','ख','ग','घ','ङ','च','छ','ज','झ','ञ','ट','ठ','ड','ढ','ण','त','थ','द','ध','न','प','फ','ब','भ','म','य','र','ल','व','श','ष','स','ह','ळ','ऱ','य़',
									// Gujarati
									'ક','ખ','ગ','ઘ','ઙ','ચ','છ','જ','ઝ','ઞ','ટ','ઠ','ડ','ઢ','ણ','ત','થ','દ','ધ','ન','પ','ફ','બ','ભ','મ','ય','ર','લ','ળ','વ','શ','ષ','સ','હ',
									// Tamil
									'க','ங','ச','ஞ','ட','ண','த','ந','ப','ம','ய','ர','ல','வ','ழ','ள','ற','ன','ஜ','ஶ','ஷ','ஸ','ஹ','ஃ'
								  };
	
	static char[] splConsonants	= {
									// Hindi
									'़',
									// Gujarati
									'઼'		
								  };
	
	static char[] Matras		= {
									// Hindi
									'ा','ि','ी','ु','ू','े','ै','ो','ौ','ॉ','ॊ','ॆ','ृ','ॅ',
									// Gujarati
									'ા','િ','ી','ુ','ૂ','ૅ','ે','ૈ','ૉ','ો','ૌ','ૃ','ૄ','ૢ','ૣ',
									//Tamil
									'ா','ி','ீ','ு','ூ','ெ','ே','ை','ொ','ோ','ௌ'
									};
	
	static char[] splMatras		= {
									// Hindi
									'ं','ँ','ः',
									// Gujarati
									'ં','ઁ','ઃ'
								   };
	
	static char[] Halanth		= { // Hindi
									'्',
									// Gujarati
									'્',
									// Tamil
									'்'
									};
	static char[] Numbers		= {
									// Hindi
									'०','१','२','३','४','५','६','७','८','९',
									// Gujarati
									'૦','૧','૨','૩','૪','૫','૬','૭','૮','૯',
									// Tamil
									'௦','௧','௨','௩','௪','௫','௬','௭','௮','௯','௰','௱','௲'
									};
	
	static char[] Tamil			= {
			'அ','ஆ','இ','ஈ','உ','ஊ','எ','ஏ','ஐ','ஒ','ஓ','ஔ',
			'க','ங','ச','ஞ','ட','ண','த','ந','ப','ம','ய','ர','ல','வ','ழ','ள','ற','ன','ஜ','ஶ','ஷ','ஸ','ஹ','ஃ',
			'ா','ி','ீ','ு','ூ','ெ','ே','ை','ொ','ோ','ௌ','்', '௦','௧','௨','௩','௪','௫','௬','௭','௮','௯','௰','௱','௲'
	};
	
	static char[] Hindi			= {
			'अ','आ','इ','ई','उ','ऊ','ए','ऐ','ओ','औ','ऒ','ऍ','ऎ','ऋ','ॠ','ऌ','ॡ',	
			'क','ख','ग','घ','ङ','च','छ','ज','झ','ञ','ट','ठ','ड','ढ','ण','त','थ','द','ध','न','प','फ','ब','भ','म','य','र','ल','व','श','ष','स','ह','ळ','ऱ','य़',
			'़', 'ा','ि','ी','ु','ू','े','ै','ो','ौ','ॉ','ॊ','ॆ','ृ','ॅ','्','०','१','२','३','४','५','६','७','८','९'
	};
	
	static char[] Gujarati		= {
			'અ','આ','ઇ','ઈ','ઉ','ઊ','ઍ','એ','ઐ','ઑ','ઓ','ઔ','ઋ','ૠ','ઌ','ૡ',
			'ક','ખ','ગ','ઘ','ઙ','ચ','છ','જ','ઝ','ઞ','ટ','ઠ','ડ','ઢ','ણ','ત','થ','દ','ધ','ન','પ','ફ','બ','ભ','મ','ય','ર','લ','ળ','વ','શ','ષ','સ','હ',
			'઼', 'ા','િ','ી','ુ','ૂ','ૅ','ે','ૈ','ૉ','ો','ૌ','ૃ','ૄ','ૢ','ૣ','્','૦','૧','૨','૩','૪','૫','૬','૭','૮','૯'
	};


}