package ar.edu.itba.paw.persistence;

import org.postgresql.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@ComponentScan({
		"ar.edu.itba.paw.persistence"
})
public class TestConfig {

	@Bean
	public DataSource dataSource() {
		final SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass(Driver.class);
		dataSource.setUrl("jdbc:hsqldb:mem:paw");
		dataSource.setUsername("hs");
		dataSource.setPassword("");

		return dataSource;
	}
}
