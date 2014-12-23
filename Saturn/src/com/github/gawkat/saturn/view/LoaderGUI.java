package com.github.gawkat.saturn.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * @author Gawkat
 *
 */
public class LoaderGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private final JLabel label;

	public LoaderGUI() {
		// TODO
		super("Saturn");
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Label
		label = new JLabel("Drop jar/class here", SwingConstants.CENTER);
		label.setFont(new Font("Verdana", Font.BOLD, 24));
		label.setForeground(Color.GRAY);
		label.setDropTarget(new DropTarget() {
			private static final long serialVersionUID = 1L;

			public synchronized void drop(DropTargetDropEvent evt) {
				try {
					evt.acceptDrop(DnDConstants.ACTION_COPY);
					@SuppressWarnings("unchecked")
					List<File> droppedFiles = (List<File>) evt
							.getTransferable().getTransferData(
									DataFlavor.javaFileListFlavor);
					for (File file : droppedFiles) {
						System.out.println(file.getAbsolutePath());
						// TODO
						MainGUI.init(file);
						dispose();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		add(label);
		setVisible(true);
	}

}
