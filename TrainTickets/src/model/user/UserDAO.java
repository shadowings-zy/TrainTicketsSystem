package model.user;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public interface UserDAO {
	//向数据库中添加一个用户
	public User addUser(User user);
	
	//修改用户信息
	public void updateUser(String uid, User user);
	
	//删除用户
	public void deleteUser(String uid);
	
	//根据用户名和密码获取到用户的所有信息
	public ArrayList<User> getUserByUnameAndPassword(String uname, String password);
	
	//根据uid获取到用户所有信息
	public ArrayList<User> getUserByUid(String uid);
	
	//修改用户状态
	public void setUserStatus(String uid, String status);
	
}
