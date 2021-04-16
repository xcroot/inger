package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;

import entity.User;

import bean.RegistManage;

public class UserManage extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		if (path.equals("/login")){
			String userId = request.getParameter("jb_text");
			String pwd = request.getParameter("jb_password");
			//System.out.println(userId+pwd);
			RegistManage reg = new RegistManage();
			try {
				if(reg.Check(userId, pwd)){
					//System.out.println("ok");
					//response.sendRedirect("login.jsp");
					User user = new User();
					UserDAO dao =  new UserDAO();
					
					HttpSession session = request.getSession();
					user = (User)session.getAttribute("user");
					if(user == null){
						user = dao.findById(userId);
						session.setAttribute("user", user);
					}
					response.getWriter().print("ok");
					//request.getRequestDispatcher("main.jsp").forward(request, response);
				}else {
					//System.out.println("fail");
					request.setAttribute("login_err", "用户名或密码错误");
					response.getWriter().print("err");
					//request.getRequestDispatcher("login.jsp").forward(request, response);
				}
					
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
		}
		
	}
}
