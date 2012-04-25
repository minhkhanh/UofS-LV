using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using LocalServerWeb.Codes;
using LocalServerWeb.ViewModels;
using LocalServerDTO;
using LocalServerBUS;

namespace LocalServerWeb.Controllers
{
    public class FoodCategoryController : BaseController
    {
        // **************************************
        // URL: /FoodCategory
        // **************************************

        public ActionResult Index()
        {
            return Redirect("/FoodCategory/Category/0");
        }

        public ActionResult Category(int id)
        {
            FoodCategoryLinksViewModel foodCategoryLinksViewModel = GetFoodCategoryLinksViewModel(id, true);
            ViewData["foodCategoryLinksViewModel"] = foodCategoryLinksViewModel;
            return View(id);
        }

        public ActionResult Food(int id)
        {
            FoodCategoryLinksViewModel foodCategoryLinksViewModel = GetFoodCategoryLinksViewModel(id, false);
            ViewData["foodCategoryLinksViewModel"] = foodCategoryLinksViewModel;
            return View(id);
        }

        private FoodCategoryLinksViewModel GetFoodCategoryLinksViewModel(int id, bool isCategory)
        {
            FoodCategoryLinksViewModel viewModel = new FoodCategoryLinksViewModel();
            viewModel.Names = new List<string>();
            viewModel.Ids = new List<int>();
            viewModel.IsCategories = new List<bool>();

            if (id == 0)
            {
                viewModel.Names.Insert(0, "Index");
                viewModel.Ids.Insert(0, 0);
                viewModel.IsCategories.Insert(0, true);

                return viewModel;
            }

            int maDanhMuc = id;
            int maNgonNgu = (Session["ngonNgu"] != null) ? ((NgonNgu)Session["ngonNgu"]).MaNgonNgu : 1;

            try
            {
                if (isCategory == false)
                {
                    MonAn monAn = MonAnBUS.LayMonAn(id); 
                    ChiTietMonAnDaNgonNgu ctMonAnDaNgonNgu = ChiTietMonAnDaNgonNguBUS.LayChiTietMonAnDaNgonNgu(monAn.MaMonAn, maNgonNgu);
                    
                    viewModel.Names.Add(ctMonAnDaNgonNgu.TenMonAn);
                    viewModel.Ids.Add(id);
                    viewModel.IsCategories.Add(false);

                    maDanhMuc = monAn.DanhMuc.MaDanhMuc;
                }

                while (DanhMucBUS.LayDanhMuc(maDanhMuc) != null)
                {
                    DanhMuc temp = DanhMucBUS.LayDanhMuc(maDanhMuc);
                    ChiTietDanhMucDaNgonNgu ctDanhMucDaNgonNgu = ChiTietDanhMucDaNgonNguBUS.LayChiTietDanhMucDaNgonNgu(maDanhMuc, maNgonNgu);

                    viewModel.Names.Insert(0, ctDanhMucDaNgonNgu.TenDanhMuc);
                    viewModel.Ids.Insert(0, maDanhMuc);
                    viewModel.IsCategories.Insert(0, true);

                    maDanhMuc = (temp.DanhMucCha != null) ? temp.DanhMucCha.MaDanhMuc : 0;
                }
    
            }
            catch (Exception e)
            {
                viewModel.Names = new List<string>();
                viewModel.Ids = new List<int>();
                viewModel.IsCategories = new List<bool>();
            }

            // Alwasy has Index at the beginning
            viewModel.Names.Insert(0, "Index");
            viewModel.Ids.Insert(0, 0);
            viewModel.IsCategories.Insert(0, true);

            return viewModel;
        }


    }
}