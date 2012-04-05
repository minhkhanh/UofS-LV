using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;
using LocalServerDAO;

namespace LocalServerBUS
{
    public class FooBUS
    {
        public static List<Foo> LayDanhSachFoo()
        {
            return FooDAO.LayDanhSachFoo();
        }
    }
}
