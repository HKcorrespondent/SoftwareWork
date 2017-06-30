package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import ui.MainWindow.CodeAggregate;

public class HistoryVersionChooser extends JDialog implements ActionListener {

	private MainWindow mainWindow =null;
	private JComboBox<String> historyfileComboBox = null;
	private JButton ok = null;
	private CodeAggregate file;
	public HistoryVersionChooser(MainWindow mainWindow,String fileName){
		super(mainWindow.getJFrame(), "选择历史版本恢复", true);
		this.mainWindow = mainWindow;
		this.file =mainWindow.processingReadFile(fileName) ;
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(120,120,100,100);
    	setLayout(new GridLayout(3, 1));
    	historyfileComboBox = new JComboBox<String>();
    	String[] saveTime = file.getSavetime();
    	String[] numberOfTimesSaved=file.getNumberOfTimesSaved();
    	for(int i=0;i<saveTime.length;i++){
    		historyfileComboBox.addItem(codeTheNumbersAndSavetime(numberOfTimesSaved[i], saveTime[i])); 
    	}
    	
    	GridLayout g= new GridLayout(1, 3);
    	ok = new JButton("确定");
    	ok.addActionListener(this);
    	add(new JLabel("选择文件历史版本:"));
    	add(historyfileComboBox);
//    	add(new JLabel(""));
    	add(ok);
    	setSize(500,120);
	}
	private String codeTheNumbersAndSavetime(String numberOfTimesSaved,String savetime){
		return savetime+"|保存次数:"+numberOfTimesSaved;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
    	
    	if(cmd.equals("确定")){
    		
    		
    		CodeTextArea newOpen = new CodeTextArea(file.getFileTotalName(),file);
    		
    		System.out.println(historyfileComboBox.getItemAt(historyfileComboBox.getSelectedIndex()));
    		String SelectedVersion = (String) historyfileComboBox.getSelectedItem();
    		System.out.println(SelectedVersion);
    		System.out.println(SelectedVersion.split("\\|")[1]);
    		
    		String numberOfTimesSaved= SelectedVersion.split("\\|")[1].replace("保存次数:", "");
    		System.out.println(numberOfTimesSaved);
    		newOpen.setText(file.getTheCodeFromNumberOfTimesSaved(numberOfTimesSaved));
    		
    		mainWindow.addTextAreaToTabbedPanel(file.getFileTotalName(), newOpen);
    		
    		
    		this.dispose();
    	}
	}
}
