using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;
using Microsoft.Phone.Controls;

using DynamicCode.ServerConnect;

namespace DynamicCode
{
    public partial class Order : PhoneApplicationPage
    {
        public Order()
        {
            InitializeComponent();
        }
        
        protected override void OnNavigatedTo(System.Windows.Navigation.NavigationEventArgs e)
        {            
            base.OnNavigatedTo(e);
            string msg = "";
            if (NavigationContext.QueryString.TryGetValue("msg", out msg))
            {                         
				this.TextName.Text +=  " "+ msg;                
            }
            
        }

        private void ApplicationBarIconButton_Click(object sender, EventArgs e)
        {
            NavigationService.Navigate(new Uri("/BanNH.xaml", UriKind.Relative));
        }
    }
}