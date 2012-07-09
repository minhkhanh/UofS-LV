-- Table: NhomTaiKhoan
CREATE TABLE NhomTaiKhoan ( 
    _id            INTEGER PRIMARY KEY AUTOINCREMENT,
    MaNhomTaiKhoan INTEGER NOT NULL
                           UNIQUE,
    TenNhom        TEXT,
    MoTaNhom       TEXT 
);


-- Table: TaiKhoan
CREATE TABLE TaiKhoan ( 
    _id            INTEGER PRIMARY KEY AUTOINCREMENT,
    MaTaiKhoan     INTEGER NOT NULL
                           UNIQUE,
    TenTaiKhoan    TEXT,
    MatKhau        TEXT,
    HoTen          TEXT,
    NgaySinh       DATE,
    GioiTinh       INTEGER,
    CMND           TEXT,
    Avatar         TEXT,
    Active         INTEGER,
    MaNhomTaiKhoan INTEGER REFERENCES NhomTaiKhoan ( MaNhomTaiKhoan ) 
);


-- Table: NgonNgu
CREATE TABLE NgonNgu ( 
    _id        INTEGER PRIMARY KEY AUTOINCREMENT,
    MaNgonNgu  INTEGER NOT NULL
                       UNIQUE,
    TenNgonNgu TEXT,
    KiHieu     TEXT 
);


-- Table: DonViTinh
CREATE TABLE DonViTinh ( 
    _id         INTEGER PRIMARY KEY AUTOINCREMENT,
    MaDonViTinh INTEGER NOT NULL
                        UNIQUE 
);


-- Table: DanhMuc
CREATE TABLE DanhMuc ( 
    _id          INTEGER PRIMARY KEY AUTOINCREMENT,
    MaDanhMuc    INTEGER NOT NULL
                         UNIQUE,
    MaDanhMucCha INTEGER REFERENCES DanhMuc ( MaDanhMuc ) 
);


-- Table: KhuVuc
CREATE TABLE KhuVuc ( 
    _id       INTEGER PRIMARY KEY AUTOINCREMENT,
    MaKhuVuc  INTEGER NOT NULL
                      UNIQUE,
    TenKhuVuc TEXT,
    MoTa      TEXT 
);


-- Table: Ban
CREATE TABLE Ban ( 
    _id        INTEGER PRIMARY KEY AUTOINCREMENT,
    MaBan      INTEGER NOT NULL
                       UNIQUE,
    MaKhuVuc   INTEGER REFERENCES KhuVuc ( MaKhuVuc ),
    TenBan     TEXT,
    GhiChu     TEXT,
    Active     INTEGER DEFAULT ( 1 ),
    TinhTrang  INTEGER,
    MaBanChinh INTEGER REFERENCES Ban ( MaBan ) 
);


-- Table: MonAn
CREATE TABLE MonAn ( 
    _id                INTEGER PRIMARY KEY AUTOINCREMENT,
    MaMonAn            INTEGER NOT NULL
                               UNIQUE,
    HinhAnh            BLOB,
    DiemDanhGia        REAL    NOT NULL
                               DEFAULT ( 0 ),
    SoLuotDanhGia      INTEGER NOT NULL
                               DEFAULT ( 0 ),
    MaDonViTinhMacDinh INTEGER REFERENCES DonViTinh ( MaDonViTinh ),
    MaDanhMuc          INTEGER REFERENCES DanhMuc ( MaDanhMuc ),
    NgungBan           INTEGER NOT NULL
                               DEFAULT ( 0 ) 
);


-- Table: ThamSo
CREATE TABLE ThamSo ( 
    _id    INTEGER PRIMARY KEY AUTOINCREMENT,
    Ten    TEXT    NOT NULL
                   UNIQUE,
    GiaTri TEXT 
);


-- Table: ChiTietDanhMucDaNgonNgu
CREATE TABLE ChiTietDanhMucDaNgonNgu ( 
    _id         INTEGER PRIMARY KEY AUTOINCREMENT,
    MaDanhMuc   INTEGER NOT NULL
                        REFERENCES DanhMuc ( MaDanhMuc ),
    MaNgonNgu   INTEGER NOT NULL
                        REFERENCES NgonNgu ( MaNgonNgu ),
    TenDanhMuc  TEXT,
    MoTaDanhMuc TEXT,
    UNIQUE ( MaDanhMuc, MaNgonNgu ) 
);


-- Table: ChiTietDonViTinhDaNgonNgu
CREATE TABLE ChiTietDonViTinhDaNgonNgu ( 
    _id          INTEGER PRIMARY KEY AUTOINCREMENT,
    MaNgonNgu    INTEGER NOT NULL
                         REFERENCES NgonNgu ( MaNgonNgu ),
    MaDonViTinh  INTEGER NOT NULL
                         REFERENCES DonViTinh ( MaDonViTinh ),
    TenDonViTinh TEXT,
    UNIQUE ( MaDonViTinh, MaNgonNgu ) 
);


