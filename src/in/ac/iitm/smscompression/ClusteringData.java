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

@Deprecated
public class ClusteringData extends UnicodeClassifier{

	private ArrayList<String> strArray = new ArrayList<String>();

	public ClusteringData(char[] sym, char[] vow, char[] cons, char[] splcons,
			char[] matr, char[] splmatr, char[] hala, char[] num) {
		super(sym, vow, cons, splcons, matr, splmatr, hala, num);

	}

	/*
	 * Form clusters from the given input string
	 */
	public ArrayList<String> pickCluster(String strMessage, int unicodeFlag) {


		char[] ch = strMessage.toCharArray();

		/***********************************************************************************
		 * Here S denotes Script Symbols including space tab and so on..
		 * 		V denotes Script Vowels
		 * 		C denotes script Consonants
		 ***********************************************************************************/
		String S	= null;
		String V	= null;
		String VM	= null;
		String C	= null;
		String SC	= null;
		String SCV	= null;
		String SCVM	= null;
		String SCH	= null;
		String SCC	= null;
		String SCCV = null;
		String CV	= null;
		String CVM	= null;
		//String CVMM	= null;
		String CH	= null;
		String CCH  = null;
		String CC	= null;
		String CCC  = null;
		String CCV	= null;
		String CCVH = null;
		String CCCV = null;
		String CCCVH = null;
		String CM	= null;

		if((unicodeFlag == 1) || (unicodeFlag == 2)) {	

			int i = 0;

			do {
					if(isSymbol(ch[i]) != -1) {
						S = Character.toString(this.symbols[isSymbol(ch[i])]);
						strArray.add(S);
						i++;
					} else if(isVowel(ch[i]) != -1) {
						V = Character.toString(this.vowels[isVowel(ch[i])]);
						i++;
						if(i < ch.length){
							if(isSplMatra(ch[i]) != -1) {
								VM = V.concat(Character.toString(this.splMatra[isSplMatra(ch[i])]));
								strArray.add(VM); i++;
							} else {
								strArray.add(V);
							}
						}else {
							strArray.add(V);
						}
					} else if(isConsonant(ch[i]) != -1) {
						C = Character.toString(this.consonants[isConsonant(ch[i])]);
						i++;
						if(i < ch.length) {
							if(isSplConsonant(ch[i]) != -1) {
								SC = C.concat(Character.toString(this.splCons[isSplConsonant(ch[i])]));
								i++;
								if(i < ch.length) {
									if(isMatra(ch[i]) != -1) {
										SCV = SC.concat(Character.toString(this.matras[isMatra(ch[i])]));
										i++;
										if(i < ch.length) {
											if(isSplMatra(ch[i]) != -1) {
												SCVM = SCV.concat(Character.toString(this.splMatra[isSplMatra(ch[i])]));
												strArray.add(SCVM); i++;
												//System.out.println(SCVM);
											} else {
												strArray.add(SCV);
											}
										} else {
											strArray.add(SCV);
										}
									} else if(isHalanth(ch[i]) != -1) {
										SCH = SC.concat(Character.toString(this.halanth[isHalanth(ch[i])]));
										i++;
										if(i < ch.length) {
											if(isConsonant(ch[i]) != -1) {
												SCC = SCH.concat(Character.toString(this.consonants[isConsonant(ch[i])]));
												i++;
												if(i < ch.length) {
													if(isMatra(ch[i]) != -1) {
														SCCV = SCC.concat(Character.toString(this.matras[isMatra(ch[i])]));
														strArray.add(SCCV);
														i++;
													} else {
														strArray.add(SCC);
													}
												} else {
													strArray.add(SCC);
												}
											} else {
												strArray.add(SCH);
											}	
										} else {
											strArray.add(SCH);
										}
									} else {
										strArray.add(SC);
									}
								} else {
									strArray.add(SC);
								}
							} else if(isMatra(ch[i]) != -1) {
								CV = C.concat(Character.toString(this.matras[isMatra(ch[i])]));
								i++;
								if(i < ch.length) {
									if(isSplMatra(ch[i]) != -1) {
										CVM = CV.concat(Character.toString(this.splMatra[isSplMatra(ch[i])]));
										strArray.add(CVM);
										i++;					
									} else {
										strArray.add(CV);
									}
								} else{
									strArray.add(CV);
								}
							} else if(isHalanth(ch[i]) != -1) {
								CH = C.concat(Character.toString(this.halanth[isHalanth(ch[i])]));
								i++;
								if(i < ch.length){
									if(isConsonant(ch[i]) != -1) {
										CC = CH.concat(Character.toString(this.consonants[isConsonant(ch[i])]));
										i++;
										if(i < ch.length){
											if(isMatra(ch[i]) != -1) {
												CCV = CC.concat(Character.toString(this.matras[isMatra(ch[i])]));
												//strArray.add(CCV);
												i++;
												if(i < ch.length) {
													if(isSplMatra(ch[i]) != -1) {
														CCVH = CCV.concat(Character.toString(this.splMatra[isSplMatra(ch[i])]));
														strArray.add(CCVH);
														i++;
													} else {
														strArray.add(CCV);
													}
												} else {
													strArray.add(CCV);
												}
											} else if(isHalanth(ch[i]) != -1) {
												CCH = CC.concat(Character.toString(this.halanth[isHalanth(ch[i])]));
												i++;
												if(i < ch.length) {
													if(isConsonant(ch[i]) != -1) {
														CCC = CCH.concat(Character.toString(this.consonants[isConsonant(ch[i])]));
														i++;
														if(i < ch.length){
															if(isMatra(ch[i]) != -1) {
																CCCV = CCC.concat(Character.toString(this.matras[isMatra(ch[i])]));
																//strArray.add(CCCV);
																i++;
																if(i < ch.length) {
																	if(isSplMatra(ch[i]) != -1) {
																		CCCVH = CCCV.concat(Character.toString(this.splMatra[isSplMatra(ch[i])]));
																		strArray.add(CCCVH);
																		i++;
																	} else {
																		strArray.add(CCCV);
																	}
																} else {
																	strArray.add(CCCV);
																}
															} else {
																strArray.add(CCC);
															}															
														}else {
															strArray.add(CCC);
														}
													} else {
														strArray.add(CCH);
													}
												} else {
													strArray.add(CCH);												
												}
											
											} else {
												strArray.add(CC);
											}
										} else {
											strArray.add(CC);
										}
									} else {
										strArray.add(CH);
									}
								} else {
									strArray.add(CH);
								}
							} else {
								if(isSplMatra(ch[i]) != -1) {
									CM = C.concat(Character.toString(this.splMatra[isSplMatra(ch[i])]));
									strArray.add(CM);i++;
								} else {
									strArray.add(C);
								}
							}

						} else {
							strArray.add(C);
						}

					} else {
						strArray.add(Character.toString(ch[i]));
						i++;
					}					
				
			} while(i < ch.length);	

		} else if(unicodeFlag == 0) {	

			int i = 0;

			do {
				if(isSymbol(ch[i]) != -1) {
					S = Character.toString(this.symbols[isSymbol(ch[i])]);
					strArray.add(S);
					i++;
				} else if(isVowel(ch[i]) != -1) {
					V = Character.toString(this.vowels[isVowel(ch[i])]);
					strArray.add(V);
					i++;					
				} else if(isConsonant(ch[i]) != -1) {
					C = Character.toString(this.consonants[isConsonant(ch[i])]);
					i++;
					if(i < ch.length) {
						if(isMatra(ch[i]) != -1) {
							CV = C.concat(Character.toString(this.matras[isMatra(ch[i])]));
							strArray.add(CV);
							i++;						
						} else if(isHalanth(ch[i]) != -1) {
							CH = C.concat(Character.toString(this.halanth[isHalanth(ch[i])]));
							i++;
							if(i < ch.length) {
								if(isConsonant(ch[i]) != -1) {
									CC = CH.concat(Character.toString(this.consonants[isConsonant(ch[i])]));
									i++;
									if(i < ch.length) {
										if(isMatra(ch[i]) != -1) {
											CCV = CC.concat(Character.toString(this.matras[isMatra(ch[i])]));
											strArray.add(CCV);
											i++;
										} else {
											strArray.add(CC);
										}
									} else {
										strArray.add(CC);
									}
								} else {
									strArray.add(CH);
								}
							} else {
								strArray.add(CH);
							}
						} else {
							strArray.add(C);
						}
					} else {
						strArray.add(C);
					}
				} else {
					strArray.add(Character.toString(ch[i]));
					i++;
				}
			} while(i < ch.length);
		}
		return strArray;	
	} 

