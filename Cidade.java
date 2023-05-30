import java.util.Formatter;

public class Cidade {
    private final int id;
    private final String nome;

    public Cidade(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    /**
     * Obtém o nome da cidade.
     *
     * @return O nome da cidade.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Obtém o ID da cidade.
     *
     * @return O ID da cidade.
     */
    public int getId() {
        return id;
    }

    /**
     * Retorna uma representação em formato de string da cidade.
     *
     * @return A representação da cidade em formato de string.
     */
    @Override
    public String toString() {
        Formatter formatter = new Formatter();
        formatter.format("┌─────────┬────┐%n");
        formatter.format("│ Cidade: %-10s │ Id: %2d │%n", nome, id);
        formatter.format("└─────────┴────┘%n");
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
