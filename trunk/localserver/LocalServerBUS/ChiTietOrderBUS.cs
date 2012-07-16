using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;
using LocalServerDTO;

namespace LocalServerBUS
{
    public class ChiTietOrderBUS
    {
        public static ChiTietOrder LayChiTietOrder(int maChiTietOrder)
        {
            return ChiTietOrderDAO.LayChiTietOrder(maChiTietOrder);
        }

        //public static ChiTietOrder LayChiTietOrderDetail(int maChiTietOrder)
        //{
        //    var ct = ChiTietOrderDAO.LayChiTietOrder(maChiTietOrder);
        //    var cto = ChiTietCheBienOrderBUS.LayChiTietCheBienOrder(maChiTietOrder);
        //    if (ct!=null && cto!=null)
        //    {
        //        ct.SoLuongDaCheBien = cto.SoLuongDaCheBien;
        //        ct.SoLuongDangCheBien = cto.SoLuongDangCheBien;
        //    }
        //    return ct;
        //}

        public static List<ChiTietOrder> LayNhieuChiTietOrder(int maOrder)
        {
            return ChiTietOrderDAO.LayNhieuChiTietOrder(maOrder);
        }

        public static List<ChiTietOrder> LayNhieuChiTietOrderChuaThanhToan(int maBan)
        {
            List<ChiTietOrder> listCTOrder = ChiTietOrderDAO.LayNhieuChiTietOrderChuaThanhToan(maBan);
            if (listCTOrder != null)
            {
                foreach (ChiTietOrder ct in listCTOrder)
                {
                    ChiTietCheBienOrder ctCheBienOrder = ChiTietCheBienOrderBUS.LayChiTietCheBienOrder(ct.MaChiTietOrder);
                    if (ctCheBienOrder != null)
                    {
                        ct.SoLuongDaCheBien = ctCheBienOrder.SoLuongDaCheBien;
                        ct.SoLuongDangCheBien = ctCheBienOrder.SoLuongDangCheBien;
                    }
                }
            }


            return listCTOrder;
        }

        // Kiem tra xem Ban A con mon nao dang che bien hay khong, thong bao cho Khach
        // Khach co the doi, hoac thanh toan luon
        public static bool DuocPhepThanhToan(int maBan)
        {
            List<ChiTietOrder> listCTOrder = LayNhieuChiTietOrderChuaThanhToan(maBan);
            if (listCTOrder == null)
                return false;

            foreach (ChiTietOrder ct in listCTOrder)
            {
                //if (ct.SoLuongDaCheBien != ct.SoLuong || ct.SoLuongDangCheBien > 0 || ct.TinhTrang == 3)
                if (ct.SoLuongDaCheBien != ct.SoLuong)
                    return false;
            }

            return true;
        }

        public static List<ChiTietOrder> LayDanhSachChiTietOrderChuaThanhToan(int maOrder)
        {
            return ChiTietOrderDAO.LayDanhSachChiTietOrderChuaThanhToan(maOrder);
        }

        public static ChiTietOrder ThemChiTietOrder(ChiTietOrder _chiTietOrder)
        {
            return ChiTietOrderDAO.ThemChiTietOrder(_chiTietOrder);
        }

        public static List<ChiTietOrder> ThemNhieuChiTietOrder(List<ChiTietOrder> _listChiTietOrder)
        {
            return ChiTietOrderDAO.ThemNhieuChiTietOrder(_listChiTietOrder);
        }

        public static bool SuaChiTietOrder(ChiTietOrder _chiTietOrder)
        {
            return ChiTietOrderDAO.SuaChiTietOrder(_chiTietOrder);
        }

