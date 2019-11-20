package model.bo;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {

	private String remetente;
	private String destinatario;
	private String msg;

	public EmailService() {
		super();
	}

	public EmailService(String remetente, String destinatario, String msg) {
		super();
		this.remetente = remetente;
		this.destinatario = destinatario;
		this.msg = msg;

	}

	public String getRemetente() {
		return remetente;
	}

	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@SuppressWarnings("unused")
	public void sendEmail() {
		Properties props = new Properties();

		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", "smtp.live.com");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("alvesvictor-@hotmail.com", "Alves2504");
			}
		});
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(this.getRemetente()));

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.getDestinatario()));
			message.setSubject("JavaMail");
			message.setText(this.getMsg());

			Transport.send(message);

			System.out.println("Feito!!!");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
