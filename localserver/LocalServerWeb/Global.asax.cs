using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.ServiceModel.Activation;
using System.Threading;
using System.Web;
using System.Web.Mvc;
using System.Web.Routing;
using LocalServerDTO;
using LocalServerWeb.Codes;

namespace LocalServerWeb
{
    // Note: For instructions on enabling IIS6 or IIS7 classic mode, 
    // visit http://go.microsoft.com/?LinkId=9394801

    public class MvcApplication : System.Web.HttpApplication
    {
        public static void RegisterRoutes(RouteCollection routes)
        {
            routes.IgnoreRoute("{resource}.axd/{*pathInfo}");

            //routes.Add(new ServiceRoute("LocalService", new ServiceHostFactory(), typeof(LocalService)));

            routes.MapRoute(
                "Default", // Route name
                "{controller}/{action}/{id}", // URL with parameters
                new { controller = "Home", action = "Index", id = UrlParameter.Optional } // Parameter defaults
            );
        }

        protected void Application_Start()
        {
            AreaRegistration.RegisterAllAreas();

            RegisterRoutes(RouteTable.Routes);
        }

        protected void Application_PreRequestHandlerExecute(object sender, EventArgs e)
        {
            if (Context.Session!=null)
            {
                HttpSessionStateBase session = new HttpSessionStateWrapper(Context.Session);
                SharedCode.LoadUserCulture(session);                
            }
        }

        protected void Session_Start(object sender, EventArgs e)
        {
            if (Session["nguoiDungOnline"]!=null)
            {
                
            }
        }
    }
}