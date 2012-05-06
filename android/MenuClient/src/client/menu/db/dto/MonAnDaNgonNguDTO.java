package client.menu.db.dto;

public class MonAnDaNgonNguDTO {
	
	int mId;
    int mMaMonAn;
	int mMaNgonNgu;
	String mTenMonAn;
	String mMoTaMonAn;
	
    public int getId() {
        return mId;
    }
    public void setId(int id) {
        mId = id;
    }
	public int getMaMonAn() {
		return mMaMonAn;
	}
	public void setMaMonAn(int maMonAn) {
		this.mMaMonAn = maMonAn;
	}
	public int getMaNgonNgu() {
		return mMaNgonNgu;
	}
	public void setMaNgonNgu(int maNgonNgu) {
		this.mMaNgonNgu = maNgonNgu;
	}
	public String getTenMonAn() {
		return mTenMonAn;
	}
	public void setTenMonAn(String tenMonAn) {
		this.mTenMonAn = tenMonAn;
	}
	public String getMoTaMonAn() {
		return mMoTaMonAn;
	}
	public void setMoTaMonAn(String moTaMonAn) {
		this.mMoTaMonAn = moTaMonAn;
	}
	
	
}
