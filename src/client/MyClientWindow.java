package client;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
 
public class MyClientWindow extends JFrame {
 
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea txt;
	private JTextField ip;
	private JTextField txtSend;
	private JLabel constatus;
	private RegisPage regis;
	private JScrollPane jScrollPane;
	private ImageIcon img;
	private JLabel background;
	URL url = this.getClass().getResource("/image/2.png");
 
	public MyClientWindow() {
		//1.��������
		super("����--�ͻ���");
		setAlwaysOnTop(true);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(660, 340, 600, 400);
		this.setBg();
		Font f = new Font("����",Font.BOLD,20);
		addComponentListener(new ComponentAdapter() {
			//���±���ͼƬ
			public void componentResized(ComponentEvent e) {
				background.setVisible(false);
				setBg();
			}
		});
		
		//2.���������¼��
		txt = new JTextArea();
		txt.setText("׼��..");
		txt.setOpaque(false);
		txt.setEditable(false);
		txt.setFont(f);
		jScrollPane = new JScrollPane(txt);
		jScrollPane.setOpaque(false);
		jScrollPane.getViewport().setOpaque(false); 
		jScrollPane.setBorder(null);
		
		//3.����������ip�趨���
		ip = new JTextField();
		ip.setText("39.105.185.29");
		
		//4.�������Ӱ�ť�������Ӽ���ע����
		JButton btnConnect = new JButton("����");
		constatus = new JLabel("δ����");
		btnConnect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ConnectionManager.getChatManager().connect(ip.getText());
			}
		});
		
		//5.����������Ϣ�ı�������Ӽ���ע����
		txtSend = new JTextField();
		txtSend.setText("");
		txtSend.setColumns(10);
		txtSend.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ea) {
				if(ea.getKeyCode() == 10) {
					ConnectionManager.getChatManager().send(regis.getName() + "˵: " + txtSend.getText());
					appendText("��˵: " + txtSend.getText());
					txtSend.setText("");
				}	
			}
		});
		
		//6.����������Ϣ��ť����Ӽ���ע����
		JButton btnSend = new JButton("����");
		btnSend.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ConnectionManager.getChatManager().send(regis.getName() + "˵: " + txtSend.getText());
				appendText("��˵: " + txtSend.getText());
				txtSend.setText("");
			}
		});
		
		//7.����������
		contentPane = new JPanel();
		contentPane.setOpaque(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//8.�������鲼�ֹ�����
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		
		//9.ָ�����ֵ�ˮƽ���
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING,gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtSend, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSend, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING,gl_contentPane.createSequentialGroup()
							.addComponent(ip, GroupLayout.PREFERRED_SIZE, 294,Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnConnect, GroupLayout.DEFAULT_SIZE, 140, GroupLayout.PREFERRED_SIZE))
						.addComponent(jScrollPane, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING,gl_contentPane.createSequentialGroup()
							.addComponent(constatus,GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap()));
 
		//10.ָ�����ֵĴ�ֱ���
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(jScrollPane, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSend)
						.addComponent(txtSend, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(ip, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
						.addComponent(btnConnect))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(constatus,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))));
				
		setContentPane(contentPane);
		contentPane.setLayout(gl_contentPane);
	}
 
	//�ͻ��˷��͵�������ӵ��м���ı��ؼ���
	public void appendText(String in) {
		txt.append("\n" + in);
		txt.selectAll();
		txt.setCaretPosition(txt.getSelectedText().length());
		txt.requestFocus();
		txtSend.requestFocus();
	}
	
	//�趨����״̬
	public void Setstatus(String in) {
		constatus.setText(in);
	}
	
	//���ñ���ͼƬ
	public void setBg() {
		((JPanel)this.getContentPane()).setOpaque(false);
		img = new ImageIcon(url);
		img.setImage(img.getImage().getScaledInstance(this.getWidth(), this.getHeight(),Image.SCALE_AREA_AVERAGING));
		background = new JLabel(img);
		this.getLayeredPane().add(background,new Integer(Integer.MIN_VALUE));
		background.setVisible(true);
		background.setBounds(0, 0, this.getWidth(), this.getHeight());
	}
	
	//����ע�����
	public void SetRegis(RegisPage regis) {
		this.regis = regis;
	}
	public RegisPage getRegis() {
		return regis;
	}
}