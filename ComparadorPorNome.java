
import java.util.Comparator;
public class ComparadorPorNome implements Comparator<Aluno> {

    @Override
    public int compare(Aluno a, Aluno b) {
        return a.getNome().compareTo(b.getNome());
    }

    public String getComparador(Aluno no){
        return no.getNome();
    }
}
