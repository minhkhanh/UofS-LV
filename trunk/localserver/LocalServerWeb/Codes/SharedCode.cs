﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace LocalServerWeb.Codes
{
    public class SharedCode
    {
        private static void FillLanguage(ViewDataDictionary ViewData)
        {
            var list = new List<String>();
            list.Add("aaa");
            list.Add("bbb");
            list.Add("XXX");
            list.Add("ccc");
            SelectList items = new SelectList(list);

            ViewData["MyListItems"] = items;
        }

        public static void FillCommonData2View(ViewDataDictionary ViewData)
        {
            FillLanguage(ViewData);
        }
    }
}