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

	private MainWindow mainWindow =null;
	private JComboBox fileNameComboBox = null;

	    
		public FileChooser(String username,MainWindow frame){  
	    	super(frame.getJFrame(), "���ļ�", true);
	    	this.mainWindow=frame;
	    	setBounds(120,120,200,200);
	    	setLayout(new GridLayout(2, 2));
	    	JLabel name = new JLabel("�ļ���:");
	    	fileNameComboBox=new JComboBox(); 
	    	
	    	Iterator<String> list=frame.getFileList().iterator();
	    	while(list.hasNext()){
	    		fileNameComboBox.addItem(list.next());
	    	}
	    	
	    	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    	
	    	
	    	
	    	JButton chose = new JButton("ȷ��");
	    	
	    	JButton quit= new JButton("ȡ��");
	    	
	    	add(name);
	    	add(fileNameComboBox);
	    	add(chose);
	    	add(quit);
	    	chose.addActionListener(this);
	    	quit.addActionListener(this);
	    	setBounds(120,120,100,100);
	    	setSize(500,120);
	    	
	    	
	    }  
	    @Override  
	    public void actionPerformed(ActionEvent e) {  
	    	String cmd = e.getActionCommand();
	    	
	    	if(cmd.equals("ȷ��")){
	    		mainWindow.openFile((String)fileNameComboBox.getSelectedItem());
	    		
	    		System.out.println("ȷ��");
	    		this.dispose();
	    	}else if(cmd.equals("ȡ��")){
	    		
	    		
	    		System.out.println("ȡ��");
	    		this.dispose();
	    	}
	      
	          
	    }  

	
	  
	
	
	
	
	
}