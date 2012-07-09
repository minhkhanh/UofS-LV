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

        [WebInvoke(Method = "POST", UriTemplate = "chuyenBan?maBanChuyenDi={maBanChuyenDi}&maBanChuyenDen={maBanChuyenDen}")]
        [OperationContract]
        bool ChuyenBan(int maBanChuyenDi, int maBanChuyenDen);

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

        [WebInvoke(Method = "GET", UriTemplate = "danhGiaMonAn?maMonAn={maMonAn}&diemDanhGia={diemDanhGia}&junk={junk}")]
        [OperationContract]
        bool DanhGiaMonAn(int maMonAn, float diemDanhGia, string junk);

        [WebInvoke(Method = "POST", UriTemplate = "danhGiaNhieuMonAn")]
        [OperationContract]
        bool DanhGiaNhieuMonAn(List<DanhGiaMonAn> listDanhGiaMonAn);
        
        // Don Vi Tinh
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachDonViTinh?junk={junk}")]
        [OperationContract]
        List<DonViTinh> LayDanhSachDonViTinh(string junk);


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

        [WebInvoke(Method = "POST", UriTemplate = "lapOrder?maTaiKhoan={maTaiKhoan}&maBan={maBan}")]
        [OperationContract]
        List<ChiTietOrder> LapOrder(int maTaiKhoan, int maBan, List<ChiTietOrder> _listChiTietOrder);


        // Chi Tiet Order
        [WebInvoke(Method = "GET", UriTemplate = "layChiTietOrder?maChiTietOrder={maChiTietOrder}&junk={junk}")]
        [OperationContract]
        ChiTietOrder LayChiTietOrder(int maChiTietOrder, string junk);

        [WebInvoke(Method = "GET", UriTemplate = "layNhieuChiTietOrder?maOrder={maOrder}&junk={junk}")]
        [OperationContract]
        List<ChiTietOrder> LayNhieuChiTietOrder(int maOrder, string junk);

        [WebInvoke(Method = "GET", UriTemplate = "layNhieuChiTietOrderChuaThanhToan?maBan={maBan}&junk={junk}")]
        [OperationContract]
        List<ChiTietOrder> LayNhieuChiTietOrderChuaThanhToan(int maBan, string junk);

        [WebInvoke(Method = "POST", UriTemplate = "duocPhepThanhToan?maBan={maBan}")]
        [OperationContract]
        bool DuocPhepThanhToan(int maBan);

        [WebInvoke(Method = "POST", UriTemplate = "themChiTietOrder")]
        [OperationContract]
        ChiTietOrder ThemChiTietOrder(ChiTietOrder _chiTietOrder);

        [WebInvoke(Method = "POST", UriTemplate = "themNhieuChiTietOrder")]
        [OperationContract]
        List<ChiTietOrder> ThemNhieuChiTietOrder(List<ChiTietOrder> _listChiTietOrder);

        [WebInvoke(Method = "PUT", UriTemplate = "suaChiTietOrder")]
        [OperationContract]
        bool SuaChiTietOrder(ChiTietOrder _chiTietOrder);

        [WebInvoke(Method = "GET", UriTemplate = "laySoLuongChoPhepHuyOrder?maChiTietOrder={maChiTietOrder}&junk={junk}")]
        [OperationContract]
        ChiTietHuyOrder LaySoLuongChoPhepHuyOrder(int maChiTietOrder, string junk);

        [WebInvoke(Method = "POST", UriTemplate = "yeuCauHuyOrder?maChiTietOrder={maChiTietOrder}&soLuongYeuCauHuy={soLuongYeuCauHuy}&ghiChu={ghiChu}")]
        [OperationContract]
        bool YeuCauHuyOrder(int maChiTietOrder, int soLuongYeuCauHuy, string ghiChu);


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

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachHoaDon?junk={junk}")]
        [OperationContract]
        List<HoaDon> LayDanhSachHoaDon(string junk);

        [WebInvoke(Method = "POST", UriTemplate = "themHoaDon")]
        [OperationContract]
        HoaDon ThemHoaDon(HoaDon _hoaDon);

        [WebInvoke(Method = "POST", UriTemplate = "lapHoaDon?maOrder={maOrder}")]
        [OperationContract]
        string LapHoaDon(int maOrder, List<String> voucherCodes);

        [WebInvoke(Method = "PUT", UriTemplate = "suaHoaDon")]
        [OperationContract]
        bool SuaHoaDon(HoaDon _hoaDon);


        // Chi Tiet Hoa Don
        [WebInvoke(Method = "GET", UriTemplate = "layChiTietHoaDon?maChiTietHoaDon={maChiTietHoaDon}&junk={junk}")]
        [OperationContract]
        ChiTietHoaDon LayChiTietHoaDon(int maChiTietHoaDon, string junk);

        [WebInvoke(Method = "GET", UriTemplate = "layNhieuChiTietHoaDon?maHoaDon={maHoaDon}&junk={junk}")]
        [OperationContract]
        List<ChiTietHoaDon> LayNhieuChiTietHoaDon(int maHoaDon, string junk);

        [WebInvoke(Method = "POST", UriTemplate = "themChiTietHoaDon")]
        [OperationContract]
        ChiTietHoaDon ThemChiTietHoaDon(ChiTietHoaDon _chiTietHoaDon);

        [WebInvoke(Method = "POST", UriTemplate = "themNhieuChiTietHoaDon")]
        [OperationContract]
        int ThemNhieuChiTietHoaDon(List<ChiTietHoaDon> _listChiTietHoaDon);

        [WebInvoke(Method = "PUT", UriTemplate = "suaChiTietHoaDon")]
        [OperationContract]
        bool SuaChiTietHoaDon(ChiTietHoaDon _chiTietHoaDon);


        // Voucher
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachVoucher?junk={junk}")]
        [OperationContract]
        List<Voucher> LayDanhSachVoucher(string junk);

        [WebInvoke(Method = "POST", UriTemplate = "kiemTraVoucher?soPhieu={soPhieu}&tongHoaDon={tongHoaDon}")]
        [OperationContract]
        float KiemTraVoucher(string soPhieu, float tongHoaDon);

        [WebInvoke(Method = "POST", UriTemplate = "suDungVoucher?soPhieu={soPhieu}")]
        [OperationContract]
        bool SuDungVoucher(string soPhieu);


        // Bao Cao
        [WebInvoke(Method = "GET", UriTemplate = "layBaoCaoNgay?ngay={ngay}&thang={thang}&nam={nam}&junk={junk}")]
        [OperationContract]
        List<BaoCaoNgay> LayBaoCaoNgay(int ngay, int thang, int nam, string junk);

        [WebInvoke(Method = "GET", UriTemplate = "layBaoCaoThang?thang={thang}&nam={nam}&junk={junk}")]
        [OperationContract]
        List<BaoCaoThang> LayBaoCaoThang(int thang, int nam, string junk);



        // Picture 
        [OperationContract]
        [WebInvoke(Method = "GET", UriTemplate = "getPicture?path={path}&junk={junk}")]
        Stream GetPicture(string path, string junk);

        [OperationContract]
        [WebInvoke(Method = "POST", UriTemplate = "addPicture?path={path}")]
        bool AddPicture(string path, Stream content);


        // LayThongTinNhaHang
        [OperationContract]
        [WebInvoke(Method = "GET", UriTemplate = "layThongTinNhaHang?junk={junk}")]
        NhaHang LayThongTinNhaHang(string junk);

        
        // Chung Thuc
        [WebInvoke(Method = "POST", UriTemplate = "chungThucMobilePhucVu?tenDangNhap={tenDangNhap}&matKhau={matKhau}")]
        [OperationContract]
        TaiKhoan ChungThucMobilePhucVu(string tenDangNhap, string matKhau);

        [WebInvoke(Method = "POST", UriTemplate = "chungThucMobileQuanLy?tenDangNhap={tenDangNhap}&matKhau={matKhau}")]
        [OperationContract]
        TaiKhoan ChungThucMobileQuanLy(string tenDangNhap, string matKhau);


        // Testing purpose
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachFoo")]
        [OperationContract]
        List<Foo> LayDanhSachFoo();

        // Dang nhap
        /// <summary>
        /// Dang nhap dung post voi body dang: tenDangNhap=superadmin&matKhau=1234
        /// </summary>
        /// <param name="body"></param>
        /// <returns></returns>
        [WebInvoke(Method = "POST", UriTemplate = "dangNhap")]
        [OperationContract]
        bool DangNhap(Stream body);

        [WebInvoke(Method = "GET", UriTemplate = "dangXuat?junk={junk}")]
        [OperationContract]
        bool DangXuat(string junk);

        //[WebInvoke(Method = "POST", UriTemplate = "dangNhapMobileQuanLy?tenDangNhap={tenDangNhap}&matKhau={matKhau}")]
        //[OperationContract]
        //TaiKhoan DangNhapMobileQuanLy(string tenDangNhap, string matKhau);

        [WebInvoke(Method = "GET", UriTemplate = "cong?a={a}&b={b}")]
        [OperationContract]
        int PhepCong(int a, int b);

        [WebInvoke(Method = "POST", UriTemplate = "addText")]
        string AddText();

        // Check to ensure server work correctly
        [WebInvoke(Method = "GET", UriTemplate = "testGet?thamSo={thamSo}&junk={junk}")]
        [OperationContract]
        string TestGET(string thamSo, string junk);

        [WebInvoke(Method = "POST", UriTemplate = "testPost?thamSo={thamSo}")]
        [OperationContract]
        string TestPOST(string thamSo);

        [WebInvoke(Method = "PUT", UriTemplate = "testPut")]
        [OperationContract]
        string TestPUT();

        /*==============JSON SERVICES AREA==============*/

        // Danh Muc
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachDanhMucJson?junk={junk}", RequestFormat=WebMessageFormat.Json, ResponseFormat=WebMessageFormat.Json)]
        [OperationContract]
        List<DanhMuc> LayDanhSachDanhMucJson(string junk);

        // danh muc da ngon ngu
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachDanhMucDaNgonNguJson?junk={junk}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        List<ChiTietDanhMucDaNgonNgu> LayDanhSachChiTietDanhMucDaNgonNguJson(string junk);

        // Khu vuc
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachKhuVucJson?junk={junk}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        List<KhuVuc> LayDanhSachKhuVucJson(string junk);

        //Ban
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachBanJson?junk={junk}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        List<Ban> LayDanhSachBanJson(string junk);

        /*Don vi tinh*/
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachDonViTinhJson?junk={junk}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        List<DonViTinh> LayDanhSachDonViTinhJson(string junk);

        // don vi tinh da ngon ngu
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachDonViTinhDaNgonNguJson?junk={junk}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        List<ChiTietDonViTinhDaNgonNgu> LayDanhSachChiTietDonViTinhDaNgonNguJson(string junk);

        /*Mon an*/
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachMonAnJson?junk={junk}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        List<MonAn> LayDanhSachMonAnJson(string junk);

        [WebInvoke(Method = "GET", UriTemplate = "danhGiaMonAnJson?maMonAn={maMonAn}&diemDanhGia={diemDanhGia}&junk={junk}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        bool DanhGiaMonAnJson(int maMonAn, float diemDanhGia, string junk);

        // mon an da ngon ngu
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachMonAnDaNgonNguJson?junk={junk}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        List<ChiTietMonAnDaNgonNgu> LayDanhSachChiTietMonAnDaNgonNguJson(string junk);

        // don vi tinh mon an
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachDonViTinhMonAnJson?junk={junk}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        List<ChiTietMonAnDonViTinh> LayDanhSachChiTietMonAnDonViTinhJson(string junk);
        
        // mon lien quan
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachMonLienQuanJson?junk={junk}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        List<ChiTietMonLienQuan> LayDanhSachChiTietMonLienQuanJson(string junk);

        /* Khuyen mai*/
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachKhuyenMaiCoHieuLucJson?junk={junk}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        List<KhuyenMai> LayDanhSachKhuyenMaiCoHieuLucJson(string junk);

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachKhuyenMaiMonCoHieuLucJson?junk={junk}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        List<KhuyenMaiMon> LayDanhSachKhuyenMaiMonCoHieuLucJson(string junk);

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachKhuyenMaiDanhMucJson?junk={junk}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        List<KhuyenMaiDanhMuc> LayDanhSachKhuyenMaiDanhMucJson(string junk);

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachKhuyenMaiMonJson?junk={junk}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        List<KhuyenMaiMon> LayDanhSachKhuyenMaiMonJson(string junk);

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachKhuyenMaiHoaDonJson?junk={junk}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        List<KhuyenMaiHoaDon> LayDanhSachKhuyenMaiHoaDonJson(string junk);

        // Ngon Ngu
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachNgonNguJson?junk={junk}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        List<NgonNgu> LayDanhSachNgonNguJson(string junk);

        // Nhom Tai Khoan
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachNhomTaiKhoanJson?junk={junk}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        List<NhomTaiKhoan> LayDanhSachNhomTaiKhoanJson(string junk);

        // Tai Khoan
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachTaiKhoanJson?junk={junk}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        List<TaiKhoan> LayDanhSachTaiKhoanJson(string junk);

        // Ti Gia
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachTiGiaJson?junk={junk}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        List<TiGia> LayDanhSachTiGiaJson(string junk);

        // Phu Thu
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachPhuThuCoHieuLucJson?junk={junk}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        List<PhuThu> LayDanhSachPhuThuCoHieuLucJson(string junk);

        // Phu Thu Khu Vuc
        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachPhuThuKhuVucCoHieuLucJson?junk={junk}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        List<PhuThuKhuVuc> LayDanhSachPhuThuKhuVucCoHieuLucJson(string junk);

        // Hoa Don
        [WebInvoke(Method = "POST", UriTemplate = "lapHoaDonJson?maOrder={maOrder}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        string LapHoaDonJson(int maOrder, List<String> voucherCodes);

        // Order
        [WebInvoke(Method = "POST", UriTemplate = "lapOrderJson?maTaiKhoan={maTaiKhoan}&maBan={maBan}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        List<ChiTietOrder> LapOrderJson(int maTaiKhoan, int maBan, List<ChiTietOrder> _listChiTietOrder);

        // ghep ban
        [WebInvoke(Method = "POST", UriTemplate = "ghepBanJson", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        bool GhepBanJson(TableIdSelection tabSel);

        // tach mot ban ra khoi nhom
        [WebInvoke(Method = "GET", UriTemplate = "tachBanJson?maBan={maBan}&junk={junk}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        bool TachBanJson(int maBan, string junk);

        // tach tat ca cac ban trong nhom
        [WebInvoke(Method = "GET", UriTemplate = "tachNhomBanJson?maBan={maBan}&junk={junk}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        bool TachNhomBanJson(int maBan, string junk);

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachOrderChuaThanhToanJson?maBan={maBan}&junk={junk}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        List<Order> LayDanhSachOrderChuaThanhToanJson(int maBan, string junk);

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachChiTietOrderJson?maOrder={maOrder}&junk={junk}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        List<ChiTietOrder> LayDanhSachChiTietOrderJson(int maOrder, string junk);

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachBanChinhJson?maKhuVuc={maKhuVuc}&junk={junk}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        List<Ban> LayDanhSachBanChinhJson(int maKhuVuc, string junk);

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachBanThuocBanChinhJson?maBanChinh={maBanChinh}&junk={junk}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        List<Ban> LayDanhSachBanThuocBanChinhJson(int maBanChinh, string junk);

        [WebInvoke(Method = "POST", UriTemplate = "themOrderJson", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        int ThemOrderJson(Order _order);

        [WebInvoke(Method = "POST", UriTemplate = "tachOrderJson", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        bool TachOrderJson(List<int> dsMaChiTiet);

        [WebInvoke(Method = "POST", UriTemplate = "themNhieuChiTietOrderJson", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        bool ThemNhieuChiTietOrderJson(List<ChiTietOrder> _listChiTietOrder);

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachPhuThuApDungJson?maOrder={maOrder}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        List<PhuThu> LayDanhSachPhuThuApDungJson(int maOrder);

        [WebInvoke(Method = "GET", UriTemplate = "layDanhSachKhuyenMaiApDungJson?maOrder={maOrder}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        List<KhuyenMai> LayDanhSachKhuyenMaiApDungJson(int maOrder);

        [WebInvoke(Method = "GET", UriTemplate = "layKhuyenMaiMonApDungJson?maMon={maMon}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        KhuyenMai LayKhuyenMaiMonApDung(int maMon);

        [WebInvoke(Method = "GET", UriTemplate = "checkYeuCauHuyChiTietOrderJson?maChiTiet={maChiTiet}&soLuong={soLuong}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        int CheckYeuCauHuyChiTietOrderJson(int maChiTiet, int soLuong);

        // Voucher
        [WebInvoke(Method = "GET", UriTemplate = "layVoucherJson?code={code}&tongHoaDon={tongHoaDon}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        Voucher LayVoucherJson(string code, float tongHoaDon);

        [WebInvoke(Method = "GET", UriTemplate = "dungVoucherJson?code={code}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        bool DungVoucherJson(string code);

        // Picture 
        [OperationContract]
        [WebInvoke(Method = "GET", UriTemplate = "getImageJson?path={path}&junk={junk}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        string GetImageJson(string path, string junk);

        [OperationContract]
        [WebInvoke(Method = "POST", UriTemplate = "kiemTraTaiKhoanJson?username={username}&junk={junk}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        bool KiemTraTaiKhoanJson(string username, string password, string junk);

        [WebInvoke(Method = "GET", UriTemplate = "chuyenBan?maOrder={maOrder}&maBanMoi={maBanMoi}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        bool ChuyenBanJson(int maOrder, int maBanMoi);

        [WebInvoke(Method = "PUT", UriTemplate = "suaChiTietOrderJson", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        bool SuaChiTietOrderJson(ChiTietOrder holder);

        [WebInvoke(Method = "GET", UriTemplate = "layChiTietOrderJson?maChiTiet={maChiTiet}&junk={junk}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        ChiTietOrder LayChiTietOrderJson(int maChiTiet, string junk);

        [WebInvoke(Method = "GET", UriTemplate = "layOrderJson?maOrder={maOrder}&junk={junk}", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        Order LayOrderJson(int maOrder, string junk);

        //dang nhap
        [WebInvoke(Method = "POST", UriTemplate = "dangNhapJson", ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        bool DangNhapJson(Stream body);

        // test GET
        [WebInvoke(Method = "GET", UriTemplate = "testGetJson", ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        Stream TestGetJson();

        [WebInvoke(Method = "GET", UriTemplate = "testTabSel", ResponseFormat = WebMessageFormat.Json)]
        [OperationContract]
        TableIdSelection TestTabSel();

        /*==============END OF JSON SERVICES AREA==============*/
    }
}
