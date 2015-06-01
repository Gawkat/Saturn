package com.github.gawkat.saturn.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rtextarea.RTextScrollPane;

import com.github.gawkat.saturn.Decompiler;
import com.github.gawkat.saturn.util.InfoHandler;

/**
 * @author Gawkat
 *
 */
public class MainGUI extends JFrame implements ActionListener {

	// TODO create in normal class file

	private static final long serialVersionUID = 1L;

	private JTabbedPane tabbedPane;

	private JPanel contentPane;
	private JMenuBar menuBar;

	private JMenu fileMenu;

	private JMenuItem open, exit;

	private JPanel infoPanel;
	private JPanel bytecodePanel;
	private JPanel mainPanel;

	private RSyntaxTextArea decompiledTextArea, bytecodeTextArea;

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

		// bytecodePanel
		bytecodePanel = new JPanel(new BorderLayout());
		bytecodeTextArea = new RSyntaxTextArea();
		bytecodeTextArea
				.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
		bytecodeTextArea.setCodeFoldingEnabled(true);
		bytecodeTextArea.setAntiAliasingEnabled(true);
		bytecodeTextArea.setEditable(false);
		RTextScrollPane dSP2 = new RTextScrollPane(bytecodeTextArea);
		dSP2.setFoldIndicatorEnabled(true);
		bytecodeTextArea.setText(Decompiler.dissasemble(file));

		bytecodePanel.add(dSP2, BorderLayout.CENTER);

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

		// Decompiled Text Pane
		decompiledTextArea = new RSyntaxTextArea();
		decompiledTextArea
				.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
		decompiledTextArea.setCodeFoldingEnabled(true);
		decompiledTextArea.setAntiAliasingEnabled(true);
		decompiledTextArea.setEditable(false);
		RTextScrollPane dSP = new RTextScrollPane(decompiledTextArea);
		dSP.setFoldIndicatorEnabled(true);
		decompiledTextArea.setText(Decompiler.decompile(file));

		try {
			Theme theme = Theme.load(getClass().getResourceAsStream(
					"/res/theme.xml"));
			theme.apply(decompiledTextArea);
			theme.apply(bytecodeTextArea);
		} catch (IOException e) {
			e.printStackTrace();
		}

		mainPanel.add(dSP, BorderLayout.CENTER);

		// Tabbed Pane
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Decompiled", mainPanel);
		tabbedPane.add("Bytecode", bytecodePanel);

		contentPane.add(tabbedPane, BorderLayout.CENTER);
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
