package model.bean;

public class Cart {
	private Book book; //Thông tin sách mua
	private int count; //Số lượng mua
	private int giaBan; //Giá bán
	
	public Cart(Book book, int count, int giaBan) {
		super();
		this.book = book;
		this.count = count;
		this.giaBan = giaBan;
	}

	public Cart() {
		super();
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(int giaBan) {
		this.giaBan = giaBan;
	}
}
