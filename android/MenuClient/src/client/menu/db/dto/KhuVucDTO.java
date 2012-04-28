package client.menu.db.dto;

import java.io.Serializable;

public class KhuVucDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	int maKhuVuc;
	String tenKhuVuc;
	String moTa;
	
	public int getMaKhuVuc() {
		return maKhuVuc;
	}
	public void setMaKhuVuc(int maKhuVuc) {
		this.maKhuVuc = maKhuVuc;
	}
	public String getTenKhuVuc() {
		return tenKhuVuc;
	}
	public void setTenKhuVuc(String tenKhuVuc) {
		this.tenKhuVuc = tenKhuVuc;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	
	@Override
	public String toString() {
		return getTenKhuVuc();
	}
}
