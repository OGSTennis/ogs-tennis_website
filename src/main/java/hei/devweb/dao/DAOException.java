package hei.devweb.dao;

public class DAOException extends RuntimeException {
	private static final long serialVersionUID = -2355856251737455300L;

	public DAOException( String message ) {
        super( message );
    }

    public DAOException( String message, Throwable cause ) {
        super( message, cause );
    }

    public DAOException( Throwable cause ) {
        super( cause );
    }
}