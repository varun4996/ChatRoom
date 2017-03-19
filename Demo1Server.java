
import java.io.*;

import java.net.*;

public class Demo1Server {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
     Demo1Server d=new Demo1Server();
     d.run();
	}
	public void run() throws Exception
	{
		ServerSocket sv=new ServerSocket(95);
		Socket so=sv.accept();
		InputStreamReader ir=new InputStreamReader(so.getInputStream());
		BufferedReader Br=new BufferedReader(ir);
		String Mess=Br.readLine();
		System.out.println(Mess);
		if(Mess!=null)
		{
			 PrintStream Ps=new PrintStream(so.getOutputStream());
			 Ps.println("Message recieved");
		}
			
				}

}
