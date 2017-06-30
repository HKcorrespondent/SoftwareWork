package ui;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import ui.MainWindow.CodeAggregate;

public class CodeTextArea extends JScrollPane{
	private JTextArea textArea =new JTextArea();
	private String title ="";
	private CodeAggregate file = null ;
	private final int  holdTimes = 15;
	private HoldStringForUndoOrRedo holdString;
	private boolean textOperanderIsHuman = true; 
	public CodeTextArea(String title,CodeAggregate file){
		this.file  = file;
		this.title = title;
		holdString = new HoldStringForUndoOrRedo(holdTimes);
		
		
		setViewportView(textArea);
		
		
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		textArea.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				if(textOperanderIsHuman){
					holdString.addNewString(getText());
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				if(textOperanderIsHuman){
					holdString.addNewString(getText());
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				
				
			}
		});
		
	}
	public CodeAggregate getCodeAggregate(){
		return this.file;
	}
	public String getFileTotalName(){
		return title;
	}
	public void undo(){
		
		textOperanderIsHuman = false;
		setText(holdString.getLastString()) ;
		textOperanderIsHuman = true ;
	}
	public void redo(){
		
		textOperanderIsHuman = false;
		setText(holdString.getNextString()) ;
		textOperanderIsHuman = true ;
	}
//	public JTextArea getJTextArea(){
//		return textArea;
//	}
	public String getText() {
		// TODO Auto-generated method stub
		
		return textArea.getText();
	}
	public void setText(String s) {
		// TODO Auto-generated method stub
		
		textArea.setText(s);
	}
	private class HoldStringForUndoOrRedo{
		private final int  holdTimes;
		private String[] record ;
		private int oldestRecordPtr ;
		private int newestRecordPtr ;
		private int nowRecordPtr ;
		HoldStringForUndoOrRedo(int holdTime){
			this.holdTimes = holdTime;
			this.record    = new String[holdTime];
			
			record[0] = getText();
			
			oldestRecordPtr = 0;
			newestRecordPtr = 0;
			nowRecordPtr    = 0;
		}
		private String getNextString(){
			if(newestRecordPtr!=nowRecordPtr){
				nowRecordPtr = ptrAddOne(nowRecordPtr);
				return record[nowRecordPtr];
			}else{
				return record[newestRecordPtr];
			}
		}
		private String getLastString(){
			if(oldestRecordPtr!=nowRecordPtr){
				nowRecordPtr = ptrSubOne(nowRecordPtr);
				return record[nowRecordPtr];
			}else{
				return record[oldestRecordPtr];
			}
			
			
		}
		private void addNewString(String string){
			nowRecordPtr = ptrAddOne(nowRecordPtr);
			newestRecordPtr = nowRecordPtr ;
			if(nowRecordPtr==oldestRecordPtr){
				oldestRecordPtr = ptrAddOne(oldestRecordPtr);
			}
			record[nowRecordPtr] = string ;
			
		}
		private int ptrAddOne(int ptr){
			ptr++;
			if(ptr == holdTimes){
				ptr = 0;
			}
			return ptr;		
		}
		private int ptrSubOne(int ptr){
			ptr--;
			if(ptr == -1){
				ptr = holdTimes-1;
			}
			return ptr;		
		}
	}
}
		