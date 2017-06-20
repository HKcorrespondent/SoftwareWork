package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class FileChooser extends  JDialog  implements ActionListener {

	


	    public FileChooser(String username,MainFrame frame){  
	    	super(frame, "123", true);
	    	JButton jb= new JButton("quit");
	    	this.add(jb);
	    	jb.addActionListener(this);
	    	setBounds(120,120,100,100);
	    	
	    	
	    	
	    }  
	    @Override  
	    public void actionPerformed(ActionEvent e) {  
	    	this.dispose();
	      
	          
	    }  

	
	    public static void main(String[] args) {  
	          
	    }  
	
	
	
	
	
}