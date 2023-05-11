import java.util.Collection;
import java.util.Comparator;

public class CollectionComparator implements Comparator<Collection<?>> {

    @Override
    public int compare(Collection<?> o1, Collection<?> o2) {
        return Integer.compare(o1.size(),o2.size());
    }

    public int compareValues(Collection<? extends Number> o1, Collection<? extends Number> o2) {
        double sumO1 = o1.stream().mapToDouble(Number::doubleValue).sum();
        double sumO2 = o2.stream().mapToDouble(Number::doubleValue).sum();
        return Double.compare(sumO1,sumO2);
    }
}
