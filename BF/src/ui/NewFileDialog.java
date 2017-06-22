package ui;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class NewFileDialog extends JDialog implements ActionListener{
	
	
	
	JComboBox fileTypeComboBox = null;
	MainWindow mainWindow = null;
	JTextField filename= null;
	public NewFileDialog(String username,MainWindow frame){  
		
    	super(frame.getJFrame(), "newFile", true);
    	this.mainWindow=frame;
    	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    	setBounds(120,120,100,100);
    	setLayout(new GridLayout(2, 3));
    	fileTypeComboBox=new JComboBox<String>();
    	JButton ok= new JButton("确定");
    	JButton quit = new JButton("取消");
    	JLabel nameLabel = new JLabel("文件名:");
    	nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
    	filename = new JTextField();
    	
    	String[] fileTyprArgs = frame.getTotalFileType();
    	
    	for(int i =0;i<fileTyprArgs.length;i++){
    		fileTypeComboBox.addItem(fileTyprArgs[i]);
    	}
    	
    	
    	
    	this.add(nameLabel);
    	
    	this.add(filename);
    	this.add(fileTypeComboBox);
    	this.add(new Label());
    	this.add(ok);
    	this.add(quit);
    	ok.addActionListener(this);
    	quit.addActionListener(this);
    	
    	
    	
    	
    }
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		
		
		
		
		
		
		
		if(cmd.equals("确定")){
			
			
			
			String message = mainWindow.creatnewFile(filename.getText(),(String)fileTypeComboBox.getSelectedItem());
			if(message.equals("成功创建!"))
			{
				this.dispose();
			}else{
				new JOptionPane().showMessageDialog(this, message);
			}
			
		}else if (cmd.equals("取消")){
			System.out.println();
			this.dispose();
		}
		
		
	}  
}
