package collegedatabaseapp;
import java.util.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class PrincipalServer {
	public static void main(String argv[]){
		try{
			ServerSocket ss = new ServerSocket(1202);
			Socket s = ss.accept();

			System.out.println("Connencted");

			DataInputStream din = new DataInputStream(s.getInputStream());
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			String msgin = "", msgout = "";

			while(!msgin.equals("end")){
				msgin = din.readUTF();
				System.out.println("Student : " + msgin);
				msgout = br.readLine();
				dout.writeUTF(msgout);
				dout.flush();
			}
			s.close();
		}catch(Exception e){
			System.out.println("Exception");
		}
	}
		//System.out.println("hey!");
}

