﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using LocalServerDTO;

namespace LocalServerWeb
{
    [ServiceContract]
    public interface ILocalService
    {
        [WebInvoke(Method = "GET", UriTemplate = "cong?a={a}&b={b}")]
        [OperationContract]
        int PhepCong(int a, int b);

        [WebInvoke(Method = "GET", UriTemplate = "test")]
        [OperationContract]
        string Test();

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachKhuVuc")]
        [OperationContract]
        List<KhuVuc> LayDanhKhuVuc();

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachBan")]
        [OperationContract]
        List<Ban> LayDanhSachBan();

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachBanChinh")]
        [OperationContract]
        List<Ban> LayDanhSachBanChinh();

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachBanThuocBanChinh?maBanChinh={maBanChinh}")]
        [OperationContract]
        List<Ban> LayDanhSachBanThuocBanChinh(int maBanChinh);

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachBanTheoKhuVuc?maKhuVuc={maKhuVuc}")]
        [OperationContract]
        List<Ban> LayDanhSachBanTheoKhuVuc(int maKhuVuc);


        /// <summary>
        /// Tách bàn đã ghép ra thành các bàn rời nhau
        /// </summary>
        /// <param name="maBan">Mã bàn cha đại diện cho nhóm bàn</param>
        /// <returns>Tách thành công hoặc thất bại.</returns>
        [WebInvoke(Method = "GET", UriTemplate = "tachBan?maBan={maBan}")]
        [OperationContract]
        bool TachBan(int maBan);

        /// <summary>
        /// Ghép bàn lại với nhau
        /// </summary>
        /// <param name="request">Có bàn chính và danh sách các bàn phụ</param>
        /// <returns>Thành công hai ko</returns>
        [WebInvoke(Method = "POST", UriTemplate = "ghepBan")]
        [OperationContract]
        bool GhepBan(RequestGhepBan request);

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachMonAn")]
        [OperationContract]
        List<MonAn> LayDanhSachMonAn();

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachMonAnTheoDanhMuc?maDanhMuc={maDanhMuc}")]
        [OperationContract]
        List<MonAn> LayDanhSachMonAnTheoDanhMuc(int maDanhMuc);

        [WebInvoke(Method = "GET", UriTemplate = "layMonAn?maMonAn={maMonAn}")]
        [OperationContract]
        MonAn LayMonAn(int maMonAn);

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachDanhMuc")]
        [OperationContract]
        List<DanhMuc> LayDanhSachDanhMuc();

        [WebInvoke(Method = "GET", UriTemplate = "layChiTietDanhMucDaNgonNgu?maDanhMuc={maDanhMuc}&maNgonNgu={maNgonNgu}")]
        [OperationContract]
        ChiTietDanhMucDaNgonNgu LayChiTietDanhMucDaNgonNgu(int maDanhMuc, int maNgonNgu);

        [WebInvoke(Method = "GET", UriTemplate = "layChiTietDonViTinhDaNgonNgu?maDonViTinh={maDonViTinh}&maNgonNgu={maNgonNgu}")]
        [OperationContract]
        ChiTietDonViTinhDaNgonNgu LayChiTietDonViTinhDaNgonNgu(int maDonViTinh, int maNgonNgu);

        [WebInvoke(Method = "GET", UriTemplate = "layChiTietMonAnDaNgonNgu?maMonAn={maMonAn}&maNgonNgu={maNgonNgu}")]
        [OperationContract]
        ChiTietMonAnDaNgonNgu LayChiTietMonAnDaNgonNgu(int maMonAn, int maNgonNgu);

        [WebInvoke(Method = "GET", UriTemplate = "layChiTietMonAnDonViTinhDonGia?maMonAn={maMonAn}&maDonViTinh={maDonViTinh}")]
        [OperationContract]
        float LayChiTietMonAnDonViTinhDonGia(int maMonAn, int maDonViTinh);

        // Testing purpose
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachFoo")]
        [OperationContract]
        List<Foo> LayDanhSachFoo();
    }
}