/**
 * Aluno
 */

public class Aluno {
    private String nome;
    private int matricula;
    private int nota;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Aluno(String nome, int matricula, int nota) {
        this.nome = nome;
        this.matricula = matricula;
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Nome - " + this.nome + " | Matricula - " + Integer.toString(this.matricula) + " | Nota - " + this.nota;
    }

}
