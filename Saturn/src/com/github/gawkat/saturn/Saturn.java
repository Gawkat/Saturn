package com.github.gawkat.saturn;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.github.gawkat.saturn.view.LoaderGUI;

/**
 * @author Gawkat
 *
 */
public class Saturn {

	public Saturn() {
		loadUISettings();
		new LoaderGUI();
	}

	public static boolean isWindows() {
		return System.getProperty("os.name").startsWith("Windows");
	}

	private void loadUISettings() {
		try {
			// Set System L&F
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Saturn();
	}
}
