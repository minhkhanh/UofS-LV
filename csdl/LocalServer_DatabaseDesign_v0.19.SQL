USE master
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
[MaDanhMuc] int  NOT NULL  
, [MaNgonNgu] int  NOT NULL  
, [TenDanhMuc] nvarchar(50)  NULL  
, [MoTaDanhMuc] nvarchar(500)  NULL  
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
[MaDonViTinh] int  NOT NULL  
, [MaNgonNgu] int  NOT NULL  
, [TenDonViTinh] nvarchar(50)  NULL  
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
[MaMonAn] int identity  NOT NULL  
, [HinhAnh] nvarchar(512)  NULL  
, [DiemDanhGia] real  NULL  
, [SoLuotDanhGia] int  NULL  
, [MaDonViTinhMacDinh] int  NULL  
, [MaDanhMuc] int  NULL  
, [NgungBan] bit  NULL
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
[MaChiTietOrder] int identity  NOT NULL  
, [MaOrder] int  NULL  
, [SoLuong] int  NULL  
, [MaMonAn] int  NULL
, [MaDonViTinh] int  NULL   
, [GhiChu] nvarchar(200)  NULL  
, [MaBoPhanCheBien] int  NULL 
, [TinhTrang] int NOT NULL DEFAULT 0
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
[MaTiGia] int identity  NOT NULL  
, [KiHieu] nvarchar(50)  NULL  
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
[MaDanhMuc] int identity  NOT NULL  
, [MaDanhMucCha] int  NULL  
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
[MaChiTietHoaDon] int identity  NOT NULL  
, [MaHoaDon] int  NULL  
, [SoLuong] int  NULL  
, [DonGiaLuuTru] real  NULL  
, [MaMonAn] int  NULL  
, [MaDonViTinh] int  NULL  
, [ThanhTien] real  NULL  
, [GiaTriKhuyenMaiLuuTru] real  NULL  
, [MaKhuyenMai] int  NULL  
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
[MaChiTietOrder] int NOT NULL  
, [SoLuongYeuCau] int  NULL  
, [SoLuongChoPhep] int  NULL  
, [TinhTrangHuy] bit  NULL  
)
GO

ALTER TABLE [dbo].[ChiTietHuyOrder] ADD CONSTRAINT [ChiTietHuyOrder_PK] PRIMARY KEY CLUSTERED (
[MaChiTietOrder]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ChiTietCheBienOrder]') AND type in (N'U'))
    DROP TABLE [dbo].[ChiTietCheBienOrder]
GO
CREATE TABLE [dbo].[ChiTietCheBienOrder] (
[MaChiTietOrder] int NOT NULL  
, [SoLuongDaCheBien] int  NULL  
, [SoLuongDangCheBien] int  NULL  
)
GO

ALTER TABLE [dbo].[ChiTietCheBienOrder] ADD CONSTRAINT [ChiTietCheBienOrder_PK] PRIMARY KEY CLUSTERED (
[MaChiTietOrder]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ChiTietKhongCheBienOrder]') AND type in (N'U'))
    DROP TABLE [dbo].[ChiTietKhongCheBienOrder]
GO
CREATE TABLE [dbo].[ChiTietKhongCheBienOrder] (
[MaChiTietKhongCheBienOrder] int identity  NOT NULL  
, [MaChiTietOrder] int NOT NULL  
, [SoLuongKhongCheBien] int  NOT NULL  
, [TinhTrang] int  NOT NULL DEFAULT 0
)
GO

ALTER TABLE [dbo].[ChiTietKhongCheBienOrder] 
ADD CONSTRAINT [ChiTietKhongCheBienOrder_PK] PRIMARY KEY CLUSTERED (
[MaChiTietKhongCheBienOrder]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ChiTietMonLienQuan]') AND type in (N'U'))
    DROP TABLE [dbo].[ChiTietMonLienQuan]
GO
CREATE TABLE [dbo].[ChiTietMonLienQuan] (
[MaMonAn] int  NOT NULL  
, [MaMonAnLienQuan] int  NOT NULL  
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
[MaKhuyenMai] int identity  NOT NULL  
, [TenKhuyenMai] nvarchar(50)  NULL  
, [MoTaKhuyenMai] nvarchar(500)  NULL  
, [GiaGiam] real  NULL  
, [TiLeGiam] real  NULL  
, [BatDau] smalldatetime  NULL  
, [KetThuc] smalldatetime  NULL  
, [LichKhuyenMai] nvarchar(500)  NULL  
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
[MaPhuThu] int identity  NOT NULL  
, [TenPhuThu] nvarchar(50)  NULL  
, [MoTaPhuThu] nvarchar(500)  NULL  
, [GiaTang] real  NULL  
, [TiLeTang] real  NULL  
, [BatDau] smalldatetime  NULL  
, [KetThuc] smalldatetime  NULL  
, [LichPhuThu] nvarchar(500)  NULL  
)
GO

