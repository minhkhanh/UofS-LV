using System;
using System.Collections.Generic;
using System.Configuration;
using System.Globalization;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Threading;
using System.Web;
using System.Web.Mvc;
using LocalServerBUS;
using LocalServerDTO;
using LocalServerWeb.Resources.Views.Shared;
using LocalServerWeb.ViewModels;

namespace LocalServerWeb.Codes
{
    public class SharedCode
    {
        public static void KhoiTaoCSDL()
        {
            string strConn = ConfigurationManager.ConnectionStrings["ApplicationServices"].ConnectionString;
            ThucDonDienTuBUS.KhoiTao(strConn);
        }

        public static string Hash(string value)
        {
            byte[] byteHash = new SHA256CryptoServiceProvider().ComputeHash(new UTF8Encoding().GetBytes(value));
            string hexString = "";
            foreach (byte i in byteHash)
            {
                hexString += String.Format("{0:x2}", i);
            }

            return hexString;

            //return Convert.ToBase64String(new SHA256CryptoServiceProvider().ComputeHash(new UTF8Encoding().GetBytes(value)));
            //return Convert.ToBase64String(new MD5CryptoServiceProvider().ComputeHash(new UTF8Encoding().GetBytes(value)));
        }

        private static void FillLanguage(ViewDataDictionary viewData, HttpContextBase httpContext)
        {
            var listLanguage = NgonNguBUS.LayDanhSachNgonNgu();
            SelectList listNgonNgu;
            if (listLanguage.Count == 0) listNgonNgu = new SelectList(listLanguage, "KiHieu", "TenNgonNgu");
            else
            {
                string kiHieuDangChon = listLanguage[0].KiHieu;
                if (httpContext.Session != null && httpContext.Session["ngonNgu"] != null) 
                    kiHieuDangChon = ((NgonNgu)httpContext.Session["ngonNgu"]).KiHieu;
                else if (httpContext.Session != null) httpContext.Session["ngonNgu"] = listLanguage[0];
                listNgonNgu = new SelectList(listLanguage, "KiHieu", "TenNgonNgu", kiHieuDangChon);
                
            }

            viewData["listNgonNgu"] = listNgonNgu;
        }

        public static void FillCommonData2View(ViewDataDictionary viewData, HttpContextBase httpContext)
        {
            FillLanguage(viewData, httpContext);
        }

        public static void StoreCommonData2Section(HttpContextBase httpContext)
        {
            //StoreLanguage(httpContext);
        }

        public static void LoadUserCulture(HttpSessionStateBase session)
        {
            if (session == null || session["ngonNgu"] == null) return;
            NgonNgu ngonNgu = (NgonNgu)session["ngonNgu"];
            var ci = new CultureInfo(ngonNgu.KiHieu);
            Thread.CurrentThread.CurrentCulture = CultureInfo.CreateSpecificCulture(ci.Name);
            Thread.CurrentThread.CurrentUICulture = ci;
        }
        
        public static void FillAdminMainMenu(ViewDataDictionary viewData, int iMenuMain, int iMenuSub)
        {
            viewData["menuMainId"] = iMenuMain;
            viewData["menuSubId"] = iMenuSub;
        }

