package model.bean;


public class Book {
	private String MaSach = "", TenSach = "", TheLoai = "", NXB = "", TacGia = "";
	private String MoTa = "";
	private double Rate = 0;
	private Image images = new Image();
	private int GiaBan;
	
	public Book() {
		super();
	}
	
	public Book(String maSach, String tenSach, int giaBan, String theLoai, String nXB, String tacGia, String moTa, 
			double rate, Image images) {
		super();
		MaSach = maSach;
		TenSach = tenSach;
		TheLoai = theLoai;
		NXB = nXB;
		TacGia = tacGia;
		MoTa = moTa;
		Rate = rate;
		this.images = images;
		GiaBan = giaBan;
	}

	public String getMaSach() {
		return MaSach;
	}

	public void setMaSach(String maSach) {
		MaSach = maSach;
	}

	public String getTenSach() {
		return TenSach;
	}

	public void setTenSach(String tenSach) {
		TenSach = tenSach;
	}
	public int getGiaBan() {
		return GiaBan;
	}

	public void setGiaBan(int giaBan) {
		GiaBan = giaBan;
	}
	
	public String getMoTa() {
		return MoTa;
	}

	public void setMoTa(String moTa) {
		MoTa = moTa;
	}

	public String getTheLoai() {
		return TheLoai;
	}

	public void setTheLoai(String theLoai) {
		TheLoai = theLoai;
	}

	public String getNXB() {
		return NXB;
	}

	public void setNXB(String nXB) {
		NXB = nXB;
	}

	public String getTacGia() {
		return TacGia;
	}

	public void setTacGia(String tacGia) {
		TacGia = tacGia;
	}

	public int getRate() {
		return (int)Math.round(Rate);
	}

	public void setRate(double rate) {
		Rate = rate;
	}

	public Image getImages() {
		return images;
	}

	public void setImages(Image images) {
		this.images = images;
	}
	
}
