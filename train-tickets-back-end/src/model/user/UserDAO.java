package model.user;

import java.util.ArrayList;

// 用户相关方法接口
public interface UserDAO {
	//向数据库中添加一个用户，并返回添加的用户
	public User addUser(User user);
	
	//根据用户的身份证号修改用户信息，并返回修改后的用户
	public User updateUser(User user);
	
	//根据用户身份证号获取到用户的所有信息
	public ArrayList<User> getUserByIdcard(String idcard);
	
	//根据用户身份证号和密码获取到用户的所有信息
	public ArrayList<User> getUserByUsernameAndPassword(String username, String password);
	
}
