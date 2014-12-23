package com.github.gawkat.saturn.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import com.github.gawkat.saturn.Decompiler;
import com.github.gawkat.saturn.util.InfoHandler;

public class MainGUI extends JFrame implements ActionListener {

	// TODO create in normal class file

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JMenuBar menuBar;

	private JMenu fileMenu;

	private JMenuItem open, exit;

	private JPanel infoPanel;
	private JPanel mainPanel;

	private JTextPane mainTextPane;

	Font largeLabelFont = new Font("Verdana", Font.BOLD, 16);
	Font smallLabelFont = new Font("Verdana", Font.PLAIN, 14);
	Font bodyFont = new Font("Consolas", Font.PLAIN, 12);

	public static void init(File file) {
		// TODO load file
		// DO THIS HERE
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI(file);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainGUI(File file) {
		super("Saturn - " + file.getName());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		// Menubar
		menuBar = new JMenuBar();
		// File
		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		open = new JMenuItem("Open File...");
		fileMenu.add(open);
		open.addActionListener(this);
		fileMenu.addSeparator();
		exit = new JMenuItem("Exit");
		fileMenu.add(exit);
		exit.addActionListener(this);
		fileMenu.add(exit);

		setJMenuBar(menuBar);

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		// InfoPanel
		infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		contentPane.add(infoPanel, BorderLayout.WEST);

		// TODO Class/Jar info
		JLabel nameLabel = new JLabel(file.getName());
		nameLabel.setFont(largeLabelFont);
		nameLabel.setForeground(Color.GRAY);
		nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		infoPanel.add(nameLabel);
		// Java version
		JLabel versionLabel = new JLabel("Compiler version: "
				+ InfoHandler.getJavaVersion(file));
		versionLabel.setFont(smallLabelFont);
		versionLabel.setForeground(Color.GRAY);
		versionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		infoPanel.add(versionLabel);
		// File size
		JLabel sizeLabel = new JLabel("Size: " + InfoHandler.getFileSize(file));
		sizeLabel.setFont(smallLabelFont);
		sizeLabel.setForeground(Color.GRAY);
		sizeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		infoPanel.add(sizeLabel);

		// MainPanel
		mainPanel = new JPanel(new BorderLayout());
		contentPane.add(mainPanel, BorderLayout.CENTER);

		// Main Text Pane
		mainTextPane = new JTextPane();
		mainTextPane.setEditable(false);
		mainTextPane.setFont(bodyFont);
		mainTextPane.setText(Decompiler.decompile(file));

		mainPanel.add(mainTextPane, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == open) {
			new LoaderGUI();
			dispose();
		}
		if (e.getSource() == exit) {
			System.exit(0);
		}
	}

}
