package ui;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MyTextArea extends JScrollPane{
	JTextArea textArea =new JTextArea();
	public MyTextArea(){
		
		setViewportView(textArea);
		
		
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		
		
	}
	
	public JTextArea getJTextArea(){
		return textArea;
	}
}
