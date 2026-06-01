public class Cofre {
    private int fundoEmCentavos;

    public synchronized Resposta jogar(Pedido pedido, int codigoSecreto) {
        fundoEmCentavos += 200;

        if (pedido.getAposta() == codigoSecreto) {
            int premioEmCentavos = fundoEmCentavos * 60 / 100;
            fundoEmCentavos = 0;
            return new Resposta("Cofre aberto, " + pedido.getNome() + "! Ganhou " + formatar(premioEmCentavos));
        }

        return new Resposta("Código errado, " + pedido.getNome() + ". O cofre tem " + formatar(fundoEmCentavos) + " acumulados.");
    }

    private String formatar(int valorEmCentavos) {
        int reais = valorEmCentavos / 100;
        int centavos = valorEmCentavos % 100;
        return String.format("R$ %d,%02d", reais, centavos);
    }
}
