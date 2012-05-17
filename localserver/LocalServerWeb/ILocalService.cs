using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using LocalServerDTO;
using System.IO;

namespace LocalServerWeb
{
    [ServiceContract(Namespace="")]
    public interface ILocalService
    {
        
        // Khu Vuc
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachKhuVuc?junk={junk}")]
        [OperationContract]
        List<KhuVuc> LayDanhKhuVuc(string junk);


        // Ban
        [WebInvoke(Method = "GET", UriTemplate = "layBan?maBan={maBan}&junk={junk}")]
        [OperationContract]
        Ban LayBan(int maBan, string junk);

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachBan?junk={junk}")]
        [OperationContract]
        List<Ban> LayDanhSachBan(string junk);

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachBanChinh?junk={junk}")]
        [OperationContract]
        List<Ban> LayDanhSachBanChinh(string junk);

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachBanThuocBanChinh?maBanChinh={maBanChinh}&junk={junk}")]
        [OperationContract]
        List<Ban> LayDanhSachBanThuocBanChinh(int maBanChinh, string junk);

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachBanTheoKhuVuc?maKhuVuc={maKhuVuc}&junk={junk}")]
        [OperationContract]
        List<Ban> LayDanhSachBanTheoKhuVuc(int maKhuVuc, string junk);

        [WebInvoke(Method = "PUT", UriTemplate = "capNhatBan")]
        [OperationContract]
        bool CapNhatBan(Ban ban);


        /// <summary>
        /// Tách bàn đã ghép ra thành các bàn rời nhau
        /// </summary>
        /// <param name="maBan">Mã bàn cha đại diện cho nhóm bàn</param>
        /// <returns>Tách thành công hoặc thất bại.</returns>
        [WebInvoke(Method = "POST", UriTemplate = "tachBan")]
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


        // Mon An
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachMonAn?junk={junk}")]
        [OperationContract]
        List<MonAn> LayDanhSachMonAn(string junk);

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachMonAnTheoDanhMuc?maDanhMuc={maDanhMuc}&junk={junk}")]
        [OperationContract]
        List<MonAn> LayDanhSachMonAnTheoDanhMuc(int maDanhMuc, string junk);

        [WebInvoke(Method = "GET", UriTemplate = "layMonAn?maMonAn={maMonAn}&junk={junk}")]
        [OperationContract]
        MonAn LayMonAn(int maMonAn, string junk);


        // Danh Muc
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachDanhMuc?junk={junk}")]
        [OperationContract]
        List<DanhMuc> LayDanhSachDanhMuc(string junk);


        // Chi Tiet Danh Muc Da Ngon Ngu
        [WebInvoke(Method = "GET", UriTemplate = "layChiTietDanhMucDaNgonNgu?maDanhMuc={maDanhMuc}&maNgonNgu={maNgonNgu}&junk={junk}")]
        [OperationContract]
        ChiTietDanhMucDaNgonNgu LayChiTietDanhMucDaNgonNgu(int maDanhMuc, int maNgonNgu, string junk);

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachChiTietDanhMucDaNgonNgu?junk={junk}")]
        [OperationContract]
        List<ChiTietDanhMucDaNgonNgu> LayDanhSachChiTietDanhMucDaNgonNgu(string junk);


        // Chi Tiet Don Vi Tinh Da Ngon Ngu
        [WebInvoke(Method = "GET", UriTemplate = "layChiTietDonViTinhDaNgonNgu?maDonViTinh={maDonViTinh}&maNgonNgu={maNgonNgu}&junk={junk}")]
        [OperationContract]
        ChiTietDonViTinhDaNgonNgu LayChiTietDonViTinhDaNgonNgu(int maDonViTinh, int maNgonNgu, string junk);

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachChiTietDonViTinhDaNgonNgu?junk={junk}")]
        [OperationContract]
        List<ChiTietDonViTinhDaNgonNgu> LayDanhSachChiTietDonViTinhDaNgonNgu(string junk);


        // Chi Tiet Mon An Da Ngon Ngu
        [WebInvoke(Method = "GET", UriTemplate = "layChiTietMonAnDaNgonNgu?maMonAn={maMonAn}&maNgonNgu={maNgonNgu}&junk={junk}")]
        [OperationContract]
        ChiTietMonAnDaNgonNgu LayChiTietMonAnDaNgonNgu(int maMonAn, int maNgonNgu, string junk);

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachChiTietMonAnDaNgonNgu?junk={junk}")]
        [OperationContract]
        List<ChiTietMonAnDaNgonNgu> LayDanhSachChiTietMonAnDaNgonNgu(string junk);


        // Chi Tiet Mon An Don Vi Tinh
        [WebInvoke(Method = "GET", UriTemplate = "layChiTietMonAnDonViTinhDonGia?maMonAn={maMonAn}&maDonViTinh={maDonViTinh}&junk={junk}")]
        [OperationContract]
        float LayChiTietMonAnDonViTinhDonGia(int maMonAn, int maDonViTinh, string junk);

