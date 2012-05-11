﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using LocalServerWeb.Codes;

namespace LocalServerWeb.Controllers
{
    public class AdminHomeController : AdminBaseController
    {
        //
        // GET: /AdminHome/

        public ActionResult Index()
        {
            SharedCode.FillAdminMainMenu(ViewData, 0, 0);
            return View();
        }

    }
}