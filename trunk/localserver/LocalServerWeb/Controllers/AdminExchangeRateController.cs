using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.Mvc;
using LocalServerBUS;
using LocalServerWeb.Codes;
using LocalServerWeb.Resources.Views.AdminExchangeRate;
using LocalServerWeb.Resources.Views.Shared;
using LocalServerDTO;
using LocalServerWeb.ViewModels;
using Webdiyer.WebControls.Mvc;
using System.Xml;

namespace LocalServerWeb.Controllers
{
    public class AdminExchangeRateController : BaseController 
    {
        public ActionResult Index(string page)
        {
            SharedCode.FillAdminMainMenu(ViewData, 3,8);

            int _page = 1;
            int.TryParse(page ?? "1", out _page);
            PagedList<TiGia> pageListTiGia = TiGiaBUS.LayDanhSachTiGiaThatSu().AsQueryable().ToPagedList(_page, 10);
            ViewData["listTiGia"] = pageListTiGia;
            ViewData["_page"] = _page;

            return View(pageListTiGia);
        }

        public ActionResult Delete(int? id)
        {
            if (id == null || id <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            TiGia objTiGia = TiGiaBUS.LayTiGia(id ?? 0);
            if (objTiGia == null)
            {
                TempData["error"] = AdminExchangeRateString.ErrorExchangeRateNotFound;
                return RedirectToAction("Index", "Error");
            }

            if (!TiGiaBUS.Xoa(objTiGia.MaTiGia))
            {
                TempData["errorCannotDelete"] = AdminExchangeRateString.ErrorCannotDelete;
            }
            else
            {
                TempData["infoDeleteSuccess"] = AdminExchangeRateString.InfoDeleteSuccess;
            }

            return RedirectToAction("Index");
        }

        public ActionResult Add()
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 9);

            if (TempData["checkDic"] == null)
            {
                TempData.Clear();
                TempData["checkDic"] = new Dictionary<string, string>();
            }
            return View();
        }

        [HttpPost]
        public ActionResult Add(string kiHieu, string giaTri)
        {
            TempData["kiHieu"] = kiHieu;
            TempData["giaTri"] = giaTri;

            var checkDic = new Dictionary<string, string>();

            bool bCheckOk = true;

            if (kiHieu == null || kiHieu.Trim().Length < 1)
            {
                bCheckOk = false;
                checkDic.Add("kiHieu", SharedString.InputWrong);
            }

            float _giaTri = 0;
            float.TryParse(giaTri, out _giaTri);
            if (_giaTri <= 0)
            {
                bCheckOk = false;
                checkDic.Add("giaTri", SharedString.InputWrong);
            }

            if (bCheckOk)
            {
                try
                {
                    TiGia tiGia = new TiGia();
                    tiGia.KiHieu = kiHieu;
                    tiGia.GiaTri = _giaTri;
                    tiGia.ThoiDiemCapNhat = DateTime.Now;


                    // Need to clear TempData
                    if (TiGiaBUS.Them(tiGia))
                    {
                        TempData["infoAddSuccess"] = AdminExchangeRateString.InfoAddSuccess;
                        return RedirectToAction("Index", "AdminExchangeRate");
                    }
                    else
                    {
                        TempData["errorCannotAdd"] = AdminExchangeRateString.ErrorCannotAdd;
                    }
                }
                catch (Exception e)
                {
                    System.Diagnostics.Debug.Write(e.StackTrace);
                }
            }

            TempData["checkDic"] = checkDic;
            return RedirectToAction("Add");

        }

