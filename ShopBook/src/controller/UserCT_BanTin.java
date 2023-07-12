package controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Banner;

import model.service.CtrBanner;


public class UserCT_BanTin extends HttpServlet{
	private static final long serialVersionUID = 1L;

	
	public UserCT_BanTin() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/CT_BanTin.jsp");
		String MaTin = request.getParameter("id");
		if (MaTin != null) {
			CtrBanner ctr_ban = new CtrBanner();
			Banner banner = ctr_ban.getBT(MaTin);
			request.setAttribute("banner", banner); //Gửi dữ liệu về Client
		} else {//Mã đơn hàng lỗi
			request.setAttribute("banner", "error");
		}
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
