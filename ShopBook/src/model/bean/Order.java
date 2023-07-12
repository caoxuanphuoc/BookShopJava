package model.bean;

public class Order {
	private String MaHoaDon, MaTaiKhoan, TenNguoiMua, SDT, TinhTrang,DiaChi, NgayIn;

	public Order() {
		super();
	}

	

	public Order(String maHoaDon, String maTaiKhoan, String tenNguoiMua, String sDT, String tinhTrang, String diaChi,
			String ngayIn) {
		super();
		MaHoaDon = maHoaDon;
		MaTaiKhoan = maTaiKhoan;
		TenNguoiMua = tenNguoiMua;
		SDT = sDT;
		TinhTrang = tinhTrang;
		DiaChi = diaChi;
		NgayIn = ngayIn;
	}



	public String getMaHoaDon() {
		return MaHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		MaHoaDon = maHoaDon;
	}

	public String getMaTaiKhoan() {
		return MaTaiKhoan;
	}

	public void setMaTaiKhoan(String maTaiKhoan) {
		MaTaiKhoan = maTaiKhoan;
	}

	public String getTenNguoiMua() {
		return TenNguoiMua;
	}

	public void setTenNguoiMua(String tenNguoiMua) {
		TenNguoiMua = tenNguoiMua;
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}

	public String getTinhTrang() {
		return TinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		TinhTrang = tinhTrang;
	}



	public String getDiaChi() {
		return DiaChi;
	}



	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}



	public String getNgayIn() {
		return NgayIn;
	}



	public void setNgayIn(String ngayIn) {
		NgayIn = ngayIn;
	}
	
	

}