        public ActionResult Edit(int? id)
        {
            SharedCode.FillAdminMainMenu(ViewData, 3, 9);

            if (TempData["checkDic"] == null)
            {
                TempData.Clear();
                TempData["checkDic"] = new Dictionary<string, string>();
            }

            if (id == null || id <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            TiGia objTiGia = TiGiaBUS.LayTiGia(id ?? 0);
            if (objTiGia == null)
            {
                TempData["errorNotFound"] = AdminExchangeRateString.ErrorExchangeRateNotFound;
                return RedirectToAction("Index", "Error");
            }
            else
            {
                TempData["kiHieu"] = objTiGia.KiHieu;
                TempData["giaTri"] = objTiGia.GiaTri.ToString();
            }

            return View();
        }

        [HttpPost]
        public ActionResult Edit(int maTiGia, string kiHieu, string giaTri)
        {
            TempData["kiHieu"] = kiHieu;
            TempData["giaTri"] = giaTri;

            var checkDic = new Dictionary<string, string>();

            bool bCheckOk = true;

            if (kiHieu == null || kiHieu.Trim().Length < 1)
            {
                bCheckOk = false;
                checkDic.Add("kiHieu", SharedString.InputWrong);
            }

            float _giaTri = 0;
            float.TryParse(giaTri, out _giaTri);
            if (_giaTri <= 0)
            {
                bCheckOk = false;
                checkDic.Add("giaTri", SharedString.InputWrong);
            }

            if (bCheckOk)
            {
                try
                {
                    TiGia tiGia = TiGiaBUS.LayTiGia(maTiGia);
                    tiGia.KiHieu = kiHieu;
                    tiGia.GiaTri = _giaTri;
                    tiGia.ThoiDiemCapNhat = DateTime.Now;


                    // Need to clear TempData
                    if (TiGiaBUS.CapNhat(tiGia))
                    {
                        TempData["infoEditSuccess"] = AdminExchangeRateString.InfoEditSuccess;
                        return RedirectToAction("Index", "AdminExchangeRate");
                    }
                    else
                    {
                        TempData["errorCannotEdit"] = AdminExchangeRateString.ErrorCannotEdit;
                    }
                }
                catch (Exception e)
                {
                    System.Diagnostics.Debug.Write(e.StackTrace);
                }
            }

            TempData["checkDic"] = checkDic;
            return RedirectToAction("Add");

        }

        public ActionResult Update(int? id)
        {
            if (id == null || id <= 0)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            TiGia objTiGia = TiGiaBUS.LayTiGia(id ?? 0);
            if (objTiGia == null)
            {
                TempData["error"] = SharedString.InputWrong;
                return RedirectToAction("Index", "Error");
            }

            // Need to GET from vnexpress.net
            float giaTri = CapNhatTiGia(objTiGia.KiHieu);
            if (giaTri != 0)
            {
                objTiGia.GiaTri = giaTri;
                objTiGia.ThoiDiemCapNhat = DateTime.Now;
                if (!TiGiaBUS.CapNhat(objTiGia))
                {
                    TempData["errorCannotUpdate"] = AdminExchangeRateString.ErrorCannotUpdate;
                }
                else
                {
                    TempData["infoUpdateSuccess"] = AdminExchangeRateString.InfoUpdateSuccess;
                }
            }
            else
            {
                TempData["errorCannotUpdate"] = AdminExchangeRateString.ErrorCannotUpdate;
            }

            return RedirectToAction("Index");
        }

        private float CapNhatTiGia(string kiHieu)
        {
            float tiGia = 0;
            try
            {
                XmlDocument doc = new XmlDocument();
                doc.Load("http://www.vietcombank.com.vn/ExchangeRates/ExrateXML.aspx");
                XmlNodeList elemList = doc.SelectNodes("//ExrateList/Exrate");
                for (int i = 0; i < elemList.Count; i++)
                {
                    if (elemList[i].Attributes["CurrencyCode"].Value.Equals(kiHieu))
                    {
                        string transfer = elemList[i].Attributes["Transfer"].Value;
                        int lastIndexOf = transfer.LastIndexOf('.');
                        if (lastIndexOf != -1)
                        {
                            transfer = transfer.Remove(lastIndexOf);
                        }
 
                        float.TryParse(transfer, out tiGia);
                        break;
                    }
                }
            }
            catch (Exception e)
            {
                tiGia = 0;
            }

            return tiGia;
            
        }
    }
}