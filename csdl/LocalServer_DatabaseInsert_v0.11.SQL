use ThucDonDienTu
go

-- Khu Vuc -----------------------------------------------------------------------------------------------
insert into KhuVuc values (N'Lầu 1', N'Khu vực ở lầu 1')
insert into KhuVuc values (N'Lầu 2', N'Khu vực ở lầu 2')
insert into KhuVuc values (N'Lầu 3', N'Khu vực ở lầu 3')


-- Tham So -----------------------------------------------------------------------------------------------
insert into ThamSo values (N'BillPrinter', N'')
insert into ThamSo values (N'MaThamSoMacDinh', N'1')
insert into ThamSo values (N'TenNhaHang', N'Nha hang sieu nhan')
insert into ThamSo values (N'MoTaNhaHang', N'Chuyen ban cac mon thuong hang')
insert into ThamSo values (N'TelNhaHang', N'0939133994')
insert into ThamSo values (N'FaxNhaHang', N'115113114')
insert into ThamSo values (N'DiaChiNhaHang', N'Thanh pho HCM')
insert into ThamSo values (N'LogoNhaHang', N' ')


-- Ban -----------------------------------------------------------------------------------------------
insert into Ban values (1, N'Bàn 1', N'Ghi chú', 1, 1, NULL)
insert into Ban values (1, N'Bàn 2', N'Ghi chú', 1, 1, NULL)
insert into Ban values (1, N'Bàn 3', N'Ghi chú', 1, 1, NULL)
insert into Ban values (2, N'Bàn 4', N'Ghi chú', 1, 1, NULL)
insert into Ban values (2, N'Bàn 5', N'Ghi chú', 1, 1, NULL)
insert into Ban values (3, N'Bàn 6', N'Ghi chú', 1, 1, NULL)


-- Ngon Ngu -----------------------------------------------------------------------------------------------
insert into NgonNgu values (N'Tiếng Việt', N'vi')
insert into NgonNgu values (N'English', N'en')


-- Danh Muc -----------------------------------------------------------------------------------------------
insert into DanhMuc values(NULL)
insert into DanhMuc values(1)
insert into DanhMuc values(1)
insert into DanhMuc values(2)
insert into DanhMuc values(2)
insert into DanhMuc values(3)
insert into DanhMuc values(3)

-- Don Vi Tinh -----------------------------------------------------------------------------------------------
insert into DonViTinh DEFAULT VALUES
insert into DonViTinh DEFAULT VALUES
insert into DonViTinh DEFAULT VALUES


-- Chi Tiet Don Vi Tinh Da Ngon Ngu ---------------------------------------------------------------------------------
insert into ChiTietDonViTinhDaNgonNgu values (1, 1, N'Chai')
insert into ChiTietDonViTinhDaNgonNgu values (1, 2, N'Bottle')
insert into ChiTietDonViTinhDaNgonNgu values (2, 1, N'Thùng')
insert into ChiTietDonViTinhDaNgonNgu values (2, 2, N'Box')
insert into ChiTietDonViTinhDaNgonNgu values (3, 1, N'Dĩa')
insert into ChiTietDonViTinhDaNgonNgu values (3, 2, N'Disk')


-- Chi Tiet Danh Muc Da Ngon Ngu -----------------------------------------------------------------------------------------------
insert into ChiTietDanhMucDaNgonNgu values (1, 1, N'Không có', N'')
insert into ChiTietDanhMucDaNgonNgu values (1, 2, N'None', N'')
insert into ChiTietDanhMucDaNgonNgu values (2, 1, N'Cơm', N'Dương châu nổi tiếng vì món cơm này!')
insert into ChiTietDanhMucDaNgonNgu values (2, 2, N'Rice', N'Made from rice')
insert into ChiTietDanhMucDaNgonNgu values (3, 1, N'Nước', N'Đồ uống')
insert into ChiTietDanhMucDaNgonNgu values (3, 2, N'Drink', N'Relief thirst')
insert into ChiTietDanhMucDaNgonNgu values (4, 1, N'Cơm thập cẩm', N'Cơm có rau thịt')
insert into ChiTietDanhMucDaNgonNgu values (4, 2, N'Paella', N'Rice with vegetable and meat')
insert into ChiTietDanhMucDaNgonNgu values (5, 1, N'Cơm thường', N'Cơm bình dân')
insert into ChiTietDanhMucDaNgonNgu values (5, 2, N'Normal rice', N'Very normal rice')
insert into ChiTietDanhMucDaNgonNgu values (6, 1, N'Nước Coca', N'Nước ngọt cocacola')
insert into ChiTietDanhMucDaNgonNgu values (6, 2, N'Coca', N'This is coca')
insert into ChiTietDanhMucDaNgonNgu values (7, 1, N'Nước Pepsi', N'Nước ngọt pepsi')
insert into ChiTietDanhMucDaNgonNgu values (7, 2, N'Pepsi', N'This is pepsi')


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

