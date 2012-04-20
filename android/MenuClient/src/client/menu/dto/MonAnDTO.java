package client.menu.dto;

import java.io.Serializable;

public class MonAnDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	int maMonAn;
	String hinhAnh;
	float diemDanhGia;
	int soLuotDanhGia;
	int maDonViTinhMacDinh;
	int maDanhMuc;
	boolean ngungBan;
	
	public int getMaMonAn() {
		return maMonAn;
	}
	public void setMaMonAn(int maMonAn) {
		this.maMonAn = maMonAn;
	}
	public String getHinhAnh() {
		return hinhAnh;
	}
	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	public float getDiemDanhGia() {
		return diemDanhGia;
	}
	public void setDiemDanhGia(float diemDanhGia) {
		this.diemDanhGia = diemDanhGia;
	}
	public int getSoLuotDanhGia() {
		return soLuotDanhGia;
	}
	public void setSoLuotDanhGia(int soLuotDanhGia) {
		this.soLuotDanhGia = soLuotDanhGia;
	}
	public int getMaDonViTinhMacDinh() {
		return maDonViTinhMacDinh;
	}
	public void setMaDonViTinhMacDinh(int maDonViTinhMacDinh) {
		this.maDonViTinhMacDinh = maDonViTinhMacDinh;
	}
	public int getMaDanhMuc() {
		return maDanhMuc;
	}
	public void setMaDanhMuc(int maDanhMuc) {
		this.maDanhMuc = maDanhMuc;
	}
	public boolean isNgungBan() {
		return ngungBan;
	}
	public void setNgungBan(boolean ngungBan) {
		this.ngungBan = ngungBan;
	}

}
