package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Book;
import model.service.CtrBook;

public class ViewBookshelf extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ViewBookshelf() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/ViewBookshelf.jsp");
		
		CtrBook ctrBook= new CtrBook();
		String categories=request.getParameter("danhMuc");
		if(categories!=null)
		{
			Book[] books = null;
			if (categories.equalsIgnoreCase("yeuthich")) {
				books = ctrBook.getTopYeuThich();
				request.setAttribute("danhMuc", "Sách được yêu thích");
			} else if(categories.equalsIgnoreCase("banchay")) {
				books = ctrBook.getTopBanChay();
				request.setAttribute("danhMuc", "Sách bán chạy");
			}
			request.setAttribute("danhSachSach", books);	
		}
		
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
