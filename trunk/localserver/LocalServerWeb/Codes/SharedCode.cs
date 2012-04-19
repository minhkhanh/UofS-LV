using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using LocalServerBUS;

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
            if (listLanguage.Count == 0) listNgonNgu = new SelectList(listLanguage, "MaNgonNgu", "TenNgonNgu");
            else
            {
                int maNgonNgu = listLanguage[0].MaNgonNgu;
                if (httpContext.Session != null && httpContext.Session["maNgonNgu"] != null) maNgonNgu = (int)httpContext.Session["maNgonNgu"];
                listNgonNgu = new SelectList(listLanguage, "MaNgonNgu", "TenNgonNgu", maNgonNgu);
            }

            viewData["listNgonNgu"] = listNgonNgu;
        }

        public static void FillCommonData2View(ViewDataDictionary viewData, HttpContextBase httpContext)
        {
            FillLanguage(viewData, httpContext);
        }

        public static void StoreCommonData2Section(HttpContextBase httpContext)
        {
            StoreLanguage(httpContext);
        }

        private static void StoreLanguage(HttpContextBase httpContext)
        {
            if (httpContext.Request["maNgonNgu"]!=null)
            {
                try
                {
                    int maNgonNgu = int.Parse(httpContext.Request["maNgonNgu"]);
                    var ngonNgu = NgonNguBUS.LayNgonNguTheoMa(maNgonNgu);
                    if (ngonNgu != null && httpContext.Session != null) httpContext.Session["maNgonNgu"] = maNgonNgu;
                }
                catch (Exception ex)
                {
                    Console.Out.WriteLine("Error: " + ex.StackTrace);
                }                
            }
        }
    }
}