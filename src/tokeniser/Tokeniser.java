/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokeniser;

import java.util.ArrayList;

/**
 *
 * @author hectormgerardo
 */
public class Tokeniser {
	ArrayList<Token> tokenList = new ArrayList();
	public Tokeniser(ArrayList<Token> tokenList){
		this.tokenList=tokenList;
	}
	
	public void tokenise(String input){
		int state = 0;
		int decimal = 0;
		int nToken = 0;
		String lexeme = "";
		String type = "";
		String[] lines = splitter(input+"\n",'\n');
		boolean closing=false;
		//	CYCLE OVER LINES
		for (int i = 0; i < lines.length; i++) {
			
			//	CYCLE OVER COLUMNS
			for (int j = 0; j < lines[i].length(); j++) {
				int now,future = -1;
				now = lines[i].codePointAt(j);
				if (state == 0){state = transition(now);}
				
				
				try{
					future = lines[i].codePointAt(j+1);
				}
				catch(Exception e){					
				}
				
				
				switch(state){
					case 1:
						//	TYPE: WORD
						lexeme = lexeme + lines[i].charAt(j);
						if ((future > 96 && future < 123) || (future > 64 && future <91) || future==95 || (future > 47 && future < 58))
							state=1;
						else{
							nToken = 1;
							type = "WORD";
							state = 0;
						}
						
						break;
					case 2:
						//	TYPE: INT
						lexeme = lexeme+lines[i].charAt(j);
						if (future > 47 && future < 58){
							state = 2;
						}
						else if (future == 46){
							state = 3;
						}
						else{
							nToken = 2;
							type = "INTEGER";
							state = 0;
						}
						break;
					case 3:
						lexeme = lexeme+lines[i].charAt(j);
						if (future > 47 && future < 58) state = 3;
						else if (future == 46) state = 504;
						else{
							nToken = 3;
							type = "REAL";
							state = 0;
						}
						break;
					case 4:
						lexeme = lexeme+lines[i].charAt(j);
						if(closing){
							if(now==34){
								nToken = 34;
								type = "STRING";
								state = 0;
								closing=false;
							}
						}
						else if(!closing && future==34){
							state = 4;
							closing = true;
						}
						break;
					case 5:
						lexeme = lexeme + lines[i].charAt(j);
						if (future==59)
							state=5;
						else{
							nToken = 5;
							type = "SEMICOLON";
							state = 0;
						}
						break;
					case 6:
						lexeme = lexeme + lines[i].charAt(j);
						if (now==43&&future==43){
							state=66;
						}
						else if (future==61){
							state=67;
						}
						else if (future==42 || future==47 || future==37 || future==94)
							state=502;
						else{
							nToken = 6;
							type = "PLUS";
							state = 0;
						}
						break;
					case 66:
						lexeme = lexeme + lines[i].charAt(j);
						if (future==42 || future==43 || future==45 || future==47 || future==37 || future==94)
							state=502;
						else{
							nToken = 66;
							type = "DPLUS";
							state = 0;
						}
						break;
					case 67:
						lexeme = lexeme + lines[i].charAt(j);
						if (future==42 || future==43 || future==45 || future==47 || future==37 || future==94 || future==61)
							state=502;
						else{
							nToken = 67;
							type = "PLEQ";
							state = 0;
						}
						break;
					case 7:
						lexeme = lexeme + lines[i].charAt(j);
						if (future==42)
							state=77;
						else if (future==61)
							state=78;
						else{
							nToken = 7;
							type = "MUL";
							state = 0;
						}
						break;
					case 77:
						lexeme = lexeme + lines[i].charAt(j);
						if (future==43 || future==45 || future==47 || future==37 || future==94 || future==61)
							state=502;
						else{
							nToken = 77;
							type = "DMUL";
							state = 0;
						}
						break;
					case 78:
						lexeme = lexeme + lines[i].charAt(j);
						if (future==61 || future==42 || future==47 || future==37 || future==94 || future==60 || future==62)
							state=502;
						else{
							nToken = 78;
							type = "MUEQ";
							state = 0;
						}
						break;
					case 8:
						lexeme = lexeme + lines[i].charAt(j);
						if (future==61)
							state=87;
						else if (future==47)
							state=502;
						else{
							nToken = 8;
							type = "DIV";
							state = 0;
						}
						break;
					case 87:
						lexeme = lexeme + lines[i].charAt(j);
						if (future==61 || future==42 || future==47 || future==37 || future==94 || future==60 || future==62)
							state=502;
						else{
							nToken = 87;
							type = "DIEQ";
							state = 0;
						}
						break;
					case 9:
						lexeme = lexeme + lines[i].charAt(j);
						if (future==45)
							state=99;
						else if (future==61)
							state=97;
						else{
							nToken = 9;
							type = "MINUS";
							state = 0;
						}
						break;
					case 99:
						lexeme = lexeme + lines[i].charAt(j);
						if (future==42 || future==43 || future==45 || future==47 || future==37 || future==94)
							state=502;
						else{
							nToken = 99;
							type = "DMINUS";
							state = 0;
						}
						break;
					case 97:
						lexeme = lexeme + lines[i].charAt(j);
						if (future==42 || future==43 || future==45 || future==47 || future==37 || future==94 || future==61)
							state=502;
						else{
							nToken = 97;
							type = "MIEQ";
							state = 0;
						}
						break;
					case 10:
						lexeme = lexeme + lines[i].charAt(j);
						if (future>41 && future<48 || future>59 && future<65 || future==37 || future==124 || future==38 || future==94) state=502;
						else{
							nToken = 10;
							type = "MOD";
							state = 0;
						}
						break;
					case 11:
						lexeme = lexeme + lines[i].charAt(j);
						if (future>41 && future<48 || future>59 && future<65 || future==37 || future==124 || future==38) state=502;
						else if (future==94) state=503;
						else{
							nToken = 11;
							type = "POW";
							state = 0;
						}
						break;
					case 12:
						lexeme = lexeme + lines[i].charAt(j);
						if (future==42 || future==43 || future==45 || future==47 || future==37 || future==94)
							state=502;
						else if (future==61) state=127;
						else if (future==60) state=128;
						else{
							nToken = 12;
							type = "LT";
							state = 0;
						}
						break;
					case 127:
						lexeme = lexeme + lines[i].charAt(j);
						if (future==42 || future==43 || future==45 || future==47 || future==37 || future==94) state=502;
						else if (future==61) state=503;
						else{
							nToken = 137;
							type = "EQLT";
							state = 0;
						}
						break;
					case 128:
						lexeme = lexeme + lines[i].charAt(j);
						if (future==60){state=503;}
						else if (future==42 || future==43 || future==45 || future==47 || future==37 || future==94)
							state=502;
						else{
							nToken = 128;
							type = "LTLT";
							state = 0;
						}
						break;
					case 13:
						lexeme = lexeme + lines[i].charAt(j);
						System.out.println("=");
						if (future==61)
							state=138;
						else if (future==62)
							state=139;
						else if (future==60)
							state=137;
						else if (future==42 || future==47 || future==37 || future==94){
							state=503;
						}
						else{
							nToken = 13;
							type = "EQ";
							state = 0;
						}
						break;
					case 137:
						lexeme = lexeme + lines[i].charAt(j);
						if (future==61 || future==42 || future==47 || future==37 || future==94 || future==60 || future==62)
							state=503;
						else{
							nToken = 137;
							type = "EQLT";
							state = 0;
						}
						break;
					case 138:
						lexeme = lexeme + lines[i].charAt(j);
						if ( future==42 || future==47 || future==37 || future==94 || future==60 || future==62)
							state=503;
						else if (now==61&&future==61){state=503;}
						else{
							nToken = 138;
							type = "EQEQ";
							state = 0;
						}
						break;
					case 139:
						lexeme = lexeme + lines[i].charAt(j);
						if (future==61 || future==42 || future==47 || future==37 || future==94 || future==60 || future==62)
							state=503;
						else{
							nToken = 139;
							type = "EQMT";
							state = 0;
						}
						break;
					case 14:
						lexeme = lexeme + lines[i].charAt(j);
						if (future==42 || future==43 || future==45 || future==47 || future==37 || future==94)
							state=502;
						else if (future==61) state=147;
						else if (future==62) state=148;
						else{
							nToken = 12;
							type = "LT";
							state = 0;
						}
						break;
					case 147:
						lexeme = lexeme + lines[i].charAt(j);
						if (future==62){state=503;}
						else if (future==61 || future==42 || future==47 || future==37 || future==94 || future==60 || future==62)
							state=502;
						else{
							nToken = 139;
							type = "EQMT";
							state = 0;
						}
						break;
					case 148:
						lexeme = lexeme + lines[i].charAt(j);
						if (future==62){state=503;}
						else if (future==61 || future==42 || future==47 || future==37 || future==94 || future==60)
							state=502;
						else{
							nToken = 148;
							type = "MTMT";
							state = 0;
						}
						break;
						
					case 15:
						lexeme = lexeme + lines[i].charAt(j);
						if (((future>33 && future<40) || future==41 || future==42 || 
							future==44 || future==46 || future==47 || (future>57 && future<61) || (future>61 && future<65) || 
							(future>90 && future<95) || future==96 || (future>122 && future<127))) 
							state=502;
						else if (future==33)state=503;
						else if (future==61) state=159;
						else{
							nToken = 15;
							type = "NOT";
							state = 0;
						}
						break;
					case 159:
						lexeme = lexeme + lines[i].charAt(j);
						if (((future>33 && future<40) || future==41 || future==42 || 
							future==44 || future==46 || future==47 || (future>57 && future<61) || (future>61 && future<65) || 
							(future>90 && future<95) || future==96 || (future>122 && future<127))) 
							state=502;
						else {
							nToken=159;
							type="NEQ";
							state = 0;
						}
						break;
					case 16:
						lexeme = lexeme + lines[i].charAt(j);
						if (future!=124 && ((future>33 && future<40) || future==41 || future==42 || 
							future==44 || future==46 || future==47 || (future>57 && future<65) || 
							(future>90 && future<95) || future==96 || (future>122 && future<127))) 
							state=502;
						else if (future==124){
							state=169;
						}
						else {
							nToken=16;
							type="OR";
							state = 0;
						}
						break;
					case 169:
						lexeme = lexeme + lines[i].charAt(j);
						if (future!=124 && ((future>33 && future<40) || future==41 || future==42 || 
							future==44 || future==46 || future==47 || (future>57 && future<65) || 
							(future>90 && future<95) || future==96 || (future>122 && future<127))) 
							state=502;
						else if(future==124) state=503;
						else {
							nToken=16;
							type="OR";
							state = 0;
						}
						break;
					case 17:
						lexeme = lexeme + lines[i].charAt(j);
						if (future!=38 && ((future>33 && future<40) || future==41 || future==42 || 
							future==44 || future==46 || future==47 || (future>57 && future<65) || 
							(future>90 && future<95) || future==96 || (future>122 && future<127))) 
							state=502;
						else if (future==38){
							state=179;
						}
						else {
							nToken=17;
							type="AND";
							state = 0;
						}
						break;
					case 179:
						lexeme = lexeme + lines[i].charAt(j);
						if (future!=38 && future!=32&& ((future>33 && future<40) || future==41 || future==42 || 
							future==44 || future==46 || future==47 || (future>57 && future<65) || 
							(future>90 && future<95) || future==96 || (future>122 && future<127))) 
							state=502;
						else if(future==38) state=503;
						else {
							nToken=17;
							type="AND";
							state = 0;
						}
						break;
					case 18:
						lexeme = lexeme + lines[i].charAt(j);
						System.out.println("XD");
						if (future!=126 && future!=32 && (!(future > 96 && future < 123) || (future > 64 && future <91) || future==95)) 
							state=502;
						else if (future==126){
							state=503;
						}
						else {
							nToken=18;
							type="TILD";
							state = 0;
						}
						break;
					case 19:
						lexeme = lexeme + lines[i].charAt(j);
						if (future!=36 && future!=32 && (!(future > 96 && future < 123) || (future > 64 && future <91) || future==95)) 
							state=502;
						else if (future==36){
							state=503;
						}
						else {
							nToken=19;
							type="CASH";
							state = 0;
						}
						break;
					case 20:
						lexeme = lexeme + lines[i].charAt(j);
						if (future!=35 && ((future>33 && future<40) || future==41 || future==42 || 
							future==44 || future==46 || future==47 || (future>57 && future<65) || 
							(future>90 && future<95) || future==96 || (future>122 && future<127))) 
							state=502;
						else if (future==35){
							state=503;
						}
						else {
							nToken=20;
							type="HASH";
							state = 0;
						}
						break;
						
					case 23:
						lexeme = lexeme + lines[i].charAt(j);
						if (future==44)state=504;
						else{
							nToken=23;
							type="COMMA";
							state = 0;
						}
						break;
					case 24:
						nToken=23;
						type="OBRA";
						state = 0;
						break;
					case 25:
						type="CBRA";
						state = 0;
						break;
					case 26:
						nToken=23;
						type="OBRK";
						state = 0;
						break;
					case 27:
						nToken=23;
						type="CBRK";
						state = 0;
						break;
					case 28:
						nToken=23;
						type="OPAR";
						state = 0;
						break;
					case 29:
						nToken=23;
						type="CPAR";
						state = 0;
						break;
					case 777:
						state = -2;
						break;
					case 500:
						lexeme = lexeme+lines[i].charAt(j);
						nToken = 500;
						type = "ERROR::unknown token";
						state = 0;
						break;
					case 501:
						lexeme = lexeme+lines[i].charAt(j);
						nToken = 501;
						type = "ERROR::bad formed string";
						state = 0;
						break;
					case 502:
						lexeme = lexeme+lines[i].charAt(j);
						nToken = 502;
						type = "ERROR:: operator not allowed";
						state = 0;
						break;
					case 503:
						lexeme = lexeme+lines[i].charAt(j);
						nToken = 503;
						type = "ERROR::too many operator";
						state = 0;
						break;
					case 504:
						lexeme = lexeme+lines[i].charAt(j);
						nToken = 504;
						type = "ERROR::bad punctuation";
						state = 0;
						break;
				}
				
				if(state == 0){
					this.tokenList.add(new Token(lexeme, nToken, i+1, j+1, type));
					lexeme = "";
					type = "";					
				}
				if(state == -2){state = 0;}
			}
			
		}
		
	}
	public int transition(int n){

		// QUOTATION MARKS
		if (n==34) return 4;

		//	SEMICOLON
		if (n==59) return 5;
		
		//	OPERATORS
		if (n==43) return 6;	//	+
		if (n==42) return 7;	//	*
		if (n==47) return 8;	//	/
		if (n==45) return 9;	//	-
		if (n==37) return 10;	//	%
		if (n==94) return 11;	//	^
		if (n==60) return 12;	//	<
		if (n==61) return 13;	//	=
		if (n==62) return 14;	//	>
		if (n==33) return 15;	//	!
		if (n==124)	return 16;	//	|
		if (n==38) return 17;	//	&
		if (n==126) return 18;	//	~
		if (n==36) return 19;	//	$
		if (n==35) return 20;	//	#
		
		//==== YET TO BE INCLUDED ====================
		if (n==63) return 21;	//	?
		if (n==64) return 22;	//	@
		//============================================

		if (n==44) return 23;	//	,
		if(n==123) return 24;	//	{
		if(n==125) return 25;	//	}
		if(n==91) return 26;	//	[
		if(n==93) return 27;	//	]
		if(n==40) return 28;	//	(
		if(n==41) return 29;	//	)
		
		// WORDS/FUNCTIONS/VARIABLE NAMES INCLUDING UNDERSCORE, NOT NUMBERS
		if ((n>96 && n < 123) || (n>64 && n<91) || n==95) return 1;
		// NUMBERS
		else if ( n>47 && n <58) return 2;

		// DOT/PUNKT/PUNTO/CIPHER/POINT
		else if (n==46) return 31;
		
		// WHITESPACES
		else if(n==32 || n==13 || n==9) 
			return 777;
		return 0;
	}
	
	public String[] splitter (String input, char separator){
		String line = "";
		int counter = 0;
		
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == separator) counter++;
		}
		String[] stringes = new String[counter];
		counter=0;
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) != separator){
				line = line + String.valueOf(input.charAt(i));
			}
			else{
				stringes[counter] = line;
				counter++;
				line = "";
			}
		}
		return stringes;
	}
}