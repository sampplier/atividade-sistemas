import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private static final String HOST_PADRAO = "localhost";
    private static final int PORTA_PADRAO = 5000;

    public static void main(String[] args) {
        String host = args.length > 0 ? args[0] : HOST_PADRAO;
        int porta = args.length > 1 ? Integer.parseInt(args[1]) : PORTA_PADRAO;

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Nome: ");
            String nome = scanner.nextLine().trim();

            int aposta = lerAposta(scanner);
            Pedido pedido = new Pedido(nome, aposta);

            try (Socket socket = new Socket(host, porta);
                 ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream())) {

                saida.flush();
                saida.writeObject(pedido);
                saida.flush();

                Resposta resposta = (Resposta) entrada.readObject();
                System.out.println(resposta.getMensagem());
            }
        } catch (Exception erro) {
            System.out.println("Erro no cliente: " + erro.getMessage());
        }
    }

    private static int lerAposta(Scanner scanner) {
        while (true) {
            System.out.print("Número de 0 a 999: ");
            String texto = scanner.nextLine().trim();

            try {
                int aposta = Integer.parseInt(texto);
                if (aposta >= 0 && aposta <= 999) {
                    return aposta;
                }
            } catch (NumberFormatException erro) {
                System.out.println("Digite um número inteiro válido.");
                continue;
            }

            System.out.println("O número precisa estar entre 0 e 999.");
        }
    }
}
