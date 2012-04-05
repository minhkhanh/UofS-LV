USE MASTER
GO
IF EXISTS(SELECT * FROM SYS.DATABASES WHERE NAME='ThucDonDienTu')
DROP DATABASE ThucDonDienTu
GO
CREATE DATABASE ThucDonDienTu
GO
USE ThucDonDienTu
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ChiTietDanhMucDaNgonNgu]') AND type in (N'U'))
    DROP TABLE [dbo].[ChiTietDanhMucDaNgonNgu]
GO
CREATE TABLE [dbo].[ChiTietDanhMucDaNgonNgu] (
[MaDanhMuc] smallint  NOT NULL  
, [MaNgonNgu] smallint  NOT NULL  
, [TenDanhMuc] char(50)  NULL  
, [MoTaDanhMuc] char(500)  NULL  
)
GO

ALTER TABLE [dbo].[ChiTietDanhMucDaNgonNgu] ADD CONSTRAINT [ChiTietDanhMucDaNgonNgu_PK] PRIMARY KEY CLUSTERED (
[MaDanhMuc]
, [MaNgonNgu]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ChiTietDonViTinhDaNgonNgu]') AND type in (N'U'))
    DROP TABLE [dbo].[ChiTietDonViTinhDaNgonNgu]
GO
CREATE TABLE [dbo].[ChiTietDonViTinhDaNgonNgu] (
[MaDonViTinh] smallint  NOT NULL  
, [MaNgonNgu] smallint  NOT NULL  
, [TenDonViTinh] char(50)  NULL  
)
GO

