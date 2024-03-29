use ThucDonDienTu
go

-- Khu Vuc -----------------------------------------------------------------------------------------------
insert into KhuVuc values (N'Lầu 1', N'Khu vực ở lầu 1')
insert into KhuVuc values (N'Lầu 2', N'Khu vực ở lầu 2')
insert into KhuVuc values (N'Lầu 3', N'Khu vực ở lầu 3')


-- Ban -----------------------------------------------------------------------------------------------
insert into Ban values (1, N'Bàn 1', N'Ghi chú', 1, 1, NULL)
insert into Ban values (1, N'Bàn 2', N'Ghi chú', 1, 1, NULL)
insert into Ban values (1, N'Bàn 3', N'Ghi chú', 1, 1, NULL)
insert into Ban values (2, N'Bàn 4', N'Ghi chú', 1, 1, NULL)
insert into Ban values (2, N'Bàn 5', N'Ghi chú', 1, 1, NULL)
insert into Ban values (3, N'Bàn 6', N'Ghi chú', 1, 1, NULL)


-- Ngon Ngu -----------------------------------------------------------------------------------------------
insert into NgonNgu values (N'Tieng Viet')
insert into NgonNgu values (N'Tieng Anh')


-- Danh Muc -----------------------------------------------------------------------------------------------
insert into DanhMuc values(NULL)
insert into DanhMuc values(NULL)
insert into DanhMuc values(NULL)
insert into DanhMuc values(NULL)
insert into DanhMuc values(NULL)
insert into DanhMuc values(NULL)

-- Don Vi Tinh -----------------------------------------------------------------------------------------------
insert into DonViTinh DEFAULT VALUES
insert into DonViTinh DEFAULT VALUES
insert into DonViTinh DEFAULT VALUES


-- Chi Tiet Don Vi Tinh Da Ngon Ngu ---------------------------------------------------------------------------------
insert into ChiTietDonViTinhDaNgonNgu values (1, 1, N'Chai')
insert into ChiTietDonViTinhDaNgonNgu values (1, 2, N'Bottle')
insert into ChiTietDonViTinhDaNgonNgu values (2, 1, N'Thung')
insert into ChiTietDonViTinhDaNgonNgu values (2, 2, N'Box')
insert into ChiTietDonViTinhDaNgonNgu values (3, 1, N'Dia')
insert into ChiTietDonViTinhDaNgonNgu values (3, 2, N'Disk')


-- Chi Tiet Danh Muc Da Ngon Ngu -----------------------------------------------------------------------------------------------
insert into ChiTietDanhMucDaNgonNgu values (1, 1, N'Com', N'Mon an lam tu gao')
insert into ChiTietDanhMucDaNgonNgu values (1, 2, N'Rice', N'Made from rice')
insert into ChiTietDanhMucDaNgonNgu values (2, 1, N'Nuoc', N'Do uong')
insert into ChiTietDanhMucDaNgonNgu values (2, 2, N'Drink', N'Relief thirst')
insert into ChiTietDanhMucDaNgonNgu values (3, 1, N'Com thap cam', N'Com co rau thit')
insert into ChiTietDanhMucDaNgonNgu values (3, 2, N'Paella', N'Rice with vegetable and meat')
insert into ChiTietDanhMucDaNgonNgu values (4, 1, N'Com thuong', N'Com binh dan')
insert into ChiTietDanhMucDaNgonNgu values (4, 2, N'Normal rice', N'Very normal rice')
insert into ChiTietDanhMucDaNgonNgu values (5, 1, N'Nuoc Coca', N'Nuoc ngot cocacola')
insert into ChiTietDanhMucDaNgonNgu values (5, 2, N'Coca', N'This is coca')
insert into ChiTietDanhMucDaNgonNgu values (6, 1, N'Nuoc Pepsi', N'Nuoc ngot pepsi')
insert into ChiTietDanhMucDaNgonNgu values (6, 2, N'Pepsi', N'This is pepsi')


-- Mon An -----------------------------------------------------------------------------------------------
insert into MonAn values (N'/Images/Com_chien_duong_chau.jpg', 10, 15, 1, 3, 0)
insert into MonAn values (N'/Images/Com_chien_tu_chau.jpg', 10, 30, 1, 3, 0)
insert into MonAn values (N'/Images/Com_chien_quang_dong.jpg', 5, 20, 2, 3, 0)
insert into MonAn values (N'/Images/Com_chien_bac_kinh.jpg', 8, 20, 2, 3, 0)

insert into MonAn values (N'/Images/Com ga xoi mo.jpg', 9, 20, 1, 4, 0)
insert into MonAn values (N'/Images/Com thit kho.jpg', 5, 20, 1, 4, 0)

insert into MonAn values (N'/Images/Coca da.jpg', 9, 20, 1, 5, 0)
insert into MonAn values (N'/Images/Coca uop lanh.jpg', 7, 20, 1, 5, 0)
insert into MonAn values (N'/Images/Coca chanh.jpg', 5, 20, 1, 5, 0)

