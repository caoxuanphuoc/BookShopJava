package model.bean;


public class Image {
	private String MaAnh = "", MaSach = "", DuongDan = "";

	public Image() {
		super();
	}

	public Image(String maSach, String duongDan) {
		super();
		MaSach = maSach;
		DuongDan = duongDan;
	}

	public Image(String maAnh, String maSach, String duongDan) {
		super();
		MaAnh = maAnh;
		MaSach = maSach;
		DuongDan = duongDan;
	}

	public String getMaAnh() {
		return MaAnh;
	}

	public void setMaAnh(String maAnh) {
		MaAnh = maAnh;
	}

	public String getDuongDan() {
		return DuongDan;
	}

	public void setDuongDan(String duongDan) {
		DuongDan = duongDan;
	}

	public String getMaSach() {
		return MaSach;
	}

	public void setMaSach(String maSach) {
		MaSach = maSach;
	}
}