        // Lay ra cac ct Order chua thanh toan cua Ban
        // Thay doi TinhTrang = 4, tuc la xac nhan da thanh toan
        public static bool ThayDoiTinhTrangDaThanhToan(int maBan)
        {
            bool ketQua = true;

            List<ChiTietOrder> listChiTietOrder = ChiTietOrderBUS.LayNhieuChiTietOrderChuaThanhToan(maBan);


            // Thay doi Tinh Trang cua cac ct Order tuong ung
            foreach (ChiTietOrder ctOrder in listChiTietOrder)
            {
                ctOrder.TinhTrang = 4;
                if (!ChiTietOrderBUS.SuaChiTietOrder(ctOrder))
                {
                    ketQua = false;
                }

                Order order = ctOrder.Order;
                if (order != null && order.TinhTrang != 4)
                {
                    order.TinhTrang = 4;
                    OrderBUS.SuaOrder(order);
                }
            }



            // Tach ban
            //BanBUS.TachBan(maBan);

            return ketQua;

        }

        public static List<ChiTietOrder> LayDanhSachChiTietOrderJson(int maOrder)
        {
            return ChiTietOrderDAO.LayDanhSachChiTietOrderJson(maOrder);
        }

        public static bool SuaChiTietOrderJson(ChiTietOrder holder)
        {
            return ChiTietOrderDAO.SuaChiTietOrderJson(holder);
        }

        //public static int LaySoLuongChuaCheBien(int maChiTiet)
        //{
        //    return ChiTietOrderDAO.LaySoLuongChuaCheBien(maChiTiet);
        //}


        public static ChiTietHuyOrder LaySoLuongChoPhepHuyOrder(int maChiTietOrder)
        {
            int soLuongChoPhepHuy = 0;
            int soLuongDaCheBien = 0;
            int soLuongDangCheBien = 0;

            ChiTietOrder ctOrder = LayChiTietOrder(maChiTietOrder);
            if(ctOrder != null)
                soLuongChoPhepHuy = ctOrder.SoLuong;

            // Chi tiet che bien order
            ChiTietCheBienOrder ctCheBienOrder = ChiTietCheBienOrderBUS.LayChiTietCheBienOrder(maChiTietOrder);
            if (ctCheBienOrder != null)
            {
                soLuongDaCheBien = ctCheBienOrder.SoLuongDaCheBien;
                soLuongDangCheBien = ctCheBienOrder.SoLuongDangCheBien;
            }


            soLuongChoPhepHuy -= soLuongDaCheBien;
            soLuongChoPhepHuy -= soLuongDangCheBien;

            ChiTietHuyOrder ctHuy = new ChiTietHuyOrder();
            ctHuy._maChiTietOrder = maChiTietOrder;
            ctHuy.SoLuongChoPhep = soLuongChoPhepHuy;

            return ctHuy;
        }

        

        public static bool YeuCauHuyOrder(int maChiTietOrder, int soLuongYeuCauHuy, string ghiChu)
        {
            bool ketQua = true;

            int soLuongChoPhepHuy = 0;
            ChiTietHuyOrder ctHuy = LaySoLuongChoPhepHuyOrder(maChiTietOrder);

            soLuongChoPhepHuy = (ctHuy != null) ? ctHuy.SoLuongChoPhep : 0;

            if (soLuongYeuCauHuy > soLuongChoPhepHuy)
                return false;  

            if (ChiTietOrderDAO.CapNhatHuy(maChiTietOrder, soLuongYeuCauHuy, ghiChu))
            {
                ketQua = true;
            }
            else
            {
                ketQua = false;
            }


            return ketQua;
        }

        public static bool KhoaCheBien(int maChiTietOrder)
        {
            int soLuongChuaDungToi = 0;
            ChiTietHuyOrder ctHuy = LaySoLuongChoPhepHuyOrder(maChiTietOrder);

            soLuongChuaDungToi = (ctHuy != null) ? ctHuy.SoLuongChoPhep : 0;
                
            return ChiTietOrderDAO.CapNhatKhoa(maChiTietOrder, soLuongChuaDungToi);

        }

        public static List<ChiTietOrder> ThemNhieuChiTietOrderJson(List<ChiTietOrder> _listChiTietOrder)
        {
            return ChiTietOrderDAO.ThemNhieuChiTietOrderJson(_listChiTietOrder);
        }
    }
}
