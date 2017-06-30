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
	    	super(frame.getJFrame(), "打开文件", true);
	    	this.mainWindow=frame;
	    	setBounds(120,120,200,200);
	    	setLayout(new GridLayout(2, 2));
	    	JLabel name = new JLabel("文件名:");
	    	fileNameComboBox=new JComboBox(); 
	    	
	    	Iterator<String> list=frame.getFileList().iterator();
	    	while(list.hasNext()){
	    		fileNameComboBox.addItem(list.next());
	    	}
	    	
	    	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    	
	    	
	    	
	    	JButton chose = new JButton("确定");
	    	
	    	JButton quit= new JButton("取消");
	    	
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
	    	
	    	if(cmd.equals("确定")){
	    		mainWindow.openFile((String)fileNameComboBox.getSelectedItem());
	    		
	    		System.out.println("确定");
	    		this.dispose();
	    	}else if(cmd.equals("取消")){
	    		
	    		
	    		System.out.println("取消");
	    		this.dispose();
	    	}
	      
	          
	    }  

	
	  
	
	
	
	
	
}