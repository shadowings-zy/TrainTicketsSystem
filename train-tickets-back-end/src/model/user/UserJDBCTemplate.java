package model.user;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

// 用户相关方法实现
public class UserJDBCTemplate implements UserDAO{
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject; 
	
	public void setDataSource(DataSource dataSource) {
	   this.dataSource = dataSource;
	   this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}
	
	@Override
	public User addUser(User user) {
		String SQL = "insert into `12307`.user (user_name, password, idcard, status, telephone) values (?, ?, ?, ?, ?)";     
		jdbcTemplateObject.update(SQL, user.getUserName(), user.getPassword(), user.getIdcard(), user.getStatus(), user.getTelephone());
		
		SQL = "select * from `12307`.user where user_name = ?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
		ArrayList<User> userList = (ArrayList<User>) jdbcTemplateObject.query(SQL, rowMapper, user.getUserName());
		
		return userList.get(0);
	}

	@Override
	public User updateUser(User user) {
		String SQL = "update `12307`.user set user_name = ?, password = ?, status = ?, telephone = ? where idcard = ?";     
		jdbcTemplateObject.update(SQL, user.getUserName(), user.getPassword(), user.getStatus(), user.getTelephone(), user.getIdcard());
	    
		SQL = "select * from `12307`.user where user_name = ?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
		ArrayList<User> userList = (ArrayList<User>) jdbcTemplateObject.query(SQL, rowMapper, user.getUserName());
		
		return userList.get(0);
	}

	@Override
	public ArrayList<User> getUserByIdcard(String idcard) {
		String SQL = "select * from `12307`.user where idcard = ?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
		ArrayList<User> userList = (ArrayList<User>) jdbcTemplateObject.query(SQL, rowMapper, idcard);
		return userList;
	}
	
	@Override
	public ArrayList<User> getUserByUsernameAndPassword(String username, String password) {
		String SQL = "select * from `12307`.user where user_name = ? and password = ?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
		ArrayList<User> userList = (ArrayList<User>) jdbcTemplateObject.query(SQL, rowMapper, username, password);
		return userList;
	}
}
