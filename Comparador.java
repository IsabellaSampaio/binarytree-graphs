
/**
 * Comparador
 */

 import java.util.Comparator;

 public class Comparador implements Comparator<Cidade> {
 
     @Override
     public int compare(Cidade a, Cidade b) {
         return Integer.compare(a.getId(), b.getId());
     }
 
 
 }
 