package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Book;
import model.bean.Image;
import model.service.CtrBook;
import model.service.CtrImage;

public class AdminQLSach extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminQLSach() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("../admin/QLSach.jsp");
		
		String action = request.getParameter("action");
		if (action != null) {
			if (action.equalsIgnoreCase("add")) {
				request.setAttribute("action", "add"); //Thêm sách mới
			} else {
				String id = request.getParameter("id");
				
				if (id != null) {
					CtrBook control_Book = new CtrBook();
					Book book = control_Book.getBookById(id); //Đọc thông tin sách
					if (book != null) {
						//Truyền thông tin sách về client
						request.setAttribute("action", "modify"); //Chế độ chỉnh sửa sách
						request.setAttribute("book", book); //Thông tin sách
						
						//Thông tin để cập nhật ảnh
						request.setAttribute("id", book.getMaSach());
						Image image = book.getImages();
						if (image.getDuongDan() != null && image.getDuongDan().length() > 0) {
							request.setAttribute("imageAction", "update"); //Chế độ cập nhật ảnh
							request.setAttribute("maAnh", image.getMaAnh()); //Mã ảnh
						} else {
							request.setAttribute("imageAction", "add"); //Thêm ảnh vào cho sách
						}
					} else {
						request.setAttribute("action", "error"); //Đọc thông tin sách lỗi
					}
					
				} else request.setAttribute("action", "error"); //Chuỗi truy vấn sai
			}
		} else request.setAttribute("action", "error"); //Chuỗi truy vấn sai
		
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		
		if (action != null) {
			CtrBook control_Book = new CtrBook();
			String tenSach = request.getParameter("name");
			String sGiaSach = request.getParameter("gia");
			String theLoai = request.getParameter("theLoai");
			String NXB = request.getParameter("nxb");
			String tacGia = request.getParameter("tacGia");
			String moTa = request.getParameter("moTa");
			String duongDanAnh = request.getParameter("duongDan");
			
			if (tenSach.length() == 0 || sGiaSach.length() == 0 || theLoai.length() == 0 || NXB.length() == 0 ||
					tacGia.length() == 0 || moTa.length() == 0 || duongDanAnh.length() == 0) {
				doGet(request, response);
				return;
			}
			
			int giaSach = Integer.parseInt(sGiaSach);
 			if (action.equals("add")) {
				//Thêm sách mới
				
				control_Book.addBook(tenSach, giaSach, theLoai, NXB, tacGia, moTa, duongDanAnh);
			} else if (action.equals("modify")) {
				String imageAction = request.getParameter("imageAction");
				String id = request.getParameter("id");
				if (imageAction.equals("add")) {
					
					//Cập nhật sách chưa có hình
					CtrImage control_Image = new CtrImage(); //Tạo một hình mới trong database
					String maAnh = control_Image.addImage(id, duongDanAnh);
					Image image = new Image(maAnh, id, duongDanAnh);
					
					//Cập nhật sách theo ảnh vừa tạo
					control_Book.updateBook(new Book(id, tenSach, giaSach, theLoai, NXB, tacGia, moTa, 0, image));
				} else if (imageAction.equals("update")) {
					
					//Cập nhật sách đã có hình
					String maAnh = request.getParameter("maAnh");
					Image image = new Image(maAnh, id, duongDanAnh);
					control_Book.updateBook(new Book(id, tenSach, giaSach, theLoai, NXB, tacGia, moTa, 0, image));
				}
			}
		}
		
		doGet(request, response);
	}

}