insert into MonAn values (N'/Images/Pepsi da.jpg', 9, 20, 1, 6, 0)
insert into MonAn values (N'/Images/Pepsi uop lanh.jpg', 7, 20, 1, 6, 0)
insert into MonAn values (N'/Images/Pepsi chanh.jpg', 5, 20, 1, 6, 0)


-- Chi Tiet Mon An Da Ngon Ngu -----------------------------------------------------------------------------------------------
insert into ChiTietMonAnDaNgonNgu values (1, 1, N'Com chien duong chau', N'Duong chau noi tieng vi mon com nay')
insert into ChiTietMonAnDaNgonNgu values (1, 2, N'Yangzhou fried rice', N'Yangzhou is famous for this rice')

insert into ChiTietMonAnDaNgonNgu values (2, 1, N'Com chien tu chau', N'Rat ngon')
insert into ChiTietMonAnDaNgonNgu values (2, 2, N'Tu chau rice', N'Very good')

insert into ChiTietMonAnDaNgonNgu values (3, 1, N'Com chien quang dong', N'Rat ngon')
insert into ChiTietMonAnDaNgonNgu values (3, 2, N'Quang dong rice', N'Very good')

insert into ChiTietMonAnDaNgonNgu values (4, 1, N'Com chien bac kinh', N'Rat ngon')
insert into ChiTietMonAnDaNgonNgu values (4, 2, N'Beijing rice', N'Very good')

insert into ChiTietMonAnDaNgonNgu values (5, 1, N'Com ga xoi mo', N'Mon an binh dan rat ngon')
insert into ChiTietMonAnDaNgonNgu values (5, 2, N'Ga xoi mo rice', N'Very good, very good ^^')

insert into ChiTietMonAnDaNgonNgu values (6, 1, N'Com thit kho', N'Mon an binh dan rat ngon')
insert into ChiTietMonAnDaNgonNgu values (6, 2, N'Thit kho rice', N'Very good, very good ^^')

insert into ChiTietMonAnDaNgonNgu values (7, 1, N'Nuoc coca', N'Ngon lam')
insert into ChiTietMonAnDaNgonNgu values (7, 2, N'Coca drink', N'Very good')

insert into ChiTietMonAnDaNgonNgu values (8, 1, N'Nuoc coca', N'Ngon lam')
insert into ChiTietMonAnDaNgonNgu values (8, 2, N'Coca drink', N'Very good')

insert into ChiTietMonAnDaNgonNgu values (9, 1, N'Nuoc coca', N'Ngon lam')
insert into ChiTietMonAnDaNgonNgu values (9, 2, N'Coca drink', N'Very good')

insert into ChiTietMonAnDaNgonNgu values (10, 1, N'Nuoc pepsi', N'Ngon lam')
insert into ChiTietMonAnDaNgonNgu values (10, 2, N'Pepsi drink', N'Very good')

insert into ChiTietMonAnDaNgonNgu values (11, 1, N'Nuoc pepsi', N'Ngon lam')
insert into ChiTietMonAnDaNgonNgu values (11, 2, N'Pepsi drink', N'Very good')

insert into ChiTietMonAnDaNgonNgu values (12, 1, N'Nuoc pepsi', N'Ngon lam')
insert into ChiTietMonAnDaNgonNgu values (12, 2, N'Pepsi drink', N'Very good')


-- Ti Gia -----------------------------------------------------------------------------------------------
insert into TiGia values (N'USD', 20000, N'12/4/2012')
insert into TiGia values (N'AUD', 15000, N'12/4/2012')

-- Chi Tiet Mon Lien Quan -----------------------------------------------------------------------------------------------
insert into ChiTietMonLienQuan values (1, 2)
insert into ChiTietMonLienQuan values (1, 3)
insert into ChiTietMonLienQuan values (1, 4)

insert into ChiTietMonLienQuan values (2, 1)
insert into ChiTietMonLienQuan values (2, 3)
insert into ChiTietMonLienQuan values (2, 5)

insert into ChiTietMonLienQuan values (3, 1)
insert into ChiTietMonLienQuan values (3, 7)
insert into ChiTietMonLienQuan values (3, 8)

insert into ChiTietMonLienQuan values (4, 1)
insert into ChiTietMonLienQuan values (4, 9)
insert into ChiTietMonLienQuan values (4, 10)

insert into ChiTietMonLienQuan values (5, 8)
insert into ChiTietMonLienQuan values (5, 11)

insert into ChiTietMonLienQuan values (6, 11)
insert into ChiTietMonLienQuan values (6, 12)

insert into ChiTietMonLienQuan values (7, 9)
insert into ChiTietMonLienQuan values (7, 2)

insert into ChiTietMonLienQuan values (8, 3)
insert into ChiTietMonLienQuan values (8, 4)

insert into ChiTietMonLienQuan values (9, 4)

insert into ChiTietMonLienQuan values (10, 2)

insert into ChiTietMonLienQuan values (11, 8)