-- Chi Tiet Mon An Don Vi Tinh -----------------------------------------------------------------------------------------------
insert into ChiTietMonAnDonViTinh values (1, 1, 100)
insert into ChiTietMonAnDonViTinh values (1, 2, 200)
insert into ChiTietMonAnDonViTinh values (1, 3, 300)
insert into ChiTietMonAnDonViTinh values (2, 1, 100)
insert into ChiTietMonAnDonViTinh values (2, 2, 200)
insert into ChiTietMonAnDonViTinh values (2, 3, 400)
insert into ChiTietMonAnDonViTinh values (3, 1, 100)
insert into ChiTietMonAnDonViTinh values (4, 1, 100)
insert into ChiTietMonAnDonViTinh values (4, 2, 500)
insert into ChiTietMonAnDonViTinh values (5, 1, 100)
insert into ChiTietMonAnDonViTinh values (5, 2, 200)
insert into ChiTietMonAnDonViTinh values (6, 1, 100)
insert into ChiTietMonAnDonViTinh values (7, 1, 100)
insert into ChiTietMonAnDonViTinh values (8, 1, 100)
insert into ChiTietMonAnDonViTinh values (9, 1, 100)
insert into ChiTietMonAnDonViTinh values (10, 1, 100)
insert into ChiTietMonAnDonViTinh values (11, 1, 100)
insert into ChiTietMonAnDonViTinh values (12, 1, 100)
insert into ChiTietMonAnDonViTinh values (12, 2, 400)



-- Chi Tiet Mon An Da Ngon Ngu -----------------------------------------------------------------------------------------------
insert into ChiTietMonAnDaNgonNgu values (1, 1, N'Cơm chiên dương châu', N'Món ăn làm từ gạo tứ xuyên rất ngon và bổ dưỡng. Cung cấp các chất dinh dưỡng cần thiết cho cơ thể con người. Được chế biến công phụ với nhiều hương vị lạ!')
insert into ChiTietMonAnDaNgonNgu values (1, 2, N'Yangzhou fried rice', N'Yangzhou is famous for this rice')

insert into ChiTietMonAnDaNgonNgu values (2, 1, N'Cơm chiên tứ châu', N'Rất ngon và thơm phù hợp với người Việt Nam!')
insert into ChiTietMonAnDaNgonNgu values (2, 2, N'Tu chau rice', N'Very good')

insert into ChiTietMonAnDaNgonNgu values (3, 1, N'Cơm chiên quảng đông', N'Rất ngon và thơm phù hợp với người Việt Nam!')
insert into ChiTietMonAnDaNgonNgu values (3, 2, N'Quang dong rice', N'Very good')

insert into ChiTietMonAnDaNgonNgu values (4, 1, N'Cơm chiên bắc kinh', N'Rất ngon và mùi vị rất hấp dẫn!')
insert into ChiTietMonAnDaNgonNgu values (4, 2, N'Beijing rice', N'Very good')

insert into ChiTietMonAnDaNgonNgu values (5, 1, N'Cơm gà xối mỡ', N'Món ăn bình dân với giá cả phải chăng')
insert into ChiTietMonAnDaNgonNgu values (5, 2, N'Ga xoi mo rice', N'Very good, very good ^^')

insert into ChiTietMonAnDaNgonNgu values (6, 1, N'Cơm thịt kho', N'Giá rẻ, hương vị thơm ngon')
insert into ChiTietMonAnDaNgonNgu values (6, 2, N'Thit kho rice', N'Very good, very good ^^')

insert into ChiTietMonAnDaNgonNgu values (7, 1, N'Nước coca', N'Hương vị ngon!')
insert into ChiTietMonAnDaNgonNgu values (7, 2, N'Coca drink', N'Very good')

insert into ChiTietMonAnDaNgonNgu values (8, 1, N'Nước coca diet', N'Ít năng lượng phù hợp với người giảm cân')
insert into ChiTietMonAnDaNgonNgu values (8, 2, N'Coca drink', N'Very good')

insert into ChiTietMonAnDaNgonNgu values (9, 1, N'Nước coca lon', N'Tiên lợi, hương vị tuyệt vời!')
insert into ChiTietMonAnDaNgonNgu values (9, 2, N'Coca drink', N'Very good')

