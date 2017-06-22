package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JDialog;

public class HistoryVersionChooser extends JDialog implements ActionListener {

	MainWindow mainWindow =null;
	JComboBox historyfileComboBox = null;
	
	public HistoryVersionChooser(MainWindow frame,String fileName){
		super(frame.getJFrame(), "选择历史版本恢复", true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(120,120,100,100);
    	setLayout(new GridLayout(2, 2));
    	historyfileComboBox = new JComboBox<String>();
		
    	
    	
    	
    	
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
