package model.bean;

public class Banner {
	private String MaTin, NoiDung, Anh, TieuDe;

	public Banner() {
		super();
	}

	public Banner(String maTin, String noiDung, String anh, String tieuDe) {
		super();
		MaTin = maTin;
		NoiDung = noiDung;
		Anh = anh;
		TieuDe = tieuDe;
	}

	public String getMaTin() {
		return MaTin;
	}

	public void setMaTin(String maTin) {
		MaTin = maTin;
	}

	public String getNoiDung() {
		return NoiDung;
	}

	public void setNoiDung(String noiDung) {
		NoiDung = noiDung;
	}

	public String getAnh() {
		return Anh;
	}

	public void setAnh(String anh) {
		Anh = anh;
	}

	public String getTieuDe() {
		return TieuDe;
	}

	public void setTieuDe(String tieuDe) {
		TieuDe = tieuDe;
	}
	

}
