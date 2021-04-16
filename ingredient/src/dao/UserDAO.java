package dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



import entity.User;

import util.DBUtil;

public class UserDAO {
	public void save(User user) throws Exception{
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep = conn.prepareStatement("insert into ingre_user(`user_id`,`user_name`, `user_pwd`, `worktype`, `dept`, `email`, `permision`)" +
				"VALUES (?, ?, ?, ?, ?, ?, ?)");
		prep.setString(1, user.getUserId());
		prep.setString(2, user.getUserName());
		prep.setString(3, user.getUserPwd());
		prep.setString(4, user.getWorkType());
		prep.setString(5, user.getWorkType());
		prep.setString(6, user.getDept());
	    prep.setString(7, user.getEmail());
	    prep.setString(8, user.getPermision());
	    prep.executeUpdate();
	    DBUtil.close(conn);
	}
	
	public User findById(String userId) throws Exception{
		User user = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep = conn.prepareStatement("SELECT * FROM ingre_user where user_id = ?");
		prep.setString(1, userId);
		ResultSet rst = prep.executeQuery();
		while(rst.next()){
			user = new User();
			user.setUserId(rst.getString("user_id"));
			user.setUserName(rst.getString("user_name"));
			user.setUserPwd(rst.getString("user_pwd"));
			user.setWorkType(rst.getString("worktype"));
			user.setDept(rst.getString("dept"));
			user.setEmail(rst.getString("email"));
			user.setPermision(rst.getString("permision"));
		}
		DBUtil.close(conn);
		return user;
	}
	public User findByEmail(String email) throws Exception{
		User user = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep = conn.prepareStatement("SELECT * FROM ingre_user where email = ?");
		prep.setString(1, email);
		ResultSet rst = prep.executeQuery();
		if(rst.next()){
			user = new User();
			user.setUserId(rst.getString("user_id"));
			user.setUserName(rst.getString("user_name"));
			user.setUserPwd(rst.getString("user_pwd"));
			user.setWorkType(rst.getString("worktype"));
			user.setDept(rst.getString("dept"));
			user.setEmail(rst.getString("email"));
			user.setPermision(rst.getString("permision"));
		}
		return user;
	}
	
	public void renewpwd(String userId, String strPwd ) throws Exception{
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep = conn.prepareStatement("update ingre_user set user_pwd = ? where user_id=?");
		prep.setString(1, strPwd);
		prep.setString(2, userId);
		prep.executeUpdate();
		DBUtil.close(conn);
		
	}
}
