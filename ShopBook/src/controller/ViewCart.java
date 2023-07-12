package controller;

import java.io.IOException;
import java.net.CookieStore;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Account;
import model.bean.Book;
import model.bean.Cart;
import model.service.CtrBook;
import model.service.CtrCart;

public class ViewCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ViewCart() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/ViewCart.jsp");
		
		Cookie[] cookies = request.getCookies();
		Vector<Cart> cart = getCart(cookies);
		
		//Gửi thông tin giỏ hàng về client
		if (cart.size() > 0)
			request.setAttribute("cart", cart); 
		else
			request.setAttribute("cart", null);
		
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null) {
			if (action.equals("add")) {
				CtrCart ctrCart = new CtrCart();
				
				Account account = (Account)request.getSession().getAttribute("User");
				String tenKhachHang = request.getParameter("name");
				String diaChi = request.getParameter("address");
				String sdt = request.getParameter("phone");
				if ((tenKhachHang != null && diaChi != null && sdt != null) && 
						(tenKhachHang.length() > 0 && diaChi.length() > 0 && sdt.length() > 0)) {
					String maKhachHang = null;
					String email = null;
					if (account != null) {
						maKhachHang = account.getTenDangNhap();
						email = account.getEmail();
					}
					Cart[] carts = getCart(request.getCookies()).toArray(new Cart[0]);
					ctrCart.addNewCart(maKhachHang, tenKhachHang, diaChi, sdt, email, carts);
				}
			}
		}
	}


	private Vector<Cart> getCart(Cookie[] cookies){
		Vector<Cart> carts = new Vector<Cart>(); //Kết quả gửi v�? client
		CtrBook ctrBook = new CtrBook();
		
		if (cookies != null)
		for (int i = 0; i<cookies.length; i++) {
			Cookie cookie = cookies[i];
			String name = cookie.getName();
			if (name.length() <= 4) continue;
			if (name.substring(0, 4).equalsIgnoreCase("book")) {
				String[] value = cookie.getValue().split("-");
				String id = getValueInCookie(value[0]);
				int count = Integer.parseInt(getValueInCookie(value[1]));
				Book book = ctrBook.getBookById(id); //Thông tin của sách
				carts.addElement(new Cart(book, count, book.getGiaBan()));
			}
		}
		return carts;
	}
	
	private String getValueInCookie(String cookie){
		String string[] = cookie.split(":");
		return string[1];
	}
}
