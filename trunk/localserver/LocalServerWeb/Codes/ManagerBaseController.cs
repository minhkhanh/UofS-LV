﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Routing;
using LocalServerWeb.Resources.Views.Error;

namespace LocalServerWeb.Codes
{
    public class ManagerBaseController: BaseController
    {
        protected override void OnActionExecuting(System.Web.Mvc.ActionExecutingContext filterContext)
        {
            if (!SharedCode.IsManagerLogin(HttpContext.Session))
            {
                TempData["error"] = ErrorString.AuthenticationFailen;
                filterContext.Result = RedirectToAction("Index", "Error");
            }
            base.OnActionExecuting(filterContext);
        }
    }
}