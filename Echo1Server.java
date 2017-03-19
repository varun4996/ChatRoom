import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Echo1Server 
{
	public static ArrayList<Socket> ConnectionArray=new ArrayList<Socket>();
	public static ArrayList<String> CurrentUsers=new ArrayList<String>();

	public static void main(String[] args) throws IOException 
	{
		// TODO Auto-generated method stub
    try
    {
    	final int Port=95;
    	ServerSocket sos= new ServerSocket(Port);
    	System.out.println("Waiting for Bakchods to Connect!!!");
    	while(true)
    	{
    		Socket so=sos.accept();
    		ConnectionArray.add(so);
    		System.out.println("Client Connected From"+so.getLocalAddress().getHostName());
    		AddUserName(so);
    		Echo2Client Chat= new Echo2Client(so);
    		Thread t= new Thread(Chat);
    		t.start();
    		
    		
    	}
	
    	 
    }
    catch(Exception e){
    	 System.out.print(e);
    }
	}
    public static void AddUserName(Socket t) throws IOException
    {
    	Scanner sc=new Scanner(t.getInputStream());
    	String UserName=sc.nextLine();
    	CurrentUsers .add(UserName);
    	for(int i=1;i<=Echo1Server.ConnectionArray.size();i++)
    	{
    		Socket temp_sock=(Socket)Echo1Server.ConnectionArray.get(i-1);
    		PrintWriter pw=new PrintWriter(temp_sock.getOutputStream());
    		pw.println("#?!"+CurrentUsers);
    		pw.flush();
    	}
    }
	

}
