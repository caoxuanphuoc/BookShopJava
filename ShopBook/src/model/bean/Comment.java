package model.bean;

public class Comment {
	private String TenNguoiDung, NoiDung, ThoiGian;
	private int DanhGia;

	public Comment() {
		super();
	}

	public Comment(String tenNguoiDung, String noiDung, String thoiGian, int danhGia) {
		super();
		TenNguoiDung = tenNguoiDung;
		NoiDung = noiDung;
		ThoiGian = thoiGian;
		DanhGia = danhGia;
	}

	public String getTenNguoiDung() {
		return TenNguoiDung;
	}

	public void setTenNguoiDung(String tenNguoiDung) {
		TenNguoiDung = tenNguoiDung;
	}

	public String getNoiDung() {
		return NoiDung;
	}

	public void setNoiDung(String noiDung) {
		NoiDung = noiDung;
	}

	public String getThoiGian() {
		if (ThoiGian.indexOf(":") != -1){
			StringBuffer sb = new StringBuffer(ThoiGian);
			int gio = Integer.parseInt(sb.substring(0, sb.indexOf(":")));
			if (gio > 0) return gio + " giờ trước";
			
			sb.replace(0, sb.length(), sb.substring(sb.indexOf(":") + 1, sb.length()));
			int phut = Integer.parseInt(sb.substring(0,  sb.indexOf(":")));
			if (phut > 0) return phut + " phút trước";
			
			sb.replace(0, sb.length(), sb.substring(sb.indexOf(":") + 1, sb.length()));
			int giay = Integer.parseInt(sb.toString());
			return giay + " giây trước";
		}
		return ThoiGian + " ngày trước";
	}

	public void setThoiGian(String thoiGian) {
		ThoiGian = thoiGian;
	}

	public int getDanhGia() {
		return DanhGia;
	}

	public void setDanhGia(int danhGia) {
		DanhGia = danhGia;
	}
}
