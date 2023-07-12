package controller;

import java.io.IOException;
import java.lang.reflect.Array;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Book;
import model.service.CtrBook;

public class ViewFilterCat extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public ViewFilterCat() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/ViewFilterCat.jsp");
	
		CtrBook book= new CtrBook();
		
		String cate = request.getParameter("cate");
		String sPage = request.getParameter("page");
		if (cate != null) {
			Book[] Books = book.getSearchCat(cate);
			request.setAttribute("FilterCate", Books);
		}
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println(request.getRequestURI());
		
	}
}
