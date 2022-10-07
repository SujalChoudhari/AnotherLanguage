package com.al.error;

import java.util.ArrayList;

public class Error {
	public String name;
	public String details;
	public Position startPosition;
	public Position endPosition;

	public static ArrayList<Error> errors = new ArrayList<>(5);

	public Error(String name, Position start, Position end, String details) {
		this.name = name;
		this.details = details;
		this.startPosition = start;
		this.endPosition = end;
		Error.errors.add(this);
	}

	public String toString() {
		String str = "[" + name + "] " + details;
		str += "\nFile " + startPosition.filename + ", line " + startPosition.line + 1;
		str += "\n\n" + defectPointerString(startPosition.filetext, startPosition, endPosition);
		return str;
	}

	public static boolean isSafe() {
		if (errors.size() <= 0)
			return true;
		return false;
	}

	public static String diaplayAll() {
		String str = "";
		for (Error e : Error.errors) {
			str += "\n" + e;
		}
		errors.clear();
		return str;
	}

	private String defectPointerString(String text, Position startPosition, Position endPosition) {
		String result = "";

		// Calculate indices
		int indexStart = Math.max(text.lastIndexOf('\n'), 0);
		int indexEnd = text.indexOf('\n', indexStart + 1);
		if (indexEnd < 0)
			indexEnd = text.length();

		// Generate each line
		int lineCount = endPosition.line - startPosition.line + 1;
		for (int i = 0; i < lineCount; i++) {
			// Calculate line columns
			String line = text.substring(indexStart, indexEnd);

			int columnStart = 0;
			if (i == 0)
				columnStart = startPosition.column;

			int columnEnd = line.length();
			if (i == lineCount)
				columnEnd = endPosition.column;

			// Append to result
			result += line + '\n';
			for (int j = 0; j < columnStart; j++) {
				result += " ";
			}

			for (int j = 0; j < (columnEnd - columnStart); j++) {
				result += "~";
			}

			

		}
		return result;
	}
}
