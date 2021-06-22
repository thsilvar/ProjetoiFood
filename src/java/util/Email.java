
package util;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
     Habilitar Acesso a app menos seguro
     Conta Gmail > Configurações > Segurança > Acesso a app menos seguro
*/    

public class Email {
    private static Email instance = new Email();
    private static Properties props = new Properties();
    public static Email getInstance(){
        return instance;
    }

    public Email() {}

    public static Properties getProps() {
        /** Parâmetros de conexão com servidor Gmail */
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        return props;
    }

    public static void enviarEmail(String titulo, String corpo, String destinatario){
        Session session = Session.getDefaultInstance(getProps(),
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication("thsilvar@gmail.com",
                                "tm6in26c"); // acertar senha
                    }
                });
        session.setDebug(true);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("thsilvar@gmail.com"));
            Address[] toUser = InternetAddress.parse(destinatario);
            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject(titulo);
            message.setText(corpo);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}