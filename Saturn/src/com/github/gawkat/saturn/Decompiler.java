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
		if (Saturn.isWindows()) {
			return CLIHandler.runWindowsCommand(command);
		} else {
			return CLIHandler.runUnixCommand(command);
		}

	}
}
