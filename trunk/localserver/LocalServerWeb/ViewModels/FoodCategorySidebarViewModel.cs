using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using LocalServerDTO;

namespace LocalServerWeb.ViewModels
{
    public class FoodCategorySidebarViewModel
    {
        public string ParentName { get; set; }
        public int ParentId { get; set; }
        public List<string> Names { get; set; }
        public List<int> Ids { get; set; }
    }
}