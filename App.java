
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {

        ArrayList<Aluno> alunos = new ArrayList<Aluno>();
        alunos.add(new Aluno("Soraia", 1014, 9));
        alunos.add(new Aluno("Fernanda", 1054, 8));
        alunos.add(new Aluno("Julia", 1050, 5));
        alunos.add(new Aluno("Pedro", 1000, 8));
        alunos.add(new Aluno("Joao", 1001, 9));

        ArvoreBinaria<Aluno> arvNome = new ArvoreBinaria<Aluno>(new ComparadorPorNome());
        ArvoreBinaria<Aluno> arvMat = new ArvoreBinaria<Aluno>(new ComparadorPorMatricula());

        for(int i=0; i<alunos.size(); i++){
            arvNome.setNovoNo(alunos.get(i));
            arvMat.setNovoNo(alunos.get(i));
        }

        System.out.println(arvMat.quantElem());
        System.out.println(arvNome.quantElem());
        
        ArrayList<Aluno> listaArvNome = arvNome.getElementsList();

        for(int i=0; i<listaArvNome.size(); i++){
            System.out.println((listaArvNome.get(i)).getNome());
        }

        ArrayList<Aluno> listaArvMat = arvMat.getElementsList();
        
        for(int i=0; i<listaArvMat.size(); i++){
            System.out.println((listaArvMat.get(i)).getMatricula());
        }

    }
}