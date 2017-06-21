package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.omg.Messaging.SyncScopeHelper;

import rmi.RemoteHelper;


public class MainFrame extends JFrame {
	private JTextArea textArea;
	private JLabel resultLabel;
	private String username;
	private JFrame frame;
	private JTabbedPane tabbedPanel;
//	private ArrayList<String> fileList =null;
	
	public static void main(String[] s){
		new MainFrame("123456");
		
	}
	
	private void menuInit(){
		
		
		
		
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		JMenuItem newMenuItem = new JMenuItem("New");
		fileMenu.add(newMenuItem);
		JMenuItem openMenuItem = new JMenuItem("Open");
		fileMenu.add(openMenuItem);
		JMenuItem saveMenuItem = new JMenuItem("Save");
		fileMenu.add(saveMenuItem);
		JMenuItem runMenuItem = new JMenuItem("Run");
		fileMenu.add(runMenuItem);
		Font font=new Font(Font.DIALOG_INPUT, Font.BOLD, 50);
		menuBar.setFont(font);
		frame.setJMenuBar(menuBar);

		
		
		
		newMenuItem.addActionListener(new MenuItemActionListener());
		openMenuItem.addActionListener(new MenuItemActionListener());
		saveMenuItem.addActionListener(new SaveActionListener());
		runMenuItem.addActionListener(new MenuItemActionListener());
	}
	private void setFrameLookAndFeel(){
		String lookAndFeel ="javax.swing.plaf.metal.MetalLookAndFeel";


		try {
			UIManager.setLookAndFeel(lookAndFeel);
			SwingUtilities.updateComponentTreeUI(frame);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	public String creatnewFile(String filename,String fileType){
		
		
		
		
		if(filename.length()<3||filename.length()>15){
			return "文件长度必须在3-15之间!";
		}

	
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]+");
		Matcher matcher = pattern.matcher(username);
		if(!matcher.matches()){
			return "文件名含有非法字符!";
		}
		
		

		
		
		if(getFileList().contains(filename+"."+fileType)){
			//弹出提示已经有了这个文件
			
			return "该文件已存在!";
		}else{
			try {
				RemoteHelper.getInstance().getIOService().writeFile("",username , filename+"."+fileType);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//服务器端先创建文件
			
			tabbedPanel.addTab( filename+"."+fileType, new JTextArea());
			
			
			
			//本地创建
			return "成功创建!";
		}
		
	}
	//得到总的文件目录
	public ArrayList<String> getFileList(){
		ArrayList<String> fileList =new ArrayList<>();
		String fileNameString="";
		try {
			fileNameString = RemoteHelper.getInstance().getIOService().readFileList(username);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			
		}
		
		
	
		
		
		
		
		if(fileNameString.equals("")){
			
		}else{
			String[] fileNameArgs = fileNameString.split(" ");
			for(int i=0;i<fileNameArgs.length;i++){
				fileList.add(fileNameArgs[i]);
			}
			
			
			
			
		}
		
		return fileList;
		
		
	}
	
	public MainFrame(String username) {
		this.username=username;
		
		// 创建窗体
		frame = new JFrame("BF Client");
		frame.setLayout(new BorderLayout());
		
		
		setFrameLookAndFeel();
		
		menuInit();
		
		tabbedPanel = new JTabbedPane();
		
		
//		JTextArea  textArea = new JTextArea();
//		textArea.setMargin(new Insets(10, 10, 10, 10));
//		textArea.setBackground(Color.LIGHT_GRAY);
//		JTextArea  textArea1 = new JTextArea();
//		textArea1.setMargin(new Insets(10, 10, 10, 10));
//		textArea1.setBackground(Color.LIGHT_GRAY);
		
		
//		frame.add(textArea, BorderLayout.CENTER);
//		tabbedPanel.addTab("panel2", textArea1);
//		tabbedPanel.addTab("panel1", textArea);
		frame.add(tabbedPanel, BorderLayout.CENTER);
		
		// 显示结果
		resultLabel = new JLabel();
		resultLabel.setText("result");
		frame.add(resultLabel, BorderLayout.SOUTH);

		frame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				try {
					RemoteHelper.getInstance().getUserService().logout(username);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("OK!");
				System.exit(0);
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
				
			}
			
			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		frame.setSize(500, 400);
		frame.setLocation(400, 200);
		frame.setVisible(true);
	}
		
	class MenuItemActionListener implements ActionListener {
		/**
		 * 子菜单响应事件
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			
		
			if(cmd.equals("New")){
				newFileDialog newfile = new newFileDialog(username, MainFrame.this);
				newfile.setLocationRelativeTo(MainFrame.this.tabbedPanel);
				newfile.setSize(300, 100);
				newfile.setVisible(true);
				
				
			}else if (cmd.equals("Open")) {
//				textArea.setText("Open");
				 ArrayList<String> list = MainFrame.this.getFileList();
				
				
				
				
				FileChooser fc= new FileChooser(username, MainFrame.this);
				fc.setVisible(true);
				
				
				
				
			}  else if (cmd.equals("Run")) {
				
				
				
				int i=	tabbedPanel.getSelectedIndex();
				if(i>-1){
					JTextArea text =(JTextArea)tabbedPanel.getComponentAt(i);
					
					try {
						resultLabel.setText(RemoteHelper.getInstance().getRunservice().run(text.getText(), "1 2"));
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else{
					resultLabel.setText("没有文件可被执行!");
				}
			}
		}
	}

	class SaveActionListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int i=	tabbedPanel.getSelectedIndex();
			System.out.println(tabbedPanel.getTitleAt(i));
			resultLabel.setText("Save");
			
//			String code = textArea.getText();
//			try {
//				RemoteHelper.getInstance().getIOService().writeFile(code, "admin", "code");
//			} catch (RemoteException e1) {
//				e1.printStackTrace();
//			}
		}

	}
	//做的不好
	class newFileDialog extends JDialog implements ActionListener{
		MainFrame frame = null;
		JTextField filename= null;
		public newFileDialog(String username,MainFrame frame){  
			
	    	super(frame, "newFile", true);
	    	this.frame=frame;
	    	
	    
	    	setLayout(new GridLayout(2, 2));
	    	JButton bf= new JButton("BF文件");
	    	JButton ook = new JButton("Ook!文件");
	    	JLabel nameLabel = new JLabel("文件名:");
	    	nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    	filename = new JTextField();
	    	this.add(nameLabel);
	    	this.add(filename);
	    	this.add(bf);
	    	this.add(ook);
	    	bf.addActionListener(this);
	    	ook.addActionListener(this);
	    	setBounds(120,120,100,100);
	    	
	    	
	    	
	    }
		
		
		
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String cmd = e.getActionCommand();
			
			
			
			
			
			
			
			if(cmd.equals("BF文件")){
				String message = frame.creatnewFile(filename.getText(),"bf");
				if(message.equals("成功创建!"))
				{
					this.dispose();
				}else{
					new JOptionPane().showMessageDialog(this, message);
				}
				
			}else if (cmd.equals("Ook!文件")){
				String message = frame.creatnewFile(filename.getText(),"ook");
				if(message.equals("成功创建!"))
				{
					this.dispose();
				}else{
					new JOptionPane().showMessageDialog(this, message);
				}
			}
			
			
		}  
	}

}
