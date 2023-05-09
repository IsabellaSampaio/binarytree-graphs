
/**
 * Comparador por Matricula
 */

import java.util.Comparator;

public class ComparadorPorMatricula implements Comparator<Aluno> {

    @Override
    public int compare(Aluno a, Aluno b) {
        return Integer.compare(a.getMatricula(), b.getMatricula());
    }

    public int getComparador(Aluno no) {
        return no.getMatricula();
    }

}
