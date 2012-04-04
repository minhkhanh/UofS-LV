using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace eMenu
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "LocalService" in code, svc and config file together.
    public class LocalService : ILocalService
    {
        public int PhepCong(int a, int b)
        {
            return a + b;
        }
    }
}
