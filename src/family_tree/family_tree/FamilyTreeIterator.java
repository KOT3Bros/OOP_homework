package family_tree.family_tree;

import family_tree.human.Human;

import java.util.Iterator;
import java.util.List;

public class FamilyTreeIterator<T> implements Iterator<T> {
    private int currentIndex;
    private List<T> humans;

    public FamilyTreeIterator(List<T> humans) {
        this.humans = humans;
        currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return humans.size() > currentIndex;
    }

    @Override
    public T next() {
        return humans.get(currentIndex++);
    }
}
