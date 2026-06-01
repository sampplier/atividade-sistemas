import java.io.Serializable;

public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String nome;
    private final int aposta;

    public Pedido(String nome, int aposta) {
        this.nome = nome;
        this.aposta = aposta;
    }

    public String getNome() {
        return nome;
    }

    public int getAposta() {
        return aposta;
    }
}
