import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class Transfer {

    Session session = null;

    public Transfer(){

    }
    
    String host="192.168.0.6";
    String user="ubuntu";
    String password="Test@123";

    public void connect(){
    try {

            JSch jsch = new JSch();
            session = jsch.getSession(user,host, 22);
            session.setPassword(password);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            System.out.println(session.getHost());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void executeCommand(String script) throws JSchException, IOException{
        System.out.println("Execute sudo");
        String sudo_pass = "Test@123";
        ChannelExec channel = (ChannelExec) session.openChannel("exec");
        ((ChannelExec) channel).setCommand( script);

        InputStream in = channel.getInputStream();
        OutputStream out = channel.getOutputStream();
        ((ChannelExec) channel).setErrStream(System.err);

        channel.connect();
        out.write((sudo_pass + "\n").getBytes());
        out.flush();

        byte[] tmp = new byte[1024];
        while (true) {
            while (in.available() > 0) {
                int i = in.read(tmp, 0, 1024);
                if (i < 0)
                    break;
                System.out.print(new String(tmp, 0, i));
            }
            if (channel.isClosed()) {
                System.out.println("exit-status: " + channel.getExitStatus());
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (Exception ee) {
                System.out.println(ee);
            }
        }
        channel.disconnect();
        System.out.println("Sudo disconnect");
    }

    public void disconnect(){
        session.disconnect();
    }


    public static void main(String... args) throws JSchException, IOException {

        Transfer up = new Transfer();
        up.connect();

        up.executeCommand("sudo apt-get install jq; /home/ubuntu/Templates/Startup.sh");

        up.disconnect();
    }
}