insert into ChiTietMonAnDaNgonNgu values (10, 1, N'Nước pepsi', N'Ngon lắm')
insert into ChiTietMonAnDaNgonNgu values (10, 2, N'Pepsi drink', N'Very good')

insert into ChiTietMonAnDaNgonNgu values (11, 1, N'Nước pepsi lon', N'Ngon, dễ dùng, hương vị mới!')
insert into ChiTietMonAnDaNgonNgu values (11, 2, N'Pepsi drink', N'Very good')

insert into ChiTietMonAnDaNgonNgu values (12, 1, N'Nước pepsi chanh', N'Thơm vị tự nhiên!')
insert into ChiTietMonAnDaNgonNgu values (12, 2, N'Pepsi drink', N'Very good')


-- Ti Gia -----------------------------------------------------------------------------------------------
insert into TiGia values (N'VND', 0, N'12/4/2012')
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
insert into TaiKhoan values (N'superadmin', N'gnzLDuqKcGxMNKFokfhOew==', N'Trần Minh Khánh', N'12/12/1990', 0, N'363600000',  N'Images/superadmin.jpg', 1, 1)
insert into TaiKhoan values (N'supermanager', N'gnzLDuqKcGxMNKFokfhOew==', N'Trần Mnh Khánh', N'12/12/1990', 1, N'363600000',  N'Images/supermanager.jpg', 1, 2)
insert into TaiKhoan values (N'waitor1', N'gnzLDuqKcGxMNKFokfhOew==', N'Trần Minh Kánh', N'12/12/1990', 0, N'363600000',  N'Images/waitor1.jpg', 1, 3)
insert into TaiKhoan values (N'waitor2', N'gnzLDuqKcGxMNKFokfhOew==', N'Trần Minh Kháh', N'12/12/1990', 1, N'363600000',  N'Images/waitor2.jpg', 1, 3)
insert into TaiKhoan values (N'kitchen', N'gnzLDuqKcGxMNKFokfhOew==', N'Trần Minh Khá', N'12/12/1990', 0, N'363600000',  N'Images/kitchen.jpg', 1, 4)
insert into TaiKhoan values (N'drinkstand', N'gnzLDuqKcGxMNKFokfhOew==', N'Trần Minh Khán', N'12/12/1990', 1, N'363600000',  N'Images/drinkstand.jpg', 1, 4)

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
insert into [ThucDonDienTu].[dbo].[Order] values (3, 1, 0)
insert into [ThucDonDienTu].[dbo].[Order] values (4, 2, 0)

insert into ChiTietOrder values (1, 7, 1, 1, N'Khach goi 7 dia com duong chau o ban 1', 1, 0)
insert into ChiTietOrder values (2, 8, 2, 1, N'Khach goi 8 dia com tu chau o ban 1', 2, 0)


-- Chi Tiet Huy Order
insert into ChiTietHuyOrder values(1, 5, 3, 1)
insert into ChiTietHuyOrder values(2, 6, 4, 0)

-- Chi Tiet CheBien Order
insert into ChiTietCheBienOrder values(1, 2, 3)
insert into ChiTietCheBienOrder values(2, 5, 3)

-- Hoa Don -----------------------------------------------------------------------------------------------
insert into HoaDon values (N'4/4/2012', 3, 10000, 1, N'4,5', 1)
insert into HoaDon values (N'4/4/2012', 4, 10000, 2, N'6', 2)

insert into ChiTietHoaDon values (1, 2, 10000, 1, 1, 5000, 5000, 1)
insert into ChiTietHoaDon values (2, 3, 15000, 2, 2, 10000, 5000, 2)


-- Voucher -----------------------------------------------------------------------------------------------
insert into Voucher values (N'Voucher 1', N'Giam cho hoa don 50k', 50000, 5000, N'3/3/2012', N'5/5/2012')
insert into Voucher values (N'Voucher 2', N'Giam cho hoa don 100k', 100000, 20000, N'4/4/2012', N'6/6/2012')

-- Chi Tiet Voucher --------------------------------------------------------------------------------------
insert into ChiTietVoucher values (1, N'1ABCDE', 1)
insert into ChiTietVoucher values (1, N'1FGHIJ', 1)
insert into ChiTietVoucher values (1, N'1KLMNO', 1)
insert into ChiTietVoucher values (2, N'2ABBBD', 1)
insert into ChiTietVoucher values (2, N'271DJH', 1)
insert into ChiTietVoucher values (2, N'2DD78S', 1)