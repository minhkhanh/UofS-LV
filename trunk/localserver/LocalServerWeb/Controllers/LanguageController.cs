using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using LocalServerBUS;
using LocalServerWeb.Codes;

namespace LocalServerWeb.Controllers
{
    public class LanguageController : BaseController
    {
        //
        // GET: /Language/
        [HttpPost]
        public ActionResult ChangeLanguage(int maNgonNgu)
        {
            //SharedCode.StoreCommonData2Section(HttpContext);
            //SharedCode.FillCommonData2View(ViewData, HttpContext);
            try
            {                
                var ngonNgu = NgonNguBUS.LayNgonNguTheoMa(maNgonNgu);
                if (ngonNgu != null && Session != null) Session["maNgonNgu"] = maNgonNgu;
                if (Request.UrlReferrer != null) 
                    return new RedirectResult(Request.UrlReferrer.ToString());
            }
            catch (Exception ex)
            {
                Console.Out.WriteLine("Error: " + ex.StackTrace);
            }    
            return new EmptyResult();
            //return RedirectToAction("Index", "Home");
        }

    }
}
