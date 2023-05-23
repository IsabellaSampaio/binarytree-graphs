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
}
