using System;
using System.Collections.Generic;
using System.Configuration;
using System.Globalization;
using System.Linq;
using System.Threading;
using System.Web;
using System.Web.Mvc;
using LocalServerBUS;
using LocalServerDTO;

namespace LocalServerWeb.Codes
{
    public class SharedCode
    {
        public static void KhoiTaoCSDL()
        {
            string strConn = ConfigurationManager.ConnectionStrings["ApplicationServices"].ConnectionString;
            ThucDonDienTuBUS.KhoiTao(strConn);
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
    }
}