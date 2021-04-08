import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Worker2 {

    public static void main (String [] argv) throws Exception {
        System.out.println (" --------- Consumidor 2 --------- ");

        //criando a fabrica de conexoes e criando uma conexao
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setUsername("");
        connectionFactory.setPassword("");
        Connection conexao = connectionFactory.newConnection();

        //criando um canal e declarando uma fila
        Channel channel = conexao.createChannel();

        String NOME_FILA2 = "Karoline";
        channel.queueDeclare(NOME_FILA2, true, false, false, null);
        System.out.println ("[*] Aguardando mensagens. Para sair, pressione CTRL + C");

        channel.basicQos(1);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String mensagem = new String (delivery.getBody (), "UTF-8");

            System.out.println ("[x] Recebido '" + mensagem + "'");
            try {
                doWork (mensagem);
            } finally {
                System.out.println ("[x] Done");
                channel.basicAck (delivery.getEnvelope (). getDeliveryTag (), false);
            }
        };
        channel.basicConsume (NOME_FILA2, false, deliverCallback, consumerTag -> {});
    }

    private static void doWork(String task) {
        for (char ch: task.toCharArray ()) {
            if (ch == '.') {
                try {
                    Thread.sleep (1000);
                } catch (InterruptedException _ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
