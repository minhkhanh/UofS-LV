using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;

namespace LocalServerDAO
{
    public class MyLogger
    {
        public static void Log(string msg)
        {
            StreamWriter sw = new StreamWriter("c:\\slog.txt", true);
            sw.WriteLine("[" + DateTime.Now + "]" + msg);
            sw.Close();
        }
    }
}
