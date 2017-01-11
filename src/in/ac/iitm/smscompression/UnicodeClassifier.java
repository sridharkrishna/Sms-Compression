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

public class UnicodeClassifier {
	
	char[] symbols	 	= null;
	char[] vowels		= null;
	char[] consonants 	= null;
	char[] splCons		= null;
	char[] matras		= null;
	char[] splMatra		= null;
	char[] halanth		= null;
	char[] numbers		= null;

	// Tamil
	public UnicodeClassifier(char[] sym, char[] vow, char[] cons, char[] matr, char[] hala, char[] num) {
		this.symbols		= sym;
		this.vowels			= vow;
		this.consonants		= cons;
		this.matras			= matr;
		this.halanth		= hala;
		this.numbers		= num;		
	}
	
	
	// Hindi
	public UnicodeClassifier(char[] sym, char[] vow, char[] cons, char[] splcons, char[] matr, char[] splmatr, char[] hala, char[] num) {
		this.symbols	= sym;
		this.vowels		= vow;
		this.consonants	= cons;
		this.splCons	= splcons;
		this.matras		= matr;
		this.splMatra	= splmatr;
		this.halanth	= hala;
		this.numbers	= num;
	}
	
	

}
