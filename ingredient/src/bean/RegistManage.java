package bean;

import dao.UserDAO;
import entity.User;

public class RegistManage {
	//登录验证
	public boolean Check(String userId,String pwd) throws Exception{
		boolean msg;
		UserDAO dao =  new UserDAO();
		User data = new User(); 
		data = dao.findById(userId);
		if(data == null){
			return false;
		}
		if(data.getUserPwd().equals(pwd)){
			msg = true;
		}else{
			msg = false;
		}
		return msg;
	}
	//注册
	public boolean Register(String userId, String userName,String userPwd, String workType, String dept, String email, String permision) throws Exception{
		UserDAO dao = new UserDAO();
		
		
		User user = new User();
		
		user = dao.findById(userId);
		if(user.getUserId().equals(userId)){
			return false;
		}
		else{
			user.setUserId(userId);
			user.setUserName(userName);
			user.setUserPwd(userPwd);
			user.setWorkType(workType);
			user.setDept(dept);
			user.setEmail(email);
			user.setPermision(permision);
			dao.save(user);
		}
		return true;
	}
}
