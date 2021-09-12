package com.example.database.databasedemo.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.database.databasedemo.entity.Person;



@Repository
public class PersonJdbcDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	//making an inner-class coz want it to be used only in personjdbcdow class
	class PersonRowMapper implements RowMapper<Person>{

		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Person person = new Person();
			person.setId(rs.getInt("id"));
			person.setName(rs.getString("name"));
			person.setLocation(rs.getString("location"));
			person.setBirthDate(rs.getTimestamp("birth_date"));
			
			return person;
		}
		
	}
	//using spring jdbc so config is different then simple jdbc
	public List<Person> findAll(){
		return jdbcTemplate.query("SELECT * FROM PERSON", 
				new PersonRowMapper());
		//rplaced new BeanPropertyRowMapper by custom PersonRowMapper()
	}
	public Person findById(int id){
		return jdbcTemplate.queryForObject("SELECT * FROM PERSON WHERE ID = ?", new Object[] { id },
				new BeanPropertyRowMapper<Person>(Person.class));
	//This method is deprecated now.
	}
	/*
	 public Person findById(int id) {

    return jdbcTemplate.queryForObject("select * from person where id = ?",

            new Object[] {id},

            new int[] {1},

            new BeanPropertyRowMapper<Person>(Person.class));

}
	 * */
	public List<Person> findByLoc(String location){
		return jdbcTemplate.query("SELECT * FROM PERSON WHERE LOCATION = ?", new Object[] { location },
				new BeanPropertyRowMapper(Person.class));
	//This method is deprecated now.
	}
	 public Person findByName(String name) {

		    return jdbcTemplate.queryForObject("select * from person where name = ?",

		            new Object[] {name},

		            new int[] {1},

		            new BeanPropertyRowMapper<Person>(Person.class));

		}
	
	 public List<Person> findByLocAndName(String location, String name){
			return jdbcTemplate.query("SELECT * FROM PERSON WHERE LOCATION = ? and NAME = ?", new Object[] { location, name },
					new BeanPropertyRowMapper(Person.class));
		//This method is deprecated now.
		}
	 public int deleteById(int id) {
			return jdbcTemplate.update("delete from person where id=?", new Object[] { id });
		}
	 public int insert(Person person) {
			return jdbcTemplate.update("insert into person (id, name, location, birth_date) " + "values(?,  ?, ?, ?)",
					new Object[] { person.getId(), person.getName(), person.getLocation(),
							new Timestamp(person.getBirthDate().getTime()) });
}
	 public int update(Person person) {
			return jdbcTemplate.update("update person " + " set name = ?, location = ?, birth_date = ? " + " where id = ?",
					new Object[] { person.getName(), person.getLocation(), new Timestamp(person.getBirthDate().getTime()),
							person.getId() });
}
	 
}	 
