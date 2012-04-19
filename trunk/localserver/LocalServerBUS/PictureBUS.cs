using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using LocalServerDAO;
using LocalServerDTO;
using System.IO;

namespace LocalServerBUS
{
    public class PictureBUS
    {
        public static Stream GetPicture(string path)
        {
            return PictureDAO.GetPicture(path);
        }

        public static bool AddPicture(string path, Stream content)
        {
            return PictureDAO.AddPicture(path, content);
        }
    }
}
