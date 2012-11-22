package exceptions;

public class NoSuchSkeletonException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public NoSuchSkeletonException() {
        super();        
    }
    
    public NoSuchSkeletonException(String msg, String lost) {
        super(msg);
    }
    
    

}
