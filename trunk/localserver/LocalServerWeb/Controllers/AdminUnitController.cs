using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.Mvc;
using LocalServerBUS;
using LocalServerWeb.Codes;
using LocalServerWeb.Resources.Views.AdminUnit;
using LocalServerWeb.Resources.Views.Shared;
using LocalServerDTO;
using LocalServerWeb.ViewModels;


namespace LocalServerWeb.Controllers
{
    public class AdminUnitController : BaseController
    {
        public ActionResult Index()
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 2);
            ViewData["listDonViTinh"] = LayDanhSachDonViTinh();
            return View();
        }

        private List<DonViTinh> LayDanhSachDonViTinh()
        {
            int maNgonNgu = (Session["ngonNgu"] != null) ? ((NgonNgu)Session["ngonNgu"]).MaNgonNgu : 1;
            List<DonViTinh> listDonViTinh = DonViTinhBUS.LayDanhSachDonViTinh();

            foreach (DonViTinh DonViTinh in listDonViTinh)
            {
                try
                {
                    ChiTietDonViTinhDaNgonNgu ct = ChiTietDonViTinhDaNgonNguBUS.LayChiTietDonViTinhDaNgonNgu(DonViTinh.MaDonViTinh, maNgonNgu);
                    if (ct != null)
                    {
                        DonViTinh.TenDonViTinh = ct.TenDonViTinh;
                    }
                    else
                    {
                        DonViTinh.TenDonViTinh = "";
                    }

                }
                catch (Exception e)
                {
                    DonViTinh.TenDonViTinh = "";
                    Console.WriteLine(e.Message);
                }
            }

            return listDonViTinh;
        }

        public ActionResult Delete(int? id)
        {
            if (id == null || id <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            DonViTinh objDonViTinh = DonViTinhBUS.LayDonViTinhTheoMa(id ?? 0);
            if (objDonViTinh == null)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            if (!DonViTinhBUS.Xoa(objDonViTinh.MaDonViTinh))
            {
                TempData["errorCannotDelete"] = AdminUnitString.ErrorCannotDelete;
            }

            return RedirectToAction("Index");
        }
    }
}