using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace LocalServerWeb.Codes
{
    public class BaseController: Controller
    {
        public BaseController()
        {
            SharedCode.KhoiTaoCSDL();            
        }

        protected override void OnActionExecuted(ActionExecutedContext filterContext)
        {
            base.OnActionExecuted(filterContext);
            SharedCode.FillCommonData2View(ViewData, HttpContext);
        }
    }
}