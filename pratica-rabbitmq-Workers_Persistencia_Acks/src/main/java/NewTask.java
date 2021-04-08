import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class NewTask {

    public static void main (String [] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setUsername("");
        connectionFactory.setPassword("");

        try (Connection connection = connectionFactory.newConnection ();
             Channel channel = connection.createChannel ()) {

            String mensagem = String.join("", args);
            mensagem += "Karoline Andrade da Silva Lima";
            String NOME_FILA = "Karoline";

            channel.queueDeclare (NOME_FILA, true, false, false, null);

            channel.basicPublish ("", NOME_FILA, MessageProperties.PERSISTENT_TEXT_PLAIN, mensagem.getBytes("UTF-8"));
            System.out.println ("[x] Enviado '" + mensagem + "'");
        }
    }

}
