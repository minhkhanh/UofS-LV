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
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachKhuVuc")]
        [OperationContract]
        List<KhuVuc> LayDanhKhuVuc();


        // Ban
        [WebInvoke(Method = "GET", UriTemplate = "layBan?maBan={maBan}")]
        [OperationContract]
        Ban LayBan(int maBan);

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
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachMonAn")]
        [OperationContract]
        List<MonAn> LayDanhSachMonAn();
  
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachMonAnTheoDanhMuc?maDanhMuc={maDanhMuc}")]
        [OperationContract]
        List<MonAn> LayDanhSachMonAnTheoDanhMuc(int maDanhMuc);

        [WebInvoke(Method = "GET", UriTemplate = "layMonAn?maMonAn={maMonAn}")]
        [OperationContract]
        MonAn LayMonAn(int maMonAn);


        // Danh Muc
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachDanhMuc")]
        [OperationContract]
        List<DanhMuc> LayDanhSachDanhMuc();


        // Chi Tiet Danh Muc Da Ngon Ngu
        [WebInvoke(Method = "GET", UriTemplate = "layChiTietDanhMucDaNgonNgu?maDanhMuc={maDanhMuc}&maNgonNgu={maNgonNgu}")]
        [OperationContract]
        ChiTietDanhMucDaNgonNgu LayChiTietDanhMucDaNgonNgu(int maDanhMuc, int maNgonNgu);

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachChiTietDanhMucDaNgonNgu")]
        [OperationContract]
        List<ChiTietDanhMucDaNgonNgu> LayDanhSachChiTietDanhMucDaNgonNgu();


        // Chi Tiet Don Vi Tinh Da Ngon Ngu
        [WebInvoke(Method = "GET", UriTemplate = "layChiTietDonViTinhDaNgonNgu?maDonViTinh={maDonViTinh}&maNgonNgu={maNgonNgu}")]
        [OperationContract]
        ChiTietDonViTinhDaNgonNgu LayChiTietDonViTinhDaNgonNgu(int maDonViTinh, int maNgonNgu);

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachChiTietDonViTinhDaNgonNgu")]
        [OperationContract]
        List<ChiTietDonViTinhDaNgonNgu> LayDanhSachChiTietDonViTinhDaNgonNgu();


        // Chi Tiet Mon An Da Ngon Ngu
        [WebInvoke(Method = "GET", UriTemplate = "layChiTietMonAnDaNgonNgu?maMonAn={maMonAn}&maNgonNgu={maNgonNgu}")]
        [OperationContract]
        ChiTietMonAnDaNgonNgu LayChiTietMonAnDaNgonNgu(int maMonAn, int maNgonNgu);

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachChiTietMonAnDaNgonNgu")]
        [OperationContract]
        List<ChiTietMonAnDaNgonNgu> LayDanhSachChiTietMonAnDaNgonNgu();


        // Chi Tiet Mon An Don Vi Tinh
        [WebInvoke(Method = "GET", UriTemplate = "layChiTietMonAnDonViTinhDonGia?maMonAn={maMonAn}&maDonViTinh={maDonViTinh}")]
        [OperationContract]
        float LayChiTietMonAnDonViTinhDonGia(int maMonAn, int maDonViTinh);

        [WebInvoke(Method = "GET", UriTemplate = "layChiTietMonAnDonViTinh?maMonAn={maMonAn}&maDonViTinh={maDonViTinh}")]
        [OperationContract]
        ChiTietMonAnDonViTinh LayChiTietMonAnDonViTinh(int maMonAn, int maDonViTinh);

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachChiTietMonAnDonViTinh")]
        [OperationContract]
        List<ChiTietMonAnDonViTinh> LayDanhSachChiTietMonAnDonViTinh();


        // Ngon Ngu
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachNgonNgu")]
        [OperationContract]
        List<NgonNgu> LayDanhSachNgonNgu();


        // Nhom Tai Khoan
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachNhomTaiKhoan")]
        [OperationContract]
        List<NhomTaiKhoan> LayDanhSachNhomTaiKhoan();


        // Tai Khoan
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachTaiKhoan")]
        [OperationContract]
        List<TaiKhoan> LayDanhSachTaiKhoan();


        // Ti Gia
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachTiGia")]
        [OperationContract]
        List<TiGia> LayDanhSachTiGia();


        // Phu Thu
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachPhuThu")]
        [OperationContract]
        List<PhuThu> LayDanhSachPhuThu();

        // Phu Thu Khu Vuc
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachPhuThuKhuVuc")]
        [OperationContract]
        List<PhuThuKhuVuc> LayDanhSachPhuThuKhuVuc();

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachPhuThuKhuVucTheoMa?maPhuThu={maPhuThu}")]
        [OperationContract]
        List<PhuThuKhuVuc> LayDanhSachPhuThuKhuVucTheoMa(int maPhuThu);


        // Khuyen Mai
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachKhuyenMai")]
        [OperationContract]
        List<KhuyenMai> LayDanhSachKhuyenMai();


        // Khuyen Mai Khu Vuc
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachKhuyenMaiKhuVuc")]
        [OperationContract]
        List<KhuyenMaiKhuVuc> LayDanhSachKhuyenMaiKhuVuc();

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachKhuyenMaiKhuVucTheoMa?maKhuyenMai={maKhuyenMai}")]
        [OperationContract]
        List<KhuyenMaiKhuVuc> LayDanhSachKhuyenMaiKhuVucTheoMa(int maKhuyenMai);

        // Khuyen Mai Danh Muc
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachKhuyenMaiDanhMuc")]
        [OperationContract]
        List<KhuyenMaiDanhMuc> LayDanhSachKhuyenMaiDanhMuc();

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachKhuyenMaiDanhMucTheoMa?maKhuyenMai={maKhuyenMai}")]
        [OperationContract]
        List<KhuyenMaiDanhMuc> LayDanhSachKhuyenMaiDanhMucTheoMa(int maKhuyenMai);

        // Khuyen Mai Mon
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachKhuyenMaiMon")]
        [OperationContract]
        List<KhuyenMaiMon> LayDanhSachKhuyenMaiMon();

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachKhuyenMaiMonTheoMa?maKhuyenMai={maKhuyenMai}")]
        [OperationContract]
        List<KhuyenMaiMon> LayDanhSachKhuyenMaiMonTheoMa(int maKhuyenMai);

        // Khuyen Mai Hoa Don
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachKhuyenMaiHoaDon")]
        [OperationContract]
        List<KhuyenMaiHoaDon> LayDanhSachKhuyenMaiHoaDon();

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachKhuyenMaiHoaDonTheoMa?maKhuyenMai={maKhuyenMai}")]
        [OperationContract]
        List<KhuyenMaiHoaDon> LayDanhSachKhuyenMaiHoaDonTheoMa(int maKhuyenMai);

        // Bo Phan Che Bien
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachBoPhanCheBien")]
        [OperationContract]
        List<BoPhanCheBien> LayDanhSachBoPhanCheBien();


        // Chi Tiet Mon Lien Quan
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachChiTietMonLienQuan")]
        [OperationContract]
        List<ChiTietMonLienQuan> LayDanhSachChiTietMonLienQuan();

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachChiTietMonLienQuanTheoMaMon?maMonAn={maMonAn}")]
        [OperationContract]
        List<ChiTietMonLienQuan> LayDanhSachChiTietMonLienQuanTheoMaMon(int maMonAn);


        // Order
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachOrder")]
        [OperationContract]
        List<Order> LayDanhSachOrder();

        [WebInvoke(Method = "GET", UriTemplate = "layOrder?maOrder={maOrder}")]
        [OperationContract]
        Order LayOrder(int maOrder);

        [WebInvoke(Method = "POST", UriTemplate = "themOrder")]
        [OperationContract]
        Order ThemOrder(Order _order);

        [WebInvoke(Method = "PUT", UriTemplate = "suaOrder")]
        [OperationContract]
        bool SuaOrder(Order _order);


        // Chi Tiet Order
        [WebInvoke(Method = "GET", UriTemplate = "layChiTietOrder?maChiTietOrder={maChiTietOrder}")]
        [OperationContract]
        ChiTietOrder LayChiTietOrder(int maChiTietOrder);

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
        [WebInvoke(Method = "GET", UriTemplate = "layChiTietHuyOrder?maChiTietHuyOrder={maChiTietHuyOrder}")]
        [OperationContract]
        ChiTietHuyOrder LayChiTietHuyOrder(int maChiTietHuyOrder);

        [WebInvoke(Method = "POST", UriTemplate = "themChiTietHuyOrder")]
        [OperationContract]
        bool ThemChiTietHuyOrder(ChiTietHuyOrder _chiTietHuyOrder);

        [WebInvoke(Method = "PUT", UriTemplate = "suaChiTietHuyOrder")]
        [OperationContract]
        bool SuaChiTietHuyOrder(ChiTietHuyOrder _chiTietHuyOrder);

        // Hoa Don
        [WebInvoke(Method = "GET", UriTemplate = "layHoaDon?maHoaDon={maHoaDon}")]
        [OperationContract]
        HoaDon LayHoaDon(int maHoaDon);

        [WebInvoke(Method = "POST", UriTemplate = "themHoaDon")]
        [OperationContract]
        HoaDon ThemHoaDon(HoaDon _hoaDon);

        [WebInvoke(Method = "PUT", UriTemplate = "suaHoaDon")]
        [OperationContract]
        bool SuaHoaDon(HoaDon _hoaDon);


        // Chi Tiet Hoa Don
        [WebInvoke(Method = "GET", UriTemplate = "layChiTietHoaDon?maChiTietHoaDon={maChiTietHoaDon}")]
        [OperationContract]
        ChiTietHoaDon LayChiTietHoaDon(int maChiTietHoaDon);

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
        [WebInvoke(Method = "GET", UriTemplate = "getPicture?path={path}")]
        Stream GetPicture(string path);

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
