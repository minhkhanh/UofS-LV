package client.menu.dto;

import java.io.Serializable;

public class BanDTO implements Serializable {
	int maBan;
	int maKhuVuc;
	String tenBan;
	String ghiChu;
	boolean active;
	String tinhTrang;
	int maBanChinh;
	public int getMaBan() {
		return maBan;
	}
	public void setMaBan(int maBan) {
		this.maBan = maBan;
	}
	public int getMaKhuVuc() {
		return maKhuVuc;
	}
	public void setMaKhuVuc(int maKhuVuc) {
		this.maKhuVuc = maKhuVuc;
	}
	public String getTenBan() {
		return tenBan;
	}
	public void setTenBan(String tenBan) {
		this.tenBan = tenBan;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getTinhTrang() {
		return tinhTrang;
	}
	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	public int getMaBanChinh() {
		return maBanChinh;
	}
	public void setMaBanChinh(int maBanChinh) {
		this.maBanChinh = maBanChinh;
	}
	
	@Override
	public String toString() {
		return getTenBan();
	}
}
