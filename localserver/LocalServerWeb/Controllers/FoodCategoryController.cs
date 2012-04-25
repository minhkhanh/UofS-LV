using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using LocalServerWeb.Codes;
using LocalServerWeb.ViewModels;
using LocalServerDTO;
using LocalServerBUS;
using LocalServerWeb.Resources.Views.FoodCategory;

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

            FoodCategorySidebarViewModel foodCategorySidebarViewModel = GetFoodCategorySidebarViewModel(id, true);
            ViewData["foodCategorySidebarViewModel"] = foodCategorySidebarViewModel;

            return View(id);
        }

        public ActionResult Food(int id)
        {
            FoodCategoryLinksViewModel foodCategoryLinksViewModel = GetFoodCategoryLinksViewModel(id, false);
            ViewData["foodCategoryLinksViewModel"] = foodCategoryLinksViewModel;

            FoodCategorySidebarViewModel foodCategorySidebarViewModel = GetFoodCategorySidebarViewModel(id, false);
            ViewData["foodCategorySidebarViewModel"] = foodCategorySidebarViewModel;

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
                viewModel.Names.Insert(0, FoodCategoryString.Index);
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
            viewModel.Names.Insert(0, FoodCategoryString.Index);
            viewModel.Ids.Insert(0, 0);
            viewModel.IsCategories.Insert(0, true);

            return viewModel;
        }

        private FoodCategorySidebarViewModel GetFoodCategorySidebarViewModel(int id, bool isCategory)
        {
            FoodCategorySidebarViewModel viewModel = new FoodCategorySidebarViewModel();
            viewModel.Names = new List<string>();
            viewModel.Ids = new List<int>();

            int maNgonNgu = (Session["ngonNgu"] != null) ? ((NgonNgu)Session["ngonNgu"]).MaNgonNgu : 1;

            try
            {
                if(id == 0)
                {
                    viewModel.ParentName = FoodCategoryString.Index;
                    viewModel.ParentId = 0;

                    List<DanhMuc> listDanhMuc = DanhMucBUS.LayDanhSachDanhMucCha();
                    for (int i = 0; i < listDanhMuc.Count; ++i)
                    {
                        ChiTietDanhMucDaNgonNgu ct = ChiTietDanhMucDaNgonNguBUS.LayChiTietDanhMucDaNgonNgu(listDanhMuc[i].MaDanhMuc, maNgonNgu);
                        viewModel.Names.Add(ct.TenDanhMuc);
                        viewModel.Ids.Add(listDanhMuc[i].MaDanhMuc);
                    }
                }
                else 
                {
                    DanhMuc danhMuc;
                    if (isCategory == true)
                        danhMuc = DanhMucBUS.LayDanhMuc(id);
                    else
                        danhMuc = MonAnBUS.LayMonAn(id).DanhMuc;

                    ChiTietDanhMucDaNgonNgu ctDanhMucDaNgonNgu = ChiTietDanhMucDaNgonNguBUS.LayChiTietDanhMucDaNgonNgu(danhMuc.MaDanhMuc, maNgonNgu);
                    viewModel.ParentName = ctDanhMucDaNgonNgu.TenDanhMuc;
                    viewModel.ParentId = danhMuc.MaDanhMuc;

                    List<DanhMuc> listDanhMuc = DanhMucBUS.LayDanhSachDanhMucTheoDanhMucCha(danhMuc.MaDanhMuc);
                    for (int i = 0; i < listDanhMuc.Count; ++i)
                    {
                        ChiTietDanhMucDaNgonNgu ct = ChiTietDanhMucDaNgonNguBUS.LayChiTietDanhMucDaNgonNgu(listDanhMuc[i].MaDanhMuc, maNgonNgu);
                        viewModel.Names.Add(ct.TenDanhMuc);
                        viewModel.Ids.Add(listDanhMuc[i].MaDanhMuc);
                    }
                }
                
            }
            catch (Exception e)
            {
                viewModel.Names = new List<string>();
                viewModel.Ids = new List<int>();
                viewModel.ParentName = FoodCategoryString.Index;
                viewModel.ParentId = 0;
            }

            return viewModel;
        }


    }
}