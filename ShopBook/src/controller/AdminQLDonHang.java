package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Account;
import model.bean.Order;
import model.service.CtrAccount;
import model.service.CtrOrder;

public class AdminQLDonHang extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminQLDonHang() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/QLDonHang.jsp");
		CtrOrder Ctr_Ord=new CtrOrder();
		Order[] danhsachdonhang=Ctr_Ord.getAllDH();
		String strSearch=request.getParameter("btnSearch");
		if(strSearch!=null)
		{
			Order[] orders=Ctr_Ord.searchDH(strSearch);
			request.setAttribute("strSearch", orders);	
		}
		else
		{
			request.setAttribute("danhsachdonhang", danhsachdonhang);
		}
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String maHD=request.getParameter("maHD");
		CtrOrder Ctr_Ord=new CtrOrder();
		if(maHD!=null)
		{
			Ctr_Ord.duyetDon(maHD);
		}
		doGet(request, response);
	}

}