        [WebInvoke(Method = "GET", UriTemplate = "layChiTietMonAnDonViTinh?maMonAn={maMonAn}&maDonViTinh={maDonViTinh}&junk={junk}")]
        [OperationContract]
        ChiTietMonAnDonViTinh LayChiTietMonAnDonViTinh(int maMonAn, int maDonViTinh, string junk);

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachChiTietMonAnDonViTinh?junk={junk}")]
        [OperationContract]
        List<ChiTietMonAnDonViTinh> LayDanhSachChiTietMonAnDonViTinh(string junk);


        // Ngon Ngu
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachNgonNgu?junk={junk}")]
        [OperationContract]
        List<NgonNgu> LayDanhSachNgonNgu(string junk);


        // Nhom Tai Khoan
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachNhomTaiKhoan?junk={junk}")]
        [OperationContract]
        List<NhomTaiKhoan> LayDanhSachNhomTaiKhoan(string junk);


        // Tai Khoan
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachTaiKhoan?junk={junk}")]
        [OperationContract]
        List<TaiKhoan> LayDanhSachTaiKhoan(string junk);


        // Ti Gia
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachTiGia?junk={junk}")]
        [OperationContract]
        List<TiGia> LayDanhSachTiGia(string junk);


        // Phu Thu
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachPhuThu?junk={junk}")]
        [OperationContract]
        List<PhuThu> LayDanhSachPhuThu(string junk);

        // Phu Thu Khu Vuc
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachPhuThuKhuVuc?junk={junk}")]
        [OperationContract]
        List<PhuThuKhuVuc> LayDanhSachPhuThuKhuVuc(string junk);

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachPhuThuKhuVucTheoMa?maPhuThu={maPhuThu}&junk={junk}")]
        [OperationContract]
        List<PhuThuKhuVuc> LayDanhSachPhuThuKhuVucTheoMa(int maPhuThu, string junk);


        // Khuyen Mai
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachKhuyenMai?junk={junk}")]
        [OperationContract]
        List<KhuyenMai> LayDanhSachKhuyenMai(string junk);


        // Khuyen Mai Khu Vuc
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachKhuyenMaiKhuVuc?junk={junk}")]
        [OperationContract]
        List<KhuyenMaiKhuVuc> LayDanhSachKhuyenMaiKhuVuc(string junk);

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachKhuyenMaiKhuVucTheoMa?maKhuyenMai={maKhuyenMai}&junk={junk}")]
        [OperationContract]
        List<KhuyenMaiKhuVuc> LayDanhSachKhuyenMaiKhuVucTheoMa(int maKhuyenMai, string junk);

        // Khuyen Mai Danh Muc
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachKhuyenMaiDanhMuc?junk={junk}")]
        [OperationContract]
        List<KhuyenMaiDanhMuc> LayDanhSachKhuyenMaiDanhMuc(string junk);

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachKhuyenMaiDanhMucTheoMa?maKhuyenMai={maKhuyenMai}&junk={junk}")]
        [OperationContract]
        List<KhuyenMaiDanhMuc> LayDanhSachKhuyenMaiDanhMucTheoMa(int maKhuyenMai, string junk);

        // Khuyen Mai Mon
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachKhuyenMaiMon?junk={junk}")]
        [OperationContract]
        List<KhuyenMaiMon> LayDanhSachKhuyenMaiMon(string junk);

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachKhuyenMaiMonTheoMa?maKhuyenMai={maKhuyenMai}&junk={junk}")]
        [OperationContract]
        List<KhuyenMaiMon> LayDanhSachKhuyenMaiMonTheoMa(int maKhuyenMai, string junk);

        // Khuyen Mai Hoa Don
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachKhuyenMaiHoaDon?junk={junk}")]
        [OperationContract]
        List<KhuyenMaiHoaDon> LayDanhSachKhuyenMaiHoaDon(string junk);

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachKhuyenMaiHoaDonTheoMa?maKhuyenMai={maKhuyenMai}&junk={junk}")]
        [OperationContract]
        List<KhuyenMaiHoaDon> LayDanhSachKhuyenMaiHoaDonTheoMa(int maKhuyenMai, string junk);

        // Bo Phan Che Bien
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachBoPhanCheBien?junk={junk}")]
        [OperationContract]
        List<BoPhanCheBien> LayDanhSachBoPhanCheBien(string junk);


        // Chi Tiet Mon Lien Quan
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachChiTietMonLienQuan?junk={junk}")]
        [OperationContract]
        List<ChiTietMonLienQuan> LayDanhSachChiTietMonLienQuan(string junk);

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachChiTietMonLienQuanTheoMaMon?maMonAn={maMonAn}&junk={junk}")]
        [OperationContract]
        List<ChiTietMonLienQuan> LayDanhSachChiTietMonLienQuanTheoMaMon(int maMonAn, string junk);


        // Order
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachOrder?junk={junk}")]
        [OperationContract]
        List<Order> LayDanhSachOrder(string junk);

