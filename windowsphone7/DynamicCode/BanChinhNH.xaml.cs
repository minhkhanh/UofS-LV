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
using DynamicCode.ViewModel;

namespace DynamicCode
{
    public partial class BanChinhNH : PhoneApplicationPage
    {
        public BanChinhNH()
        {
            InitializeComponent();
            list_DanhSachBanDuocGhep.IsHitTestVisible = true;
        }
        private string maKhuVuc;
        protected override void  OnNavigatedTo(System.Windows.Navigation.NavigationEventArgs e)
        { 	        
            base.OnNavigatedTo(e);            
            
            string[] chuoiDanhSachBanGhep;
            string msg = "";

            if ( NavigationContext.QueryString.TryGetValue("msg", out msg) )
            {
                chuoiDanhSachBanGhep = msg.Split(',');                
                LoadDanhSachBanGhep(chuoiDanhSachBanGhep);
                maKhuVuc = chuoiDanhSachBanGhep[chuoiDanhSachBanGhep.Count() - 1];
            }
            
            else
            {

            }                                                                                
        }

        private List<Ban> DanhSachBanGhep;

        public void LoadDanhSachBanGhep(string []chuoiDanhSachBanGhep)
        {
            if (LayDuLieuTuServer.DanhSachBanCacKhuVuc != null)
            {
                DanhSachBanGhep = new List<Ban>();
                //Tìm danh sách bàn trong khu vực
                for (int i = 0; i < LayDuLieuTuServer.DanhSachBanCacKhuVuc.Count(); ++i)
                {
                    for (int j = 0; j < chuoiDanhSachBanGhep.Count() - 1; ++j)
                    {
                        if (LayDuLieuTuServer.DanhSachBanCacKhuVuc[i].MaBan.ToString() == chuoiDanhSachBanGhep[j])
                        {
                            DanhSachBanGhep.Add(LayDuLieuTuServer.DanhSachBanCacKhuVuc[i]);
                            list_DanhSachBanDuocGhep.Items.Add(LayDuLieuTuServer.DanhSachBanCacKhuVuc[i]);
                        }
                    }
                }
            }                        
        }
        
        
        private int maBanChinh = -1;
        
        private void list_DanhSachBanDuocGhep_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            int index = list_DanhSachBanDuocGhep.SelectedIndex;
            ListBoxItem item = this.list_DanhSachBanDuocGhep.ItemContainerGenerator.ContainerFromIndex(index) as ListBoxItem;
            CheckBox tagregCheckBox = FindFirstElementInVisualTree<CheckBox>(item);
            tagregCheckBox.IsChecked = true;

            ListBoxItem selectedItem = this.list_DanhSachBanDuocGhep.ItemContainerGenerator.ContainerFromItem(this.list_DanhSachBanDuocGhep.SelectedItem) as ListBoxItem;
            maBanChinh = (selectedItem.DataContext as Ban).MaBan;            

            for (int i = 0; i < list_DanhSachBanDuocGhep.Items.Count(); i++)
            {
                if (index != i)
                {
                    ListBoxItem itemPhu = this.list_DanhSachBanDuocGhep.ItemContainerGenerator.ContainerFromIndex(i) as ListBoxItem;
                    CheckBox tagregCheckBoxPhu = FindFirstElementInVisualTree<CheckBox>(itemPhu);
                    tagregCheckBoxPhu.IsChecked = false;
                }
            }
            list_DanhSachBanDuocGhep.IsHitTestVisible = false;
            bt_QuayVe.IsHitTestVisible = false;
        }

        private T FindFirstElementInVisualTree<T>(DependencyObject parentElement) where T : DependencyObject
        {
            var count = VisualTreeHelper.GetChildrenCount(parentElement);
            if (count == 0)
                return null;

            for (int i = 0; i < count; i++)
            {
                var child = VisualTreeHelper.GetChild(parentElement, i);

                if (child != null && child is T)
                {
                    return (T)child;
                }
                else
                {
                    var result = FindFirstElementInVisualTree<T>(child);
                    if (result != null)
                        return result;
                }
            }
            return null;
        }
        
        
        private void Xacnhan_MessageBox_Click(object sender, RoutedEventArgs e)
        {
            if (maBanChinh != -1)
            {
                //Thay đổi csdl của hệ thống cho phù hợp với nhóm bàn
                if (LayDuLieuTuServer.DanhSachBanCacKhuVuc != null)
                {
                    for (int i = 0; i < LayDuLieuTuServer.DanhSachBanCacKhuVuc.Count(); i++)
                    {
                        for (int j = 0; j < DanhSachBanGhep.Count; j++)
                        {
                            //Lấy ra danh sách các bàn cần ghép trong csdl
                            //trong đó không lấy bàn chính
                            if (LayDuLieuTuServer.DanhSachBanCacKhuVuc[i].MaBan == DanhSachBanGhep[j].MaBan && DanhSachBanGhep[j].MaBan != maBanChinh)
                            {
                                LayDuLieuTuServer.DanhSachBanCacKhuVuc[i]._maBanChinh = maBanChinh;
                                LayDuLieuTuServer.DanhSachBanCacKhuVuc[i].Active = false;                             
                            }                                                                                
                        }

                        //Lưu trữ cho bàn chính
                        if (LayDuLieuTuServer.DanhSachBanCacKhuVuc[i].MaBan == maBanChinh)
                        {
                            LayDuLieuTuServer.DanhSachBanCacKhuVuc[i]._maBanChinh = null;
                            LayDuLieuTuServer.DanhSachBanCacKhuVuc[i].Active = false;                            
                        }
                    }
                }

                NavigationService.Navigate(new Uri("/BanNH.xaml?msg=" + maKhuVuc, UriKind.Relative));
            }
        }
     

        private void Thoat_MessageBox_Click(object sender, RoutedEventArgs e)
        {
            list_DanhSachBanDuocGhep.IsHitTestVisible = true;
            bt_QuayVe.IsHitTestVisible = true;
        }

        private void bt_QuayVe_Tap(object sender, GestureEventArgs e)
        {
            NavigationService.Navigate(new Uri("/GhepBanNH.xaml?msg=" + maKhuVuc, UriKind.Relative));
        }
    }
}