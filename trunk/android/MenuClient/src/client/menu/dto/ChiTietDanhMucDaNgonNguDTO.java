package client.menu.dto;

import java.io.Serializable;

public class ChiTietDanhMucDaNgonNguDTO implements Serializable {
	int maDanhMuc;
	int maNgonNgu;
	String tenDanhMuc;
	String moTaDanhMuc;
	
	public int getMaDanhMuc() {
		return maDanhMuc;
	}
	public void setMaDanhMuc(int maDanhMuc) {
		this.maDanhMuc = maDanhMuc;
	}
	public int getMaNgonNgu() {
		return maNgonNgu;
	}
	public void setMaNgonNgu(int maNgonNgu) {
		this.maNgonNgu = maNgonNgu;
	}
	public String getTenDanhMuc() {
		return tenDanhMuc;
	}
	public void setTenDanhMuc(String tenDanhMuc) {
		this.tenDanhMuc = tenDanhMuc;
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