-- Table: ChiTietMonAnDaNgonNgu
CREATE TABLE ChiTietMonAnDaNgonNgu ( 
    _id       INTEGER PRIMARY KEY AUTOINCREMENT,
    MaMonAn   INTEGER NOT NULL
                      REFERENCES MonAn ( MaMonAn ),
    MaNgonNgu INTEGER NOT NULL
                      REFERENCES NgonNgu ( MaNgonNgu ),
    TenMonAn  TEXT,
    MoTaMonAn TEXT,
    UNIQUE ( MaMonAn, MaNgonNgu ) 
);

CREATE VIRTUAL TABLE ChiTietMonAnDaNgonNgu_FTS USING fts3 ( 
    _id,
    MaMonAn,
    MaNgonNgu,
    TenMonAn  TEXT,
    MoTaMonAn TEXT
);


-- Table: ChiTietMonAnDonViTinh
CREATE TABLE ChiTietMonAnDonViTinh ( 
    _id         INTEGER PRIMARY KEY AUTOINCREMENT,
    MaMonAn     INTEGER NOT NULL
                        REFERENCES MonAn ( MaMonAn ),
    MaDonViTinh INTEGER NOT NULL
                        REFERENCES DonViTinh ( MaDonViTinh ),
    DonGia      INTEGER,
    UNIQUE ( MaMonAn, MaDonViTinh ) 
);


-- Table: Order
CREATE TABLE [Order] ( 
    _id        INTEGER PRIMARY KEY AUTOINCREMENT,
    MaOrder    INTEGER NOT NULL
                       UNIQUE,
    MaTaiKhoan INTEGER REFERENCES TaiKhoan ( MaTaiKhoan ),
    MaBan      INTEGER REFERENCES Ban ( MaBan ) 
);


-- Table: ChiTietOrder
CREATE TABLE ChiTietOrder ( 
    _id             INTEGER PRIMARY KEY AUTOINCREMENT,
    MaChiTietOrder  INTEGER NOT NULL
                            UNIQUE,
    MaOrder         INTEGER,
    SoLuong         INTEGER,
    GhiChu          TEXT,
    MaBoPhanCheBien INTEGER,
    TinhTrang       INTEGER NOT NULL
                            DEFAULT 0,
    MaMonAn         INTEGER,
    MaDonViTinh     INTEGER,
    FOREIGN KEY ( MaMonAn, MaDonViTinh ) REFERENCES ChiTietMonAnDonViTinh ( MaMonAn, MaDonViTinh ) 
);


-- Table: TiGia
CREATE TABLE TiGia ( 
    _id             INTEGER  PRIMARY KEY AUTOINCREMENT,
    MaTiGia         INTEGER  NOT NULL
                             UNIQUE,
    KiHieu          TEXT,
    GiaTri          REAL,
    ThoiDiemCapNhat DATETIME 
);


-- Table: ChiTietMonLienQuan
CREATE TABLE ChiTietMonLienQuan ( 
    _id             INTEGER PRIMARY KEY AUTOINCREMENT,
    MaMonAn         INTEGER NOT NULL
                            REFERENCES MonAn ( MaMonAn ),
    MaMonAnLienQuan INTEGER REFERENCES MonAn ( MaMonAn ),
    UNIQUE ( MaMonAn, MaMonAnLienQuan ) 
);


-- Table: KhuyenMai
CREATE TABLE KhuyenMai ( 
    _id           INTEGER  PRIMARY KEY AUTOINCREMENT,
    MaKhuyenMai   INTEGER  NOT NULL
                           UNIQUE,
    TenKhuyenMai  TEXT,
    MoTaKhuyenMai TEXT,
    GiaGiam       INTEGER     DEFAULT ( 0 ),
    TiLeGiam      REAL     DEFAULT ( 0 ),
    BatDau        DATETIME,
    KetThuc       DATETIME,
    LichKhuyenMai TEXT 
);


-- Table: KhuyenMaiMon
CREATE TABLE KhuyenMaiMon ( 
    _id         INTEGER PRIMARY KEY AUTOINCREMENT,
    MaKhuyenMai INTEGER NOT NULL
                        REFERENCES KhuyenMai ( MaKhuyenMai ),
    MaMonAn     INTEGER NOT NULL
                        REFERENCES MonAn ( MaMonAn ),
    UNIQUE ( MaKhuyenMai, MaMonAn ) 
);


-- Table: PhuThu
CREATE TABLE PhuThu ( 
    _id        INTEGER  PRIMARY KEY AUTOINCREMENT,
    MaPhuThu   INTEGER  NOT NULL
                        UNIQUE,
    TenPhuThu  TEXT,
    MoTaPhuThu TEXT,
    GiaTang    INTEGER  DEFAULT ( 0 ),
    TiLeTang   REAL     DEFAULT ( 0 ),
    BatDau     DATETIME,
    KetThuc    DATETIME,
    LichPhuThu TEXT 
);

-- Table: PhuThuKhuVuc
CREATE TABLE PhuThuKhuVuc ( 
    _id      INTEGER PRIMARY KEY AUTOINCREMENT,
    MaPhuThu INTEGER NOT NULL
                     REFERENCES PhuThu ( MaPhuThu ),
    MaKhuVuc INTEGER NOT NULL
                     REFERENCES KhuVuc ( MaKhuVuc ),
    UNIQUE ( MaPhuThu, MaKhuVuc ) 
);

