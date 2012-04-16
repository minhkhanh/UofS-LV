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
using DynamicCode.ViewModel;
using System.Runtime.Serialization;

namespace DynamicCode.ServerConnect
{
    public partial class LayDuLieuTuServer : PhoneApplicationPage
    {
        public LayDuLieuTuServer()
        {
            InitializeComponent();
            
        }
        
        //Trước tiên lưu trữ dạng static, tuần sau sẽ lưu trữ trên csdl của chính điệnt thoại
        public static KhuVuc[] DachSachCacKhuVuc;        
        public static Ban[] DanhSachBanCacKhuVuc;
                
        public static void LayDanhSachKhuVucTuServer()
        {
            HttpWebRequest req = HttpWebRequest.Create("http://localhost:5252/LocalService.svc/layDanhSachKhuVuc") as HttpWebRequest;
            req.Method = "GET";

            req.BeginGetResponse(HandleResponseKhuVuc, req);
        }


        public static void HandleResponseKhuVuc(IAsyncResult result)
        {
            try
            {
                HttpWebRequest req = (HttpWebRequest)result.AsyncState;
                HttpWebResponse res = (HttpWebResponse)req.EndGetResponse(result);
                DataContractSerializer ser = new DataContractSerializer(typeof(KhuVuc[]));
                DachSachCacKhuVuc = (KhuVuc[])ser.ReadObject(res.GetResponseStream());
            }
            catch (Exception ex)
            {
            
            }
        }


        public static void LayDanhSachBanTuServer()
        {
            HttpWebRequest req = HttpWebRequest.Create("http://localhost:5252/LocalService.svc/layDanhSachBan") as HttpWebRequest;
            req.Method = "GET";

            req.BeginGetResponse(HandleResponseBan, req);
        }

        public static void HandleResponseBan(IAsyncResult result)
        {
            try
            {
                HttpWebRequest req = (HttpWebRequest)result.AsyncState;
                HttpWebResponse res = (HttpWebResponse)req.EndGetResponse(result);
                DataContractSerializer ser = new DataContractSerializer(typeof(Ban[]));
                DanhSachBanCacKhuVuc = (Ban[])ser.ReadObject(res.GetResponseStream());
                int a;
            }
            catch (Exception ex)
            {
            
            }            
        }

        
        
        
        public static DanhMuc[] DanhSachDanhMuc;
        public static void LayDanhSachDanhMuc()
        {
            HttpWebRequest req = HttpWebRequest.Create("http://localhost:5252/LocalService.svc/layDanhSachDanhMuc") as HttpWebRequest;
            req.Method = "GET";

            req.BeginGetResponse(HandleResponseDanhMuc, req);
        }
        
        public static void HandleResponseDanhMuc(IAsyncResult result)
        {
            try
            {
                HttpWebRequest req = (HttpWebRequest)result.AsyncState;
                HttpWebResponse res = (HttpWebResponse)req.EndGetResponse(result);
                DataContractSerializer ser = new DataContractSerializer(typeof(DanhMuc[]));
                DanhSachDanhMuc = (DanhMuc[])ser.ReadObject(res.GetResponseStream());
                int a;
            }
            catch (Exception ex)
            {

            }
        }









        private void KhuVuc_Click(object sender, RoutedEventArgs e)
        {
            LayDanhSachKhuVucTuServer();               
        }
        
        private void Ban_Click(object sender, RoutedEventArgs e)
        {
            LayDanhSachBanTuServer();            
        }
        
        private void bt_MonAn_Click(object sender, RoutedEventArgs e)
        {
            LayDanhSachDanhMuc();
        }      
        
        private void TatCa_Click(object sender, RoutedEventArgs e)
        {
            LayDanhSachKhuVucTuServer();
            LayDanhSachBanTuServer();            
        }

        private void HeThong_Click(object sender, RoutedEventArgs e)
        {
            NavigationService.Navigate(new Uri("/KhuVucNH.xaml", UriKind.Relative));
        }          
    }
}