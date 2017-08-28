package com.sp.wordcreator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * @author Sasmita
 *
 */
public class HTMLTextEditor extends Application {

	private final String INITIAL_TEXT = " ";
	public static String sName =""; 
	
	private JOptionPane pane;
	private JDialog dialog;
	private Timer timer;

	public static void main(String[] args) {
		
		new HTMLTextEditor().start();
		
	}
	
	public void start()
	{
		try {
			sName = ScreenclipTaker.takeScreenShot();
			launch();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Message: " + "", JOptionPane.INFORMATION_MESSAGE);
		} 
		
	}
	

	@Override
	public void start(final Stage stage) {
		stage.setTitle("Text Editor");
		stage.setWidth(1000);
		stage.setHeight(300);

		// stage.requestFocus();

		VBox root = new VBox();
		root.setPadding(new Insets(8, 8, 8, 8));
		root.setSpacing(5);
		root.setAlignment(Pos.BOTTOM_LEFT);
		// root.requestFocus();
		final HTMLEditor htmlEditor = new HTMLEditor();
		htmlEditor.setPrefHeight(245);
		htmlEditor.setHtmlText(INITIAL_TEXT);
		htmlEditor.requestFocus();
		Scene scene = new Scene(htmlEditor);

		Button showHTMLButton = new Button("Save in Document");
		root.setAlignment(Pos.CENTER);

		showHTMLButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.out.println(htmlEditor.getHtmlText());
				// htmlEditor.requestFocus();
				try {
					stage.hide();
					new WriteContentsToDoc().writeContents(htmlEditor.getHtmlText().toString(),sName);
					
					timer = new Timer(2000, closeJDialog);
					timer.start();
					pane = new JOptionPane("Successfully placed the screenshot :)");
					dialog = pane.createDialog("Message");
					//JOptionPane.showMessageDialog(null, "Successfully placed the screenshot :)", "Message: " + "", JOptionPane.INFORMATION_MESSAGE);
					dialog.setVisible(true);
					
					System.exit(0);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Exception :"+e1.getMessage(), "Exception: " + "", JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});

		root.getChildren().addAll(htmlEditor, showHTMLButton);
		scene.setRoot(root);

		stage.setScene(scene);
		stage.show();
	

	}
	
	
	java.awt.event.ActionListener closeJDialog = new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
			if (dialog.isShowing()) {
				dialog.dispose();
			}
			
		}
	};


}