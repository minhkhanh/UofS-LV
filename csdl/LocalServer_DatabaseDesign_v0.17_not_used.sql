USE master
GO
IF EXISTS(SELECT * FROM SYS.DATABASES WHERE NAME='ThucDonDienTu')
	DROP DATABASE ThucDonDienTu
GO
CREATE DATABASE ThucDonDienTu
GO
USE ThucDonDienTu
GO
/****** Object:  Table [dbo].[ChiTietMonLienQuan]    Script Date: 05/16/2012 01:46:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietMonLienQuan](
	[MaMonAn] [int] NOT NULL,
	[MaMonAnLienQuan] [int] NOT NULL,
 CONSTRAINT [ChiTietMonLienQuan_PK] PRIMARY KEY CLUSTERED 
(
	[MaMonAn] ASC,
	[MaMonAnLienQuan] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhuThu]    Script Date: 05/16/2012 01:46:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhuThu](
	[MaPhuThu] [int] IDENTITY(1,1) NOT NULL,
	[TenPhuThu] [nvarchar](50) NULL,
	[MoTaPhuThu] [nvarchar](500) NULL,
	[GiaTang] [real] NULL,
	[TiLeTang] [real] NULL,
	[BatDau] [smalldatetime] NULL,
	[KetThuc] [smalldatetime] NULL,
	[LichPhuThu] [nvarchar](500) NULL,
 CONSTRAINT [PhuThu_PK] PRIMARY KEY CLUSTERED 
(
	[MaPhuThu] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TiGia]    Script Date: 05/16/2012 01:46:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TiGia](
	[MaTiGia] [int] IDENTITY(1,1) NOT NULL,
	[KiHieu] [nvarchar](50) NULL,
	[GiaTri] [real] NULL,
	[ThoiDiemCapNhat] [smalldatetime] NULL,
 CONSTRAINT [TiGia_PK] PRIMARY KEY CLUSTERED 
(
	[MaTiGia] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ThamSo]    Script Date: 05/16/2012 01:46:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ThamSo](
	[Ten] [nvarchar](50) NOT NULL,
	[GiaTri] [nvarchar](1000) NOT NULL,
 CONSTRAINT [ThamSo_PK] PRIMARY KEY CLUSTERED 
(
	[Ten] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhuyenMai]    Script Date: 05/16/2012 01:46:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhuyenMai](
	[MaKhuyenMai] [int] IDENTITY(1,1) NOT NULL,
	[TenKhuyenMai] [nvarchar](50) NULL,
	[MoTaKhuyenMai] [nvarchar](500) NULL,
	[GiaGiam] [real] NULL,
	[TiLeGiam] [real] NULL,
	[BatDau] [smalldatetime] NULL,
	[KetThuc] [smalldatetime] NULL,
	[LichKhuyenMai] [nvarchar](500) NULL,
 CONSTRAINT [KhuyenMai_PK] PRIMARY KEY CLUSTERED 
(
	[MaKhuyenMai] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhuVuc]    Script Date: 05/16/2012 01:46:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhuVuc](
	[MaKhuVuc] [int] IDENTITY(1,1) NOT NULL,
	[TenKhuVuc] [nvarchar](50) NULL,
	[MoTa] [nvarchar](500) NULL,
 CONSTRAINT [KhuVuc_PK] PRIMARY KEY CLUSTERED 
(
	[MaKhuVuc] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhomTaiKhoan]    Script Date: 05/16/2012 01:46:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhomTaiKhoan](
	[MaNhomTaiKhoan] [int] IDENTITY(1,1) NOT NULL,
	[TenNhom] [nvarchar](50) NULL,
	[MoTaNhom] [nvarchar](50) NULL,
 CONSTRAINT [NhomTaiKhoan_PK] PRIMARY KEY CLUSTERED 
(
	[MaNhomTaiKhoan] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NgonNgu]    Script Date: 05/16/2012 01:46:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NgonNgu](
	[MaNgonNgu] [int] IDENTITY(1,1) NOT NULL,
	[TenNgonNgu] [nvarchar](50) NULL,
	[KiHieu] [nvarchar](10) NULL,
 CONSTRAINT [NgonNgu_PK] PRIMARY KEY CLUSTERED 
(
	[MaNgonNgu] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DonViTinh]    Script Date: 05/16/2012 01:46:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DonViTinh](
	[MaDonViTinh] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [DonViTinh_PK] PRIMARY KEY CLUSTERED 
(
	[MaDonViTinh] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DanhMuc]    Script Date: 05/16/2012 01:46:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DanhMuc](
	[MaDanhMuc] [int] IDENTITY(1,1) NOT NULL,
	[MaDanhMucCha] [int] NULL,
 CONSTRAINT [DanhMuc_PK] PRIMARY KEY CLUSTERED 
(
	[MaDanhMuc] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChiTietDonViTinhDaNgonNgu]    Script Date: 05/16/2012 01:46:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietDonViTinhDaNgonNgu](
	[MaDonViTinh] [int] NOT NULL,
	[MaNgonNgu] [int] NOT NULL,
	[TenDonViTinh] [nvarchar](50) NULL,
 CONSTRAINT [ChiTietDonViTinhDaNgonNgu_PK] PRIMARY KEY CLUSTERED 
(
	[MaDonViTinh] ASC,
	[MaNgonNgu] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChiTietDanhMucDaNgonNgu]    Script Date: 05/16/2012 01:46:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietDanhMucDaNgonNgu](
	[MaDanhMuc] [int] NOT NULL,
	[MaNgonNgu] [int] NOT NULL,
	[TenDanhMuc] [nvarchar](50) NULL,
	[MoTaDanhMuc] [nvarchar](500) NULL,
 CONSTRAINT [ChiTietDanhMucDaNgonNgu_PK] PRIMARY KEY CLUSTERED 
(
	[MaDanhMuc] ASC,
	[MaNgonNgu] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Ban]    Script Date: 05/16/2012 01:46:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Ban](
	[MaBan] [int] IDENTITY(1,1) NOT NULL,
	[MaKhuVuc] [int] NULL,
	[TenBan] [nvarchar](50) NULL,
	[GhiChu] [nvarchar](500) NULL,
	[Active] [bit] NULL,
	[TinhTrang] [bit] NULL,
	[MaBanChinh] [int] NULL,
 CONSTRAINT [Ban_PK] PRIMARY KEY CLUSTERED 
(
	[MaBan] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MonAn]    Script Date: 05/16/2012 01:46:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MonAn](
	[MaMonAn] [int] IDENTITY(1,1) NOT NULL,
	[HinhAnh] [nvarchar](512) NULL,
	[DiemDanhGia] [real] NULL,
	[SoLuotDanhGia] [int] NULL,
	[MaDonViTinhMacDinh] [int] NULL,
	[MaDanhMuc] [int] NULL,
	[NgungBan] [bit] NULL,
 CONSTRAINT [MonAn_PK] PRIMARY KEY CLUSTERED 
(
	[MaMonAn] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 05/16/2012 01:46:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[MaTaiKhoan] [int] IDENTITY(1,1) NOT NULL,
	[TenTaiKhoan] [nvarchar](50) NULL,
	[MatKhau] [nvarchar](50) NULL,
	[HoTen] [nvarchar](50) NULL,
	[NgaySinh] [date] NULL,
	[GioiTinh] [int] NULL,
	[CMND] [nvarchar](50) NULL,
	[Avatar] [nvarchar](50) NULL,
	[Active] [bit] NULL,
	[MaNhomTaiKhoan] [int] NULL,
 CONSTRAINT [TaiKhoan_PK] PRIMARY KEY CLUSTERED 
(
	[MaTaiKhoan] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhuThuKhuVuc]    Script Date: 05/16/2012 01:46:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhuThuKhuVuc](
	[MaPhuThu] [int] NOT NULL,
	[MaKhuVuc] [int] NOT NULL,
 CONSTRAINT [PhuThuKhuVuc_PK] PRIMARY KEY CLUSTERED 
(
	[MaPhuThu] ASC,
	[MaKhuVuc] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhuyenMaiKhuVuc]    Script Date: 05/16/2012 01:46:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhuyenMaiKhuVuc](
	[MaKhuyenMai] [int] NOT NULL,
	[MaKhuVuc] [int] NOT NULL,
 CONSTRAINT [KhuyenMaiKhuVuc_PK] PRIMARY KEY CLUSTERED 
(
	[MaKhuyenMai] ASC,
	[MaKhuVuc] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhuyenMaiHoaDon]    Script Date: 05/16/2012 01:46:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhuyenMaiHoaDon](
	[MaKhuyenMai] [int] NOT NULL,
	[MucGiaApDung] [real] NULL,
 CONSTRAINT [KhuyenMaiHoaDon_PK] PRIMARY KEY CLUSTERED 
(
	[MaKhuyenMai] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhuyenMaiDanhMuc]    Script Date: 05/16/2012 01:46:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhuyenMaiDanhMuc](
	[MaKhuyenMai] [int] NOT NULL,
	[MaDanhMuc] [int] NOT NULL,
 CONSTRAINT [KhuyenMaiDanhMuc_PK] PRIMARY KEY CLUSTERED 
(
	[MaKhuyenMai] ASC,
	[MaDanhMuc] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order]    Script Date: 05/16/2012 01:46:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order](
	[MaOrder] [int] IDENTITY(1,1) NOT NULL,
	[MaTaiKhoan] [int] NULL,
	[MaBan] [int] NULL,
	[TinhTrang] [int] NOT NULL,
 CONSTRAINT [Order_PK] PRIMARY KEY CLUSTERED 
(
	[MaOrder] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChiTietMonAnDonViTinh]    Script Date: 05/16/2012 01:46:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietMonAnDonViTinh](
	[MaMonAn] [int] NOT NULL,
	[MaDonViTinh] [int] NOT NULL,
	[DonGia] [real] NULL,
 CONSTRAINT [ChiTietMonAnDonViTinh_PK] PRIMARY KEY CLUSTERED 
(
	[MaDonViTinh] ASC,
	[MaMonAn] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChiTietMonAnDaNgonNgu]    Script Date: 05/16/2012 01:46:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietMonAnDaNgonNgu](
	[MaMonAn] [int] NOT NULL,
	[MaNgonNgu] [int] NOT NULL,
	[TenMonAn] [nvarchar](50) NULL,
	[MoTaMonAn] [nvarchar](max) NULL,
 CONSTRAINT [ChiTietMonAnDaNgonNgu_PK] PRIMARY KEY CLUSTERED 
(
	[MaMonAn] ASC,
	[MaNgonNgu] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhuyenMaiMon]    Script Date: 05/16/2012 01:46:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhuyenMaiMon](
	[MaKhuyenMai] [int] NOT NULL,
	[MaMonAn] [int] NOT NULL,
 CONSTRAINT [KhuyenMaiMon_PK] PRIMARY KEY CLUSTERED 
(
	[MaKhuyenMai] ASC,
	[MaMonAn] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 05/16/2012 01:46:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[MaHoaDon] [int] IDENTITY(1,1) NOT NULL,
	[ThoiDiemLap] [smalldatetime] NULL,
	[MaTaiKhoan] [int] NULL,
	[TongTien] [real] NULL,
	[MaBanChinh] [int] NULL,
	[MoTaBanGhep] [nvarchar](500) NULL,
	[MaPhuThu] [int] NULL,
 CONSTRAINT [HoaDon_PK] PRIMARY KEY CLUSTERED 
(
	[MaHoaDon] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BoPhanCheBien]    Script Date: 05/16/2012 01:46:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BoPhanCheBien](
	[MaBoPhanCheBien] [int] IDENTITY(1,1) NOT NULL,
	[TenBoPhan] [nvarchar](50) NULL,
	[MoTa] [nvarchar](500) NULL,
	[MaTaiKhoan] [int] NULL,
 CONSTRAINT [BoPhanCheBien_PK] PRIMARY KEY CLUSTERED 
(
	[MaBoPhanCheBien] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChiTietOrder]    Script Date: 05/16/2012 01:46:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietOrder](
	[MaChiTietOrder] [int] IDENTITY(1,1) NOT NULL,
	[MaOrder] [int] NULL,
	[SoLuong] [int] NULL,
	[MaMonAn] [int] NULL,
	[MaDonViTinh] [int] NULL,
	[GhiChu] [nvarchar](200) NULL,
	[MaBoPhanCheBien] [int] NULL,
	[TinhTrang] [int] NOT NULL,
 CONSTRAINT [ChiTietOrder_PK] PRIMARY KEY CLUSTERED 
(
	[MaChiTietOrder] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChiTietDanhMucBoPhanCheBien]    Script Date: 05/16/2012 01:46:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietDanhMucBoPhanCheBien](
	[MaDanhMuc] [int] NOT NULL,
	[MaBoPhanCheBien] [int] NOT NULL,
 CONSTRAINT [ChiTietDanhMucBoPhanCheBien_PK] PRIMARY KEY CLUSTERED 
(
	[MaDanhMuc] ASC,
	[MaBoPhanCheBien] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChiTietHoaDon]    Script Date: 05/16/2012 01:46:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietHoaDon](
	[MaChiTietHoaDon] [int] IDENTITY(1,1) NOT NULL,
	[MaHoaDon] [int] NULL,
	[SoLuong] [int] NULL,
	[DonGiaLuuTru] [real] NULL,
	[MaMonAn] [int] NULL,
	[MaDonViTinh] [int] NULL,
	[ThanhTien] [real] NULL,
	[GiaTriKhuyenMaiLuuTru] [real] NULL,
	[MaKhuyenMai] [int] NULL,
 CONSTRAINT [ChiTietHoaDon_PK] PRIMARY KEY CLUSTERED 
(
	[MaChiTietHoaDon] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChiTietKhongCheBienOrder]    Script Date: 05/16/2012 01:46:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietKhongCheBienOrder](
	[MaChiTietKhongCheBienOrder] [int] IDENTITY(1,1) NOT NULL,
	[MaChiTietOrder] [int] NOT NULL,
	[SoLuongKhongCheBien] [int] NOT NULL,
	[TinhTrang] [int] NOT NULL,
 CONSTRAINT [ChiTietKhongCheBienOrder_PK] PRIMARY KEY CLUSTERED 
(
	[MaChiTietKhongCheBienOrder] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChiTietHuyOrder]    Script Date: 05/16/2012 01:46:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietHuyOrder](
	[MaChiTietOrder] [int] NOT NULL,
	[SoLuongYeuCau] [int] NULL,
	[SoLuongChoPhep] [int] NULL,
	[TinhTrangHuy] [bit] NULL,
 CONSTRAINT [ChiTietHuyOrder_PK] PRIMARY KEY CLUSTERED 
(
	[MaChiTietOrder] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChiTietCheBienOrder]    Script Date: 05/16/2012 01:46:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietCheBienOrder](
	[MaChiTietOrder] [int] NOT NULL,
	[SoLuongDaCheBien] [int] NULL,
	[SoLuongDangCheBien] [int] NULL,
 CONSTRAINT [ChiTietCheBienOrder_PK] PRIMARY KEY CLUSTERED 
(
	[MaChiTietOrder] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Default [DF__ChiTietKh__TinhT__108B795B]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[ChiTietKhongCheBienOrder] ADD  DEFAULT ((0)) FOR [TinhTrang]
GO
/****** Object:  Default [DF__ChiTietOr__TinhT__0425A276]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[ChiTietOrder] ADD  DEFAULT ((0)) FOR [TinhTrang]
GO
/****** Object:  Default [DF__Order__TinhTrang__35BCFE0A]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[Order] ADD  DEFAULT ((0)) FOR [TinhTrang]
GO
/****** Object:  ForeignKey [Ban_Ban_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[Ban]  WITH CHECK ADD  CONSTRAINT [Ban_Ban_FK1] FOREIGN KEY([MaBanChinh])
REFERENCES [dbo].[Ban] ([MaBan])
GO
ALTER TABLE [dbo].[Ban] CHECK CONSTRAINT [Ban_Ban_FK1]
GO
/****** Object:  ForeignKey [KhuVuc_Ban_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[Ban]  WITH CHECK ADD  CONSTRAINT [KhuVuc_Ban_FK1] FOREIGN KEY([MaKhuVuc])
REFERENCES [dbo].[KhuVuc] ([MaKhuVuc])
GO
ALTER TABLE [dbo].[Ban] CHECK CONSTRAINT [KhuVuc_Ban_FK1]
GO
/****** Object:  ForeignKey [TaiKhoan_BoPhanCheBien_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[BoPhanCheBien]  WITH CHECK ADD  CONSTRAINT [TaiKhoan_BoPhanCheBien_FK1] FOREIGN KEY([MaTaiKhoan])
REFERENCES [dbo].[TaiKhoan] ([MaTaiKhoan])
GO
ALTER TABLE [dbo].[BoPhanCheBien] CHECK CONSTRAINT [TaiKhoan_BoPhanCheBien_FK1]
GO
/****** Object:  ForeignKey [ChiTietOrder_ChiTietCheBienOrder_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[ChiTietCheBienOrder]  WITH CHECK ADD  CONSTRAINT [ChiTietOrder_ChiTietCheBienOrder_FK1] FOREIGN KEY([MaChiTietOrder])
REFERENCES [dbo].[ChiTietOrder] ([MaChiTietOrder])
GO
ALTER TABLE [dbo].[ChiTietCheBienOrder] CHECK CONSTRAINT [ChiTietOrder_ChiTietCheBienOrder_FK1]
GO
/****** Object:  ForeignKey [BoPhanCheBien_ChiTietDanhMucBoPhanCheBien_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[ChiTietDanhMucBoPhanCheBien]  WITH CHECK ADD  CONSTRAINT [BoPhanCheBien_ChiTietDanhMucBoPhanCheBien_FK1] FOREIGN KEY([MaBoPhanCheBien])
REFERENCES [dbo].[BoPhanCheBien] ([MaBoPhanCheBien])
GO
ALTER TABLE [dbo].[ChiTietDanhMucBoPhanCheBien] CHECK CONSTRAINT [BoPhanCheBien_ChiTietDanhMucBoPhanCheBien_FK1]
GO
/****** Object:  ForeignKey [DanhMuc_ChiTietDanhMucBoPhanCheBien_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[ChiTietDanhMucBoPhanCheBien]  WITH CHECK ADD  CONSTRAINT [DanhMuc_ChiTietDanhMucBoPhanCheBien_FK1] FOREIGN KEY([MaDanhMuc])
REFERENCES [dbo].[DanhMuc] ([MaDanhMuc])
GO
ALTER TABLE [dbo].[ChiTietDanhMucBoPhanCheBien] CHECK CONSTRAINT [DanhMuc_ChiTietDanhMucBoPhanCheBien_FK1]
GO
/****** Object:  ForeignKey [DanhMuc_ChiTietDanhMucDaNgonNgu_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[ChiTietDanhMucDaNgonNgu]  WITH CHECK ADD  CONSTRAINT [DanhMuc_ChiTietDanhMucDaNgonNgu_FK1] FOREIGN KEY([MaDanhMuc])
REFERENCES [dbo].[DanhMuc] ([MaDanhMuc])
GO
ALTER TABLE [dbo].[ChiTietDanhMucDaNgonNgu] CHECK CONSTRAINT [DanhMuc_ChiTietDanhMucDaNgonNgu_FK1]
GO
/****** Object:  ForeignKey [NgonNgu_ChiTietDanhMucDaNgonNgu_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[ChiTietDanhMucDaNgonNgu]  WITH CHECK ADD  CONSTRAINT [NgonNgu_ChiTietDanhMucDaNgonNgu_FK1] FOREIGN KEY([MaNgonNgu])
REFERENCES [dbo].[NgonNgu] ([MaNgonNgu])
GO
ALTER TABLE [dbo].[ChiTietDanhMucDaNgonNgu] CHECK CONSTRAINT [NgonNgu_ChiTietDanhMucDaNgonNgu_FK1]
GO
/****** Object:  ForeignKey [DonViTinh_ChiTietDonViTinhDaNgonNgu_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[ChiTietDonViTinhDaNgonNgu]  WITH CHECK ADD  CONSTRAINT [DonViTinh_ChiTietDonViTinhDaNgonNgu_FK1] FOREIGN KEY([MaDonViTinh])
REFERENCES [dbo].[DonViTinh] ([MaDonViTinh])
GO
ALTER TABLE [dbo].[ChiTietDonViTinhDaNgonNgu] CHECK CONSTRAINT [DonViTinh_ChiTietDonViTinhDaNgonNgu_FK1]
GO
/****** Object:  ForeignKey [NgonNgu_ChiTietDonViTinhDaNgonNgu_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[ChiTietDonViTinhDaNgonNgu]  WITH CHECK ADD  CONSTRAINT [NgonNgu_ChiTietDonViTinhDaNgonNgu_FK1] FOREIGN KEY([MaNgonNgu])
REFERENCES [dbo].[NgonNgu] ([MaNgonNgu])
GO
ALTER TABLE [dbo].[ChiTietDonViTinhDaNgonNgu] CHECK CONSTRAINT [NgonNgu_ChiTietDonViTinhDaNgonNgu_FK1]
GO
/****** Object:  ForeignKey [FK_ChiTietHoaDon_ChiTietMonAnDonViTinh]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietHoaDon_ChiTietMonAnDonViTinh] FOREIGN KEY([MaDonViTinh], [MaMonAn])
REFERENCES [dbo].[ChiTietMonAnDonViTinh] ([MaDonViTinh], [MaMonAn])
GO
ALTER TABLE [dbo].[ChiTietHoaDon] CHECK CONSTRAINT [FK_ChiTietHoaDon_ChiTietMonAnDonViTinh]
GO
/****** Object:  ForeignKey [HoaDon_ChiTietHoaDon_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD  CONSTRAINT [HoaDon_ChiTietHoaDon_FK1] FOREIGN KEY([MaHoaDon])
REFERENCES [dbo].[HoaDon] ([MaHoaDon])
GO
ALTER TABLE [dbo].[ChiTietHoaDon] CHECK CONSTRAINT [HoaDon_ChiTietHoaDon_FK1]
GO
/****** Object:  ForeignKey [KhuyenMai_ChiTietHoaDon_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD  CONSTRAINT [KhuyenMai_ChiTietHoaDon_FK1] FOREIGN KEY([MaKhuyenMai])
REFERENCES [dbo].[KhuyenMai] ([MaKhuyenMai])
GO
ALTER TABLE [dbo].[ChiTietHoaDon] CHECK CONSTRAINT [KhuyenMai_ChiTietHoaDon_FK1]
GO
/****** Object:  ForeignKey [ChiTietOrder_ChiTietHuyOrder_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[ChiTietHuyOrder]  WITH CHECK ADD  CONSTRAINT [ChiTietOrder_ChiTietHuyOrder_FK1] FOREIGN KEY([MaChiTietOrder])
REFERENCES [dbo].[ChiTietOrder] ([MaChiTietOrder])
GO
ALTER TABLE [dbo].[ChiTietHuyOrder] CHECK CONSTRAINT [ChiTietOrder_ChiTietHuyOrder_FK1]
GO
/****** Object:  ForeignKey [ChiTietOrder_ChiTietKhongCheBienOrder_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[ChiTietKhongCheBienOrder]  WITH CHECK ADD  CONSTRAINT [ChiTietOrder_ChiTietKhongCheBienOrder_FK1] FOREIGN KEY([MaChiTietOrder])
REFERENCES [dbo].[ChiTietOrder] ([MaChiTietOrder])
GO
ALTER TABLE [dbo].[ChiTietKhongCheBienOrder] CHECK CONSTRAINT [ChiTietOrder_ChiTietKhongCheBienOrder_FK1]
GO
/****** Object:  ForeignKey [MonAn_ChiTietMonAnDaNgonNgu_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[ChiTietMonAnDaNgonNgu]  WITH CHECK ADD  CONSTRAINT [MonAn_ChiTietMonAnDaNgonNgu_FK1] FOREIGN KEY([MaMonAn])
REFERENCES [dbo].[MonAn] ([MaMonAn])
GO
ALTER TABLE [dbo].[ChiTietMonAnDaNgonNgu] CHECK CONSTRAINT [MonAn_ChiTietMonAnDaNgonNgu_FK1]
GO
/****** Object:  ForeignKey [NgonNgu_ChiTietMonAnDaNgonNgu_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[ChiTietMonAnDaNgonNgu]  WITH CHECK ADD  CONSTRAINT [NgonNgu_ChiTietMonAnDaNgonNgu_FK1] FOREIGN KEY([MaNgonNgu])
REFERENCES [dbo].[NgonNgu] ([MaNgonNgu])
GO
ALTER TABLE [dbo].[ChiTietMonAnDaNgonNgu] CHECK CONSTRAINT [NgonNgu_ChiTietMonAnDaNgonNgu_FK1]
GO
/****** Object:  ForeignKey [DonViTinh_ChiTietMonAnDonViTinh_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[ChiTietMonAnDonViTinh]  WITH CHECK ADD  CONSTRAINT [DonViTinh_ChiTietMonAnDonViTinh_FK1] FOREIGN KEY([MaDonViTinh])
REFERENCES [dbo].[DonViTinh] ([MaDonViTinh])
GO
ALTER TABLE [dbo].[ChiTietMonAnDonViTinh] CHECK CONSTRAINT [DonViTinh_ChiTietMonAnDonViTinh_FK1]
GO
/****** Object:  ForeignKey [MonAn_ChiTietMonAnDonViTinh_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[ChiTietMonAnDonViTinh]  WITH CHECK ADD  CONSTRAINT [MonAn_ChiTietMonAnDonViTinh_FK1] FOREIGN KEY([MaMonAn])
REFERENCES [dbo].[MonAn] ([MaMonAn])
GO
ALTER TABLE [dbo].[ChiTietMonAnDonViTinh] CHECK CONSTRAINT [MonAn_ChiTietMonAnDonViTinh_FK1]
GO
/****** Object:  ForeignKey [BoPhanCheBien_ChiTietOrder_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[ChiTietOrder]  WITH CHECK ADD  CONSTRAINT [BoPhanCheBien_ChiTietOrder_FK1] FOREIGN KEY([MaBoPhanCheBien])
REFERENCES [dbo].[BoPhanCheBien] ([MaBoPhanCheBien])
GO
ALTER TABLE [dbo].[ChiTietOrder] CHECK CONSTRAINT [BoPhanCheBien_ChiTietOrder_FK1]
GO
/****** Object:  ForeignKey [FK_ChiTietOrder_ChiTietMonAnDonViTinh]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[ChiTietOrder]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietOrder_ChiTietMonAnDonViTinh] FOREIGN KEY([MaDonViTinh], [MaMonAn])
REFERENCES [dbo].[ChiTietMonAnDonViTinh] ([MaDonViTinh], [MaMonAn])
GO
ALTER TABLE [dbo].[ChiTietOrder] CHECK CONSTRAINT [FK_ChiTietOrder_ChiTietMonAnDonViTinh]
GO
/****** Object:  ForeignKey [Order_ChiTietOrder_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[ChiTietOrder]  WITH CHECK ADD  CONSTRAINT [Order_ChiTietOrder_FK1] FOREIGN KEY([MaOrder])
REFERENCES [dbo].[Order] ([MaOrder])
GO
ALTER TABLE [dbo].[ChiTietOrder] CHECK CONSTRAINT [Order_ChiTietOrder_FK1]
GO
/****** Object:  ForeignKey [DanhMuc_DanhMuc_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[DanhMuc]  WITH CHECK ADD  CONSTRAINT [DanhMuc_DanhMuc_FK1] FOREIGN KEY([MaDanhMucCha])
REFERENCES [dbo].[DanhMuc] ([MaDanhMuc])
GO
ALTER TABLE [dbo].[DanhMuc] CHECK CONSTRAINT [DanhMuc_DanhMuc_FK1]
GO
/****** Object:  ForeignKey [Ban_HoaDon_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [Ban_HoaDon_FK1] FOREIGN KEY([MaBanChinh])
REFERENCES [dbo].[Ban] ([MaBan])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [Ban_HoaDon_FK1]
GO
/****** Object:  ForeignKey [PhuThu_HoaDon_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [PhuThu_HoaDon_FK1] FOREIGN KEY([MaPhuThu])
REFERENCES [dbo].[PhuThu] ([MaPhuThu])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [PhuThu_HoaDon_FK1]
GO
/****** Object:  ForeignKey [TaiKhoan_HoaDon_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [TaiKhoan_HoaDon_FK1] FOREIGN KEY([MaTaiKhoan])
REFERENCES [dbo].[TaiKhoan] ([MaTaiKhoan])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [TaiKhoan_HoaDon_FK1]
GO
/****** Object:  ForeignKey [DanhMuc_KhuyenMaiDanhMuc_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[KhuyenMaiDanhMuc]  WITH CHECK ADD  CONSTRAINT [DanhMuc_KhuyenMaiDanhMuc_FK1] FOREIGN KEY([MaDanhMuc])
REFERENCES [dbo].[DanhMuc] ([MaDanhMuc])
GO
ALTER TABLE [dbo].[KhuyenMaiDanhMuc] CHECK CONSTRAINT [DanhMuc_KhuyenMaiDanhMuc_FK1]
GO
/****** Object:  ForeignKey [KhuyenMai_KhuyenMaiDanhMuc_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[KhuyenMaiDanhMuc]  WITH CHECK ADD  CONSTRAINT [KhuyenMai_KhuyenMaiDanhMuc_FK1] FOREIGN KEY([MaKhuyenMai])
REFERENCES [dbo].[KhuyenMai] ([MaKhuyenMai])
GO
ALTER TABLE [dbo].[KhuyenMaiDanhMuc] CHECK CONSTRAINT [KhuyenMai_KhuyenMaiDanhMuc_FK1]
GO
/****** Object:  ForeignKey [KhuyenMai_KhuyenMaiHoaDon_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[KhuyenMaiHoaDon]  WITH CHECK ADD  CONSTRAINT [KhuyenMai_KhuyenMaiHoaDon_FK1] FOREIGN KEY([MaKhuyenMai])
REFERENCES [dbo].[KhuyenMai] ([MaKhuyenMai])
GO
ALTER TABLE [dbo].[KhuyenMaiHoaDon] CHECK CONSTRAINT [KhuyenMai_KhuyenMaiHoaDon_FK1]
GO
/****** Object:  ForeignKey [KhuVuc_KhuyenMaiKhuVuc_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[KhuyenMaiKhuVuc]  WITH CHECK ADD  CONSTRAINT [KhuVuc_KhuyenMaiKhuVuc_FK1] FOREIGN KEY([MaKhuVuc])
REFERENCES [dbo].[KhuVuc] ([MaKhuVuc])
GO
ALTER TABLE [dbo].[KhuyenMaiKhuVuc] CHECK CONSTRAINT [KhuVuc_KhuyenMaiKhuVuc_FK1]
GO
/****** Object:  ForeignKey [KhuyenMai_KhuyenMaiKhuVuc_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[KhuyenMaiKhuVuc]  WITH CHECK ADD  CONSTRAINT [KhuyenMai_KhuyenMaiKhuVuc_FK1] FOREIGN KEY([MaKhuyenMai])
REFERENCES [dbo].[KhuyenMai] ([MaKhuyenMai])
GO
ALTER TABLE [dbo].[KhuyenMaiKhuVuc] CHECK CONSTRAINT [KhuyenMai_KhuyenMaiKhuVuc_FK1]
GO
/****** Object:  ForeignKey [KhuyenMai_KhuyenMaiMon_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[KhuyenMaiMon]  WITH CHECK ADD  CONSTRAINT [KhuyenMai_KhuyenMaiMon_FK1] FOREIGN KEY([MaKhuyenMai])
REFERENCES [dbo].[KhuyenMai] ([MaKhuyenMai])
GO
ALTER TABLE [dbo].[KhuyenMaiMon] CHECK CONSTRAINT [KhuyenMai_KhuyenMaiMon_FK1]
GO
/****** Object:  ForeignKey [MonAn_KhuyenMaiMon_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[KhuyenMaiMon]  WITH CHECK ADD  CONSTRAINT [MonAn_KhuyenMaiMon_FK1] FOREIGN KEY([MaMonAn])
REFERENCES [dbo].[MonAn] ([MaMonAn])
GO
ALTER TABLE [dbo].[KhuyenMaiMon] CHECK CONSTRAINT [MonAn_KhuyenMaiMon_FK1]
GO
/****** Object:  ForeignKey [DanhMuc_MonAn_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[MonAn]  WITH CHECK ADD  CONSTRAINT [DanhMuc_MonAn_FK1] FOREIGN KEY([MaDanhMuc])
REFERENCES [dbo].[DanhMuc] ([MaDanhMuc])
GO
ALTER TABLE [dbo].[MonAn] CHECK CONSTRAINT [DanhMuc_MonAn_FK1]
GO
/****** Object:  ForeignKey [DonViTinh_MonAn_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[MonAn]  WITH CHECK ADD  CONSTRAINT [DonViTinh_MonAn_FK1] FOREIGN KEY([MaDonViTinhMacDinh])
REFERENCES [dbo].[DonViTinh] ([MaDonViTinh])
GO
ALTER TABLE [dbo].[MonAn] CHECK CONSTRAINT [DonViTinh_MonAn_FK1]
GO
/****** Object:  ForeignKey [Ban_Order_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [Ban_Order_FK1] FOREIGN KEY([MaBan])
REFERENCES [dbo].[Ban] ([MaBan])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [Ban_Order_FK1]
GO
/****** Object:  ForeignKey [TaiKhoan_Order_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [TaiKhoan_Order_FK1] FOREIGN KEY([MaTaiKhoan])
REFERENCES [dbo].[TaiKhoan] ([MaTaiKhoan])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [TaiKhoan_Order_FK1]
GO
/****** Object:  ForeignKey [KhuVuc_PhuThuKhuVuc_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[PhuThuKhuVuc]  WITH CHECK ADD  CONSTRAINT [KhuVuc_PhuThuKhuVuc_FK1] FOREIGN KEY([MaKhuVuc])
REFERENCES [dbo].[KhuVuc] ([MaKhuVuc])
GO
ALTER TABLE [dbo].[PhuThuKhuVuc] CHECK CONSTRAINT [KhuVuc_PhuThuKhuVuc_FK1]
GO
/****** Object:  ForeignKey [PhuThu_PhuThuKhuVuc_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[PhuThuKhuVuc]  WITH CHECK ADD  CONSTRAINT [PhuThu_PhuThuKhuVuc_FK1] FOREIGN KEY([MaPhuThu])
REFERENCES [dbo].[PhuThu] ([MaPhuThu])
GO
ALTER TABLE [dbo].[PhuThuKhuVuc] CHECK CONSTRAINT [PhuThu_PhuThuKhuVuc_FK1]
GO
/****** Object:  ForeignKey [NhomTaiKhoan_TaiKhoan_FK1]    Script Date: 05/16/2012 01:46:06 ******/
ALTER TABLE [dbo].[TaiKhoan]  WITH CHECK ADD  CONSTRAINT [NhomTaiKhoan_TaiKhoan_FK1] FOREIGN KEY([MaNhomTaiKhoan])
REFERENCES [dbo].[NhomTaiKhoan] ([MaNhomTaiKhoan])
GO
ALTER TABLE [dbo].[TaiKhoan] CHECK CONSTRAINT [NhomTaiKhoan_TaiKhoan_FK1]
GO
