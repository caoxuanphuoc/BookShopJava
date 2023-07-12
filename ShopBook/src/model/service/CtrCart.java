package model.service;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.Vector;

import model.bean.Book;
import model.bean.Cart;
import model.dao.DBConnection;

public class CtrCart {

	private DBConnection connection;
	
	public CtrCart() {
		super();
		connection = new DBConnection();
	}
	
	public void addNewCart(String maKhachHang, String tenKhachHang, String diaChi, String sdt, String email, Cart[] carts) {
		connection.connect();
		try {
			//Tạo hóa đơn mới
			Vector<Object[]> paramsIn  = connection.createParams(new int[] {2, 3, 4, 5, 6}, new Object[] {maKhachHang, tenKhachHang, diaChi, sdt, email});
			Vector<Object[]> paramsOut = connection.createParams(new int[] {1}, new Object[] {Types.VARCHAR});
			Object[] result = connection.executeProcedure("sp_AddCart", paramsIn, paramsOut, null);
			
			//Thêm sách đã mua vô hóa đơn
			addBookToCart(result[0].toString(), carts);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			connection.close();
		}
	}

	private void addBookToCart(String maHoaDon, Cart[] carts) {
		try {
			for (Cart cart : carts) {
				Vector<Object[]> paramsIn = connection.createParams(new int[] {1, 2, 3, 4}, 
						new Object[] {cart.getBook().getMaSach(), maHoaDon, cart.getCount(), cart.getGiaBan()});
				connection.executeProcedure("sp_AddBookToCart", paramsIn, null, null);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Cart[] getCart(String maHoaDon) {
		connection.connect();
		CtrBook ctrBook = new CtrBook();
		Vector<Cart> carts = new Vector<>();

		try {
			Vector<Object[]> paramsIn = connection.createParams(new int[] {1}, new Object[] {maHoaDon});
			ResultSet resultSet = connection.executeTableFunction("fc_getCartDetail", paramsIn);
			while(resultSet.next()) {
				String maSach = resultSet.getString(1);
				Book book = ctrBook.getBookById(maSach);
				
				Cart cart = new Cart();
				cart.setBook(book);
				cart.setCount(resultSet.getInt(2));
				cart.setGiaBan(resultSet.getInt(3));
				
				carts.addElement(cart);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			connection.close();
		}
		return carts.toArray(new Cart[0]);
	}
}
