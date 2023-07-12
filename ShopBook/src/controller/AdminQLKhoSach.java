package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Book;
import model.service.CtrBook;

public class AdminQLKhoSach extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminQLKhoSach() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/QLKhoSach.jsp");
		
		if (request.getParameter("action") == null) {
			CtrBook control_Book = new CtrBook();
			Book[] danhSachSach = control_Book.getAllBook();
			request.setAttribute("danhSachSach", danhSachSach);
		}
		
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("searchBook")) {
			CtrBook ctrBook = new CtrBook();
			Book[] books = ctrBook.getSearchBook(request.getParameter("strSearch"));
			request.setAttribute("danhSachSach", books);
		}
		
		doGet(request, response);
	}

}