ALTER TABLE [dbo].[PhuThu] ADD CONSTRAINT [PhuThu_PK] PRIMARY KEY CLUSTERED (
[MaPhuThu]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Voucher]') AND type in (N'U'))
    DROP TABLE [dbo].[Voucher]
GO
CREATE TABLE [dbo].[Voucher] (
[MaVoucher] int identity  NOT NULL  
, [TenVoucher] nvarchar(50)  NULL  
, [MoTaVoucher] nvarchar(500)  NULL  
, [MucGiaApDung] real  NULL
, [GiaGiam] real  NULL  
, [BatDau] smalldatetime  NULL  
, [KetThuc] smalldatetime  NULL  
)
GO

ALTER TABLE [dbo].[Voucher] ADD CONSTRAINT [VoucherPK] PRIMARY KEY CLUSTERED (
[MaVoucher]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ChiTietVoucher]') AND type in (N'U'))
    DROP TABLE [dbo].[ChiTietVoucher]
GO
CREATE TABLE [dbo].[ChiTietVoucher] (
[MaChiTietVoucher] int identity  NOT NULL  
, [MaVoucher] int  NULL    
, [SoPhieu] nvarchar(200)  NULL  
, [Active] bit  NULL 
)
GO

ALTER TABLE [dbo].[ChiTietVoucher] ADD CONSTRAINT [ChiTietVoucher_PK] PRIMARY KEY CLUSTERED (
[MaChiTietVoucher]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[DonViTinh]') AND type in (N'U'))
    DROP TABLE [dbo].[DonViTinh]
GO
CREATE TABLE [dbo].[DonViTinh] (
[MaDonViTinh] int identity  NOT NULL  
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
[MaMonAn] int  NOT NULL  
, [MaDonViTinh] int  NOT NULL  
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
[MaKhuyenMai] int  NOT NULL  
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
[MaDanhMuc] int  NOT NULL  
, [MaBoPhanCheBien] int  NOT NULL  
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
[MaKhuVuc] int identity  NOT NULL  
, [TenKhuVuc] nvarchar(50)  NULL  
, [MoTa] nvarchar(500)  NULL  
)
GO

ALTER TABLE [dbo].[KhuVuc] ADD CONSTRAINT [KhuVuc_PK] PRIMARY KEY CLUSTERED (
[MaKhuVuc]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ThamSo]') AND type in (N'U'))
    DROP TABLE [dbo].[ThamSo]
GO
CREATE TABLE [dbo].[ThamSo] (
[Ten] nvarchar(50) NOT NULL  
, [GiaTri] nvarchar(1000)  NOT NULL  
)
GO

ALTER TABLE [dbo].[ThamSo] ADD CONSTRAINT [ThamSo_PK] PRIMARY KEY CLUSTERED (
[Ten]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[KhuyenMaiDanhMuc]') AND type in (N'U'))
    DROP TABLE [dbo].[KhuyenMaiDanhMuc]
GO
CREATE TABLE [dbo].[KhuyenMaiDanhMuc] (
[MaKhuyenMai] int  NOT NULL  
, [MaDanhMuc] int  NOT NULL  
)
GO

ALTER TABLE [dbo].[KhuyenMaiDanhMuc] ADD CONSTRAINT [KhuyenMaiDanhMuc_PK] PRIMARY KEY CLUSTERED (
[MaKhuyenMai],
[MaDanhMuc]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[TaiKhoan]') AND type in (N'U'))
    DROP TABLE [dbo].[TaiKhoan]
GO
CREATE TABLE [dbo].[TaiKhoan] (
[MaTaiKhoan] int identity  NOT NULL  
, [TenTaiKhoan] nvarchar(50)  NULL  
, [MatKhau] nvarchar(100)  NULL  
, [HoTen] nvarchar(50)  NULL  
, [NgaySinh] date  NULL  
, [GioiTinh] int  NULL  
, [CMND] nvarchar(50)  NULL  
, [Avatar] nvarchar(50)  NULL  
, [Active] bit  NULL  
, [MaNhomTaiKhoan] int  NULL  
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
[MaKhuyenMai] int  NOT NULL  
, [MaKhuVuc] int  NOT NULL  
)
GO

ALTER TABLE [dbo].[KhuyenMaiKhuVuc] ADD CONSTRAINT [KhuyenMaiKhuVuc_PK] PRIMARY KEY CLUSTERED (
[MaKhuyenMai],
[MaKhuVuc]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Ban]') AND type in (N'U'))
    DROP TABLE [dbo].[Ban]
GO
CREATE TABLE [dbo].[Ban] (
[MaBan] int identity  NOT NULL  
, [MaKhuVuc] int  NULL  
, [TenBan] nvarchar(50)  NULL  
, [GhiChu] nvarchar(500)  NULL  
, [Active] bit  NULL  
, [TinhTrang] bit  NULL  
, [MaBanChinh] int  NULL  
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
[MaNgonNgu] int identity  NOT NULL  
, [TenNgonNgu] nvarchar(50)  NULL 
, [KiHieu] nvarchar(10) NULL 
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
[MaKhuyenMai] int  NOT NULL  
, [MaMonAn] int  NOT NULL  
)
GO

ALTER TABLE [dbo].[KhuyenMaiMon] ADD CONSTRAINT [KhuyenMaiMon_PK] PRIMARY KEY CLUSTERED (
[MaKhuyenMai],
[MaMonAn]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[NhomTaiKhoan]') AND type in (N'U'))
    DROP TABLE [dbo].[NhomTaiKhoan]
GO
CREATE TABLE [dbo].[NhomTaiKhoan] (
[MaNhomTaiKhoan] int identity  NOT NULL  
, [TenNhom] nvarchar(50)  NULL  
, [MoTaNhom] nvarchar(50)  NULL  
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
[MaMonAn] int  NOT NULL  
, [MaNgonNgu] int  NOT NULL  
, [TenMonAn] nvarchar(50)  NULL  
, [MoTaMonAn] nvarchar(MAX)  NULL  
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
[MaHoaDon] int identity  NOT NULL  
, [ThoiDiemLap] smalldatetime  NULL  
, [MaTaiKhoan] int  NULL  
, [TongTien] real  NULL  
, [MaBanChinh] int  NULL  
, [MoTaBanGhep] nvarchar(500)  NULL  
, [MaPhuThu] int  NULL  
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
[MaOrder] int identity  NOT NULL  
, [MaTaiKhoan] int  NULL  
, [MaBan] int  NULL  
, [TinhTrang] int NOT NULL DEFAULT 0
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
[MaPhuThu] int NOT NULL  
, [MaKhuVuc] int NOT NULL  
)
GO

ALTER TABLE [dbo].[PhuThuKhuVuc] ADD CONSTRAINT [PhuThuKhuVuc_PK] PRIMARY KEY CLUSTERED (
[MaPhuThu],
[MaKhuVuc]
)
GO
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[BoPhanCheBien]') AND type in (N'U'))
    DROP TABLE [dbo].[BoPhanCheBien]
GO
CREATE TABLE [dbo].[BoPhanCheBien] (
[MaBoPhanCheBien] int identity  NOT NULL  
, [TenBoPhan] nvarchar(50)  NULL  
, [MoTa] nvarchar(500)  NULL  
, [MaTaiKhoan] int  NULL  
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
[MaDonViTinhMacDinh]
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
ALTER TABLE [dbo].[ChiTietOrder] WITH CHECK ADD CONSTRAINT [DonViTinh_ChiTietOrder_FK1] FOREIGN KEY (
[MaDonViTinh]
)
REFERENCES [dbo].[DonViTinh] (
[MaDonViTinh]
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
ALTER TABLE [dbo].[ChiTietHoaDon] WITH CHECK ADD CONSTRAINT [DonViTinh_ChiTietHoaDon_FK1] FOREIGN KEY (
[MaDonViTinh]
)
REFERENCES [dbo].[DonViTinh] (
[MaDonViTinh]
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

ALTER TABLE [dbo].[ChiTietCheBienOrder] WITH CHECK ADD CONSTRAINT [ChiTietOrder_ChiTietCheBienOrder_FK1] FOREIGN KEY (
[MaChiTietOrder]
)
REFERENCES [dbo].[ChiTietOrder] (
[MaChiTietOrder]
)
GO

ALTER TABLE [dbo].[ChiTietKhongCheBienOrder] WITH CHECK ADD CONSTRAINT [ChiTietOrder_ChiTietKhongCheBienOrder_FK1] FOREIGN KEY (
[MaChiTietOrder]
)
REFERENCES [dbo].[ChiTietOrder] (
[MaChiTietOrder]
)
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

ALTER TABLE [dbo].[ChiTietVoucher] WITH CHECK ADD CONSTRAINT [Voucher_ChiTietVoucher_FK1] FOREIGN KEY (
[MaVoucher]
)
REFERENCES [dbo].[Voucher] (
[MaVoucher]
)