ALTER TABLE [dbo].[ChiTietDonViTinhDaNgonNgu] ADD CONSTRAINT [ChiTietDonViTinhDaNgonNgu_PK] PRIMARY KEY CLUSTERED (
[MaDonViTinh]
, [MaNgonNgu]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[MonAn]') AND type in (N'U'))
    DROP TABLE [dbo].[MonAn]
GO
CREATE TABLE [dbo].[MonAn] (
[MaMonAn] smallint identity  NOT NULL  
, [HinhAnh] char(50)  NULL  
, [DiemDanhGia] real  NULL  
, [SoLuotDanhGia] smallint  NULL  
, [MaDonViTinhMacDinh] smallint  NULL  
, [MaDanhMuc] smallint  NULL  
, [NgungBan] bit  NULL  
, [MaDonViTinh] smallint  NULL  
)
GO

ALTER TABLE [dbo].[MonAn] ADD CONSTRAINT [MonAn_PK] PRIMARY KEY CLUSTERED (
[MaMonAn]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ChiTietOrder]') AND type in (N'U'))
    DROP TABLE [dbo].[ChiTietOrder]
GO
CREATE TABLE [dbo].[ChiTietOrder] (
[MaChiTietOrder] smallint identity  NOT NULL  
, [MaOrder] smallint  NULL  
, [SoLuong] smallint  NULL  
, [MaMonAn] smallint  NULL  
, [GhiChu] char(50)  NULL  
, [MaBoPhanCheBien] smallint  NULL  
)
GO

ALTER TABLE [dbo].[ChiTietOrder] ADD CONSTRAINT [ChiTietOrder_PK] PRIMARY KEY CLUSTERED (
[MaChiTietOrder]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[TiGia]') AND type in (N'U'))
    DROP TABLE [dbo].[TiGia]
GO
CREATE TABLE [dbo].[TiGia] (
[MaTiGia] smallint identity  NOT NULL  
, [KiHieu] char(50)  NULL  
, [GiaTri] real  NULL  
, [ThoiDiemCapNhat] smalldatetime  NULL  
)
GO

ALTER TABLE [dbo].[TiGia] ADD CONSTRAINT [TiGia_PK] PRIMARY KEY CLUSTERED (
[MaTiGia]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[DanhMuc]') AND type in (N'U'))
    DROP TABLE [dbo].[DanhMuc]
GO
CREATE TABLE [dbo].[DanhMuc] (
[MaDanhMuc] smallint identity  NOT NULL  
, [MaDanhMucCha] smallint  NULL  
)
GO

ALTER TABLE [dbo].[DanhMuc] ADD CONSTRAINT [DanhMuc_PK] PRIMARY KEY CLUSTERED (
[MaDanhMuc]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ChiTietHoaDon]') AND type in (N'U'))
    DROP TABLE [dbo].[ChiTietHoaDon]
GO
CREATE TABLE [dbo].[ChiTietHoaDon] (
[MaChiTietHoaDon] smallint identity  NOT NULL  
, [MaHoaDon] smallint  NULL  
, [SoLuong] smallint  NULL  
, [DonGiaLuuTru] real  NULL  
, [MaMonAn] smallint  NULL  
, [ThanhTien] real  NULL  
, [GiaTriKhuyenMaiLuuTru] real  NULL  
, [MaKhuyenMai] smallint  NULL  
)
GO

ALTER TABLE [dbo].[ChiTietHoaDon] ADD CONSTRAINT [ChiTietHoaDon_PK] PRIMARY KEY CLUSTERED (
[MaChiTietHoaDon]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ChiTietHuyOrder]') AND type in (N'U'))
    DROP TABLE [dbo].[ChiTietHuyOrder]
GO
CREATE TABLE [dbo].[ChiTietHuyOrder] (
[MaChiTietOrder] smallint identity  NOT NULL  
, [SoLuongYeuCau] smallint  NULL  
, [SoLuongChoPhep] smallint  NULL  
, [TinhTrangHuy] bit  NULL  
)
GO

ALTER TABLE [dbo].[ChiTietHuyOrder] ADD CONSTRAINT [ChiTietHuyOrder_PK] PRIMARY KEY CLUSTERED (
[MaChiTietOrder]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ChiTietMonLienQuan]') AND type in (N'U'))
    DROP TABLE [dbo].[ChiTietMonLienQuan]
GO
CREATE TABLE [dbo].[ChiTietMonLienQuan] (
[MaMonAn] smallint  NOT NULL  
, [MaMonAnLienQuan] smallint  NOT NULL  
)
GO

ALTER TABLE [dbo].[ChiTietMonLienQuan] ADD CONSTRAINT [ChiTietMonLienQuan_PK] PRIMARY KEY CLUSTERED (
[MaMonAn]
, [MaMonAnLienQuan]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[KhuyenMai]') AND type in (N'U'))
    DROP TABLE [dbo].[KhuyenMai]
GO
CREATE TABLE [dbo].[KhuyenMai] (
[MaKhuyenMai] smallint identity  NOT NULL  
, [TenKhuyenMai] char(50)  NULL  
, [MoTaKhuyenMai] char(500)  NULL  
, [GiaGiam] real  NULL  
, [TiLeGiam] real  NULL  
, [BatDau] smalldatetime  NULL  
, [KetThuc] smalldatetime  NULL  
, [LichKhuyenMai] char(500)  NULL  
)
GO

ALTER TABLE [dbo].[KhuyenMai] ADD CONSTRAINT [KhuyenMai_PK] PRIMARY KEY CLUSTERED (
[MaKhuyenMai]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[PhuThu]') AND type in (N'U'))
    DROP TABLE [dbo].[PhuThu]
GO
CREATE TABLE [dbo].[PhuThu] (
[MaPhuThu] smallint identity  NOT NULL  
, [TenPhuThu] char(50)  NULL  
, [MoTaPhuThu] char(500)  NULL  
, [GiaTang] real  NULL  
, [TiLeTang] real  NULL  
, [BatDau] smalldatetime  NULL  
, [KetThuc] smalldatetime  NULL  
, [LichPhuThu] char(500)  NULL  
)
GO

ALTER TABLE [dbo].[PhuThu] ADD CONSTRAINT [PhuThu_PK] PRIMARY KEY CLUSTERED (
[MaPhuThu]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[DonViTinh]') AND type in (N'U'))
    DROP TABLE [dbo].[DonViTinh]
GO
CREATE TABLE [dbo].[DonViTinh] (
[MaDonViTinh] smallint identity  NOT NULL  
)
GO

ALTER TABLE [dbo].[DonViTinh] ADD CONSTRAINT [DonViTinh_PK] PRIMARY KEY CLUSTERED (
[MaDonViTinh]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ChiTietMonAnDonViTinh]') AND type in (N'U'))
    DROP TABLE [dbo].[ChiTietMonAnDonViTinh]
GO
CREATE TABLE [dbo].[ChiTietMonAnDonViTinh] (
[MaMonAn] smallint  NOT NULL  
, [MaDonViTinh] smallint  NOT NULL  
, [DonGia] real  NULL  
)
GO

ALTER TABLE [dbo].[ChiTietMonAnDonViTinh] ADD CONSTRAINT [ChiTietMonAnDonViTinh_PK] PRIMARY KEY CLUSTERED (
[MaDonViTinh]
, [MaMonAn]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[KhuyenMaiHoaDon]') AND type in (N'U'))
    DROP TABLE [dbo].[KhuyenMaiHoaDon]
GO
CREATE TABLE [dbo].[KhuyenMaiHoaDon] (
[MaKhuyenMai] smallint  NOT NULL  
, [MucGiaApDung] real  NULL  
)
GO

ALTER TABLE [dbo].[KhuyenMaiHoaDon] ADD CONSTRAINT [KhuyenMaiHoaDon_PK] PRIMARY KEY CLUSTERED (
[MaKhuyenMai]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ChiTietDanhMucBoPhanCheBien]') AND type in (N'U'))
    DROP TABLE [dbo].[ChiTietDanhMucBoPhanCheBien]
GO
CREATE TABLE [dbo].[ChiTietDanhMucBoPhanCheBien] (
[MaDanhMuc] smallint  NOT NULL  
, [MaBoPhanCheBien] smallint  NOT NULL  
)
GO

ALTER TABLE [dbo].[ChiTietDanhMucBoPhanCheBien] ADD CONSTRAINT [ChiTietDanhMucBoPhanCheBien_PK] PRIMARY KEY CLUSTERED (
[MaDanhMuc]
, [MaBoPhanCheBien]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[KhuVuc]') AND type in (N'U'))
    DROP TABLE [dbo].[KhuVuc]
GO
CREATE TABLE [dbo].[KhuVuc] (
[MaKhuVuc] smallint identity  NOT NULL  
, [TenKhuVuc] char(50)  NULL  
, [MoTa] char(500)  NULL  
)
GO

ALTER TABLE [dbo].[KhuVuc] ADD CONSTRAINT [KhuVuc_PK] PRIMARY KEY CLUSTERED (
[MaKhuVuc]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[KhuyenMaiDanhMuc]') AND type in (N'U'))
    DROP TABLE [dbo].[KhuyenMaiDanhMuc]
GO
CREATE TABLE [dbo].[KhuyenMaiDanhMuc] (
[MaKhuyenMai] smallint  NOT NULL  
, [MaDanhMuc] smallint  NULL  
)
GO

ALTER TABLE [dbo].[KhuyenMaiDanhMuc] ADD CONSTRAINT [KhuyenMaiDanhMuc_PK] PRIMARY KEY CLUSTERED (
[MaKhuyenMai]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[TaiKhoan]') AND type in (N'U'))
    DROP TABLE [dbo].[TaiKhoan]
GO
CREATE TABLE [dbo].[TaiKhoan] (
[MaTaiKhoan] smallint identity  NOT NULL  
, [TenTaiKhoan] char(50)  NULL  
, [MatKhau] char(50)  NULL  
, [ThongTinDinhDanh] char(500)  NULL  
, [Avatar] char(50)  NULL  
, [Active] bit  NULL  
, [MaNhomTaiKhoan] smallint  NULL  
)
GO

ALTER TABLE [dbo].[TaiKhoan] ADD CONSTRAINT [TaiKhoan_PK] PRIMARY KEY CLUSTERED (
[MaTaiKhoan]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[KhuyenMaiKhuVuc]') AND type in (N'U'))
    DROP TABLE [dbo].[KhuyenMaiKhuVuc]
GO
CREATE TABLE [dbo].[KhuyenMaiKhuVuc] (
[MaKhuyenMai] smallint  NOT NULL  
, [MaKhuVuc] smallint  NULL  
)
GO

ALTER TABLE [dbo].[KhuyenMaiKhuVuc] ADD CONSTRAINT [KhuyenMaiKhuVuc_PK] PRIMARY KEY CLUSTERED (
[MaKhuyenMai]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Ban]') AND type in (N'U'))
    DROP TABLE [dbo].[Ban]
GO
CREATE TABLE [dbo].[Ban] (
[MaBan] smallint identity  NOT NULL  
, [MaKhuVuc] smallint  NULL  
, [TenBan] char(50)  NULL  
, [GhiChu] char(500)  NULL  
, [Active] bit  NULL  
, [TinhTrang] bit  NULL  
, [MaBanChinh] smallint  NULL  
)
GO

ALTER TABLE [dbo].[Ban] ADD CONSTRAINT [Ban_PK] PRIMARY KEY CLUSTERED (
[MaBan]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[NgonNgu]') AND type in (N'U'))
    DROP TABLE [dbo].[NgonNgu]
GO
CREATE TABLE [dbo].[NgonNgu] (
[MaNgonNgu] smallint identity  NOT NULL  
, [TenNgonNgu] char(50)  NULL  
)
GO

ALTER TABLE [dbo].[NgonNgu] ADD CONSTRAINT [NgonNgu_PK] PRIMARY KEY CLUSTERED (
[MaNgonNgu]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[KhuyenMaiMon]') AND type in (N'U'))
    DROP TABLE [dbo].[KhuyenMaiMon]
GO
CREATE TABLE [dbo].[KhuyenMaiMon] (
[MaKhuyenMai] smallint  NOT NULL  
, [MaMonAn] smallint  NULL  
)
GO

ALTER TABLE [dbo].[KhuyenMaiMon] ADD CONSTRAINT [KhuyenMaiMon_PK] PRIMARY KEY CLUSTERED (
[MaKhuyenMai]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[NhomTaiKhoan]') AND type in (N'U'))
    DROP TABLE [dbo].[NhomTaiKhoan]
GO
CREATE TABLE [dbo].[NhomTaiKhoan] (
[MaNhomTaiKhoan] smallint identity  NOT NULL  
, [TenNhom] char(50)  NULL  
, [MoTaNhom] char(50)  NULL  
)
GO

ALTER TABLE [dbo].[NhomTaiKhoan] ADD CONSTRAINT [NhomTaiKhoan_PK] PRIMARY KEY CLUSTERED (
[MaNhomTaiKhoan]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ChiTietMonAnDaNgonNgu]') AND type in (N'U'))
    DROP TABLE [dbo].[ChiTietMonAnDaNgonNgu]
GO
CREATE TABLE [dbo].[ChiTietMonAnDaNgonNgu] (
[MaMonAn] smallint  NOT NULL  
, [MaNgonNgu] smallint  NOT NULL  
, [TenMonAn] char(50)  NULL  
, [MoTaMonAn] char(500)  NULL  
)
GO

ALTER TABLE [dbo].[ChiTietMonAnDaNgonNgu] ADD CONSTRAINT [ChiTietMonAnDaNgonNgu_PK] PRIMARY KEY CLUSTERED (
[MaMonAn]
, [MaNgonNgu]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[HoaDon]') AND type in (N'U'))
    DROP TABLE [dbo].[HoaDon]
GO
CREATE TABLE [dbo].[HoaDon] (
[MaHoaDon] smallint identity  NOT NULL  
, [ThoiDiemLap] smalldatetime  NULL  
, [MaTaiKhoan] smallint  NULL  
, [TongTien] real  NULL  
, [MaBanChinh] smallint  NULL  
, [MoTaBanGhep] char(500)  NULL  
, [MaPhuThu] smallint  NULL  
)
GO

ALTER TABLE [dbo].[HoaDon] ADD CONSTRAINT [HoaDon_PK] PRIMARY KEY CLUSTERED (
[MaHoaDon]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Order]') AND type in (N'U'))
    DROP TABLE [dbo].[Order]
GO
CREATE TABLE [dbo].[Order] (
[MaOrder] smallint identity  NOT NULL  
, [MaTaiKhoan] smallint  NULL  
, [MaBan] smallint  NULL  
)
GO

ALTER TABLE [dbo].[Order] ADD CONSTRAINT [Order_PK] PRIMARY KEY CLUSTERED (
[MaOrder]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[PhuThuKhuVuc]') AND type in (N'U'))
    DROP TABLE [dbo].[PhuThuKhuVuc]
GO
CREATE TABLE [dbo].[PhuThuKhuVuc] (
[MaPhuThu] smallint identity  NOT NULL  
, [MaKhuVuc] smallint  NULL  
)
GO

ALTER TABLE [dbo].[PhuThuKhuVuc] ADD CONSTRAINT [PhuThuKhuVuc_PK] PRIMARY KEY CLUSTERED (
[MaPhuThu]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[BoPhanCheBien]') AND type in (N'U'))
    DROP TABLE [dbo].[BoPhanCheBien]
GO
CREATE TABLE [dbo].[BoPhanCheBien] (
[MaBoPhanCheBien] smallint identity  NOT NULL  
, [TenBoPhan] char(50)  NULL  
, [MoTa] char(500)  NULL  
, [MaTaiKhoan] smallint  NULL  
)
GO

ALTER TABLE [dbo].[BoPhanCheBien] ADD CONSTRAINT [BoPhanCheBien_PK] PRIMARY KEY CLUSTERED (
[MaBoPhanCheBien]
)
GO
GO

ALTER TABLE [dbo].[ChiTietDanhMucDaNgonNgu] WITH CHECK ADD CONSTRAINT [DanhMuc_ChiTietDanhMucDaNgonNgu_FK1] FOREIGN KEY (
[MaDanhMuc]
)
REFERENCES [dbo].[DanhMuc] (
[MaDanhMuc]
)
ALTER TABLE [dbo].[ChiTietDanhMucDaNgonNgu] WITH CHECK ADD CONSTRAINT [NgonNgu_ChiTietDanhMucDaNgonNgu_FK1] FOREIGN KEY (
[MaNgonNgu]
)
REFERENCES [dbo].[NgonNgu] (
[MaNgonNgu]
)
GO

ALTER TABLE [dbo].[ChiTietDonViTinhDaNgonNgu] WITH CHECK ADD CONSTRAINT [DonViTinh_ChiTietDonViTinhDaNgonNgu_FK1] FOREIGN KEY (
[MaDonViTinh]
)
REFERENCES [dbo].[DonViTinh] (
[MaDonViTinh]
)
ALTER TABLE [dbo].[ChiTietDonViTinhDaNgonNgu] WITH CHECK ADD CONSTRAINT [NgonNgu_ChiTietDonViTinhDaNgonNgu_FK1] FOREIGN KEY (
[MaNgonNgu]
)
REFERENCES [dbo].[NgonNgu] (
[MaNgonNgu]
)
GO

ALTER TABLE [dbo].[MonAn] WITH CHECK ADD CONSTRAINT [DonViTinh_MonAn_FK1] FOREIGN KEY (
[MaDonViTinh]
)
REFERENCES [dbo].[DonViTinh] (
[MaDonViTinh]
)
ALTER TABLE [dbo].[MonAn] WITH CHECK ADD CONSTRAINT [DanhMuc_MonAn_FK1] FOREIGN KEY (
[MaDanhMuc]
)
REFERENCES [dbo].[DanhMuc] (
[MaDanhMuc]
)
GO

ALTER TABLE [dbo].[ChiTietOrder] WITH CHECK ADD CONSTRAINT [BoPhanCheBien_ChiTietOrder_FK1] FOREIGN KEY (
[MaBoPhanCheBien]
)
REFERENCES [dbo].[BoPhanCheBien] (
[MaBoPhanCheBien]
)
ALTER TABLE [dbo].[ChiTietOrder] WITH CHECK ADD CONSTRAINT [Order_ChiTietOrder_FK1] FOREIGN KEY (
[MaOrder]
)
REFERENCES [dbo].[Order] (
[MaOrder]
)
ALTER TABLE [dbo].[ChiTietOrder] WITH CHECK ADD CONSTRAINT [MonAn_ChiTietOrder_FK1] FOREIGN KEY (
[MaMonAn]
)
REFERENCES [dbo].[MonAn] (
[MaMonAn]
)
GO

GO

ALTER TABLE [dbo].[DanhMuc] WITH CHECK ADD CONSTRAINT [DanhMuc_DanhMuc_FK1] FOREIGN KEY (
[MaDanhMucCha]
)
REFERENCES [dbo].[DanhMuc] (
[MaDanhMuc]
)
GO

ALTER TABLE [dbo].[ChiTietHoaDon] WITH CHECK ADD CONSTRAINT [HoaDon_ChiTietHoaDon_FK1] FOREIGN KEY (
[MaHoaDon]
)
REFERENCES [dbo].[HoaDon] (
[MaHoaDon]
)
ALTER TABLE [dbo].[ChiTietHoaDon] WITH CHECK ADD CONSTRAINT [MonAn_ChiTietHoaDon_FK1] FOREIGN KEY (
[MaMonAn]
)
REFERENCES [dbo].[MonAn] (
[MaMonAn]
)
ALTER TABLE [dbo].[ChiTietHoaDon] WITH CHECK ADD CONSTRAINT [KhuyenMai_ChiTietHoaDon_FK1] FOREIGN KEY (
[MaKhuyenMai]
)
REFERENCES [dbo].[KhuyenMai] (
[MaKhuyenMai]
)
GO

ALTER TABLE [dbo].[ChiTietHuyOrder] WITH CHECK ADD CONSTRAINT [ChiTietOrder_ChiTietHuyOrder_FK1] FOREIGN KEY (
[MaChiTietOrder]
)
REFERENCES [dbo].[ChiTietOrder] (
[MaChiTietOrder]
)
GO

GO

GO

GO

GO

ALTER TABLE [dbo].[ChiTietMonAnDonViTinh] WITH CHECK ADD CONSTRAINT [DonViTinh_ChiTietMonAnDonViTinh_FK1] FOREIGN KEY (
[MaDonViTinh]
)
REFERENCES [dbo].[DonViTinh] (
[MaDonViTinh]
)
ALTER TABLE [dbo].[ChiTietMonAnDonViTinh] WITH CHECK ADD CONSTRAINT [MonAn_ChiTietMonAnDonViTinh_FK1] FOREIGN KEY (
[MaMonAn]
)
REFERENCES [dbo].[MonAn] (
[MaMonAn]
)
GO

ALTER TABLE [dbo].[KhuyenMaiHoaDon] WITH CHECK ADD CONSTRAINT [KhuyenMai_KhuyenMaiHoaDon_FK1] FOREIGN KEY (
[MaKhuyenMai]
)
REFERENCES [dbo].[KhuyenMai] (
[MaKhuyenMai]
)
GO

ALTER TABLE [dbo].[ChiTietDanhMucBoPhanCheBien] WITH CHECK ADD CONSTRAINT [DanhMuc_ChiTietDanhMucBoPhanCheBien_FK1] FOREIGN KEY (
[MaDanhMuc]
)
REFERENCES [dbo].[DanhMuc] (
[MaDanhMuc]
)
ALTER TABLE [dbo].[ChiTietDanhMucBoPhanCheBien] WITH CHECK ADD CONSTRAINT [BoPhanCheBien_ChiTietDanhMucBoPhanCheBien_FK1] FOREIGN KEY (
[MaBoPhanCheBien]
)
REFERENCES [dbo].[BoPhanCheBien] (
[MaBoPhanCheBien]
)
GO

GO

ALTER TABLE [dbo].[KhuyenMaiDanhMuc] WITH CHECK ADD CONSTRAINT [DanhMuc_KhuyenMaiDanhMuc_FK1] FOREIGN KEY (
[MaDanhMuc]
)
REFERENCES [dbo].[DanhMuc] (
[MaDanhMuc]
)
ALTER TABLE [dbo].[KhuyenMaiDanhMuc] WITH CHECK ADD CONSTRAINT [KhuyenMai_KhuyenMaiDanhMuc_FK1] FOREIGN KEY (
[MaKhuyenMai]
)
REFERENCES [dbo].[KhuyenMai] (
[MaKhuyenMai]
)
GO

ALTER TABLE [dbo].[TaiKhoan] WITH CHECK ADD CONSTRAINT [NhomTaiKhoan_TaiKhoan_FK1] FOREIGN KEY (
[MaNhomTaiKhoan]
)
REFERENCES [dbo].[NhomTaiKhoan] (
[MaNhomTaiKhoan]
)
GO

ALTER TABLE [dbo].[KhuyenMaiKhuVuc] WITH CHECK ADD CONSTRAINT [KhuyenMai_KhuyenMaiKhuVuc_FK1] FOREIGN KEY (
[MaKhuyenMai]
)
REFERENCES [dbo].[KhuyenMai] (
[MaKhuyenMai]
)
ALTER TABLE [dbo].[KhuyenMaiKhuVuc] WITH CHECK ADD CONSTRAINT [KhuVuc_KhuyenMaiKhuVuc_FK1] FOREIGN KEY (
[MaKhuVuc]
)
REFERENCES [dbo].[KhuVuc] (
[MaKhuVuc]
)
GO

ALTER TABLE [dbo].[Ban] WITH CHECK ADD CONSTRAINT [KhuVuc_Ban_FK1] FOREIGN KEY (
[MaKhuVuc]
)
REFERENCES [dbo].[KhuVuc] (
[MaKhuVuc]
)
ALTER TABLE [dbo].[Ban] WITH CHECK ADD CONSTRAINT [Ban_Ban_FK1] FOREIGN KEY (
[MaBanChinh]
)
REFERENCES [dbo].[Ban] (
[MaBan]
)
GO

GO

ALTER TABLE [dbo].[KhuyenMaiMon] WITH CHECK ADD CONSTRAINT [MonAn_KhuyenMaiMon_FK1] FOREIGN KEY (
[MaMonAn]
)
REFERENCES [dbo].[MonAn] (
[MaMonAn]
)
ALTER TABLE [dbo].[KhuyenMaiMon] WITH CHECK ADD CONSTRAINT [KhuyenMai_KhuyenMaiMon_FK1] FOREIGN KEY (
[MaKhuyenMai]
)
REFERENCES [dbo].[KhuyenMai] (
[MaKhuyenMai]
)
GO

GO

ALTER TABLE [dbo].[ChiTietMonAnDaNgonNgu] WITH CHECK ADD CONSTRAINT [NgonNgu_ChiTietMonAnDaNgonNgu_FK1] FOREIGN KEY (
[MaNgonNgu]
)
REFERENCES [dbo].[NgonNgu] (
[MaNgonNgu]
)
ALTER TABLE [dbo].[ChiTietMonAnDaNgonNgu] WITH CHECK ADD CONSTRAINT [MonAn_ChiTietMonAnDaNgonNgu_FK1] FOREIGN KEY (
[MaMonAn]
)
REFERENCES [dbo].[MonAn] (
[MaMonAn]
)
GO

ALTER TABLE [dbo].[HoaDon] WITH CHECK ADD CONSTRAINT [PhuThu_HoaDon_FK1] FOREIGN KEY (
[MaPhuThu]
)
REFERENCES [dbo].[PhuThu] (
[MaPhuThu]
)
ALTER TABLE [dbo].[HoaDon] WITH CHECK ADD CONSTRAINT [Ban_HoaDon_FK1] FOREIGN KEY (
[MaBanChinh]
)
REFERENCES [dbo].[Ban] (
[MaBan]
)
ALTER TABLE [dbo].[HoaDon] WITH CHECK ADD CONSTRAINT [TaiKhoan_HoaDon_FK1] FOREIGN KEY (
[MaTaiKhoan]
)
REFERENCES [dbo].[TaiKhoan] (
[MaTaiKhoan]
)
GO

ALTER TABLE [dbo].[Order] WITH CHECK ADD CONSTRAINT [TaiKhoan_Order_FK1] FOREIGN KEY (
[MaTaiKhoan]
)
REFERENCES [dbo].[TaiKhoan] (
[MaTaiKhoan]
)
ALTER TABLE [dbo].[Order] WITH CHECK ADD CONSTRAINT [Ban_Order_FK1] FOREIGN KEY (
[MaBan]
)
REFERENCES [dbo].[Ban] (
[MaBan]
)
GO

ALTER TABLE [dbo].[PhuThuKhuVuc] WITH CHECK ADD CONSTRAINT [KhuVuc_PhuThuKhuVuc_FK1] FOREIGN KEY (
[MaKhuVuc]
)
REFERENCES [dbo].[KhuVuc] (
[MaKhuVuc]
)
ALTER TABLE [dbo].[PhuThuKhuVuc] WITH CHECK ADD CONSTRAINT [PhuThu_PhuThuKhuVuc_FK1] FOREIGN KEY (
[MaPhuThu]
)
REFERENCES [dbo].[PhuThu] (
[MaPhuThu]
)
GO

ALTER TABLE [dbo].[BoPhanCheBien] WITH CHECK ADD CONSTRAINT [TaiKhoan_BoPhanCheBien_FK1] FOREIGN KEY (
[MaTaiKhoan]
)
REFERENCES [dbo].[TaiKhoan] (
[MaTaiKhoan]
)
GO

