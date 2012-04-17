using System;
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

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachChiTietDanhMucDaNgonNgu")]
        [OperationContract]
        List<ChiTietDanhMucDaNgonNgu> LayDanhSachChiTietDanhMucDaNgonNgu();

        [WebInvoke(Method = "GET", UriTemplate = "layChiTietDonViTinhDaNgonNgu?maDonViTinh={maDonViTinh}&maNgonNgu={maNgonNgu}")]
        [OperationContract]
        ChiTietDonViTinhDaNgonNgu LayChiTietDonViTinhDaNgonNgu(int maDonViTinh, int maNgonNgu);

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachChiTietDonViTinhDaNgonNgu")]
        [OperationContract]
        List<ChiTietDonViTinhDaNgonNgu> LayDanhSachChiTietDonViTinhDaNgonNgu();

        [WebInvoke(Method = "GET", UriTemplate = "layChiTietMonAnDaNgonNgu?maMonAn={maMonAn}&maNgonNgu={maNgonNgu}")]
        [OperationContract]
        ChiTietMonAnDaNgonNgu LayChiTietMonAnDaNgonNgu(int maMonAn, int maNgonNgu);

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachChiTietMonAnDaNgonNgu")]
        [OperationContract]
        List<ChiTietMonAnDaNgonNgu> LayDanhSachChiTietMonAnDaNgonNgu();

        [WebInvoke(Method = "GET", UriTemplate = "layChiTietMonAnDonViTinhDonGia?maMonAn={maMonAn}&maDonViTinh={maDonViTinh}")]
        [OperationContract]
        float LayChiTietMonAnDonViTinhDonGia(int maMonAn, int maDonViTinh);

        [WebInvoke(Method = "GET", UriTemplate = "layChiTietMonAnDonViTinh?maMonAn={maMonAn}&maDonViTinh={maDonViTinh}")]
        [OperationContract]
        ChiTietMonAnDonViTinh LayChiTietMonAnDonViTinh(int maMonAn, int maDonViTinh);

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachChiTietMonAnDonViTinh")]
        [OperationContract]
        List<ChiTietMonAnDonViTinh> LayDanhSachChiTietMonAnDonViTinh();

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachNgonNgu")]
        [OperationContract]
        List<NgonNgu> LayDanhSachNgonNgu();

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachNhomTaiKhoan")]
        [OperationContract]
        List<NhomTaiKhoan> LayDanhSachNhomTaiKhoan();

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachTaiKhoan")]
        [OperationContract]
        List<TaiKhoan> LayDanhSachTaiKhoan();

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachTiGia")]
        [OperationContract]
        List<TiGia> LayDanhSachTiGia();

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachPhuThu")]
        [OperationContract]
        List<PhuThu> LayDanhSachPhuThu();

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachKhuyenMai")]
        [OperationContract]
        List<KhuyenMai> LayDanhSachKhuyenMai();

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachBoPhanCheBien")]
        [OperationContract]
        List<BoPhanCheBien> LayDanhSachBoPhanCheBien();

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachChiTietMonLienQuan")]
        [OperationContract]
        List<ChiTietMonLienQuan> LayDanhSachChiTietMonLienQuan();

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachChiTietMonLienQuanTheoMaMon?maMonAn={maMonAn}")]
        [OperationContract]
        List<ChiTietMonLienQuan> LayDanhSachChiTietMonLienQuanTheoMaMon(int maMonAn);


        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachOrder")]
        [OperationContract]
        List<Order> LayDanhSachOrder();

        [WebInvoke(Method = "GET", UriTemplate = "layOrder?maOrder={maOrder}")]
        [OperationContract]
        Order LayOrder(int maOrder);

        [WebInvoke(Method = "GET", UriTemplate = "layChiTietOrder?maChiTietOrder={maChiTietOrder}")]
        [OperationContract]
        ChiTietOrder LayChiTietOrder(int maChiTietOrder);

        [WebInvoke(Method = "GET", UriTemplate = "layChiTietHuyOrder?maChiTietHuyOrder={maChiTietHuyOrder}")]
        [OperationContract]
        ChiTietHuyOrder LayChiTietHuyOrder(int maChiTietHuyOrder);

        [WebInvoke(Method = "POST", UriTemplate = "themOrder")]
        [OperationContract]
        Order ThemOrder(Order _order);

        [WebInvoke(Method = "POST", UriTemplate = "themChiTietOrder")]
        [OperationContract]
        ChiTietOrder ThemChiTietOrder(ChiTietOrder _chiTietOrder);

        [WebInvoke(Method = "POST", UriTemplate = "themChiTietHuyOrder")]
        [OperationContract]
        ChiTietHuyOrder ThemChiTietHuyOrder(ChiTietHuyOrder _chiTietHuyOrder);

        [WebInvoke(Method = "PUT", UriTemplate = "suaOrder")]
        [OperationContract]
        bool SuaOrder(Order _order);

        [WebInvoke(Method = "PUT", UriTemplate = "suaChiTietOrder")]
        [OperationContract]
        bool SuaChiTietOrder(ChiTietOrder _chiTietOrder);

        [WebInvoke(Method = "PUT", UriTemplate = "suaChiTietHuyOrder")]
        [OperationContract]
        bool SuaChiTietHuyOrder(ChiTietHuyOrder _chiTietHuyOrder);

        // Testing purpose
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachFoo")]
        [OperationContract]
        List<Foo> LayDanhSachFoo();
    }
}
