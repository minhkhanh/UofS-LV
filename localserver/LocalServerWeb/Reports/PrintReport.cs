using System;
using System.Collections.Generic;
using System.Data;
using System.Drawing;
using System.Drawing.Imaging;
using System.Drawing.Printing;
using System.IO;
using System.Linq;
using System.Text;
using System.Web;
using Microsoft.Reporting.WebForms;

namespace LocalServerWeb.Reports
{
    public class PrintReport: IDisposable
    {
        private int m_currentPageIndex;
        private IList<Stream> m_streams;
        private LocalReport report;
        private PrintDocument printDoc;
        private string deviceInfo;

        // Routine to provide to the report renderer, in order to
        //    save an image for each page of the report.
        private Stream CreateStream(string name,
          string fileNameExtension, Encoding encoding,
          string mimeType, bool willSeek)
        {
            Stream stream = new MemoryStream();
            m_streams.Add(stream);
            return stream;
        }
        // Export the given report as an EMF (Enhanced Metafile) file.
        private void Export(LocalReport report)
        {
            Warning[] warnings;
            m_streams = new List<Stream>();
            report.Render("Image", deviceInfo, CreateStream,
               out warnings);
            foreach (Stream stream in m_streams)
                stream.Position = 0;
        }
        // Handler for PrintPageEvents
        private void PrintPage(object sender, PrintPageEventArgs ev)
        {
            Metafile pageImage = new
               Metafile(m_streams[m_currentPageIndex]);

            // Adjust rectangular area with printer margins.
            Rectangle adjustedRect = new Rectangle(
                ev.PageBounds.Left - (int)ev.PageSettings.HardMarginX,
                ev.PageBounds.Top - (int)ev.PageSettings.HardMarginY,
                ev.PageBounds.Width,
                ev.PageBounds.Height);

            // Draw a white background for the report
            ev.Graphics.FillRectangle(Brushes.White, adjustedRect);

            // Draw the report content
            ev.Graphics.DrawImage(pageImage, adjustedRect);

            // Prepare for the next page. Make sure we haven't hit the end.
            m_currentPageIndex++;
            ev.HasMorePages = (m_currentPageIndex < m_streams.Count);
        }

        public void Print()
        {
            Export(report);
            
            if (m_streams == null || m_streams.Count == 0) throw new Exception("Error: no stream to print.");
            m_currentPageIndex = 0;
            printDoc.Print();
        }

        public PrintReport(string reportPath, string printer, string deviceInfo)
        {
            this.deviceInfo = deviceInfo;
            report = new LocalReport();
            report.ReportEmbeddedResource = reportPath;
            report.DataSources.Clear();

            // Su dung external image
            //report.EnableExternalImages = true;

            printDoc = new PrintDocument();
            printDoc.PrinterSettings.PrinterName = printer;
            printDoc.PrintPage += new PrintPageEventHandler(PrintPage);            
        }

        public void AddDataSoruce(ReportDataSource dataSource)
        {
            report.DataSources.Add(dataSource);
        }

        public void SetParameters(ReportParameter[] listParameter)
        {
            report.Refresh();
            report.SetParameters(listParameter);
        }

        public void Dispose()
        {
            if (m_streams != null)
            {
                foreach (Stream stream in m_streams)
                    stream.Close();
                m_streams = null;
            }
        }
    }
}