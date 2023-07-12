package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Account;
import model.bean.Book;
import model.service.CtrAccount;
import model.service.CtrBook;

public class AdminQLKhachHang extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminQLKhachHang() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/QLKhachHang.jsp");
		CtrAccount Ctr_Acc=new CtrAccount();
		Account[] danhsachkhachhang=Ctr_Acc.getAllUser();
		String strSearch=request.getParameter("btnSearch");
		if(strSearch!=null)
		{
			Account[] accounts=Ctr_Acc.searchUser(strSearch);
			request.setAttribute("strSearch", accounts);	
		}
		else
		{
			request.setAttribute("danhsachkhachhang", danhsachkhachhang);
		}
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