	/*****************************************************************************************
	 * Helper Methods
	 *****************************************************************************************/

	// To find symbols in the given string
	private int isSymbol(char ch) {
		for(int i = 0; i < this.symbols.length; i++) {
			if(this.symbols[i] == ch) return i;
		}
		return -1;
	}

	// To find vowels in the given string
	private int isVowel(char ch) {	
		for(int i = 0; i < this.vowels.length; i++) {
			if(this.vowels[i] == ch) return i;
		}
		return -1;
	}

	// To find consonants in the given string
	private int isConsonant(char ch) {
		for(int i = 0; i < this.consonants.length; i++) {
			if(this.consonants[i] == ch) return i;
		}
		return -1;
	}

	// To find extra consonants type especially for Hindi and Gujarati
	// Ex: ऱ = र + ़
	private int isSplConsonant(char ch) {
		if(ch == '\0') {
			return -1;
		} else {
			for(int i = 0; i < this.splCons.length; i++) {
				if(this.splCons[i] == ch) return i;
			}
		}
		return -1;
	}


	// To find consonants in the given string
	private int isMatra(char ch) {
		for(int i = 0; i < this.matras.length; i++) {
			if(this.matras[i] == ch) return i;
		}
		return -1;
	}

	// To find extra matra type especially for Hindi and Gujarati
	// Ex: में = म - े-ं
	private int isSplMatra(char ch) {
		for(int i = 0; i < this.splMatra.length; i++) {
			if(this.splMatra[i] == ch) return i;
		}
		return -1;
	}

	// To find consonants in the given string
	private int isHalanth(char ch) {
		for(int i = 0; i < this.halanth.length; i++) {
			if(this.halanth[i] == ch) return i;
		}
		return -1;
	}
}