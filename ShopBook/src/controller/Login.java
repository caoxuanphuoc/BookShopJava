package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Account;
import model.service.CtrAccount;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		request.getSession().removeAttribute("User");

		RequestDispatcher dispatcher = request.getRequestDispatcher("view/Login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> user = request.getParameterMap();
		CtrAccount ctrAccount = new CtrAccount();
		if (user.size() > 0) {
			if (ctrAccount.Login(user.get("nameLogin")[0], user.get("passLogin")[0])) {//�?ăng nhập thành công
				HttpSession session = request.getSession();
				Account account = ctrAccount.getInformation(user.get("nameLogin")[0]);
				session.setAttribute("User", account);
				
				if (account.getChucVu().equals("user"))
					response.sendRedirect(request.getContextPath());
				else
					response.sendRedirect(request.getContextPath() + "/Manager/BangTin");
				
			} else {
				StringBuffer path = new StringBuffer(request.getContextPath() + "/login?err=");
				if (!user.get("nameLogin")[0].equals("user")) path.append("1");
				else if (!user.get("passLogin")[0].equals("1234")) path.append("0");
				response.sendRedirect(path.toString());
			}
		}
	}
}
