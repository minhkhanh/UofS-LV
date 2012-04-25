using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using LocalServerDTO;

namespace LocalServerWeb.ViewModels
{
    public class FoodDetailViewModel
    {
        public string HinhAnh { get; set; }
        public int MaMonAn { get; set; }
        public string TenMonAn { get; set; }
        public string MoTaMonAn { get; set; }

        public float DonGiaMacDinh { get; set; }
        public string TenDonViTinhMacDinh { get; set; }

        public List<float> listDonGia { get; set; }
        public List<string> listTenDonViTinh { get; set;}

        public string TenDanhMuc { get; set;}

        public float DiemDanhGia { get; set; }
        public int SoLuotDanhGia { get; set; }

        public List<FoodGalleryItemViewModel> FoodGalleryItems { get; set; }
    }
}