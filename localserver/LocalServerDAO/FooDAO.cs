using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDTO;

namespace LocalServerDAO
{
    public class FooDAO
    {
        public static List<Foo> LayDanhSachFoo()
        {
            List<Foo> listFoo = new List<Foo>();
            Foo a = new Foo();
            a.id = 1;
            a.name = "superman";

            Foo b = new Foo();
            b.id = 2;
            b.name = "spider";

            listFoo.Add(a);
            listFoo.Add(b);

            return listFoo;

        }
    }
}