        [WebInvoke(Method = "GET", UriTemplate = "layOrder?maOrder={maOrder}&junk={junk}")]
        [OperationContract]
        Order LayOrder(int maOrder, string junk);

        [WebInvoke(Method = "POST", UriTemplate = "themOrder")]
        [OperationContract]
        Order ThemOrder(Order _order);

        [WebInvoke(Method = "PUT", UriTemplate = "suaOrder")]
        [OperationContract]
        bool SuaOrder(Order _order);


        // Chi Tiet Order
        [WebInvoke(Method = "GET", UriTemplate = "layChiTietOrder?maChiTietOrder={maChiTietOrder}&junk={junk}")]
        [OperationContract]
        ChiTietOrder LayChiTietOrder(int maChiTietOrder, string junk);

        [WebInvoke(Method = "POST", UriTemplate = "themChiTietOrder")]
        [OperationContract]
        ChiTietOrder ThemChiTietOrder(ChiTietOrder _chiTietOrder);

        [WebInvoke(Method = "POST", UriTemplate = "themNhieuChiTietOrder")]
        [OperationContract]
        List<ChiTietOrder> ThemNhieuChiTietOrder(List<ChiTietOrder> _listChiTietOrder);

        [WebInvoke(Method = "PUT", UriTemplate = "suaChiTietOrder")]
        [OperationContract]
        bool SuaChiTietOrder(ChiTietOrder _chiTietOrder);


        // Chi Tiet Huy Order
        [WebInvoke(Method = "GET", UriTemplate = "layChiTietHuyOrder?maChiTietHuyOrder={maChiTietHuyOrder}&junk={junk}")]
        [OperationContract]
        ChiTietHuyOrder LayChiTietHuyOrder(int maChiTietHuyOrder, string junk);

        [WebInvoke(Method = "POST", UriTemplate = "themChiTietHuyOrder")]
        [OperationContract]
        bool ThemChiTietHuyOrder(ChiTietHuyOrder _chiTietHuyOrder);

        [WebInvoke(Method = "PUT", UriTemplate = "suaChiTietHuyOrder")]
        [OperationContract]
        bool SuaChiTietHuyOrder(ChiTietHuyOrder _chiTietHuyOrder);

        // Hoa Don
        [WebInvoke(Method = "GET", UriTemplate = "layHoaDon?maHoaDon={maHoaDon}&junk={junk}")]
        [OperationContract]
        HoaDon LayHoaDon(int maHoaDon, string junk);

        [WebInvoke(Method = "POST", UriTemplate = "themHoaDon")]
        [OperationContract]
        HoaDon ThemHoaDon(HoaDon _hoaDon);

        [WebInvoke(Method = "PUT", UriTemplate = "suaHoaDon")]
        [OperationContract]
        bool SuaHoaDon(HoaDon _hoaDon);


        // Chi Tiet Hoa Don
        [WebInvoke(Method = "GET", UriTemplate = "layChiTietHoaDon?maChiTietHoaDon={maChiTietHoaDon}&junk={junk}")]
        [OperationContract]
        ChiTietHoaDon LayChiTietHoaDon(int maChiTietHoaDon, string junk);

        [WebInvoke(Method = "POST", UriTemplate = "themChiTietHoaDon")]
        [OperationContract]
        ChiTietHoaDon ThemChiTietHoaDon(ChiTietHoaDon _chiTietHoaDon);

        [WebInvoke(Method = "POST", UriTemplate = "themNhieuChiTietHoaDon")]
        [OperationContract]
        List<ChiTietHoaDon> ThemNhieuChiTietHoaDon(List<ChiTietHoaDon> _listChiTietHoaDon);

        [WebInvoke(Method = "PUT", UriTemplate = "suaChiTietHoaDon")]
        [OperationContract]
        bool SuaChiTietHoaDon(ChiTietHoaDon _chiTietHoaDon);

        
        // Picture 
        [OperationContract]
        [WebInvoke(Method = "GET", UriTemplate = "getPicture?path={path}&junk={junk}")]
        Stream GetPicture(string path, string junk);

        [OperationContract]
        [WebInvoke(Method = "POST", UriTemplate = "addPicture?path={path}")]
        bool AddPicture(string path, Stream content);



        // Testing purpose
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachFoo")]
        [OperationContract]
        List<Foo> LayDanhSachFoo();

        [WebInvoke(Method = "GET", UriTemplate = "cong?a={a}&b={b}")]
        [OperationContract]
        int PhepCong(int a, int b);

        [WebInvoke(Method = "POST", UriTemplate = "addText")]
        string AddText();

        // Check to ensure server work correctly
        [WebInvoke(Method = "GET", UriTemplate = "testGet")]
        [OperationContract]
        string TestGET();

        [WebInvoke(Method = "POST", UriTemplate = "testPost")]
        [OperationContract]
        string TestPOST();

        [WebInvoke(Method = "PUT", UriTemplate = "testPut")]
        [OperationContract]
        string TestPUT();

        
    }
}
