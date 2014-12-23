package com.github.gawkat.saturn.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Gawkat
 *
 */
public class CLIHandler {

	public static String runWindowsCommand(String command) {
		try {
			StringBuilder output = new StringBuilder();
			Runtime rt = Runtime.getRuntime();
			String[] commands = { "cmd.exe", "/C", command };
			Process proc;
			proc = rt.exec(commands);

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(
					proc.getInputStream()));

			// read the output from the command
			String s = null;
			while ((s = stdInput.readLine()) != null) {
				output.append(s).append("\n");
			}
			return output.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Unknown";

	}

	public static String runUnixCommand(String command) {
		// Does this work?
		try {
			StringBuilder output = new StringBuilder();
			Process p = Runtime.getRuntime().exec(command);
			p.waitFor();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					p.getInputStream()));

			String s = null;
			while ((s = reader.readLine()) != null) {
				output.append(s).append("\n");
			}
			return output.toString();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Unknown";
	}

}
