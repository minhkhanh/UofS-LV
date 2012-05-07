using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.Mvc;
using LocalServerBUS;
using LocalServerWeb.Codes;
using LocalServerWeb.Resources.Views.AdminOrder;
using LocalServerDTO;
using LocalServerWeb.ViewModels;
namespace LocalServerWeb.Controllers
{
    public class AdminInvoiceController : BaseController
    {
        public ActionResult Index()
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 1);

            return View();
        }

        public ActionResult InvoiceDetail(int? id)
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 1);
            if (id == null || id < 1)
                return View();

            int maNgonNgu = (Session["ngonNgu"] != null) ? ((NgonNgu)Session["ngonNgu"]).MaNgonNgu : 1;

            return View();
        }
    }
}