package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminManager() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin/Manager.jsp");
		
		//Xử lý query
		String sIndex = request.getParameter("index");
		if (sIndex != null) {
			String site = null;
			String sSubIndex = request.getParameter("subIndex");
			int index, subIndex;
			if (sSubIndex == null) {
				index = Integer.parseInt(sIndex);
				site = getSite(index);
			} else {
				index = Integer.parseInt(sIndex);
				subIndex = Integer.parseInt(sSubIndex);
				site = getSite(index, subIndex);
			}
			request.setAttribute("site", site);
		}
		else
		{
			request.setAttribute("site", "");
		}
		
		dispatcher.forward(request, response);
	}

	private String getSite(int index, int subIndex) {
		if (index == 4) {
			return "QLSach.jsp";
		}
		return "";
	}

	private String getSite(int index) {
		switch (index) {
		case 1:
			return "QLBanTin.jsp";
		case 2:
			return "QLThuChi.jsp";
		case 3:
			return "QLDonHang.jsp";
		case 4:
			return "QLKhoSach.jsp";
		case 5:
			return "QLKhachHang.jsp";
		}
		return "";
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
