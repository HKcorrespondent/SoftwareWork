package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FileChooser extends  JDialog  implements ActionListener {

	


	    public FileChooser(String username,MainFrame frame){  
	    	super(frame, "���ļ�", true);
	    	setBounds(120,120,200,200);
	    	setLayout(new GridLayout(2, 2));
	    	JLabel name = new JLabel("�ļ���:");
	    	JComboBox fileName=new JComboBox(); 
	    	
	    	Iterator<String> list=frame.getFileList().iterator();
	    	while(list.hasNext()){
	    		fileName.addItem(list.next());
	    	}
	    	
	    	
	    	
	    	
	    	
	    	JButton chose = new JButton("ȷ��");
	    	
	    	JButton quit= new JButton("ȡ��");
	    	
	    	add(name);
	    	add(fileName);
	    	add(chose);
	    	add(quit);
	    	chose.addActionListener(this);
	    	quit.addActionListener(this);
	    	setBounds(120,120,100,100);
	    	setSize(500,180);
	    	
	    	
	    }  
	    @Override  
	    public void actionPerformed(ActionEvent e) {  
	    	String cmd = e.getActionCommand();
	    	
	    	if(cmd.equals("ȷ��")){
	    		System.out.println("ȷ��");
	    	}else if(cmd.equals("ȡ��")){
	    		System.out.println("ȡ��");
	    	}
	      
	          
	    }  

	
	  
	
	
	
	
	
}