public class testApp {
    public static void main(String[] args) {
        try {
            String to = "cesarsalva91@gmail.com";
            String subject = "Prueba de Mailgun";
            String text = "hola mundo";

            String response = MailgunEmailSender.sendSimpleMessage(to, subject, text);
            System.out.println("Respuesta del servidor: " + response);
        } catch (Exception e) {
            System.err.println("Error al enviar el correo: " + e.getMessage());
        }
    }
}
