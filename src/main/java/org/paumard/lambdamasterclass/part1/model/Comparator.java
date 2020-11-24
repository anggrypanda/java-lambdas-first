package org.paumard.lambdamasterclass.part1.model;

import java.util.function.Function;

@FunctionalInterface
public interface Comparator<T> {
    public int compare (T t1, T t2);

    public default Comparator<T> thenComparing (Comparator<T> cmp) {
        return (p1, p2) -> compare(p1, p2) == 0 ? cmp.compare(p1, p2) : compare(p1, p2); //if the objects are equal in the sense of the first comparator, then they will be compared in the sense of the second cmp
    }

    public default Comparator<T> thenComparing (Function<T, Comparable> f) { //function of T rather than U, because we're in a non-static method
        return thenComparing(comparing(f));
/*                      OR
        Comparator<T> cmp = comparing(f);
        return thenComparing(cmp);          */
    }

    public static <U> Comparator<U> comparing(Function<U, Comparable> f) {
        return (p1, p2) -> f.apply(p1).compareTo(f.apply(p2));
    }

}
