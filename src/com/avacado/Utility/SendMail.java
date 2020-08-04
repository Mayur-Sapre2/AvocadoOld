package com.avacado.Utility;

import java.util.Properties;
import javax.activation.*;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@SuppressWarnings("unused")
public class SendMail{
	
	public static void sendMail() throws MessagingException {
		//Recipient's Mail id
		String receipientTo = "msapre@avocadosys.com";
		
		//Sender's Mail id
		String senderFrom = "mayur.sapre@gslab.com";
		
		//Path of PDF test report
		String path = "/home/gslab/eclipse-workspace/Avacado/reports/Test_Report.html";
		
		//Getting System properties
		Properties prop = System.getProperties();
		
		//Setting up smtp host
		prop.setProperty("mail.smtp.host", "smtp.gmail.com");
		
		//Creating a new session for smtp
		Session session = Session.getDefaultInstance(prop);
		MimeMessage msg = new MimeMessage(session);
		
		//Instance of From Internet address
		InternetAddress frmAddress = new InternetAddress(senderFrom);
		
		//Instance of To Internet address
		InternetAddress toAddress = new InternetAddress(receipientTo);
		
		//Setting up sender's address
		msg.setFrom(frmAddress);
		
		//Setting up recipient's address
		msg.addRecipient(Message.RecipientType.TO, toAddress);
		
		//Setting email's subject
		msg.setSubject("Test Status Report");
		BodyPart msgBody = new MimeBodyPart();
		
		//Setting email's message body
		msgBody.setText("This is test report through mail");
		
		//Instance of second part
		Multipart multiPart = new MimeMultipart();
		multiPart.addBodyPart(msgBody);
		
		//Another mail body
		msgBody = new MimeBodyPart();
		
		//Path to file for attachment
		DataSource source = new FileDataSource(path);
		DataHandler dataHandler = new DataHandler(source);
		msgBody.setDataHandler(dataHandler);
		msgBody.setFileName(path);
		multiPart.addBodyPart(msgBody);
		msg.setContent(multiPart);
		
		//Authentication and connection establishment to the sender's mail
		Transport transport = session.getTransport("smtps");
		transport.connect("smtp.gmail.com",587,"mayur.sapre@gslab.com","");
		transport.sendMessage(msg, msg.getAllRecipients());
		transport.close();
		System.out.println("Mail Sent");
		}
}




