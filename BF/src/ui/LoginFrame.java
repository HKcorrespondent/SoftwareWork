package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import rmi.RemoteHelper;

public class LoginFrame extends JFrame{
	private JLabel userLabel;
	private JTextField  username;
	private JLabel passwordLabel;
	private JPasswordField password;
	private JButton loginButton;
	private JButton signupButton;
	private JLabel showLabel;
	
	
	
	
	
	public LoginFrame(){
		initSizeAndOthers();
	    initComnponent();
	    setBackground(Color.red);

	    setVisible(true);
	}
	
	public void disposeTheLoginFrame(){
		this.dispose();
	}
	public boolean login(String username, String password) throws RemoteException{
		if(RemoteHelper.getInstance().getUserService().login(username, password)){
			return true;
		}else{
			return false;
		}
		
	}
	public String signup(String username, String password) throws RemoteException{
		
		return RemoteHelper.getInstance().getUserService().signup(username, password);
	}
	
	private void initSizeAndOthers(){
		setLayout(null);
		setTitle("denglu jiemian");
		setSize(550, 320);
		setLocation(500, 300);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	private void initComnponent(){
		userLabel=new JLabel("用户名");
		username=new JTextField();
		passwordLabel=new JLabel("密码");
		password = new JPasswordField();
		loginButton = new JButton("登录");
		signupButton = new JButton("注册");
		showLabel = new JLabel("输入用户名和密码,若未注册请输入后点击注册");
		Font font=new Font(Font.DIALOG_INPUT, Font.BOLD, 18);
		
		int x=70;
		int y=60;
		
		userLabel.setFont(font);
		userLabel.setBounds(x, y, 100, 40);
	    add(userLabel);
		
	    passwordLabel.setFont(font);
	    passwordLabel.setBounds(x, y+40, 100, 40);
		add(passwordLabel);
		
		username.setFont(font);
		username.setBounds(x+100, y+5, 300, 30);
		add(username);
		
		password.setFont(font);
		password.setBounds(x+100, y+45, 300, 30);
		add(password);
		
		showLabel.setFont(font);
		showLabel.setHorizontalAlignment(SwingConstants.CENTER);
		showLabel.setBounds(0, y+170, 550, 30);
		add(showLabel);
		
		loginButton.setFont(font);
		loginButton.setBounds(x+70, y+100, 100, 40);
		loginButton.setContentAreaFilled(false);
		loginButton.addActionListener(new loginAction());
		add(loginButton);
		
		signupButton.setFont(font);
		signupButton.setBounds(x+220, y+100, 100, 40);
		signupButton.setContentAreaFilled(false);
		signupButton.addActionListener(new signupAction());
		add(signupButton);
	}
	
	class loginAction implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			
			
			
			
			// TODO Auto-generated method stub
			try {
				
				
				if(login(username.getText(),encode(password.getPassword()))){
					
					String trueUsername=username.getText();
					disposeTheLoginFrame();
					new MainWindow(trueUsername);
				}else{
					showLabel.setText("用户名或密码错误或已经登录");
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	private String changePassword(String MD5){
		String ret="";
		for(int i=0;i<MD5.length();i++){
			char c=MD5.charAt(i);
			ret=ret+(char)('A'+c%26);
		}
		
		
		
		return ret;
		
	}
	
	private String encode(char[] c){
		MessageDigest md5 = null;
		try {
			md5=MessageDigest.getInstance("MD5");
			
			
			
			
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String retString = null;
		try {
			retString = new String(md5.digest(String.valueOf(c).getBytes()),"UTF-8");
			
			
			
			
			
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return changePassword(retString);
	}
	public boolean judgeUsernameAndPasswordLegalOrNot(String username,char[] password){
		if(username.length()<5||username.length()>15){
			showLabel.setText("用户名长度必须在6位到16位之间!");
			return false;
		}

		char firstchar=username.charAt(0);
		if(firstchar<='z'&&firstchar>='a'||firstchar<='Z'&&firstchar>='A'){}else{
			showLabel.setText("用户名首位必须为字母!");
			return false;
		}
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]+");
		Matcher matcher = pattern.matcher(username);
		if(!matcher.matches()){
			showLabel.setText("用户名必须由字母和数字组成!");
			return false;
		}
		if(password.length<6||password.length>15){
			showLabel.setText("密码长度必须在6位到16位之间!");
			return false;
		}
		
		
		
		return true;
		
	}
	
	
	class signupAction implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			// TODO Auto-generated method stub
			boolean isLegal=judgeUsernameAndPasswordLegalOrNot(username.getText(),password.getPassword());

			
		
			
			if(isLegal){
				try {
					
					showLabel.setText(signup(username.getText(), encode(password.getPassword())));
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}
