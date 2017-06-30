package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import org.omg.Messaging.SyncScopeHelper;

import rmi.RemoteHelper;


public class MainWindow {
//	private JTextArea textArea;
	private InAndOutPutTextArea outputArea;
	private InAndOutPutTextArea inputArea;
	private String username;
	private JFrame frame;
	private JTabbedPane tabbedPanel;
	
//	private ArrayList<String> fileList =null;
	private String[] fileTypeArgs = null;
	public JFrame getJFrame(){
		return frame;
	}
	public  String[] getTotalFileType(){
		return fileTypeArgs;
	}
	public void ConfigurationInit(){
		try {
			fileTypeArgs = RemoteHelper.getInstance().getConfiguratioSservice().getExecutableFileType();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
//	public static void main(String[] s){
//		//获取系统中可用的字体的名字  
//	
//
//		new MainWindow("x123456");
//		
//	}
	class CloseButton extends JButton{
		int countOnTheTab = 0;
		public CloseButton(String s,int count){
			super(s);
			countOnTheTab=count;
			Font font = new Font("微软雅黑",Font.PLAIN,13);
			setFont(font);
//			setBorderPainted(false);
//			closeButton.setOpaque(false);
			setContentAreaFilled(false);
			setHorizontalAlignment(SwingConstants.RIGHT);
			setBorder(new EmptyBorder(2, 2, 2, 2));
			addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					tabbedPanel.remove(count);
				}
			});
			
		}
	}
	
	public void addTextAreaToTabbedPanel(String title,CodeTextArea textArea){
//		System.out.println("title: "+title);
		for(int i=0;i<tabbedPanel.getTabCount();i++){
//			System.out.println("tabbedPanel.getTitleAt(i): "+tabbedPanel.getTitleAt(i));
			if(tabbedPanel.getTitleAt(i).equals(title)){
				tabbedPanel.setComponentAt(i, textArea);
				return ;
			}
		}
		
		
		
		Font font = new Font("微软雅黑",Font.PLAIN,13);
		int count=tabbedPanel.getTabCount();
		tabbedPanel.add(title, textArea);

		
		JLabel titleLabel = new JLabel(title);
		titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		titleLabel.setFont(font);
		titleLabel.setOpaque(false);
		
		font = new Font("微软雅黑",Font.BOLD,13);
		CloseButton closeButton = new CloseButton("X",count);
		
		
		
		JPanel tabPanel = new JPanel();
		
		tabPanel.add(titleLabel);
		tabPanel.add(closeButton);
		
		GridBagLayout gl =new GridBagLayout();
		
		GridBagConstraints gc= new GridBagConstraints();
		
		gc.fill=GridBagConstraints.BOTH;
		
//		frame.add(tabbedPanel, BorderLayout.NORTH);
//		s.gridwidth=1; 
//		s.weightx = 0; 
//		s.weighty=0; 
		
		gc.gridx=1;
		gc.gridy=1;
		gc.gridwidth=3;
		gc.gridheight=1;
		gc.weightx=1;
		gc.weighty=1;
		
		gl.setConstraints(titleLabel, gc);
		gc.fill=GridBagConstraints.BOTH;
	
		gc.gridx=4;
		gc.gridy=1;
		gc.gridwidth=1;
		gc.gridheight=1;
		gc.weightx=1;
		gc.weighty=1;
		
		gl.setConstraints(closeButton, gc);
		

		tabPanel.setLayout(gl);
		tabPanel.setOpaque(false);
		
		
//		tabPanel.setSize(100,50);
//		tabbedPanel.setTabComponentAt(count, titleLabel);
//		tabbedPanel.setTabComponentAt(count, closeButton);
		tabbedPanel.setTabComponentAt(count, tabPanel);
		
	}
	public MainWindow(String username) {
		frame = new JFrame("Hello! "+username+"-BF Client");
		setUIFont();
		this.username=username;
		
		// 创建窗体
		
		
		
		setFrameLookAndFeel();
		ConfigurationInit();
		initMenu();
		
		tabbedPanel = new JTabbedPane();
		
		
		outputArea = new InAndOutPutTextArea("outputArea");
		outputArea.getJTextArea().setLineWrap(true);
		outputArea.getJTextArea().setText("output");
		outputArea.setBorder(BorderFactory.createLineBorder(Color.black,1));
		inputArea = new InAndOutPutTextArea("outputArea");
		inputArea.getJTextArea().setLineWrap(true);
		inputArea.getJTextArea().setText("input");
		inputArea.setBorder(BorderFactory.createLineBorder(Color.black,1));
		
		
//		MyTextArea  textArea = new MyTextArea("new.bf");
//		textArea.getJTextArea().setLineWrap(true);
//		tabbedPanel.add("new.bf", textArea);
////		tabbedPanel.setComponentAt();
//		
//		tabbedPanel.setTabComponentAt(0, new JButton("X"));
		frame.add(tabbedPanel);
		frame.add(inputArea);
		frame.add(outputArea);
//		textArea.setMargin(new Insets(10, 10, 10, 10));
//		textArea.setBackground(Color.LIGHT_GRAY);
//		JTextArea  textArea1 = new JTextArea();
//		textArea1.setMargin(new Insets(10, 10, 10, 10));
//		textArea1.setBackground(Color.LIGHT_GRAY);
		
		
//		frame.add(textArea, BorderLayout.CENTER);
//		tabbedPanel.addTab("panel2", textArea1);
//		tabbedPanel.addTab("panel1", textArea);
		
		GridBagLayout gl =new GridBagLayout();
		
		GridBagConstraints gc= new GridBagConstraints();
		
		gc.fill=GridBagConstraints.BOTH;
		
//		frame.add(tabbedPanel, BorderLayout.NORTH);
//		s.gridwidth=1; 
//		s.weightx = 0; 
//		s.weighty=0; 
		gc.ipadx=0;
		gc.ipady=0;
		gc.gridx=0;
		gc.gridy=0;
		gc.gridwidth=2;
		gc.gridheight=2;
		gc.weightx=1;
		gc.weighty=1;
		
		gl.setConstraints(tabbedPanel, gc);
		gc.fill=GridBagConstraints.BOTH;
		gc.ipadx=0;
		gc.ipady=0;
		gc.gridx=0;
		gc.gridy=2;
		gc.gridwidth=1;
		gc.gridheight=1;
		gc.weightx=1;
		gc.weighty=1;
		
		gl.setConstraints(inputArea, gc);
		gc.fill=GridBagConstraints.BOTH;
		gc.ipadx=0;
		gc.ipady=0;
		gc.gridx=1;
		gc.gridy=2;
		gc.gridwidth=1;
		gc.gridheight=1;
		gc.weightx=1;
		gc.weighty=1;
		
		gl.setConstraints(outputArea, gc);
		frame.setLayout(gl);
//		frame.add(outputLabel, BorderLayout.EAST);
//		frame.add(inputLabel, BorderLayout.WEST);
	
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
				MainWindow.this.getJFrame().dispose();
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
		frame.setSize(800, 600);
//		setSize(800, 600);
		frame.setLocation(400, 200);
		frame.setVisible(true);
	}
	
	private void initMenu(){
		
		
		JMenuBar menuBar = new JMenuBar();
		{
			JMenu fileMenu = new JMenu("File");
			
			
			JMenuItem newMenuItem = new JMenuItem("New");
			newMenuItem.addActionListener(new MenuItemActionListener());
			fileMenu.add(newMenuItem);
			
			JMenuItem openMenuItem = new JMenuItem("Open");
			openMenuItem.addActionListener(new MenuItemActionListener());
			fileMenu.add(openMenuItem);
			
			JMenuItem saveMenuItem = new JMenuItem("Save");
			saveMenuItem.addActionListener(new SaveActionListener());
			fileMenu.add(saveMenuItem);

			JMenuItem quitMenuItem = new JMenuItem("Quit");
			quitMenuItem.addActionListener(new MenuItemActionListener());
			fileMenu.add(quitMenuItem);

			
			menuBar.add(fileMenu);
		}
		{
			JMenu editMenu = new JMenu("edit");
			
			
			JMenuItem undoMenuItem = new JMenuItem("Undo");
			undoMenuItem.addActionListener(new MenuItemActionListener());
			undoMenuItem.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z,  
					                                           java.awt.Event.CTRL_MASK));
			
			editMenu.add(undoMenuItem);
			
			JMenuItem redoMenuItem = new JMenuItem("Redo");
			redoMenuItem.addActionListener(new MenuItemActionListener());
			redoMenuItem.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y,  
                    										   java.awt.Event.CTRL_MASK));
			editMenu.add(redoMenuItem);
			
			JMenuItem historyVersion = new JMenuItem("HistoryVersion");
			historyVersion.addActionListener(new MenuItemActionListener());
			editMenu.add(historyVersion);
			
			
			menuBar.add(editMenu);
		}
		{
			JMenu runMenu = new JMenu("edit");

			
			JMenuItem runMenuItem = new JMenuItem("Run");
			runMenuItem.addActionListener(new MenuItemActionListener());
			runMenuItem.setAccelerator(KeyStroke.getKeyStroke("F5"));
			runMenu.add(runMenuItem);
			
			JMenuItem oneStepRunMenuItem = new JMenuItem("OneStep");
			oneStepRunMenuItem.addActionListener(new MenuItemActionListener());
			oneStepRunMenuItem.setAccelerator(KeyStroke.getKeyStroke("F6"));
			runMenu.add(oneStepRunMenuItem);

		
			menuBar.add(runMenu);
		}
		
		
		
		
		
		
		
		
		
		frame.setJMenuBar(menuBar);
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
	
	public void openFile(String filename,String fileType){

		CodeAggregate file = processingReadFile(filename+fileType) ;
		CodeTextArea newOpen = new CodeTextArea(filename+fileType,file);
		newOpen.setText(file.getTheNewlyCode());
		addTextAreaToTabbedPanel(filename+fileType, newOpen);
//		tabbedPanel.add( filename+fileType, newOpen);
		
	}
	public  CodeAggregate processingReadFile(String fileTotalName){
		String content = null;
		try {
//			System.out.println(username+"!!!!!!!"+ fileTotalName);
			 content = RemoteHelper.getInstance().getIOService().readFile(username, fileTotalName);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CodeAggregate file = new CodeAggregate(content,fileTotalName);
	
		return file;
		
	}

	class CodeAggregate{
		
		private String[] NumberOfTimesSaved  ;
		private String[] Savetime;
		private String[] Code;
		private String fileTotalName ;
		public String getFileTotalName(){
			return fileTotalName;
			
		}
		CodeAggregate(String content,String fileTotalName){
			this.fileTotalName =fileTotalName;
			String[] allInformation = content.split("_");
		
			
			
			String[] processedfile = content.split("@");
			 
			NumberOfTimesSaved 	= new String[allInformation.length];
			Savetime			= new String[allInformation.length];
			Code				= new String[allInformation.length];
			
			for(int i=0;i<allInformation.length;i++){
				allInformation[i] = allInformation[i].replaceAll("_", "")+" ";
//				System.out.println("allInformation"+i+":"+ allInformation[i]);
				
				String[] oneFileInformatin = allInformation[i].split("@");
				if(oneFileInformatin[2].equals(" ")){
					oneFileInformatin[2]="";
				}
				NumberOfTimesSaved[i] 	= oneFileInformatin[0];
				Savetime[i]				= oneFileInformatin[1];
				Code[i]					= oneFileInformatin[2];
			}
			
		}
		
		public String getTheNewlyCode(){
			return Code[Code.length-1];
			
		}
		public String[] getNumberOfTimesSaved(){
			return NumberOfTimesSaved;
			
		}
		public String[] getSavetime(){
			return Savetime;
			
		}
		public String getTheCodeFromNumberOfTimesSaved(String numberOfTimesSaved){
			System.out.println("numberOfTimesSaved:"+numberOfTimesSaved);
			for(int i = 0;i<NumberOfTimesSaved.length;i++){
				System.out.println("NumberOfTimesSaved[]"+i+":"+NumberOfTimesSaved[i]);
				if(NumberOfTimesSaved[i].equals(numberOfTimesSaved)){
					return Code[i];
				}
			}
			return "code not found";
			
		}
		
	}
	
	public void openFile(String fileTotalName){
	
		CodeAggregate file = processingReadFile(fileTotalName) ;
		
		CodeTextArea newOpen = new CodeTextArea(fileTotalName,file);
		
		newOpen.setText(file.getTheNewlyCode());
		
		addTextAreaToTabbedPanel(fileTotalName, newOpen);
//		tabbedPanel.add( fileTotalName, newOpen);
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

		if(getFileList().contains(filename+fileType)){
			//弹出提示已经有了这个文件
			
			return "该文件已存在!";
		}else{
			//服务器端先创建文件
			try {
				RemoteHelper.getInstance().getIOService().writeFile("",username , filename+fileType);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//本地创建

			openFile(filename+fileType);

			return "成功创建!";
		}
		
	}
	//得到总的文件目录
	private void menuRun(ActionEvent e){
		String param = inputArea.getText();
			
			
		int i=	tabbedPanel.getSelectedIndex();
		if(i>-1){
			CodeTextArea text =((CodeTextArea)tabbedPanel.getComponentAt(i));

//				
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						System.out.println("!!!!!!!!!!!!!!");
						outputArea.getJTextArea().setText(RemoteHelper.getInstance().getRunservice().run(text.getText(), param,text.getFileTotalName()));
					} catch (RemoteException e1) {
							// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			t.start();
		}else{
			outputArea.getJTextArea().setText("没有文件可被执行!");
		}
	}
	private void menuNewFile(ActionEvent e){
		NewFileDialog newfile = new NewFileDialog(username, MainWindow.this);
		newfile.setLocationRelativeTo(MainWindow.this.tabbedPanel);
		newfile.setSize(300, 100);
		newfile.setVisible(true);
	}
	private void menuOpenFile(ActionEvent e){
//		textArea.setText("Open");
//		 ArrayList<String> list = MainFrame.this.getFileList();
		
		
		
		
		FileChooser fc= new FileChooser(username, MainWindow.this);
		fc.setLocationRelativeTo(MainWindow.this.tabbedPanel);
//		fc.setSize(200,200);
		fc.setVisible(true);
	}
	private void menuOneStepRun(ActionEvent e){
		
		
		
	}
	private void menuOpenHistoryVersion(ActionEvent e){
		if(tabbedPanel.getSelectedIndex()!=-1){
			
			
			HistoryVersionChooser historyChooser = new HistoryVersionChooser(MainWindow.this, 
					((CodeTextArea)tabbedPanel.getComponentAt(tabbedPanel.getSelectedIndex())).getCodeAggregate().getFileTotalName());
			historyChooser.setLocationRelativeTo(MainWindow.this.tabbedPanel);
			historyChooser.setVisible(true);
			
			}else{
				new JOptionPane().showMessageDialog(MainWindow.this.frame, "请至少选择一个文件!");
			}
	}
	private void menuUndo(ActionEvent e){
	if(tabbedPanel.getSelectedIndex()!=-1){
		((CodeTextArea)tabbedPanel.getComponentAt(tabbedPanel.getSelectedIndex())).undo();
			
		
			
			}
	}
		
		
	
	private void menuRedo(ActionEvent e){
		if(tabbedPanel.getSelectedIndex()!=-1){
			((CodeTextArea)tabbedPanel.getComponentAt(tabbedPanel.getSelectedIndex())).redo();
				
			
				
				}
		
		
	}
	
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
	public static void setUIFont()
	{
		Font f = new Font("微软雅黑",Font.PLAIN,18);
		String   names[]={ "Label", "CheckBox", "PopupMenu","MenuItem", "CheckBoxMenuItem",
				"JRadioButtonMenuItem","ComboBox", "Button", "Tree", "ScrollPane",
				"TabbedPane", "EditorPane", "TitledBorder", "Menu", "TextArea",
				"OptionPane", "MenuBar", "ToolBar", "ToggleButton", "ToolTip",
				"ProgressBar", "TableHeader", "Panel", "List", "ColorChooser",
				"PasswordField","TextField", "Table", "Label", "Viewport",
				"RadioButtonMenuItem","RadioButton", "DesktopPane", "InternalFrame"
		}; 
		for (String item : names) {
			 UIManager.put(item+ ".font",f); 
		}
	}
	class MenuItemActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			
		
			if(cmd.equals("New")){

				MainWindow.this.menuNewFile(e);
				
			}else if (cmd.equals("Open")) {
			
				MainWindow.this.menuOpenFile(e);

			}  else if(cmd.equals("OneStep")){
				
				MainWindow.this.menuOneStepRun(e);
				
			}else if (cmd.equals("Run")) {
				 
				 MainWindow.this.menuRun(e);
				 
			}else if(cmd.equals("Undo")){
				
				MainWindow.this.menuUndo(e);
				
			}else if(cmd.equals("Redo")){
				
				MainWindow.this.menuRedo(e);
				
			}else if(cmd.equals("HistoryVersion")){
			
				MainWindow.this.menuOpenHistoryVersion(e);
			
			}
		}
	}

	class SaveActionListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int i=	tabbedPanel.getSelectedIndex();
			
			if(i>-1){
				CodeTextArea text =(CodeTextArea)tabbedPanel.getComponentAt(i);
				//非法字符值得商榷
				try {
					RemoteHelper.getInstance().getIOService().writeFile(text.getText(), username, tabbedPanel.getTitleAt(i));
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
			
			
			
//			String code = textArea.getText();
//			try {
//				RemoteHelper.getInstance().getIOService().writeFile(code, "admin", "code");
//			} catch (RemoteException e1) {
//				e1.printStackTrace();
//			}
		}

	}
	//做的不好
	

}
