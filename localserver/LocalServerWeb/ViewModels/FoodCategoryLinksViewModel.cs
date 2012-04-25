using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using LocalServerBUS;
using LocalServerDTO;

namespace LocalServerWeb.ViewModels
{
    public class FoodCategoryLinksViewModel
    {
        public List<string> Names { get; set; }
        public List<int> Ids { get; set; }
        public List<bool> IsCategories { get; set; }

    }
}