insert into ChiTietMonLienQuan values (12, 6)

-- Nhom Tai Khoan -----------------------------------------------------------------------------------------------
insert into NhomTaiKhoan values (N'Admin', N'Nhom cap quyen cac user khac')
insert into NhomTaiKhoan values (N'Manager', N'Quan ly server')
insert into NhomTaiKhoan values (N'Waitor', N'Tai khoan phuc vu dung mobile')
insert into NhomTaiKhoan values (N'Producer', N'Tai khoan dung cho mot bo phan che bien')

-- Tai Khoan -----------------------------------------------------------------------------------------------------
insert into TaiKhoan values (N'superadmin', N'12345', N'Thong tin dinh danh admin',  N'Images/superadmin.jpg', 1, 1)
insert into TaiKhoan values (N'supermanager', N'12345', N'Thong tin dinh danh manager',  N'Images/supermanager.jpg', 1, 2)
insert into TaiKhoan values (N'waitor1', N'12345', N'Thong tin dinh danh waitor 1',  N'Images/waitor1.jpg', 1, 3)
insert into TaiKhoan values (N'waitor2', N'12345', N'Thong tin dinh danh waitor 2',  N'Images/waitor2.jpg', 1, 3)
insert into TaiKhoan values (N'kitchen', N'12345', N'Thong tin dinh danh kitchen',  N'Images/kitchen.jpg', 1, 4)
insert into TaiKhoan values (N'drinkstand', N'12345', N'Thong tin dinh danh drinkstand',  N'Images/drinkstand.jpg', 1, 4)

-- Bo Phan Che Bien -----------------------------------------------------------------------------------------------
insert into BoPhanCheBien values (N'Kitchen nha bep', N'Nau mon an duoc', 5)
insert into BoPhanCheBien values (N'Drinkstand pha che', N'Lam mon uong duoc', 6)

insert into ChiTietDanhMucBoPhanCheBien values (1, 1)
insert into ChiTietDanhMucBoPhanCheBien values (2, 1)
insert into ChiTietDanhMucBoPhanCheBien values (3, 1)
insert into ChiTietDanhMucBoPhanCheBien values (4, 1)
insert into ChiTietDanhMucBoPhanCheBien values (5, 2)
insert into ChiTietDanhMucBoPhanCheBien values (6, 2)

-- Khuyen Mai ---------------------------------------------------------------------------------------------
insert into KhuyenMai values (N'Khuyen mai 1', N'Khuyen mai mon com', 10000, 0, N'4/4/2012', N'5/5/2012', N'2,3,4') 
insert into KhuyenMai values (N'Khuyen mai 2', N'Khuyen mai hoa don', 0, 10, N'5/5/2012', N'6/6/2012', N'6,7')
insert into KhuyenMai values (N'Khuyen mai 3', N'Khuyen mai danh muc', 15000, 0, N'7/7/2012', N'8/8/2012', N'5,6') 
insert into KhuyenMai values (N'Khuyen mai 4', N'Khuyen mai khu vuc', 12000, 0, N'8/8/2012', N'9/9/2012', N'2') 

insert into KhuyenMaiMon values (1, 1)
insert into KhuyenMaiMon values (1, 2)
insert into KhuyenMaiMon values (1, 3)
insert into KhuyenMaiMon values (1, 4)

insert into KhuyenMaiDanhMuc values (3, 2)

insert into KhuyenMaiHoaDon values (2, 100000)

insert into KhuyenMaiKhuVuc values (4, 1)


-- Phu Thu -----------------------------------------------------------------------------------------------
insert into PhuThu values (N'Phu Thu 1', N'Phu thu khu vuc 1', 5000, 0, N'3/3/2012', N'4/4/2012', N'4,5')
insert into PhuThu values (N'Phu Thu 2', N'Phu thu khu vuc 2,3', 0, 2, N'3/3/2012', N'5/5/2012', N'4,5')

insert into PhuThuKhuVuc values (1, 1)
insert into PhuThuKhuVuc values (2, 2)
insert into PhuThuKhuVuc values (2, 3)


-- Order -----------------------------------------------------------------------------------------------
insert into [ThucDonDienTu].[dbo].[Order] values (3, 1)
insert into [ThucDonDienTu].[dbo].[Order] values (4, 2)

insert into ChiTietOrder values (1, 2, 1, N'Khach goi 2 dia com duong chau o ban 1', 1)
insert into ChiTietOrder values (2, 3, 2, N'Khach goi 3 dia com tu chau o ban 1', 2)


-- Chi Tiet Huy Order



-- Hoa Don -----------------------------------------------------------------------------------------------
insert into HoaDon values (N'4/4/2012', 3, 10000, 1, N'4,5', 1)
insert into HoaDon values (N'4/4/2012', 4, 10000, 2, N'6', 2)

insert into ChiTietHoaDon values (1, 2, 10000, 1, 5000, 5000, 1)
insert into ChiTietHoaDon values (2, 3, 15000, 2, 10000, 5000, 2)
