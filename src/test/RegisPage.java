package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegisPage extends JFrame{
	private static final long serialVersionUID = 1L;
	private JButton register;
	private JTextField name;
	private JTextField tip;
	private JTextField tip2;
	private MyClientWindow frame;
	URL url = this.getClass().getResource("/image/1.png");
	
	public RegisPage() {
		//1.创建窗口
		super("创建用户界面");
		setAlwaysOnTop(true);
		this.setBg();
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(660, 340, 600, 400);
		
		Font f = new Font("楷体",Font.BOLD,20);
		tip = new JTextField("请输入聊天室的昵称,范围在3-20个字符之间。");
		tip.setFont(f);
		tip.setHorizontalAlignment(JTextField.CENTER);
		tip.setEditable(false);
		tip.setOpaque(false);
		tip.setBorder(null);
		tip.setBounds(80, 60, 440, 30);
		
		name = new JTextField();
		name.setFont(f);
		name.setHorizontalAlignment(JTextField.CENTER);
		name.setBounds(150, 90, 300, 30);
		
		register = new JButton("进入聊天室");
		register.setFont(f);
		register.setBounds(225, 125, 150, 30);
		
		tip2 = new JTextField("");
		tip2.setFont(f);
		tip2.setForeground(Color.RED);
		tip2.setHorizontalAlignment(JTextField.CENTER);
		tip2.setEditable(false);
		tip2.setOpaque(false);
		tip2.setBorder(null);
		tip2.setBounds(80, 270, 440, 30);
		
		register.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int length = name.getText().length();
				if(length > 20 || length < 3) {
					tip2.setText("昵称不在合法范围内，请重新输入！");
					name.setText("");
				} else {
					setVisible(false);
					setWindow();
				}
			}
		});
		
		name.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10) {
					int length = name.getText().length();
					if(length > 20 || length < 3) {
						tip2.setText("昵称不在合法范围内，请重新输入！");
						name.setText("");
					} else {
						setVisible(false);
						setWindow();
					}
				}
			}
		});
		
		add(tip);
		add(name);
		add(register);
		add(tip2);
	}

	private void setBg() {
		((JPanel)this.getContentPane()).setOpaque(false);
		ImageIcon img = new ImageIcon(url);
		JLabel background = new JLabel(img);
		this.getLayeredPane().add(background,new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
	}
		
	public String getName() {
		return name.getText();
	}
	
	private void setWindow(){
		frame=new MyClientWindow();
		frame.setVisible(true);
		frame.SetRegis(this);
		ConnectionManager.getChatManager().setWindow(frame);
	}
}
