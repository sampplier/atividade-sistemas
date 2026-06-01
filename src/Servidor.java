import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private static final int PORTA_PADRAO = 5000;

    public static void main(String[] args) {
        int porta = args.length > 0 ? Integer.parseInt(args[0]) : PORTA_PADRAO;
        Cofre cofre = new Cofre();

        try (ServerSocket servidor = new ServerSocket(porta)) {
            System.out.println("Servidor à escuta na porta " + porta);

            while (true) {
                Socket cliente = servidor.accept();
                new Thread(new AtendimentoCliente(cliente, cofre)).start();
            }
        } catch (Exception erro) {
            System.out.println("Erro no servidor: " + erro.getMessage());
        }
    }
}
