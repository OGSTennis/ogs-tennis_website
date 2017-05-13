package hei.devweb.dao.impl;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DataSourceProvider {

	private static MysqlDataSource dataSource;
	
	public static DataSource getDataSource() {
		if (dataSource == null) {
			dataSource = new MysqlDataSource();
			dataSource.setServerName("xxx");
			dataSource.setPort(3306);
			dataSource.setDatabaseName("xxx");
			dataSource.setUser("xxx");
			dataSource.setPassword("xxx");
		}
		return dataSource;
	}
}
