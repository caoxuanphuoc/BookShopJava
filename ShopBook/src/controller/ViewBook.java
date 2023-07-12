package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.ranges.RangeException;

import model.bean.Account;
import model.bean.Book;
import model.service.CtrBook;
import model.service.CtrComment;

public class ViewBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ViewBook() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/ViewBook.jsp");
		CtrBook control_Book = new CtrBook();
		CtrComment ctrComment = new CtrComment();

		String id = (String) request.getParameter("id");
		if (id != null) {
			Book book = control_Book.getBookById(id);
			if (book != null) {
				request.setAttribute("book", book);
				request.setAttribute("rate", book.getRate());
				request.setAttribute("comment", ctrComment.getCommentOfBook(book.getMaSach()));
			} else {
				request.setAttribute("book", null);
			}
	
			Book[] bookTT = control_Book.getBookTT(id);
			if (bookTT != null) {
				request.setAttribute("bookTT", bookTT);
			} else {
				request.setAttribute("bookTT", null);
			}
		}
		
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String maSach = request.getParameter("id");
		String user = null;
		if (request.getSession().getAttribute("User") != null)
			user = ((Account)(request.getSession().getAttribute("User"))).getTenDangNhap();
		
		String name = request.getParameter("userName");
		String noiDung = request.getParameter("noiDung");
		String sRate = request.getParameter("rate");
		if ((name != null && noiDung != null && sRate != null && maSach != null) && name.length() > 0){
			if (name.length() == 0) name = null;
			int rate = Integer.parseInt(sRate);
			
			//Add comment
			CtrComment ctrComment = new CtrComment();
			ctrComment.addCommentToBook(user, name,  maSach, noiDung, rate);
			
			//Update rating of book
			CtrBook ctrBook = new CtrBook();
			if (rate > 0 && rate < 6) {
				ctrBook.setRate(maSach, rate);
			}
		}
	}


	
	
	
}
