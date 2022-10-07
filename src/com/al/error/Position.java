package com.al.error;

public class Position {
	public int column;
	public int line;
	public int index;
	
	public String filename;
	public String filetext;

	public Position(int index, int line,int column,String filename,String filetext) {
		super();
		this.column = column;
		this.line = line;
		this.index = index;
		this.filetext = filetext;
		this.filename = filename;
	}
	
	public Position advance(char currentChar){
		index++;
		column ++;
		
		if (currentChar =='\n') {
			line++;	
			column = 0;
		}
		return this;
	}
	
	public Position copy() {
		return new Position(this.index,this.line,this.column,filename,filetext);
	}
}
