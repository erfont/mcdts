package elements;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import utils.SkeletonLogic;

public class Population {
    
    private List<Skeleton> cemetery;
    
    public Population(){
        this.cemetery = new ArrayList<Skeleton>();
    }
    
    public void addSkeleton(Skeleton s){
        this.cemetery.add( s );
    }
    
    public Skeleton getSkeleton(String name){
        if (this.cemetery.size() == 0) return null;
        Iterator<Skeleton> it = this.cemetery.iterator();
        while (it.hasNext()){
            Skeleton aux = it.next();
            if (aux.getName().equals( name )) return aux;
        }
        return null;
    }
    
    public int getSize(){
        return this.cemetery.size();
    }

    public Skeleton getSkeletonInPos( int pos ) {
        if (this.cemetery.size() == 0) return null;
        return this.cemetery.get( pos );        
    }
    
    public Skeleton getBest(){
        final SortedSet<Skeleton> rankOrder = new TreeSet<Skeleton>( SkeletonLogic.SCORE_COMPARATOR );
        rankOrder.addAll( this.cemetery );        
        return rankOrder.first();
    }
}
