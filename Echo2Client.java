import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Echo2Client implements Runnable
{
    Socket Sock;
    private Scanner input;
    private PrintWriter pw;
    String Message="";
    public Echo2Client(Socket X)
    {
    	this.Sock=X;
    	
    }
    public void CheckConnection() throws IOException
    {
    	if(!Sock.isConnected())
    	{
    		for(int i=1;i<=Echo1Server.ConnectionArray.size();i++)
    		{
    			if(Echo1Server.ConnectionArray.get(i)==Sock)
    			{
    				Echo1Server.ConnectionArray.remove(i);
    			}
    		}
    		for(int i=1;i<=Echo1Server.ConnectionArray.size();i++)
    		{
    			Socket Temp_Sock=(Socket)Echo1Server.ConnectionArray.get(i-1);
    			PrintWriter Temp_Out=new PrintWriter(Temp_Sock.getOutputStream());
    			Temp_Out.println(Temp_Sock.getLocalAddress().getHostName()+"disconnected");
    			Temp_Out.flush();
    			System.out.println(Temp_Sock.getLocalAddress().getHostName()+"disconnected");
    			
    		}
    	}
    }
    public void run()
    {
    	try
    	{
    		 try
    		 {
    			 input= new Scanner(Sock.getInputStream());
    			 pw=new PrintWriter(Sock.getOutputStream());
    			 while(true)
    			 {
    				 CheckConnection();
    				 if(!input.hasNext())
    				 {
    					 return;
    				 }
    				 Message=input.nextLine();
    				 System.out.println("Client said:"+Message);
    				 for(int i=1;i<=Echo1Server.ConnectionArray.size();i++)
    				 {
    					 Socket Temp_Sock=(Socket)Echo1Server.ConnectionArray.get(i-1);
    		    	     PrintWriter Temp_Out=new PrintWriter(Temp_Sock.getOutputStream());
    		    		 Temp_Out.println(Message);
    		    		 Temp_Out.flush();
    		    		System.out.println("Sent to:"+Temp_Sock.getLocalAddress().getHostName());
    				 }
    			 }
    		 }
    		 finally
    		 {
    			  Sock.close();
    		 }
    	}
    	catch(Exception X)
    	{
    		System.out.print(X);
    	}
    	
    }
	

}
