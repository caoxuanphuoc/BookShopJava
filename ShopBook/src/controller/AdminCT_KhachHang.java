package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Account;
import model.service.CtrAccount;

public class AdminCT_KhachHang extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminCT_KhachHang() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/CT_KhachHang.jsp");
		String maKH = request.getParameter("id");
		if (maKH != null && maKH.length() > 0) {//Mã khách hàng hợp lệ
			CtrAccount ctrAccount = new CtrAccount();
			Account khachHang = ctrAccount.getInformation(maKH);
			request.setAttribute("Account", khachHang);//Gửi thông tin khách hàng về client
		} else {//Mã khách hàng lỗi
			request.setAttribute("Account", "error");
		}
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
