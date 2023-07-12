package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.Banner;
import model.bean.Book;
import model.service.CtrBanner;
import model.service.CtrBook;

public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Home() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/Home.jsp");
		CtrBanner ctr_Ban = new CtrBanner();
		CtrBook ctrBook = new CtrBook();
		Banner[] danhsachbantin = ctr_Ban.getAllBT();
		Book[] topBanChay = ctrBook.getTopBanChay(8);
		Book[] topYeuThich = ctrBook.getTopYeuThich(8);
		request.setAttribute("danhsachbantin", danhsachbantin);
		request.setAttribute("topBanChay", topBanChay);
		request.setAttribute("topYeuThich", topYeuThich);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}