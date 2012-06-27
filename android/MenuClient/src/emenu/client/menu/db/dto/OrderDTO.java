package client.menu.db.dto;

import java.io.StringReader;

import javax.xml.transform.stream.StreamResult;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.provider.BaseColumns;
import client.menu.util.XmlSerializerWrapper;

public class OrderDTO {
    public static final String TABLE_NAME = "Order";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_MA_ORDER = "MaOrder";
    public static final String CL_MA_TAI_KHOAN = "MaTaiKhoan";
    public static final String CL_MA_BAN = "MaBan";
//    public static final String CL_TINH_TRANG = "TinhTrang";

    private Integer mId;
    private Integer mMaOrder;
    private Integer mMaTaiKhoan = 1;
    private Integer mMaBan;
//    private Integer mTinhTrang = 0;

    public static final OrderDTO fromXml(String xmlData) {
        try {
            XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();
            parser.setInput(new StringReader(xmlData));
            return fromXml(parser);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static final OrderDTO fromXml(XmlPullParser parser) {
        OrderDTO obj = null;

        try {
            int type = parser.getEventType();
            String tag = "";
            String text;

            while (type != XmlPullParser.END_DOCUMENT) {
                switch (type) {
                    case XmlPullParser.START_TAG:
                        tag = parser.getName();
                        if (tag.compareTo(TABLE_NAME) == 0) {
                            obj = new OrderDTO();
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        tag = parser.getName();
                        if (tag.compareTo(TABLE_NAME) == 0) {
                            return obj;
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        if (text.trim().length() == 0) {
                            break;
                        }
                        if (tag.compareTo(CL_MA_ORDER) == 0) {
                            obj.mMaOrder = Integer.valueOf(text);
                        } else if (tag.compareTo(CL_MA_TAI_KHOAN) == 0) {
                            obj.mMaTaiKhoan = Integer.valueOf(text);
                        } else if (tag.compareTo(CL_MA_BAN) == 0) {
                            obj.mMaBan = Integer.valueOf(text);
                        } 
//                        else if (tag.compareTo(CL_TINH_TRANG) == 0) {
//                            obj.mTinhTrang = Integer.valueOf(text);
//                        }
                        break;

                    default:
                        break;
                }

                type = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String toXml() {
        XmlSerializerWrapper serializer = new XmlSerializerWrapper();

        try {
            serializer.startDocument();
            serializer.startTag(TABLE_NAME);
            serializer.writeSimpleElement(CL_MA_BAN, mMaBan);
            serializer.writeSimpleElement(CL_MA_ORDER, mMaOrder);
            serializer.writeSimpleElement(CL_MA_TAI_KHOAN, mMaTaiKhoan);
//            serializer.writeSimpleElement(CL_TINH_TRANG, mTinhTrang);
            serializer.endTag(TABLE_NAME);
            serializer.endDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return serializer.toString();
    }

//    public Integer getTinhTrang() {
//        return mTinhTrang;
//    }
//
//    public void setTinhTrang(Integer tinhTrang) {
//        mTinhTrang = tinhTrang;
//    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public Integer getMaOrder() {
        return mMaOrder;
    }

    public void setMaOrder(Integer maOrder) {
        mMaOrder = maOrder;
    }

    public Integer getMaTaiKhoan() {
        return mMaTaiKhoan;
    }

    public void setMaTaiKhoan(Integer maTaiKhoan) {
        mMaTaiKhoan = maTaiKhoan;
    }

    public Integer getMaBan() {
        return mMaBan;
    }

    public void setMaBan(Integer maBan) {
        mMaBan = maBan;
    }

}
