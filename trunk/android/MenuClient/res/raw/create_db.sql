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
    HinhAnh            TEXT,
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
    MaTiGia INTEGER PRIMARY KEY,
    KiHieu  TEXT,
    GiaTri  REAL,
	ThoiDiemCapNhat DATETIME 
);


-- Table: ChiTietMonLienQuan
CREATE TABLE ChiTietMonLienQuan ( 
    MaMonAn         INTEGER REFERENCES MonAn ( MaMonAn ),
    MaMonAnLienQuan INTEGER REFERENCES MonAn ( MaMonAn ),
    PRIMARY KEY ( MaMonAn, MaMonAnLienQuan ) 
);

