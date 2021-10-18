/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokeniser;

/**
 *
 * @author hectormgerardo
 */
public class Token {
	private String lexeme;
	private int nToken;
	private int row;
	private int col;
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Token() {
	}

	public Token(String lexeme, int nToken, int row, int col, String type) {
		this.lexeme = lexeme;
		this.nToken = nToken;
		this.row = row;
		this.col = col;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Token{" + "lexeme=" + lexeme + ", n=" + nToken + ", line=" + row + ", col=" + col + ", type=" + type + '}';
	}

	public String getLexeme() {
		return lexeme;
	}

	public void setLexeme(String lexeme) {
		this.lexeme = lexeme;
	}

	public int getnToken() {
		return nToken;
	}

	public void setnToken(int nToken) {
		this.nToken = nToken;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
	
		
}