-- Table: ThamSo
INSERT INTO [ThamSo] ([Ten], [GiaTri]) VALUES ('BillPrinter', '');
INSERT INTO [ThamSo] ([Ten], [GiaTri]) VALUES ('MaThamSoMacDinh', 1);


-- Table: NgonNgu
INSERT INTO [NgonNgu] ([MaNgonNgu], [TenNgonNgu], [KiHieu]) VALUES (1, 'Tiếng Việt', 'vi');
INSERT INTO [NgonNgu] ([MaNgonNgu], [TenNgonNgu], [KiHieu]) VALUES (2, 'English', 'en');
INSERT INTO [NgonNgu] ([MaNgonNgu], [TenNgonNgu], [KiHieu]) VALUES (3, '日本の', 'ja');

/*

-- Table: KhuVuc
INSERT INTO [KhuVuc] ([MaKhuVuc], [TenKhuVuc], [MoTa]) VALUES (1, 'Lầu 1', 'Khu vực ở lầu 1');
INSERT INTO [KhuVuc] ([MaKhuVuc], [TenKhuVuc], [MoTa]) VALUES (2, 'Lầu 2', 'Khu vực ở lầu 2');
INSERT INTO [KhuVuc] ([MaKhuVuc], [TenKhuVuc], [MoTa]) VALUES (3, 'Lầu 3', 'Khu vực ở lầu 3');


-- Table: Ban
INSERT INTO [Ban] ([MaBan], [MaKhuVuc], [TenBan], [GhiChu], [Active], [TinhTrang], [MaBanChinh]) VALUES (1, 1, 'Bàn 1', 'Ghi chú', 1, 1, null);
INSERT INTO [Ban] ([MaBan], [MaKhuVuc], [TenBan], [GhiChu], [Active], [TinhTrang], [MaBanChinh]) VALUES (2, 1, 'Bàn 2', 'Ghi chú', 1, 1, null);
INSERT INTO [Ban] ([MaBan], [MaKhuVuc], [TenBan], [GhiChu], [Active], [TinhTrang], [MaBanChinh]) VALUES (3, 1, 'Bàn 3', 'Ghi chú', 1, 1, null);
INSERT INTO [Ban] ([MaBan], [MaKhuVuc], [TenBan], [GhiChu], [Active], [TinhTrang], [MaBanChinh]) VALUES (4, 2, 'Bàn 4', 'Ghi chú', 1, 1, null);
INSERT INTO [Ban] ([MaBan], [MaKhuVuc], [TenBan], [GhiChu], [Active], [TinhTrang], [MaBanChinh]) VALUES (5, 2, 'Bàn 5', 'Ghi chú', 1, 1, null);
INSERT INTO [Ban] ([MaBan], [MaKhuVuc], [TenBan], [GhiChu], [Active], [TinhTrang], [MaBanChinh]) VALUES (6, 3, 'Bàn 6', 'Ghi chú', 1, 1, null);



-- Table: DanhMuc
INSERT INTO [DanhMuc] ([MaDanhMuc], [MaDanhMucCha]) VALUES (1, null);
INSERT INTO [DanhMuc] ([MaDanhMuc], [MaDanhMucCha]) VALUES (2, 1);
INSERT INTO [DanhMuc] ([MaDanhMuc], [MaDanhMucCha]) VALUES (3, 1);
INSERT INTO [DanhMuc] ([MaDanhMuc], [MaDanhMucCha]) VALUES (4, 2);
INSERT INTO [DanhMuc] ([MaDanhMuc], [MaDanhMucCha]) VALUES (5, 2);
INSERT INTO [DanhMuc] ([MaDanhMuc], [MaDanhMucCha]) VALUES (6, 3);
INSERT INTO [DanhMuc] ([MaDanhMuc], [MaDanhMucCha]) VALUES (7, 3);

-- Table: DonViTinh
INSERT INTO [DonViTinh] ([MaDonViTinh]) VALUES (1);
INSERT INTO [DonViTinh] ([MaDonViTinh]) VALUES (2);
INSERT INTO [DonViTinh] ([MaDonViTinh]) VALUES (3);

-- Table: ChiTietDonViTinhDaNgonNgu
INSERT INTO [ChiTietDonViTinhDaNgonNgu] ([MaDonViTinh], [MaNgonNgu], [TenDonViTinh]) VALUES (1, 1, 'Chai');
INSERT INTO [ChiTietDonViTinhDaNgonNgu] ([MaDonViTinh], [MaNgonNgu], [TenDonViTinh]) VALUES (1, 2, 'Bottle');
INSERT INTO [ChiTietDonViTinhDaNgonNgu] ([MaDonViTinh], [MaNgonNgu], [TenDonViTinh]) VALUES (2, 1, 'Thùng');
INSERT INTO [ChiTietDonViTinhDaNgonNgu] ([MaDonViTinh], [MaNgonNgu], [TenDonViTinh]) VALUES (2, 2, 'Box');
INSERT INTO [ChiTietDonViTinhDaNgonNgu] ([MaDonViTinh], [MaNgonNgu], [TenDonViTinh]) VALUES (3, 1, 'Dĩa');
INSERT INTO [ChiTietDonViTinhDaNgonNgu] ([MaDonViTinh], [MaNgonNgu], [TenDonViTinh]) VALUES (3, 2, 'Disk');

-- Table: ChiTietDanhMucDaNgonNgu
INSERT INTO [ChiTietDanhMucDaNgonNgu] ([MaDanhMuc], [MaNgonNgu], [TenDanhMuc], [MoTaDanhMuc]) VALUES (1, 1, 'Không có', '');
INSERT INTO [ChiTietDanhMucDaNgonNgu] ([MaDanhMuc], [MaNgonNgu], [TenDanhMuc], [MoTaDanhMuc]) VALUES (1, 2, 'None', '');
INSERT INTO [ChiTietDanhMucDaNgonNgu] ([MaDanhMuc], [MaNgonNgu], [TenDanhMuc], [MoTaDanhMuc]) VALUES (2, 1, 'Cơm', 'Dương châu nổi tiếng vì món cơm này!');
INSERT INTO [ChiTietDanhMucDaNgonNgu] ([MaDanhMuc], [MaNgonNgu], [TenDanhMuc], [MoTaDanhMuc]) VALUES (2, 2, 'Rice', 'Made from rice');
INSERT INTO [ChiTietDanhMucDaNgonNgu] ([MaDanhMuc], [MaNgonNgu], [TenDanhMuc], [MoTaDanhMuc]) VALUES (3, 1, 'Nước', 'Đồ uống');
INSERT INTO [ChiTietDanhMucDaNgonNgu] ([MaDanhMuc], [MaNgonNgu], [TenDanhMuc], [MoTaDanhMuc]) VALUES (3, 2, 'Drink', 'Relief thirst');
INSERT INTO [ChiTietDanhMucDaNgonNgu] ([MaDanhMuc], [MaNgonNgu], [TenDanhMuc], [MoTaDanhMuc]) VALUES (4, 1, 'Cơm thập cẩm', 'Cơm có rau thịt');
INSERT INTO [ChiTietDanhMucDaNgonNgu] ([MaDanhMuc], [MaNgonNgu], [TenDanhMuc], [MoTaDanhMuc]) VALUES (4, 2, 'Paella', 'Rice with vegetable and meat');
INSERT INTO [ChiTietDanhMucDaNgonNgu] ([MaDanhMuc], [MaNgonNgu], [TenDanhMuc], [MoTaDanhMuc]) VALUES (5, 1, 'Cơm thường', 'Cơm bình dân');
INSERT INTO [ChiTietDanhMucDaNgonNgu] ([MaDanhMuc], [MaNgonNgu], [TenDanhMuc], [MoTaDanhMuc]) VALUES (5, 2, 'Normal rice', 'Very normal rice');
INSERT INTO [ChiTietDanhMucDaNgonNgu] ([MaDanhMuc], [MaNgonNgu], [TenDanhMuc], [MoTaDanhMuc]) VALUES (6, 1, 'Nước Coca', 'Nước ngọt cocacola');
INSERT INTO [ChiTietDanhMucDaNgonNgu] ([MaDanhMuc], [MaNgonNgu], [TenDanhMuc], [MoTaDanhMuc]) VALUES (6, 2, 'Coca', 'This is coca');
INSERT INTO [ChiTietDanhMucDaNgonNgu] ([MaDanhMuc], [MaNgonNgu], [TenDanhMuc], [MoTaDanhMuc]) VALUES (7, 1, 'Nước Pepsi', 'Nước ngọt pepsi');
INSERT INTO [ChiTietDanhMucDaNgonNgu] ([MaDanhMuc], [MaNgonNgu], [TenDanhMuc], [MoTaDanhMuc]) VALUES (7, 2, 'Pepsi', 'This is pepsi');

-- Table: MonAn
INSERT INTO [MonAn] ([MaMonAn], [HinhAnh], [DiemDanhGia], [SoLuotDanhGia], [MaDonViTinhMacDinh], [MaDanhMuc], [NgungBan]) VALUES (1, '/Images/Com_chien_duong_chau.jpg', '10.0', 15, 1, 3, 0);
INSERT INTO [MonAn] ([MaMonAn], [HinhAnh], [DiemDanhGia], [SoLuotDanhGia], [MaDonViTinhMacDinh], [MaDanhMuc], [NgungBan]) VALUES (2, '/Images/Com_chien_tu_chau.jpg', '10.0', 30, 1, 3, 0);
INSERT INTO [MonAn] ([MaMonAn], [HinhAnh], [DiemDanhGia], [SoLuotDanhGia], [MaDonViTinhMacDinh], [MaDanhMuc], [NgungBan]) VALUES (3, '/Images/Com_chien_quang_dong.jpg', '5.0', 20, 2, 3, 0);
INSERT INTO [MonAn] ([MaMonAn], [HinhAnh], [DiemDanhGia], [SoLuotDanhGia], [MaDonViTinhMacDinh], [MaDanhMuc], [NgungBan]) VALUES (4, '/Images/Com_chien_bac_kinh.jpg', '8.0', 20, 2, 3, 0);
INSERT INTO [MonAn] ([MaMonAn], [HinhAnh], [DiemDanhGia], [SoLuotDanhGia], [MaDonViTinhMacDinh], [MaDanhMuc], [NgungBan]) VALUES (5, '/Images/Com ga xoi mo.jpg', '9.0', 20, 1, 4, 0);
INSERT INTO [MonAn] ([MaMonAn], [HinhAnh], [DiemDanhGia], [SoLuotDanhGia], [MaDonViTinhMacDinh], [MaDanhMuc], [NgungBan]) VALUES (6, '/Images/Com thit kho.jpg', '5.0', 20, 1, 4, 0);
INSERT INTO [MonAn] ([MaMonAn], [HinhAnh], [DiemDanhGia], [SoLuotDanhGia], [MaDonViTinhMacDinh], [MaDanhMuc], [NgungBan]) VALUES (7, '/Images/Coca da.jpg', '9.0', 20, 1, 5, 0);
INSERT INTO [MonAn] ([MaMonAn], [HinhAnh], [DiemDanhGia], [SoLuotDanhGia], [MaDonViTinhMacDinh], [MaDanhMuc], [NgungBan]) VALUES (8, '/Images/Coca uop lanh.jpg', '7.0', 20, 1, 5, 0);
INSERT INTO [MonAn] ([MaMonAn], [HinhAnh], [DiemDanhGia], [SoLuotDanhGia], [MaDonViTinhMacDinh], [MaDanhMuc], [NgungBan]) VALUES (9, '/Images/Coca chanh.jpg', '5.0', 20, 1, 5, 0);
INSERT INTO [MonAn] ([MaMonAn], [HinhAnh], [DiemDanhGia], [SoLuotDanhGia], [MaDonViTinhMacDinh], [MaDanhMuc], [NgungBan]) VALUES (10, '/Images/Pepsi da.jpg', '9.0', 20, 1, 6, 0);
INSERT INTO [MonAn] ([MaMonAn], [HinhAnh], [DiemDanhGia], [SoLuotDanhGia], [MaDonViTinhMacDinh], [MaDanhMuc], [NgungBan]) VALUES (11, '/Images/Pepsi uop lanh.jpg', '7.0', 20, 1, 6, 0);
INSERT INTO [MonAn] ([MaMonAn], [HinhAnh], [DiemDanhGia], [SoLuotDanhGia], [MaDonViTinhMacDinh], [MaDanhMuc], [NgungBan]) VALUES (12, '/Images/Pepsi chanh.jpg', '5.0', 20, 1, 6, 0);

-- Table: ChiTietMonAnDonViTinh
INSERT INTO [ChiTietMonAnDonViTinh] ([MaMonAn], [MaDonViTinh], [DonGia]) VALUES (1, 1, '100.0');
INSERT INTO [ChiTietMonAnDonViTinh] ([MaMonAn], [MaDonViTinh], [DonGia]) VALUES (2, 1, '100.0');
INSERT INTO [ChiTietMonAnDonViTinh] ([MaMonAn], [MaDonViTinh], [DonGia]) VALUES (3, 1, '100.0');
INSERT INTO [ChiTietMonAnDonViTinh] ([MaMonAn], [MaDonViTinh], [DonGia]) VALUES (4, 1, '100.0');
INSERT INTO [ChiTietMonAnDonViTinh] ([MaMonAn], [MaDonViTinh], [DonGia]) VALUES (5, 1, '100.0');
INSERT INTO [ChiTietMonAnDonViTinh] ([MaMonAn], [MaDonViTinh], [DonGia]) VALUES (6, 1, '100.0');
INSERT INTO [ChiTietMonAnDonViTinh] ([MaMonAn], [MaDonViTinh], [DonGia]) VALUES (7, 1, '100.0');
INSERT INTO [ChiTietMonAnDonViTinh] ([MaMonAn], [MaDonViTinh], [DonGia]) VALUES (8, 1, '100.0');
INSERT INTO [ChiTietMonAnDonViTinh] ([MaMonAn], [MaDonViTinh], [DonGia]) VALUES (9, 1, '100.0');
INSERT INTO [ChiTietMonAnDonViTinh] ([MaMonAn], [MaDonViTinh], [DonGia]) VALUES (10, 1, '100.0');
INSERT INTO [ChiTietMonAnDonViTinh] ([MaMonAn], [MaDonViTinh], [DonGia]) VALUES (11, 1, '100.0');
INSERT INTO [ChiTietMonAnDonViTinh] ([MaMonAn], [MaDonViTinh], [DonGia]) VALUES (12, 1, '100.0');
INSERT INTO [ChiTietMonAnDonViTinh] ([MaMonAn], [MaDonViTinh], [DonGia]) VALUES (1, 2, '200.0');
INSERT INTO [ChiTietMonAnDonViTinh] ([MaMonAn], [MaDonViTinh], [DonGia]) VALUES (2, 2, '200.0');
INSERT INTO [ChiTietMonAnDonViTinh] ([MaMonAn], [MaDonViTinh], [DonGia]) VALUES (4, 2, '500.0');
INSERT INTO [ChiTietMonAnDonViTinh] ([MaMonAn], [MaDonViTinh], [DonGia]) VALUES (5, 2, '200.0');
INSERT INTO [ChiTietMonAnDonViTinh] ([MaMonAn], [MaDonViTinh], [DonGia]) VALUES (12, 2, '400.0');
INSERT INTO [ChiTietMonAnDonViTinh] ([MaMonAn], [MaDonViTinh], [DonGia]) VALUES (1, 3, '300.0');
INSERT INTO [ChiTietMonAnDonViTinh] ([MaMonAn], [MaDonViTinh], [DonGia]) VALUES (2, 3, '400.0');


-- Table: ChiTietMonAnDaNgonNgu
INSERT INTO [ChiTietMonAnDaNgonNgu] ([MaMonAn], [MaNgonNgu], [TenMonAn], [MoTaMonAn]) VALUES (1, 1, 'Cơm chiên dương châu', 'Món ăn làm từ gạo tứ xuyên rất ngon và bổ dưỡng. Cung cấp các chất dinh dưỡng cần thiết cho cơ thể con người. Được chế biến công phụ với nhiều hương vị lạ!');
INSERT INTO [ChiTietMonAnDaNgonNgu] ([MaMonAn], [MaNgonNgu], [TenMonAn], [MoTaMonAn]) VALUES (1, 2, 'Yangzhou fried rice', 'Yangzhou is famous for this rice');
INSERT INTO [ChiTietMonAnDaNgonNgu] ([MaMonAn], [MaNgonNgu], [TenMonAn], [MoTaMonAn]) VALUES (2, 1, 'Cơm chiên tứ châu', 'Rất ngon và thơm phù hợp với người Việt Nam!');
INSERT INTO [ChiTietMonAnDaNgonNgu] ([MaMonAn], [MaNgonNgu], [TenMonAn], [MoTaMonAn]) VALUES (2, 2, 'Tu chau rice', 'Very good');
INSERT INTO [ChiTietMonAnDaNgonNgu] ([MaMonAn], [MaNgonNgu], [TenMonAn], [MoTaMonAn]) VALUES (3, 1, 'Cơm chiên quảng đông', 'Rất ngon và thơm phù hợp với người Việt Nam!');
INSERT INTO [ChiTietMonAnDaNgonNgu] ([MaMonAn], [MaNgonNgu], [TenMonAn], [MoTaMonAn]) VALUES (3, 2, 'Quang dong rice', 'Very good');
INSERT INTO [ChiTietMonAnDaNgonNgu] ([MaMonAn], [MaNgonNgu], [TenMonAn], [MoTaMonAn]) VALUES (4, 1, 'Cơm chiên bắc kinh', 'Rất ngon và mùi vị rất hấp dẫn!');
INSERT INTO [ChiTietMonAnDaNgonNgu] ([MaMonAn], [MaNgonNgu], [TenMonAn], [MoTaMonAn]) VALUES (4, 2, 'Beijing rice', 'Very good');
INSERT INTO [ChiTietMonAnDaNgonNgu] ([MaMonAn], [MaNgonNgu], [TenMonAn], [MoTaMonAn]) VALUES (5, 1, 'Cơm gà xối mỡ', 'Món ăn bình dân với giá cả phải chăng');
INSERT INTO [ChiTietMonAnDaNgonNgu] ([MaMonAn], [MaNgonNgu], [TenMonAn], [MoTaMonAn]) VALUES (5, 2, 'Ga xoi mo rice', 'Very good, very good ^^');
INSERT INTO [ChiTietMonAnDaNgonNgu] ([MaMonAn], [MaNgonNgu], [TenMonAn], [MoTaMonAn]) VALUES (6, 1, 'Cơm thịt kho', 'Giá rẻ, hương vị thơm ngon');
INSERT INTO [ChiTietMonAnDaNgonNgu] ([MaMonAn], [MaNgonNgu], [TenMonAn], [MoTaMonAn]) VALUES (6, 2, 'Thit kho rice', 'Very good, very good ^^');
INSERT INTO [ChiTietMonAnDaNgonNgu] ([MaMonAn], [MaNgonNgu], [TenMonAn], [MoTaMonAn]) VALUES (7, 1, 'Nước coca', 'Hương vị ngon!');
INSERT INTO [ChiTietMonAnDaNgonNgu] ([MaMonAn], [MaNgonNgu], [TenMonAn], [MoTaMonAn]) VALUES (7, 2, 'Coca drink', 'Very good');
INSERT INTO [ChiTietMonAnDaNgonNgu] ([MaMonAn], [MaNgonNgu], [TenMonAn], [MoTaMonAn]) VALUES (8, 1, 'Nước coca diet', 'Ít năng lượng phù hợp với người giảm cân');
INSERT INTO [ChiTietMonAnDaNgonNgu] ([MaMonAn], [MaNgonNgu], [TenMonAn], [MoTaMonAn]) VALUES (8, 2, 'Coca drink', 'Very good');
INSERT INTO [ChiTietMonAnDaNgonNgu] ([MaMonAn], [MaNgonNgu], [TenMonAn], [MoTaMonAn]) VALUES (9, 1, 'Nước coca lon', 'Tiên lợi, hương vị tuyệt vời!');
INSERT INTO [ChiTietMonAnDaNgonNgu] ([MaMonAn], [MaNgonNgu], [TenMonAn], [MoTaMonAn]) VALUES (9, 2, 'Coca drink', 'Very good');
INSERT INTO [ChiTietMonAnDaNgonNgu] ([MaMonAn], [MaNgonNgu], [TenMonAn], [MoTaMonAn]) VALUES (10, 1, 'Nước pepsi', 'Ngon lắm');
INSERT INTO [ChiTietMonAnDaNgonNgu] ([MaMonAn], [MaNgonNgu], [TenMonAn], [MoTaMonAn]) VALUES (10, 2, 'Pepsi drink', 'Very good');
INSERT INTO [ChiTietMonAnDaNgonNgu] ([MaMonAn], [MaNgonNgu], [TenMonAn], [MoTaMonAn]) VALUES (11, 1, 'Nước pepsi lon', 'Ngon, dễ dùng, hương vị mới!');
INSERT INTO [ChiTietMonAnDaNgonNgu] ([MaMonAn], [MaNgonNgu], [TenMonAn], [MoTaMonAn]) VALUES (11, 2, 'Pepsi drink', 'Very good');
INSERT INTO [ChiTietMonAnDaNgonNgu] ([MaMonAn], [MaNgonNgu], [TenMonAn], [MoTaMonAn]) VALUES (12, 1, 'Nước pepsi chanh', 'Thơm vị tự nhiên!');
INSERT INTO [ChiTietMonAnDaNgonNgu] ([MaMonAn], [MaNgonNgu], [TenMonAn], [MoTaMonAn]) VALUES (12, 2, 'Pepsi drink', 'Very good');


-- Table: ChiTietMonLienQuan
INSERT INTO [ChiTietMonLienQuan] ([MaMonAn], [MaMonAnLienQuan]) VALUES (1, 2);
INSERT INTO [ChiTietMonLienQuan] ([MaMonAn], [MaMonAnLienQuan]) VALUES (1, 3);
INSERT INTO [ChiTietMonLienQuan] ([MaMonAn], [MaMonAnLienQuan]) VALUES (1, 4);
INSERT INTO [ChiTietMonLienQuan] ([MaMonAn], [MaMonAnLienQuan]) VALUES (2, 1);
INSERT INTO [ChiTietMonLienQuan] ([MaMonAn], [MaMonAnLienQuan]) VALUES (2, 3);
INSERT INTO [ChiTietMonLienQuan] ([MaMonAn], [MaMonAnLienQuan]) VALUES (2, 5);
INSERT INTO [ChiTietMonLienQuan] ([MaMonAn], [MaMonAnLienQuan]) VALUES (3, 1);
INSERT INTO [ChiTietMonLienQuan] ([MaMonAn], [MaMonAnLienQuan]) VALUES (3, 7);
INSERT INTO [ChiTietMonLienQuan] ([MaMonAn], [MaMonAnLienQuan]) VALUES (3, 8);
INSERT INTO [ChiTietMonLienQuan] ([MaMonAn], [MaMonAnLienQuan]) VALUES (4, 1);
INSERT INTO [ChiTietMonLienQuan] ([MaMonAn], [MaMonAnLienQuan]) VALUES (4, 9);
INSERT INTO [ChiTietMonLienQuan] ([MaMonAn], [MaMonAnLienQuan]) VALUES (4, 10);
INSERT INTO [ChiTietMonLienQuan] ([MaMonAn], [MaMonAnLienQuan]) VALUES (5, 8);
INSERT INTO [ChiTietMonLienQuan] ([MaMonAn], [MaMonAnLienQuan]) VALUES (5, 11);
INSERT INTO [ChiTietMonLienQuan] ([MaMonAn], [MaMonAnLienQuan]) VALUES (6, 11);
INSERT INTO [ChiTietMonLienQuan] ([MaMonAn], [MaMonAnLienQuan]) VALUES (6, 12);
INSERT INTO [ChiTietMonLienQuan] ([MaMonAn], [MaMonAnLienQuan]) VALUES (7, 2);
INSERT INTO [ChiTietMonLienQuan] ([MaMonAn], [MaMonAnLienQuan]) VALUES (7, 9);
INSERT INTO [ChiTietMonLienQuan] ([MaMonAn], [MaMonAnLienQuan]) VALUES (8, 3);
INSERT INTO [ChiTietMonLienQuan] ([MaMonAn], [MaMonAnLienQuan]) VALUES (8, 4);
INSERT INTO [ChiTietMonLienQuan] ([MaMonAn], [MaMonAnLienQuan]) VALUES (9, 4);
INSERT INTO [ChiTietMonLienQuan] ([MaMonAn], [MaMonAnLienQuan]) VALUES (10, 2);
INSERT INTO [ChiTietMonLienQuan] ([MaMonAn], [MaMonAnLienQuan]) VALUES (11, 8);
INSERT INTO [ChiTietMonLienQuan] ([MaMonAn], [MaMonAnLienQuan]) VALUES (12, 6);



-- Table: NhomTaiKhoan
INSERT INTO [NhomTaiKhoan] ([MaNhomTaiKhoan], [TenNhom], [MoTaNhom]) VALUES (1, 'Admin', 'Nhom cap quyen cac user khac');
INSERT INTO [NhomTaiKhoan] ([MaNhomTaiKhoan], [TenNhom], [MoTaNhom]) VALUES (2, 'Manager', 'Quan ly server');
INSERT INTO [NhomTaiKhoan] ([MaNhomTaiKhoan], [TenNhom], [MoTaNhom]) VALUES (3, 'Waitor', 'Tai khoan phuc vu dung mobile');
INSERT INTO [NhomTaiKhoan] ([MaNhomTaiKhoan], [TenNhom], [MoTaNhom]) VALUES (4, 'Producer', 'Tai khoan dung cho mot bo phan che bien');


-- Table: TaiKhoan
INSERT INTO [TaiKhoan] ([MaTaiKhoan], [TenTaiKhoan], [MatKhau], [HoTen], [NgaySinh], [GioiTinh], [CMND], [Avatar], [Active], [MaNhomTaiKhoan]) VALUES (1, 'superadmin', 'gnzLDuqKcGxMNKFokfhOew==', 'Trần Minh Khánh', '1990-12-12', 0, 363600000, 'Images/superadmin.jpg', 1, 1);
INSERT INTO [TaiKhoan] ([MaTaiKhoan], [TenTaiKhoan], [MatKhau], [HoTen], [NgaySinh], [GioiTinh], [CMND], [Avatar], [Active], [MaNhomTaiKhoan]) VALUES (2, 'supermanager', 'gnzLDuqKcGxMNKFokfhOew==', 'Trần Mnh Khánh', '1990-12-12', 1, 363600000, 'Images/supermanager.jpg', 1, 2);
INSERT INTO [TaiKhoan] ([MaTaiKhoan], [TenTaiKhoan], [MatKhau], [HoTen], [NgaySinh], [GioiTinh], [CMND], [Avatar], [Active], [MaNhomTaiKhoan]) VALUES (3, 'waitor1', 'gnzLDuqKcGxMNKFokfhOew==', 'Trần Minh Kánh', '1990-12-12', 0, 363600000, 'Images/waitor1.jpg', 1, 3);
INSERT INTO [TaiKhoan] ([MaTaiKhoan], [TenTaiKhoan], [MatKhau], [HoTen], [NgaySinh], [GioiTinh], [CMND], [Avatar], [Active], [MaNhomTaiKhoan]) VALUES (4, 'waitor2', 'gnzLDuqKcGxMNKFokfhOew==', 'Trần Minh Kháh', '1990-12-12', 1, 363600000, 'Images/waitor2.jpg', 1, 3);
INSERT INTO [TaiKhoan] ([MaTaiKhoan], [TenTaiKhoan], [MatKhau], [HoTen], [NgaySinh], [GioiTinh], [CMND], [Avatar], [Active], [MaNhomTaiKhoan]) VALUES (5, 'kitchen', 'gnzLDuqKcGxMNKFokfhOew==', 'Trần Minh Khá', '1990-12-12', 0, 363600000, 'Images/kitchen.jpg', 1, 4);
INSERT INTO [TaiKhoan] ([MaTaiKhoan], [TenTaiKhoan], [MatKhau], [HoTen], [NgaySinh], [GioiTinh], [CMND], [Avatar], [Active], [MaNhomTaiKhoan]) VALUES (6, 'drinkstand', 'gnzLDuqKcGxMNKFokfhOew==', 'Trần Minh Khán', '1990-12-12', 1, 363600000, 'Images/drinkstand.jpg', 1, 4);


-- Table: TiGia
INSERT INTO [TiGia] ([MaTiGia], [KiHieu], [GiaTri], [ThoiDiemCapNhat]) VALUES (1, 'VND', '0.0', '2012-12-04');
INSERT INTO [TiGia] ([MaTiGia], [KiHieu], [GiaTri], [ThoiDiemCapNhat]) VALUES (2, 'USD', '20000.0', '2012-12-04');
INSERT INTO [TiGia] ([MaTiGia], [KiHieu], [GiaTri], [ThoiDiemCapNhat]) VALUES (3, 'AUD', '15000.0', '2012-12-04');


-- Table: KhuyenMai
INSERT INTO [KhuyenMai] ([_id], [MaKhuyenMai], [TenKhuyenMai], [MoTaKhuyenMai], [GiaGiam], [TiLeGiam], [BatDau], [KetThuc], [LichKhuyenMai]) VALUES (1, 1, 'Khuyến mãi món 1', null, '10.0', '0.0', 0, 1, null);
INSERT INTO [KhuyenMai] ([_id], [MaKhuyenMai], [TenKhuyenMai], [MoTaKhuyenMai], [GiaGiam], [TiLeGiam], [BatDau], [KetThuc], [LichKhuyenMai]) VALUES (2, 2, 'Khuyến mãi món 2', null, '10.0', '0.0', 2, 3, null);


-- Table: KhuyenMaiMon
INSERT INTO [KhuyenMaiMon] ([_id], [MaKhuyenMai], [MaMonAn]) VALUES (1, 1, 7);
INSERT INTO [KhuyenMaiMon] ([_id], [MaKhuyenMai], [MaMonAn]) VALUES (2, 2, 7);
*/