package com.blockchain.exception;

public class ApplicationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4813968361899934094L;

	public ApplicationException() {
        super();        
    }
	
    public ApplicationException(String message, Throwable cause) {
        super(message, cause);        
    }
    
    public ApplicationException(String message) {
        super(message);        
    }
    
    public ApplicationException(Throwable cause) {
        super(cause);        
    }
}
