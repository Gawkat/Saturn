package com.github.gawkat.saturn;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.github.gawkat.saturn.view.LoaderGUI;

/**
 * @author Gawkat
 *
 */
public class Saturn {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		// TODO System specifics
		new LoaderGUI();
	}

}
