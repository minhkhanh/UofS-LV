package client.menu.util;

import java.io.IOException;
import java.io.StringWriter;

import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

public class XmlSerializerWrapper {
    private XmlSerializer mSerializer = null;
    private StringWriter mWriter = null;
    private String mNamespace = null;

    public String toString() {
        return mWriter.toString();
    }

    public XmlSerializer endTag(String name) throws IOException,
            IllegalArgumentException, IllegalStateException {
        return mSerializer.endTag("", name);
    }

    public XmlSerializer startTag(String name) throws IOException,
            IllegalArgumentException, IllegalStateException {
        return mSerializer.startTag("", name);
    }

    public void endDocument() throws IOException, IllegalArgumentException,
            IllegalStateException {
        mSerializer.endDocument();
    }

    public void startDocument() throws IOException, IllegalArgumentException,
            IllegalStateException {
        mSerializer = Xml.newSerializer();
        mWriter = new StringWriter();
        mNamespace = "http://www.w3.org/2001/XMLSchema-instance";

        mSerializer.setOutput(mWriter);
        mSerializer.startDocument("UTF-8", null);
        mSerializer.setPrefix("i", mNamespace);
    }

    public void writeSimpleElement(String name, Object value)
            throws IllegalArgumentException, IllegalStateException, IOException {
        startTag(name);
        if (value == null) {
            mSerializer.attribute(mNamespace, "nil", "true");
        } else {
            mSerializer.text(value.toString());
        }
        endTag(name);
    }
}
