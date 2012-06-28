using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using LocalServerBUS;
using LocalServerWeb.Codes;

namespace LocalServerWeb.Controllers
{
    public class LanguageController : ManagerBaseController
    {
        [HttpPost]
        public ActionResult ChangeLanguage(string kiHieuNgonNgu, string returnUrlLanguage)
        {
            try
            {
                var ngonNgu = NgonNguBUS.LayNgonNguTheoKiHieu(kiHieuNgonNgu);
                if (ngonNgu != null && Session != null) 
                    Session["ngonNgu"] = ngonNgu;
                //if (Request.UrlReferrer != null)
                return Redirect(returnUrlLanguage);
                    //return new RedirectResult(Request.UrlReferrer.ToString());
            }
            catch (Exception ex)
            {
                System.Diagnostics.Debug.Write("Error: " + ex.StackTrace);
            }    
            return new EmptyResult();
        }

    }
}
