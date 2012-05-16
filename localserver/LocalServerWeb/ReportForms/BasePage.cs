using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using LocalServerWeb.Codes;

namespace LocalServerWeb.ReportForms
{
    public class BasePage: Page
    {
        protected void Page_Init(object sender, EventArgs e)
        {
            SharedCode.KhoiTaoCSDL();            
        }
        protected void Page_LoadComplete(object sender, EventArgs e)
        {
            //phan quyen
            //Response.Write("<script> window.close();</script>");
            //throw new Exception("access denied");
        }
    }
}