import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

public class AtendimentoCliente implements Runnable {
    private final Socket socket;
    private final Cofre cofre;

    public AtendimentoCliente(Socket socket, Cofre cofre) {
        this.socket = socket;
        this.cofre = cofre;
    }

    @Override
    public void run() {
        try (Socket conexao = socket;
             ObjectOutputStream saida = new ObjectOutputStream(conexao.getOutputStream());
             ObjectInputStream entrada = new ObjectInputStream(conexao.getInputStream())) {

            saida.flush();
            Pedido pedido = (Pedido) entrada.readObject();
            int codigoSecreto = ThreadLocalRandom.current().nextInt(1000);
            Resposta resposta = cofre.jogar(pedido, codigoSecreto);
            saida.writeObject(resposta);
            saida.flush();
        } catch (Exception erro) {
            System.out.println("Falha ao atender cliente: " + erro.getMessage());
        }
    }
}
