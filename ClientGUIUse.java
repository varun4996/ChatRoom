import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class ClientGUIUse implements Runnable
{
     Socket Sock;
     Scanner input;
     Scanner sc= new Scanner(System.in);
     PrintWriter pw;
     
     public ClientGUIUse(Socket X)
     {
    	 this.Sock=X;
    	 
     }
     
	
	

	@Override
	public void run() {
		
		// TODO Auto-generated method stub
		try
		{
			try
			{
				input=new Scanner(Sock.getInputStream());
				pw=new PrintWriter(Sock.getOutputStream());
				pw.flush();
				CheckStream();
				
			}
			finally
			{
				Sock.close();
			}
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
		
	}
	public void DISCONNECT() throws IOException
	{
		pw.println(ClientGUI.USERANME+"has Disconnected");
		pw.flush();
		Sock.close();
		JOptionPane.showMessageDialog(null, "you Disconnected");
		System.exit(0);
		
	}
	public void CheckStream()
	{
		while(true)
		{
			Recieve();
			
		}
		
	}
	public void Recieve()
	{
		if(input.hasNext())
		{
			String Message=input.nextLine();
			if(Message.contains("#?!"))
			{
				String temp1=Message.substring(3);
				temp1.replace("(","");
				temp1.replace(")","");
				String[] CurrentUsers=temp1.split(",  ");
				ClientGUI.JL_online.setListData(CurrentUsers);
				
				
			}
			else
			{
				ClientGUI.TA_conversation.append(Message+"\n");
			}
		}
	}
	public void SEND(String X)
	{
		pw.println(ClientGUI.USERANME+"|"+X);
		pw.flush();
		ClientGUI.TF_Message.setText("");
	}

}
