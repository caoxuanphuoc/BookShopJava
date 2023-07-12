package model.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Vector;

import model.bean.Account;
import model.bean.Banner;
import model.dao.DBConnection;

public class CtrBanner {
	private DBConnection connection;

	public CtrBanner() {
		connection = new DBConnection();
	}

	public Banner[] getAllBT() {
		Vector<Banner> results = new Vector<>();
		connection.connect();
		try {
			ResultSet resultSet = connection.executeTableFunction("fc_getAllBT", null);
			while (resultSet.next()) {
				Banner banner = new Banner();
				banner.setMaTin(resultSet.getString(1));
				banner.setNoiDung(resultSet.getString(2));
				banner.setAnh(resultSet.getString(3));
				banner.setTieuDe(resultSet.getString(4));
				results.addElement(banner);

			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			connection.close();
		}
		return results.toArray(new Banner[0]);
	}

	public String addBT(String NoiDung, String Anh, String TieuDe) {
		// Thêm sách mới vào bảng
		connection.connect();
		String MaTin = "";

		try {

			Vector<Object[]> paramsIn_Book = connection.createParams(new int[] { 2, 3, 4 },
					new Object[] { NoiDung, Anh, TieuDe });
			Vector<Object[]> paramsOut_Book = connection.createParams(new int[] { 1 }, new Object[] { Types.VARCHAR });
			Object[] result = connection.executeProcedure("sp_ThemTin", paramsIn_Book, paramsOut_Book, null);
			if (result != null && result.length > 0) {
				MaTin = result[0].toString();

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			connection.close();
		}
		return MaTin;

	}

	public void upBT(String MaTin, String NoiDung, String Anh, String TieuDe) {
		connection.connect();
		try {
			Vector<Object[]> paramsIn_Book = connection.createParams(new int[] { 1, 2, 3, 4 },
					new Object[] { MaTin, NoiDung, Anh, TieuDe });
			connection.executeProcedure("sp_updateBT", paramsIn_Book, null, null);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			connection.close();
		}

	}
	public void delBT(String MaTin)
	{
		connection.connect();
		try
		{
			Vector<Object[]> paramsIn_Book = connection.createParams(new int[] { 1 },
					new Object[] { MaTin});
			connection.executeProcedure("sp_delBT", paramsIn_Book, null, null);
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			connection.close();
		}
	}
	public Banner getBT(String MaTin) {
		Banner banner = null;
		connection.connect();
		try {
			Vector<Object[]> paramsIn = connection.createParams(new int[] { 1 }, new Object[] { MaTin });
			ResultSet resultSet = connection.executeTableFunction("fc_getBTById", paramsIn);
			while (resultSet.next()) {
				banner = new Banner();
				banner.setMaTin(resultSet.getString(1));
				banner.setNoiDung(resultSet.getString(2));
				banner.setAnh(resultSet.getString(3));
				banner.setTieuDe(resultSet.getString(4));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			connection.close();
		}
		return banner;
	}
}
