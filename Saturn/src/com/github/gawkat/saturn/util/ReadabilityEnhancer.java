package com.github.gawkat.saturn.util;

/**
 * @author Gawkat
 *
 */
public class ReadabilityEnhancer {

	// TODO make prettier

	public String enhance(String text) {
		String processed = text.replaceAll(" java.lang.String ", " String ");
		processed = processed.replaceAll(" java.lang.Boolean ", " boolean ");
		return processed;
	}

	public String reverse(String text) {
		String processed = text.replaceAll(" String ", " java.lang.String ");
		processed = processed.replaceAll(" boolean ", " java.lang.Boolean ");
		return processed;
	}

}
