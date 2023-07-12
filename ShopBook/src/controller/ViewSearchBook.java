package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Book;
import model.service.CtrBook;

public class ViewSearchBook extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    public ViewSearchBook() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/Search.jsp");
	
		CtrBook book= new CtrBook();
		String strSearch=request.getParameter("strSearch");
		if(strSearch!=null)
		{
			Book[] Books=book.getSearchBook(strSearch);
			request.setAttribute("strSearch", Books);	
		}
	
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

}
