import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.json.simple.parser.ParseException;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class TransferFileToRemote 
{	
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		
	String host="192.168.1.204";
    String user="ubuntu";
    String password="Test@123";
    
    try{
    	//Connection to Remote Machine
    	java.util.Properties config = new java.util.Properties(); 
    	config.put("StrictHostKeyChecking", "no");
    	JSch jsch = new JSch();
    	Session session=jsch.getSession(user, host, 22);
    	session.setPassword(password);
    	session.setConfig(config);
    	session.connect();
    	System.out.println(session.getHost());

    	//Copy files from Local to Remote
		ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
		sftpChannel.connect();
		sftpChannel.put("/home/gslab/Templates/Startup.sh", "/home/ubuntu/Templates");
		sftpChannel.put("/home/gslab/Templates/config.json", "/home/ubuntu/Templates");
		sftpChannel.put("/home/gslab/Templates/installer.sh", "/home/ubuntu/Templates");
			
		sftpChannel.put("/home/gslab/Templates/mysql-offline-5.7.26-1ubuntu16.04_amd64.deb-bundle.tar", "/home/ubuntu/Templates");
		sftpChannel.put("/home/gslab/Templates/jdk-8u211-linux-x64.tar.gz","/home/ubuntu/Templates");
		sftpChannel.put("/home/gslab/Templates/orchestrator-2.1.75.tar","/home/ubuntu/Templates");
		
		sftpChannel.chmod(0777,"/home/ubuntu/Templates/Startup.sh");
		sftpChannel.chmod(0777,"/home/ubuntu/Templates/config.json");
		sftpChannel.chmod(0777,"/home/ubuntu/Templates/installer.sh");
		sftpChannel.chmod(0777,"/home/ubuntu/Templates/mysql-offline-5.7.26-1ubuntu16.04_amd64.deb-bundle.tar");
		sftpChannel.chmod(0777,"/home/ubuntu/Templates/jdk-8u211-linux-x64.tar.gz");
		sftpChannel.chmod(0777,"/home/ubuntu/Templates/orchestrator-2.1.75.tar");
		
		System.out.println("Files copy successully from Local to Remote server");
    
		//Execute Script on Server
		String sudo_pass = "Test@123";
		ChannelExec channel = (ChannelExec) session.openChannel("exec");
		session.setPassword(password);
		channel.setCommand("cd /home/ubuntu/Templates && ./Startup.sh");
//		channel.setCommand("./Startup.sh");
		channel.setInputStream(null);
		channel.setErrStream(System.err);
		InputStream in = channel.getInputStream();
		OutputStream out=channel.getOutputStream();
		channel.connect();
		out.write((sudo_pass + "\n").getBytes());
		out.flush();
		
		byte[] tmp=new byte[1024];
	      while(true){
	        while(in.available()>0){
	          int i=in.read(tmp, 0, 1024);
	          if(i<0)break;
	          System.out.print(new String(tmp, 0, i));
	        }
	        if(channel.isClosed()){
	          if(in.available()>0) continue; 
	          System.out.println("exit-status: "+channel.getExitStatus());
	          break;
	        }
	        try{Thread.sleep(1000);}catch(Exception ee){}
	      }
	      channel.disconnect();
	      session.disconnect();
		
	}
    
    catch(Exception e) {
    	e.printStackTrace();
    }
	}
}

