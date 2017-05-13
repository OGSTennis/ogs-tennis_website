package hei.devweb.dao;

public class DAOConfigurationException extends RuntimeException {
	private static final long serialVersionUID = -3653612474289878622L;

	public DAOConfigurationException( String message ) {
        super( message );
    }

    public DAOConfigurationException( String message, Throwable cause ) {
        super( message, cause );
    }

    public DAOConfigurationException( Throwable cause ) {
        super( cause );
    }
}