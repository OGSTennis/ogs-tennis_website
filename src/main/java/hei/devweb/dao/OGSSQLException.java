package hei.devweb.dao;

import java.sql.SQLException;

public class OGSSQLException extends RuntimeException{
	private static final long serialVersionUID = -3686445391350844716L;

	public OGSSQLException(SQLException e) {
	}

}
