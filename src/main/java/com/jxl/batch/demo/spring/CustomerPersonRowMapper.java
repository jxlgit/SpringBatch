package com.jxl.batch.demo.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CustomerPersonRowMapper implements RowMapper<Person> {

    public static final String ID_COLUMN = "person_id";
    public static final String FIRST_NAME_COLUMN = "first_name";
    public static final String LAST_NAME_COLUMN = "last_name";

    @Override
	public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Person person = new Person();
    	
    	person.setPersonId(rs.getInt(ID_COLUMN));
    	person.setFirstName(rs.getString(FIRST_NAME_COLUMN));
    	person.setLastName(rs.getString(LAST_NAME_COLUMN));

        return person;
    }
}