        public static bool IsUserLogin(HttpSessionStateBase session)
        {
            if (session == null || session["taiKhoan"] == null) return false;
            return true;
        }
        public static bool IsAdminLogin(HttpSessionStateBase session)
        {
            if (!IsUserLogin(session) || ((TaiKhoan) session["taiKhoan"]).NhomTaiKhoan.TenNhom!="Admin") return false;
            return true;
        }
        public static bool IsManagerLogin(HttpSessionStateBase session)
        {
            if (!IsUserLogin(session) || ((TaiKhoan)session["taiKhoan"]).NhomTaiKhoan.TenNhom != "Manager") return false;
            return true;
        }
        public static TaiKhoan GetTaiKhoan(HttpSessionStateBase session)
        {
            return session["taiKhoan"] as TaiKhoan;
        }
        public static NgonNgu GetCurrentLanguage(HttpSessionStateBase session)
        {
            return session["ngonNgu"] as NgonNgu;
        }
        public static string GetHostApplicationAddress(HttpRequest request)
        {
            if (request!=null)
            {
                return string.Format("{0}://{1}{2}", request.Url.Scheme, request.Url.Authority, request.ApplicationPath);
            }
            return "";
        }
        // Get a list of ViewModel for displaying Food Gallery (many Food items), get by MaDanhMuc
        public static List<FoodGalleryItemViewModel> GetFoodRandom(int num, int maNgonNgu)
        {
            List<FoodGalleryItemViewModel> viewModels = new List<FoodGalleryItemViewModel>();
            List<DanhMuc> dsDanhMuc;
            List<MonAn> dsMonAn = new List<MonAn>();            

            try
            {
                dsMonAn = MonAnBUS.LayDanhSachMonAn();
                if (num <= 0 || dsMonAn.Count <= 0) return new List<FoodGalleryItemViewModel>();
                if (num > dsMonAn.Count) num = dsMonAn.Count;
                if (num < dsMonAn.Count)
                {
                    HashSet<MonAn> sets = new HashSet<MonAn>();
                    Random random = new Random();
                    while (sets.Count < num)
                    {
                        sets.Add(dsMonAn[random.Next(dsMonAn.Count)]);
                    }
                    dsMonAn = new List<MonAn>(sets);
                }

            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
            }


            if (dsMonAn != null)
            {

                for (int i = 0; i < dsMonAn.Count; ++i)
                {

                    try
                    {
                        MonAn monAn = dsMonAn[i];

                        FoodGalleryItemViewModel viewModel = GetFoodGalleryItemViewModel(monAn, maNgonNgu);

                        viewModels.Add(viewModel);

                    }
                    catch (Exception e)
                    {
                        Console.WriteLine(e.Message);
                    }

                }
            }

            return viewModels;
        }
        // Get ViewModel for displaying Food Gallery Item, get by MonAn
        private static FoodGalleryItemViewModel GetFoodGalleryItemViewModel(MonAn monAn, int maNgonNgu)
        {
            FoodGalleryItemViewModel viewModel = new FoodGalleryItemViewModel();
            viewModel.HinhAnh = monAn.HinhAnh;
            viewModel.MaMonAn = monAn.MaMonAn;

            // Hien thi Ten mon an theo ngon ngu
            ChiTietMonAnDaNgonNgu ctMonAnDaNgonNgu = ChiTietMonAnDaNgonNguBUS.LayChiTietMonAnDaNgonNgu(monAn.MaMonAn, maNgonNgu);
            if (ctMonAnDaNgonNgu != null)
            {
                viewModel.TenMonAn = ctMonAnDaNgonNgu.TenMonAn;
            }
            else
            {
                viewModel.TenMonAn = SharedString.NoInformation;
            }

            // Hien thi ten don vi tinh
            List<ChiTietMonAnDonViTinh> listCTMonAnDonViTinh = ChiTietMonAnDonViTinhBUS.LayDanhSachChiTietMonAnDonViTinhTheoMonAn(monAn.MaMonAn);
            if (listCTMonAnDonViTinh != null && listCTMonAnDonViTinh[0] != null)
            {
                viewModel.DonGia = listCTMonAnDonViTinh[0].DonGia;

                ChiTietDonViTinhDaNgonNgu ctDonViTinhDaNgonNgu = ChiTietDonViTinhDaNgonNguBUS.LayChiTietDonViTinhDaNgonNgu(listCTMonAnDonViTinh[0].DonViTinh.MaDonViTinh, maNgonNgu);
                if (ctDonViTinhDaNgonNgu != null)
                {
                    viewModel.TenDonViTinh = ctDonViTinhDaNgonNgu.TenDonViTinh;
                }
                else
                {
                    viewModel.TenDonViTinh = SharedString.NoInformation;
                }
            }

            return viewModel;
        }
    }
}