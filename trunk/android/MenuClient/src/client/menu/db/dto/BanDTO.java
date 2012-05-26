package client.menu.db.dto;

import org.xmlpull.v1.XmlPullParser;

import android.database.Cursor;
import android.provider.BaseColumns;
import client.menu.util.U;
import client.menu.util.XmlSerializerWrapper;

public class BanDTO
// implements Parcelable
{
    public static final String TABLE_NAME = "Ban";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_MA_BAN = "MaBan";
    public static final String CL_MA_KHU_VUC = "MaKhuVuc";
    public static final String CL_TEN_BAN = "TenBan";
    public static final String CL_GHI_CHU = "GhiChu";
    public static final String CL_ACTIVE = "Active";
    public static final String CL_TINH_TRANG = "TinhTrang";
    public static final String CL_MA_BAN_CHINH = "MaBanChinh";

    private Integer mId;
    private Integer mMaBan;
    private Integer mMaKhuVuc;
    private Integer mMaBanChinh;
    private String mTenBan;
    private String mGhiChu;
    private Boolean mActive;
    private Boolean mTinhTrang;

    public String toXml() {
        XmlSerializerWrapper serializer = new XmlSerializerWrapper();

        try {
            serializer.startDocument();
            serializer.startTag(TABLE_NAME);
            serializer.writeSimpleElement(CL_ACTIVE, mActive);
            serializer.writeSimpleElement(CL_GHI_CHU, mGhiChu);
            serializer.writeSimpleElement(CL_MA_BAN, mMaBan);
            serializer.writeSimpleElement(CL_MA_BAN_CHINH, mMaBanChinh);
            serializer.writeSimpleElement(CL_MA_KHU_VUC, mMaKhuVuc);
            serializer.writeSimpleElement(CL_TEN_BAN, mTenBan);
            serializer.writeSimpleElement(CL_TINH_TRANG, mTinhTrang);
            serializer.endTag(TABLE_NAME);
            serializer.endDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return serializer.toString();
    }

    public static final BanDTO fromXml(XmlPullParser parser) {
        BanDTO obj = null;

        try {
            int type = parser.getEventType();
            String tag = "";
            String text;

            while (type != XmlPullParser.END_DOCUMENT) {
                switch (type) {
                    case XmlPullParser.START_TAG:
                        tag = parser.getName();
                        if (tag.compareTo(TABLE_NAME) == 0) {
                            obj = new BanDTO();
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
                        if (tag.compareTo(CL_MA_BAN) == 0) {
                            obj.mMaBan = Integer.valueOf(text);
                        } else if (tag.compareTo(CL_MA_KHU_VUC) == 0) {
                            obj.mMaKhuVuc = Integer.valueOf(text);
                        } else if (tag.compareTo(CL_TEN_BAN) == 0) {
                            obj.mTenBan = text;
                        } else if (tag.compareTo(CL_GHI_CHU) == 0) {
                            obj.mGhiChu = text;
                        } else if (tag.compareTo(CL_ACTIVE) == 0) {
                            obj.mActive = Boolean.valueOf(text);
                        } else if (tag.compareTo(CL_TINH_TRANG) == 0) {
                            obj.mTinhTrang = Boolean.valueOf(text);
                        } else if (tag.compareTo(CL_MA_BAN_CHINH) == 0) {
                            obj.mMaBanChinh = Integer.valueOf(text);
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

    public static final BanDTO extractFrom(Cursor cursor) {
        BanDTO obj = new BanDTO();
        int i;
        if ((i = cursor.getColumnIndex(CL_ID)) != -1) {
            obj.mId = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_MA_BAN)) != -1) {
            obj.mMaBan = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_MA_KHU_VUC)) != -1) {
            obj.mMaKhuVuc = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_MA_BAN_CHINH)) != -1) {
            obj.mMaBanChinh = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_TEN_BAN)) != -1) {
            obj.mTenBan = cursor.getString(i);
        }
        if ((i = cursor.getColumnIndex(CL_GHI_CHU)) != -1) {
            obj.mGhiChu = cursor.getString(i);
        }
        if ((i = cursor.getColumnIndex(CL_ACTIVE)) != -1) {
            obj.mActive = U.getCursorBool(cursor, i);
        }
        if ((i = cursor.getColumnIndex(CL_TINH_TRANG)) != -1) {
            obj.mTinhTrang = U.getCursorBool(cursor, i);
        }

        return obj;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public Integer getMaBan() {
        return mMaBan;
    }

    public void setMaBan(Integer maBan) {
        this.mMaBan = maBan;
    }

    public Integer getMaKhuVuc() {
        return mMaKhuVuc;
    }

    public void setMaKhuVuc(Integer maKhuVuc) {
        this.mMaKhuVuc = maKhuVuc;
    }

    public String getTenBan() {
        return mTenBan;
    }

    public void setTenBan(String tenBan) {
        this.mTenBan = tenBan;
    }

    public String getGhiChu() {
        return mGhiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.mGhiChu = ghiChu;
    }

    public Integer getMaBanChinh() {
        return mMaBanChinh;
    }

    public void setMaBanChinh(Integer maBanChinh) {
        this.mMaBanChinh = maBanChinh;
    }

    public Boolean getActive() {
        return mActive;
    }

    public void setActive(Boolean active) {
        mActive = active;
    }

    public Boolean getTinhTrang() {
        return mTinhTrang;
    }

    public void setTinhTrang(Boolean tinhTrang) {
        mTinhTrang = tinhTrang;
    }

    @Override
    public String toString() {
        return getTenBan();
    }

    // @Override
    // public Integer describeContents() {
    // return 0;
    // }
    //
    // @Override
    // public void writeToParcel(Parcel dest, Integer flags) {
    // dest.writeValue(mActive);
    // dest.writeValue(mGhiChu);
    // dest.writeValue(mId);
    // dest.writeValue(mMaBan);
    // dest.writeValue(mMaBanChinh);
    // dest.writeValue(mMaKhuVuc);
    // dest.writeValue(mTenBan);
    // dest.writeValue(mTinhTrang);
    // }
    //
    // public BanDTO(Parcel src) {
    // mActive = (Boolean) src.readValue(Boolean.class.getClassLoader());
    // mGhiChu = src.readString();
    // mId = src.readInt();
    // mMaBan = src.readInt();
    // mMaBanChinh = (Integer) data[i++];
    // mMaKhuVuc = (Integer) data[i++];
    // mTenBan = (String) data[i++];
    // mTinhTrang = (Boolean) data[i++];
    // }
    //
    // public static final Parcelable.Creator<BanDTO> CREATOR = new
    // Creator<BanDTO>() {
    //
    // @Override
    // public BanDTO createFromParcel(Parcel source) {
    // BanDTO ban = new BanDTO();
    //
    // Object[] data = source.readArray(Object.class.getClassLoader());
    // Integer i = 0;
    // ban.mActive = (Boolean) data[i++];
    // ban.mGhiChu = (String) data[i++];
    // ban.mId = (Integer) data[i++];
    // ban.mMaBan = (Integer) data[i++];
    // ban.mMaBanChinh = (Integer) data[i++];
    // ban.mMaKhuVuc = (Integer) data[i++];
    // ban.mTenBan = (String) data[i++];
    // ban.mTinhTrang = (Boolean) data[i++];
    //
    // return ban;
    // }
    //
    // @Override
    // public BanDTO[] newArray(Integer size) {
    // return new BanDTO[size];
    // }
    // };
}
