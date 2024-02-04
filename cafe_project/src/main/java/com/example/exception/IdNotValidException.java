package com.example.exception;

public class IdNotValidException extends RuntimeException {

	private static int Sourceid;
	private String sourcevalue;
	private String sourcefield;

	public IdNotValidException(String sourcefield, String sourcevalue, int sourceid) {
		super("Id is not valid:" + Sourceid);
		Sourceid = sourceid;
		this.sourcevalue = sourcevalue;
		this.sourcefield = sourcefield;
	}

	public static int getSourceid() {
		return Sourceid;
	}

	public String getSourcevalue() {
		return sourcevalue;
	}

	public String getSourcefield() {
		return sourcefield;
	}

}
