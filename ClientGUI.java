import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;
import javax.swing.text.JTextComponent;

import java.io.PrintWriter;
import java.net.*;
import java.util.MissingFormatArgumentException;

public class ClientGUI 
{
	private static ClientGUIUse chatclient;
	public static String USERANME="Annonymous";
	public static JFrame MainWindow=new JFrame();
	private static JButton B_Connect= new JButton();
	private static JButton B_DisConnect= new JButton();
	private static JButton B_Help=  new JButton();
	private static JButton B_Send= new JButton();
	private static  JLabel L_Message= new JLabel("Message:   ");
	public static JTextField TF_Message=new JTextField(20);
	private static JLabel L_Conversation=new JLabel();
	public static JTextArea TA_conversation=new JTextArea();
	private static JScrollPane SP_conversation=new JScrollPane();
	private static JLabel L_online=new JLabel();
	public static JList JL_online =new JList();
	private static JScrollPane SP_online= new JScrollPane();
	private static JLabel L_Loggedinas= new JLabel();
	private static JLabel L_LoggedinasBox= new JLabel();
	
	
	public static JFrame LoginWindow= new JFrame();
	public static JTextField TF_UserNameBox=new JTextField(20);
	public static JButton B_Enter=new JButton("Enter");
	private static JLabel L_EnterUserName=new JLabel("Enter UserName:");
	private static JPanel P_login=new JPanel();
	 
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
      BuildMainWindow();
      Initialize();
	}
	public static void Connect()
	{
		try
		{
			final int PORT=95;
			final String HOST="localhost";
			Socket Sock=new Socket(HOST,PORT);
			System.out.println("You are Connected to"+HOST);
		    chatclient=new ClientGUIUse(Sock);
		    PrintWriter pw= new PrintWriter(Sock.getOutputStream());
		    pw.println(USERANME);
		    pw.flush();
		    
		    Thread X= new Thread(chatclient);
		    X.start();
		    
		    		
		}
		catch(Exception e)
		{
			System.out.print(e);
			JOptionPane.showMessageDialog(null,"server not responding");
			System.exit(0);
		}
	}
	public static void Initialize()
	{
		B_Send.setEnabled(false);
		B_DisConnect.setEnabled(false);
		B_Connect.setEnabled(true);
		
	}
	public static void BuildLogInWindow()
	{
		LoginWindow.setTitle("What is your name");
		LoginWindow.setSize(400,100);
		LoginWindow.setLocation(250,200);
		LoginWindow.setResizable(false);
		P_login=new JPanel();
		P_login.add(L_EnterUserName);
		P_login.add(TF_UserNameBox);
		P_login.add(B_Enter);
		LoginWindow.add(P_login);
		Login_Action();
		LoginWindow.setVisible(true);
		
		
		
	}
    public static void BuildMainWindow()
    {
	MainWindow.setTitle(USERANME+"'s chat box");
	MainWindow.setSize(450,500);
	MainWindow.setLocation(220, 180);
    MainWindow.setResizable(false);
    ConfigureMainWindow();
    MainWindow_Action();
    MainWindow.setVisible(true);
    }
    public static void ConfigureMainWindow()
    {
    	MainWindow.setBackground(new java.awt.Color(255,255,255));
    	MainWindow.setSize(500,320);
    	MainWindow.getContentPane().setLayout(null);
    	B_Send.setBackground(new java.awt.Color(0,0,255));
    	B_Send.setForeground(new java.awt.Color(255,255,255));
    	B_Send.setText("SEND");
    	MainWindow.getContentPane().add(B_Send);
    	B_Send.setBounds(250, 40, 81, 25);
    	B_DisConnect.setBackground(new java.awt.Color(0,0,255));
    	B_DisConnect.setForeground(new java.awt.Color(255,255,255));
    	B_DisConnect.setText("DisConnect");
    	MainWindow.getContentPane().add(B_DisConnect);
    	B_DisConnect.setBounds(10, 40, 110,25);
    	  
    	
    	B_Connect.setBackground(new java.awt.Color(0,0,255));
    	B_Connect.setForeground(new java.awt.Color(255,255,255));
    	B_Connect.setText("Connect");
    	B_Connect.setToolTipText("");
    	MainWindow.getContentPane().add(B_Connect);
    	B_Connect.setBounds(130, 40, 110,25);
    	
    	B_Help.setBackground(new java.awt.Color(0,0,255));
    	B_Help.setForeground(new java.awt.Color(255,255,255));
    	B_Help.setText("Help");
    	MainWindow.getContentPane().add(B_Help);
    	B_Help.setBounds(420,40,70,25);
    	
    	
    L_Message.setText("Message");
    MainWindow.getContentPane().add(L_Message);
    L_Message.setBounds(10,10,60,20);
    
    TF_Message.setForeground(new java.awt.Color(0,0,255));
    TF_Message.requestFocus();
    MainWindow.getContentPane().add(TF_Message);
    TF_Message.setBounds(70,4,260,30);
    
    L_Conversation.setHorizontalAlignment(SwingConstants.CENTER);
    L_Conversation.setText("Conversation");
    MainWindow.getContentPane().add(L_Conversation);
    L_Conversation.setBounds(100, 70, 140, 16);
    
    
    TA_conversation.setColumns(20);
    TA_conversation.setFont(new java.awt.Font("Tahoma",0,12));
    TA_conversation.setForeground(new java.awt.Color(0,0,255));
    TA_conversation.setLineWrap(true);
    TA_conversation.setRows(5);
    TA_conversation.setEditable(false);
    
    SP_conversation.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    SP_conversation.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    SP_conversation.setViewportView(TA_conversation);
    MainWindow.getContentPane().add(SP_conversation);
    SP_conversation.setBounds(10,80,330,180);
    
    L_online.setHorizontalAlignment(SwingConstants.CENTER);
    L_online.setText("Currently ONline");
    L_online.setToolTipText("");
    MainWindow.getContentPane().add(L_online);
    L_online.setBounds(350,70,130,16);
    //String[] TestNames={"Varun","Aishwary"};
    JL_online.setForeground(new java.awt.Color(0,0,255));
    //JL_online.setListData(TestNames);
    SP_online.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    SP_online.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    
    SP_online.setViewportView(JL_online);
    MainWindow.getContentPane().add(SP_online);
    SP_online.setBounds(350, 90, 130, 180);
    
    L_Loggedinas.setFont(new java.awt.Font("Tahoma",0,12));
    L_Loggedinas.setText("Curently logged in as");
    MainWindow.getContentPane().add(L_LoggedinasBox);
    L_Loggedinas.setBounds(348, 0, 140, 16);
    
    
    L_LoggedinasBox.setHorizontalAlignment(SwingConstants.CENTER);
    L_LoggedinasBox.setFont(new java.awt.Font("Tahoma",0,12));
    L_LoggedinasBox.setBackground(new java.awt.Color(255,0,0));
    
    L_LoggedinasBox.setBorder(
    	BorderFactory.createLineBorder(new java.awt.Color(0,0,0)));
    MainWindow.getContentPane().add(L_LoggedinasBox);
    L_LoggedinasBox.setBounds(340,17,150,20);
    
    }
    public static void Login_Action()
    {
    	B_Enter.addActionListener(
    		new java.awt.event.ActionListener()
    		{
    			public void actionPerformed(java.awt.event.ActionEvent ae)
    			{
    			   ACTION_B_ENTER();
    			}
    		}
    	   );
    }
    public static void ACTION_B_ENTER()
    {
    	if(!TF_UserNameBox.getText().equals(""))
    	{
    		USERANME=TF_UserNameBox.getText().trim();
    		L_LoggedinasBox.setText(USERANME);
    		Echo1Server.CurrentUsers.add(USERANME);
    		MainWindow.setTitle(USERANME+"'s Chat Box");
    		LoginWindow.setVisible(false);
    		B_Send.setEnabled(true);
    		B_DisConnect.setEnabled(true);
    		B_Connect.setEnabled(false);
    		Connect();
    		
    		
    		
    	}
    	else
    	{
    		JOptionPane.showMessageDialog(null, "Please enter a name");
    	}
    }
    
    public static void MainWindow_Action()
    {
    	B_Send.addActionListener(
    			
    			new java.awt.event.ActionListener()
    			{
    				public void actionPerformed(java.awt.event.ActionEvent ae)
    				{
    					ACTION_B_SEND();
    				}
    			}
    			
    			);
    	
B_DisConnect.addActionListener(
    			
    			new java.awt.event.ActionListener()
    			{
    				public void actionPerformed(java.awt.event.ActionEvent ae)
    				{
    					ACTION_B_DISCONNECT();
    				}
    			}
    			
    			);


B_Connect.addActionListener(
		
		new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent ae)
			{
				BuildLogInWindow();
			}
		}
		
		);

B_Help.addActionListener(
		
		new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent ae)
			{
				ACTION_B_HELP();
			}
		}
		
		);
       

    }
    public static void ACTION_B_SEND()
    {
    	if(!TF_Message.getText().equals(""))
    	{
    		chatclient.SEND(TF_Message.getText());
    		TF_Message.requestFocus();
    		
    	}

     }
    public static void ACTION_B_DISCONNECT()
    {
    	try
    	{
    		chatclient.DISCONNECT();
    		
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    }
   
    public static void ACTION_B_HELP()
    {
        JOptionPane.showMessageDialog(null, "MultiClient Chat Program\n CEO: Varun Jain  \t9460494259");
     }
    	
}

