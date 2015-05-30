package com.github.gawkat.saturn;

import java.io.File;

import com.github.gawkat.saturn.util.CLIHandler;

/**
 * @author Gawkat
 *
 */
public class Decompiler {

	public static String decompile(File file) {
		// TODO YEAH HOLY SHIT A LOT TODO
		String command = "javap " + file.getAbsolutePath();
		String unProcessed;
		if (Saturn.isWindows()) {
			unProcessed = CLIHandler.runWindowsCommand(command);
		} else {
			unProcessed = CLIHandler.runUnixCommand(command);
		}
		return stripMetadata(unProcessed);
	}

	// Removes "Compiled from"
	private static String stripMetadata(String text) {
		String lines[] = text.split("\\r?\\n");
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < lines.length; i++) {
			if (i != 0 && i != lines.length) {
				stringBuilder.append(lines[i] + System.getProperty("line.separator"));
			} else if (i == lines.length) {
				stringBuilder.append(lines[i]);
			}
		}
		return stringBuilder.toString();
	}
}