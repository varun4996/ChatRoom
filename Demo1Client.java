import java.io.*;
import java.net.Socket;
import java.util.*;
public class Demo1Client {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
      Demo1Client d=new Demo1Client();
      d.run();
	}
	public void run() throws Exception
	{
		Socket so= new Socket("localhost",95);
		PrintStream ps=new PrintStream(so.getOutputStream());
		ps.println("hello to server from client");
		InputStreamReader ir= new InputStreamReader(so.getInputStream());
		BufferedReader br= new BufferedReader(ir);
		String Mess=br.readLine();
	    System.out.println(Mess);
		
	}

}
