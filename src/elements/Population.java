package elements;

import java.util.Enumeration;
import java.util.Hashtable;

public class Population {
    
    private Hashtable<String, Skeleton> cemetery;
    
    public Population(){
        this.cemetery = new Hashtable<String,Skeleton>();
    }
    
    public void addSkeleton(Skeleton s){
        this.cemetery.put( s.getName(), s );
    }
    
    public Skeleton getSkeleton(String key){
        return this.cemetery.get( key );        
    }
    
    public Skeleton removeSkeleton(String key){
        return this.cemetery.remove( key );
    }
    
    public int getSize(){
        return this.cemetery.size();
    }

    public Skeleton getSkeletonInPos( int pos ) {
        Enumeration<Skeleton> e = this.cemetery.elements();
        for (int i=0; i<pos;i++) e.nextElement();
        return e.nextElement();
    }

}
