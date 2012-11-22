package exceptions;

public class EmptyCemeteryException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public EmptyCemeteryException() {
        super();        
    }
    
    public EmptyCemeteryException(String msg) {
        super(msg);
    }
    
    

}
