using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerBUS
{
    public class NhaHangBUS
    {
        public static NhaHang LayThongTinNhaHang()
        {
            NhaHang nhaHang = new NhaHang();

            var tsTenNhaHang = ThamSoBUS.LayThamSo("TenNhaHang");
            var tsDiaChiNhaHang = ThamSoBUS.LayThamSo("DiaChiNhaHang");
            var tsMoTaNhaHang = ThamSoBUS.LayThamSo("MoTaNhaHang");
            var tsLogoNhaHang = ThamSoBUS.LayThamSo("LogoNhaHang");
            var tsFaxNhaHang = ThamSoBUS.LayThamSo("FaxNhaHang");
            var tsTelNhaHang = ThamSoBUS.LayThamSo("TelNhaHang");

            nhaHang.Ten = (tsTenNhaHang != null) ? tsTenNhaHang.GiaTri : " ";
            nhaHang.DiaChi = (tsDiaChiNhaHang != null) ? tsDiaChiNhaHang.GiaTri : " ";
            nhaHang.MoTa = (tsMoTaNhaHang != null) ? tsMoTaNhaHang.GiaTri : " ";
            nhaHang.Logo = (tsLogoNhaHang != null) ? tsLogoNhaHang.GiaTri : " ";
            nhaHang.Fax = (tsFaxNhaHang != null) ? tsFaxNhaHang.GiaTri : " ";
            nhaHang.Tel = (tsTelNhaHang != null) ? tsTelNhaHang.GiaTri : " ";

            return nhaHang;
        }
       
    }
}
