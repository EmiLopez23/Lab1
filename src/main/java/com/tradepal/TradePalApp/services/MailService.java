package com.tradepal.TradePalApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
@Service
public class MailService {


    @Autowired
    private JavaMailSender mailSender;
    public void sendMail(String toEmail, String body, String subj){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("tradepallabs@gmail.com");
        msg.setTo(toEmail);
        msg.setText(body);
        msg.setSubject(subj);
        mailSender.send(msg);
    }

    @Async
    public void sendRegisterMail(String email, String username){
        String subject = "Bienvenido a TradePal!";
        String body = "Hola " + username + "!\n ¡Bienvenido a TradePal! Nos complace tenerte a bordo como miembro de nuestra creciente comunidad de intercambio de objetos de juego. Con TradePal, podrás organizar y gestionar tus inventarios de distintos juegos, además de conectarte con otros usuarios para realizar intercambios emocionantes.\n" +
                "\n" +
                "Antes de comenzar a explorar las oportunidades de intercambio en TradePal, te recomendamos que des de alta tu inventario personal. Al hacerlo, podrás exhibir los artículos que tienes disponibles para el intercambio y obtener una mejor visión de las posibilidades que tienes frente a ti. Para añadir tus artículos al inventario personal, simplemente inicia sesión en tu cuenta y clickea sobre los items en la sección de inventario.\n" +
                "\n" +
                "Una vez que hayas configurado tu inventario personal, estarás listo para comenzar a explorar los intercambios disponibles. TradePal cuenta con una amplia variedad de usuarios dispuestos a intercambiar artículos en una amplia gama de juegos, lo que te brinda una gran oportunidad de ampliar tu colección y obtener esos objetos que tanto deseas. Explora los listados de intercambio, realiza ofertas y coordina los detalles de tus transacciones directamente con otros usuarios.\n" +
                "\n" +
                "Nuestro equipo de soporte siempre está disponible para ayudarte en cualquier momento durante tu experiencia en TradePal. Si tienes alguna pregunta, inquietud o necesitas asistencia, no dudes en contactarnos a través del correo electrónico tradepallabs@gmail.com. Estaremos encantados de ayudarte en todo lo que podamos.\n" +
                "\n" +
                "Una vez más, te damos la bienvenida a TradePal. Esperamos que disfrutes de tu tiempo aquí, conociendo a otros entusiastas de los juegos y encontrando los intercambios perfectos para enriquecer tu experiencia de juego.\n" +
                "\n" +
                "¡Que tengas grandes intercambios y mucha diversión!\n" +
                "\n" +
                "Atentamente,\n" +
                "\n" +
                "El equipo de TradePal";
        sendMail(email, body, subject);
    }

    @Async
    public void sendInviteSentMail(String emailTo, String usernameTo, String usernameFrom){
        String subject = "¡Invitación de intercambio en TradePal!";
        String body = "Estimado " + usernameTo + ",\n" +
                "\n" +
                "¡" + usernameFrom + " quiere tradear contigo! Inicia sesión para revisar los detalles y responder a la propuesta.\n" +
                "\n" +
                "¡Amplía tu colección de objetos de juego con TradePal!\n" +
                "\n" +
                "Atentamente,\n" +
                "\n" +
                "El equipo de TradePal";
        sendMail(emailTo, body, subject);
    }
    @Async
    public void sendInviteAcceptedMail(String emailTo, String usernameTo, String usernameFrom){
        String subject = "Invitación de intercambio aceptada";
        String body = "Estimado " + usernameTo + ",\n" +
                "\n" +
                "¡Felicidades! Tu invitación de intercambio en TradePal ha sido aceptada por " + usernameFrom + "!\n" +
                "\n" +
                "Revisa tu inventario o usa el chat para comunicarte.\n" +
                "\n" +
                "Atentamente,\n" +
                "\n" +
                "El equipo de TradePal";
        sendMail(emailTo, body, subject);
    }
}
