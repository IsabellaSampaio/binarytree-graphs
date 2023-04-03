public class Aluno{
    private String nome;
    private int matricula;
    private int nota;
    
    public Aluno(String nome, int matricula, int nota){
        this.nome=nome;
        this.matricula=matricula;
        this.nota=nota;
    }
    public int getMatricula() {
        return matricula;
    }
    public int getNota() {
        return nota;
    }
    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return this.nome + " " + Integer.toString(this.matricula);
    }

}


