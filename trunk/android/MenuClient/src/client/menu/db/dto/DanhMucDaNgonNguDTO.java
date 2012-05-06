package client.menu.db.dto;

public class DanhMucDaNgonNguDTO {

	int mId;
    int mMaDanhMuc;
	int mMaNgonNgu;
	String mTenDanhMuc;
	String moTaDanhMuc;
	

    public int getId() {
        return mId;
    }
    public void setId(int id) {
        mId = id;
    }
	
	public int getMaDanhMuc() {
		return mMaDanhMuc;
	}
	public void setMaDanhMuc(int maDanhMuc) {
		this.mMaDanhMuc = maDanhMuc;
	}
	public int getMaNgonNgu() {
		return mMaNgonNgu;
	}
	public void setMaNgonNgu(int maNgonNgu) {
		this.mMaNgonNgu = maNgonNgu;
	}
	public String getTenDanhMuc() {
		return mTenDanhMuc;
	}
	public void setTenDanhMuc(String tenDanhMuc) {
		this.mTenDanhMuc = tenDanhMuc;
	}
	public String getMoTaDanhMuc() {
		return moTaDanhMuc;
	}
	public void setMoTaDanhMuc(String moTaDanhMuc) {
		this.moTaDanhMuc = moTaDanhMuc;
	}
	
	@Override
	public String toString() {
		return getTenDanhMuc();
	}
}
