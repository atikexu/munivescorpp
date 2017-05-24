package pe.nasqa.values.control;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class SendMail {
	
	private Logger log = Logger.getLogger(SendMail.class);
	
	public void sendSimpleMail(String para, String asunto, String mensaje, String nombre, File adjunto){
		final String username = CVConstante.EMAIL_SENDER_USERNAME_FIX;
		final String password = CVConstante.EMAIL_SENDER_PASSWORD_FIX;
		
		final String urlimgdi = "http://values.dataimagenes.pe/static/img/dataimagenes.png";
		final String urlimgvl = "http://values.dataimagenes.pe/static/img/values_favicon.png";

		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.host", "outlook.office365.com");
//		props.put("mail.smtp.port", "25");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {
			InternetAddress[] destinatarios = new InternetAddress[1];
			destinatarios[0]=new InternetAddress(para);
			InternetAddress[] copiaoculta = new InternetAddress[1];
			copiaoculta[0]=new InternetAddress(username);

			MimeMessage message = new MimeMessage(session);
			message.addHeader("Content-type", "text/HTML; charset=UTF-8");
			message.addHeader("format", "flowed");
			message.addHeader("Content-Transfer-Encoding", "8bit");
            
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, destinatarios);
			message.setRecipients(Message.RecipientType.BCC, copiaoculta);
			message.setSubject(asunto + " / Web Valorados");
		
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			
	        messageBodyPart = new MimeBodyPart();
	        Multipart multipart = new MimeMultipart("related");
	        
	        String html="";
	        html+="Estimado(a) "+nombre+",<br/><br/>";
	        html+=mensaje.replace("\n", "<br/>")+"<br/><br/>";
	        html+="Saludos cordiales,<br/><br/>";
	        html+="Soporte Web Valorados<br/>";
	        html+="<font size='2'>Sistemas de Distribuci&oacute;n</font><br/>";
	        html+="<img src='"+urlimgvl+"'><br/><br/>";
	        html+="<font size='1'>Dataimagenes S.A.C (Grupo El Comercio)</font><br/>";
	        html+="<img src='"+urlimgdi+"'>";
	        
            messageBodyPart.setContent(html, "text/html");
            multipart.addBodyPart(messageBodyPart);
            
            if(adjunto!=null){
		        messageBodyPart = new MimeBodyPart();
		        FileDataSource source = new FileDataSource(adjunto);
		        messageBodyPart.setDataHandler(new DataHandler((javax.activation.DataSource) source));
		        messageBodyPart.setFileName(adjunto.getName());
		        multipart.addBodyPart(messageBodyPart);
            }
	        
	        message.setContent(multipart);
			Transport.send(message);

			log.info("Mensaje enviado a "+para);
			
		} catch (MessagingException e) {
			log.error(e.getMessage());
			//throw new RuntimeException(e);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Otro: "+e.getMessage());
		}
	}
}