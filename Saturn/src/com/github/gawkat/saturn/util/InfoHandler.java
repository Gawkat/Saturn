package com.github.gawkat.saturn.util;

import java.io.File;

import com.github.gawkat.saturn.Saturn;

/**
 * @author Gawkat
 *
 */
public class InfoHandler {

	public static String getFileSize(File file) {
		if (file.length() > 1000000) {
			return file.length() / 1000000 + " MB";
		} else if (file.length() > 1000) {
			return file.length() / 1000 + " kB";
		} else {
			return file.length() + " Bytes";
		}
	}

	public static String getJavaVersion(File file) {
		// TODO fix for jar
		// TODO fix
		if (Saturn.isWindows()) {
			String winCmd = "javap -verbose " + file.getAbsolutePath()
					+ " | findstr \"major\"";
			return convertVersion(CLIHandler.runWindowsCommand(winCmd));
		} else {
			String unixCmd = "javap -verbose " + file.getAbsolutePath()
					+ " | grep \"major\"";
			return convertVersion(CLIHandler.runUnixCommand(unixCmd));
		}
	}

	private static String convertVersion(String input) {
		String strippedInput = input.replaceAll("[^0-9]", "");
		switch (strippedInput) {
		case "46":
			return "1.2";
		case "47":
			return "1.3";
		case "48":
			return "1.4";
		case "49":
			return "5";
		case "50":
			return "6";
		case "51":
			return "7";
		case "52":
			return "8";
		default:
			return "Unknown";
		}
	}

}
