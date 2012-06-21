using System;
using System.Data;
using System.Configuration;
using System.Linq;
using System.Web;
using System.Xml.Linq;
using System.IO;
using System.Reflection;
using System.Drawing;
using System.Web.Hosting;
using System.Drawing.Imaging;

namespace LocalServerDAO
{
    public class PictureDAO
    {
        public static Stream GetPicture(string path)
        {
            string imagePath;
            try
            {
                imagePath = HostingEnvironment.ApplicationPhysicalPath + path;

                if (File.Exists(imagePath))
                {
                    MemoryStream memoryStream = new MemoryStream();
                    Bitmap image = new Bitmap(imagePath);
                    image.Save(memoryStream, ImageFormat.Jpeg);
                    memoryStream.Position = 0;

                    return memoryStream;
                }
                return null;
            }
            catch (Exception)
            {
                return null;
            }
        }

        public static string GetBase64Image(string path)
        {
            string imagePath;
            try
            {
                imagePath = HostingEnvironment.ApplicationPhysicalPath + path;

                if (File.Exists(imagePath))
                {
                    MemoryStream memoryStream = new MemoryStream();
                    Bitmap image = new Bitmap(imagePath);

                    ImageFormat ext = ImageFormat.Jpeg;
                    if (imagePath.EndsWith(".png"))
                    {
                        ext = ImageFormat.Png;
                    }
                    else if (imagePath.EndsWith(".bmp"))
                    {
                        ext = ImageFormat.Bmp;
                    }
                    image.Save(memoryStream, ext);
                    //memoryStream.Position = 0;

                    byte[] imageBytes = memoryStream.ToArray();

                    // Convert byte[] to Base64 String
                    string base64String = Convert.ToBase64String(imageBytes);

                    return base64String;
                }
                return null;
            }
            catch (Exception)
            {
                return null;
            }
        }


        public static bool AddPicture(string path, Stream content)
        {
            FileStream fileStream = null;
            string imagePath;

            try
            {
                //filePath = HttpContext.Current.Server.MapPath(".") +
                //           ConfigurationManager.AppSettings["PictureUploadDirectory"] +
                //           picture.PictureName;

                imagePath = HostingEnvironment.ApplicationPhysicalPath + path;

                Bitmap image = new Bitmap(content);
                image.Save(imagePath);

                return true;
            }
            catch (Exception)
            {
                return false;
            }
            finally
            {
                if (fileStream != null)
                    fileStream.Close();
            }
            return true;
        }
    }
}
