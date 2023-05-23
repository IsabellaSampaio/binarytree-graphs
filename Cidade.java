public class Cidade {
    private int id;
    private String nome;
    public Cidade(int id, String nome) {
        this.id=id;
        this.nome=nome;
    }

    public String getNome(){
        return nome;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "----------------------------\nCidade: " + nome + "\nId: " + id;
    }
}