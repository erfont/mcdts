package utils;

import java.util.ArrayList;
import java.util.Iterator;

import elements.Skeleton;

public class PopIterator implements Iterator<Skeleton> {

    private Iterator<Skeleton> cem;    

    public PopIterator( ArrayList<Skeleton> cem ) {
        this.cem = cem.iterator();
    }

    @Override
    public boolean hasNext() {
        return this.cem.hasNext();
    }

    @Override
    public Skeleton next() {
        return this.cem.next();
    }

    @Override
    public void remove() {
        this.cem.remove();
    }

}
