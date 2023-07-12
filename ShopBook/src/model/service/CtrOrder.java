package model.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.bean.Account;
import model.bean.Order;
import model.dao.DBConnection;

public class CtrOrder {
	private DBConnection connection;

	public CtrOrder() {
		super();
		connection = new DBConnection();
	}

	public Order[] getAllDH() {
		Vector<Order> results = new Vector<>();
		connection.connect();
		try {
			ResultSet resultSet = connection.executeTableFunction("fc_getAllDH", null);
			while (resultSet.next()) {
				Order order = new Order();
				order.setMaHoaDon(resultSet.getString(1));
				order.setMaTaiKhoan(resultSet.getString(2));
				order.setTenNguoiMua(resultSet.getString(3));
				order.setSDT(resultSet.getString(4));
				order.setTinhTrang(resultSet.getString(5));
				results.addElement(order);

			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			connection.close();
		}
		return results.toArray(new Order[0]);
	}

	public Order[] searchDH(String SearchDH) {
		Vector<Order> Orders = new Vector<>();
		connection.connect();
		try {
			Vector<Object[]> paramsIn = connection.createParams(new int[] { 1 }, new Object[] { SearchDH });
			ResultSet resultSet = connection.executeTableFunction("fc_getSearchDH", paramsIn);
			while (resultSet.next()) {
				Order order = new Order();
				order.setMaHoaDon(resultSet.getString(1));
				order.setMaTaiKhoan(resultSet.getString(2));
				order.setTenNguoiMua(resultSet.getString(3));
				order.setSDT(resultSet.getString(4));
				order.setTinhTrang(resultSet.getString(5));
				Orders.addElement(order);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			connection.close();
		}
		return Orders.toArray(new Order[0]);
	}

	public void duyetDon(String MaDH) {
		connection.connect();
		try {
			Vector<Object[]> paramsIn = connection.createParams(new int[] { 1 }, new Object[] { MaDH });
			connection.executeProcedure("sp_setTT", paramsIn, null, null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			connection.close();
		}

	}
	public Order[] getDHById(String MaKH) {
		Vector<Order> Orders = new Vector<>();
		connection.connect();
		try {
			Vector<Object[]> paramsIn = connection.createParams(new int[] { 1 }, new Object[] { MaKH });
			ResultSet resultSet = connection.executeTableFunction("fc_getDHbyID", paramsIn);
			while (resultSet.next()) {
				Order order = new Order();
				order.setMaHoaDon(resultSet.getString(1));
				order.setNgayIn(resultSet.getString(2));
				order.setTinhTrang(resultSet.getString(3));
				Orders.addElement(order);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			connection.close();
		}
		return Orders.toArray(new Order[0]);
	}
	public void huyDon(String MaDH) {
		connection.connect();
		try {
			Vector<Object[]> paramsIn = connection.createParams(new int[] { 1 }, new Object[] { MaDH });
			connection.executeProcedure("sp_HuyDon", paramsIn, null, null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			connection.close();
		}

	}

}
