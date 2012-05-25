package client.menu.db.dto;

import java.io.IOException;
import java.io.StringReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.database.Cursor;
import android.provider.BaseColumns;

public class NgonNguDTO {

    public static final String TABLE_NAME = "NgonNgu";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_MA_NGON_NGU = "MaNgonNgu";
    public static final String CL_TEN_NGON_NGU = "TenNgonNgu";
    public static final String CL_KI_HIEU = "KiHieu";

    private Integer mId;
    private Integer mMaNgonNgu;
    private String mTenNgonNgu;
    private String mKiHieu;

    public static NgonNguDTO valueOf(XmlPullParser parser) {
        NgonNguDTO obj = null;

        try {
            int type = parser.getEventType();
            String tag = "";
            String text;

            while (type != XmlPullParser.END_DOCUMENT) {
                switch (type) {
                    case XmlPullParser.START_TAG:
                        tag = parser.getName();
                        if (tag.compareTo(TABLE_NAME) == 0) {
                            obj = new NgonNguDTO();
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
                        if (tag.compareTo(CL_MA_NGON_NGU) == 0) {
                            obj.mMaNgonNgu = Integer.valueOf(text);
                        } else if (tag.compareTo(CL_TEN_NGON_NGU) == 0) {
                            obj.mTenNgonNgu = text;
                        } else if (tag.compareTo(CL_KI_HIEU) == 0) {
                            obj.mKiHieu = text;
                        }
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

    public static NgonNguDTO valueOf(Cursor cursor) {
        NgonNguDTO obj = new NgonNguDTO();

        int index;
        if ((index = cursor.getColumnIndex(CL_ID)) != -1) {
            obj.mId = cursor.getInt(index);
        }
        if ((index = cursor.getColumnIndex(CL_MA_NGON_NGU)) != -1) {
            obj.mMaNgonNgu = cursor.getInt(index);
        }
        if ((index = cursor.getColumnIndex(CL_TEN_NGON_NGU)) != -1) {
            obj.mTenNgonNgu = cursor.getString(index);
        }
        if ((index = cursor.getColumnIndex(CL_KI_HIEU)) != -1) {
            obj.mKiHieu = cursor.getString(index);
        }

        return obj;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public Integer getMaNgonNgu() {
        return mMaNgonNgu;
    }

    public void setMaNgonNgu(Integer maNgonNgu) {
        mMaNgonNgu = maNgonNgu;
    }

    public String getTenNgonNgu() {
        return mTenNgonNgu;
    }

    public void setTenNgonNgu(String tenNgonNgu) {
        mTenNgonNgu = tenNgonNgu;
    }

    public String getKiHieu() {
        return mKiHieu;
    }

    public void setKiHieu(String kiHieu) {
        mKiHieu = kiHieu;
    }

}
