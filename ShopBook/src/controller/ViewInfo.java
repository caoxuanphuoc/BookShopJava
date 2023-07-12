package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Account;
import model.bean.Order;
import model.service.CtrAccount;
import model.service.CtrOrder;

/**
 * Servlet implementation class ViewInfo
 */
public class ViewInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/ViewInfo.jsp");
		CtrAccount control_Acc = new CtrAccount();
		CtrOrder control_Order = new CtrOrder();
		String MaKH = ((Account) request.getSession().getAttribute("User")).getTenDangNhap();
		if (MaKH != null) {
			Account userProfile = control_Acc.UserProfile(MaKH);
			Order[] userOrder = control_Order.getDHById(MaKH);
			request.setAttribute("userProfile", userProfile);
			request.setAttribute("userOrder", userOrder);
		}
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String TenDN = ((Account) request.getSession().getAttribute("User")).getTenDangNhap();
		CtrAccount ctrAccount = new CtrAccount();
		if (action.equals("UpdateInfo")) {
			Map<String, String[]> users = request.getParameterMap();
			ctrAccount.UpdateUserInf(TenDN, users.get("UserSignUp")[0], users.get("Sex")[0],
					users.get("Date")[0], users.get("Address")[0], users.get("Email")[0], users.get("PhoneNumber")[0]);
		}  
		if (action == "UpPass"){
			String MKM = request.getParameter("Matkhaumoi");
			ctrAccount.ChangePass(TenDN, MKM);
		} else {
			String maHD = request.getParameter("maHD");
			CtrOrder Ctr_Ord = new CtrOrder();
			if (maHD != null) {
				Ctr_Ord.huyDon(maHD);

			}
		}

		doGet(request, response);
	}
}
