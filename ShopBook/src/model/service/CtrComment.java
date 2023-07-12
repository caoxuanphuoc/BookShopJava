package model.service;

import java.sql.ResultSet;
import java.util.Vector;

import model.bean.Comment;
import model.dao.DBConnection;

public class CtrComment {
	private DBConnection connection;

	public CtrComment() {
		super();
		connection = new DBConnection();
	}
	
	public Comment[] getCommentOfBook(String maSach) {
		//Lấy danh sách bình luận của cuốn sách
		Vector<Comment> comments = new Vector<>();
		connection.connect();
		
		try {
			Vector<Object[]> paramsIn = connection.createParams(new int[] {1}, new Object[] {maSach});
			ResultSet resultSet = connection.executeTableFunction("fc_GetAllCommentOfBook", paramsIn);
			
			while(resultSet.next()) {
				Comment comment = new Comment();
				comment.setTenNguoiDung(resultSet.getString(1));
				comment.setNoiDung(resultSet.getString(2));
				comment.setThoiGian(resultSet.getString(3));
				comment.setDanhGia(resultSet.getInt(4));
				comments.addElement(comment);
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			connection.close();
		}
		return comments.toArray(new Comment[0]);
	}
	
	public void addCommentToBook(String maTaiKhoan, String tenTaiKhoan, String maSach, String noiDung, int danhGia) {
		//Thêm bình luận cho sách
		connection.connect();
		 try {
			Vector<Object[]> paramsIn = connection.createParams(new int[] {1, 2, 3, 4, 5}, new Object[]{maTaiKhoan, tenTaiKhoan, maSach, noiDung, danhGia});
			connection.executeProcedure("sp_AddCommentToBook", paramsIn, null, null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			connection.close();
		}
	}
}
