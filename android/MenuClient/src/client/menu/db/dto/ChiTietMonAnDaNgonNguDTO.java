package client.menu.db.dto;

import java.io.Serializable;

public class ChiTietMonAnDaNgonNguDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	int maMonAn;
	int maNgonNgu;
	String tenMonAn;
	String moTaMonAn;
	public int getMaMonAn() {
		return maMonAn;
	}
	public void setMaMonAn(int maMonAn) {
		this.maMonAn = maMonAn;
	}
	public int getMaNgonNgu() {
		return maNgonNgu;
	}
	public void setMaNgonNgu(int maNgonNgu) {
		this.maNgonNgu = maNgonNgu;
	}
	public String getTenMonAn() {
		return tenMonAn;
	}
	public void setTenMonAn(String tenMonAn) {
		this.tenMonAn = tenMonAn;
	}
	public String getMoTaMonAn() {
		return moTaMonAn;
	}
	public void setMoTaMonAn(String moTaMonAn) {
		this.moTaMonAn = moTaMonAn;
	}
	
	
}
