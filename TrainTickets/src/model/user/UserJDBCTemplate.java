package model.user;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@Scope("prototype")
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
		String SQL = "insert into `user` (`uname`, `password`, `idcard`, `status`, `tel`) values (?, ?, ?, ?, ?)";     
		jdbcTemplateObject.update(SQL, user.getUname(), user.getPassword(), user.getIdcard(), user.getStatus(), user.getTel());
		
		SQL = "select * from `user` where `uname` = ?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
		ArrayList<User> userList = (ArrayList<User>) jdbcTemplateObject.query(SQL, rowMapper, user.getUname());
		
		return userList.get(0);
	}

	@Override
	public void updateUser(String uid, User user) {
		String SQL = "update `user` set `uname` = ?, `password` = ?, `idcard` = ?, `status` = ?, `tel` = ? where `uid` = ?";     
		jdbcTemplateObject.update(SQL, user.getUname(), user.getPassword(), user.getIdcard(), user.getStatus(), user.getTel(), uid);
	    System.out.println("Update user complete!");
	}

	@Override
	public void deleteUser(String uid) {
		String SQL = "delete from `user` where `uid` = ?";     
		jdbcTemplateObject.update(SQL, uid);
	    System.out.println("Delete user complete!");
	}

	@Override
	public ArrayList<User> getUserByUnameAndPassword(String uname, String password) {
		String SQL = "select * from `user` where `uname` = ? and `password` = ? ";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
		ArrayList<User> userList = (ArrayList<User>) jdbcTemplateObject.query(SQL, rowMapper, uname, password);
		return userList;
	}
	
	@Override
	public ArrayList<User> getUserByUid(String uid) {
		String SQL = "select * from `user` where `uid` = ?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
		ArrayList<User> userList = (ArrayList<User>) jdbcTemplateObject.query(SQL, rowMapper, uid);
		return userList;
	}

	@Override
	public void setUserStatus(String uid, String status) {
		String SQL = "update `user` set `status` = ? where `uid` = ?";     
		jdbcTemplateObject.update(SQL, status, uid);
	    System.out.println("Update user complete!");
	}
}
