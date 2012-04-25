using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using LocalServerDTO;

namespace LocalServerWeb.ViewModels
{
    public class FoodGalleryItemViewModel
    {
        public string HinhAnh { get; set; }
        public int MaMonAn { get; set; }
        public string TenMonAn { get; set; }
        public float DonGia { get; set; }
        public string TenDonViTinhMacDinh { get; set; }
        
    }
}