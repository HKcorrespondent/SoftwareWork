package ui;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InAndOutPutTextArea extends JScrollPane{
	JTextArea textArea =new JTextArea();
	String title ="";
	
	
	public InAndOutPutTextArea(String title){
		this.title=title;
		setViewportView(textArea);
		
		
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		
		
	}
	
	
	
	





	public JTextArea getJTextArea(){
		return textArea;
	}
	public String getTitle(){
		return title;
	}
}
