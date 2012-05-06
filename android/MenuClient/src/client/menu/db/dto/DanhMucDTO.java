package client.menu.db.dto;

public class DanhMucDTO {
	int maDanhMuc;
	int maDanhMucCha;
	
	public int getMaDanhMuc() {
		return maDanhMuc;
	}
	public void setMaDanhMuc(int maDanhMuc) {
		this.maDanhMuc = maDanhMuc;
	}
	public int getMaDanhMucCha() {
		return maDanhMucCha;
	}
	public void setMaDanhMucCha(int maDanhMucCha) {
		this.maDanhMucCha = maDanhMucCha;
	}	
	
	@Override
	public String toString() {
		return getMaDanhMuc() + "";
	}
}
