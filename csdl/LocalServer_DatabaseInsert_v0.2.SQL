use ThucDonDienTu
go
insert into KhuVuc values (N'Lầu 1', N'Khu vực ở lầu 1')
insert into KhuVuc values (N'Lầu 2', N'Khu vực ở lầu 2')
insert into KhuVuc values (N'Lầu 3', N'Khu vực ở lầu 3')

insert into Ban values (1, N'Bàn 1', N'Ghi chú', 1, 1, NULL)
insert into Ban values (1, N'Bàn 2', N'Ghi chú', 1, 1, NULL)
insert into Ban values (1, N'Bàn 3', N'Ghi chú', 1, 1, NULL)
insert into Ban values (2, N'Bàn 4', N'Ghi chú', 1, 1, NULL)
insert into Ban values (2, N'Bàn 5', N'Ghi chú', 1, 1, NULL)
insert into Ban values (3, N'Bàn 6', N'Ghi chú', 1, 1, NULL)

insert into NgonNgu values (N'Tieng Viet')
insert into NgonNgu values (N'Tieng Anh')

insert into DanhMuc values(NULL)
insert into DanhMuc values(NULL)
insert into DanhMuc values(NULL)
insert into DanhMuc values(NULL)

insert into DonViTinh DEFAULT VALUES
insert into DonViTinh DEFAULT VALUES
insert into DonViTinh DEFAULT VALUES

insert into ChiTietDonViTinhDaNgonNgu values (1, 1, N'Chai')
insert into ChiTietDonViTinhDaNgonNgu values (1, 2, N'Bottle')
insert into ChiTietDonViTinhDaNgonNgu values (2, 1, N'Thung')
insert into ChiTietDonViTinhDaNgonNgu values (2, 2, N'Box')
insert into ChiTietDonViTinhDaNgonNgu values (3, 1, N'Dia')
insert into ChiTietDonViTinhDaNgonNgu values (3, 2, N'Disk')

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

insert into ChiTietMonAnDaNgonNgu values (10, 1, N'Nuoc pepsi', N'Ngon lam')
insert into ChiTietMonAnDaNgonNgu values (10, 2, N'Pepsi drink', N'Very good')

insert into ChiTietMonAnDaNgonNgu values (10, 1, N'Nuoc pepsi', N'Ngon lam')
insert into ChiTietMonAnDaNgonNgu values (10, 2, N'Pepsi drink', N'Very good')