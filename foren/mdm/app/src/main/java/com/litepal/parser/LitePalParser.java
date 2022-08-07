package com.litepal.parser;

import android.content.res.AssetManager;
import android.content.res.Resources;
import com.hy.frame.util.MyLog;
import com.litepal.LitePalApplication;
import com.litepal.exceptions.ParseConfigurationFileException;
import com.litepal.util.Const;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* loaded from: classes2.dex */
public class LitePalParser {
    static final String ATTR_CLASS = "class";
    static final String ATTR_VALUE = "value";
    static final String NODE_CASES = "cases";
    static final String NODE_DB_NAME = "dbname";
    static final String NODE_LIST = "list";
    static final String NODE_MAPPING = "mapping";
    static final String NODE_STORAGE = "storage";
    static final String NODE_VERSION = "version";
    private static LitePalParser parser;

    public static void parseLitePalConfiguration() {
        if (parser == null) {
            parser = new LitePalParser();
        }
        parser.useSAXParser();
    }

    void useSAXParser() {
        try {
            XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
            xmlReader.setContentHandler(new LitePalContentHandler());
            xmlReader.parse(new InputSource(getConfigInputStream()));
        } catch (Resources.NotFoundException e) {
            MyLog.e(ParseConfigurationFileException.CAN_NOT_FIND_LITEPAL_FILE);
            e.printStackTrace();
        } catch (IOException e2) {
            MyLog.e(ParseConfigurationFileException.IO_EXCEPTION);
            e2.printStackTrace();
        } catch (ParserConfigurationException e3) {
            MyLog.e(ParseConfigurationFileException.PARSE_CONFIG_FAILED);
            e3.printStackTrace();
        } catch (SAXException e4) {
            MyLog.e(ParseConfigurationFileException.FILE_FORMAT_IS_NOT_CORRECT);
            e4.printStackTrace();
        }
    }

    void usePullParse() {
        try {
            LitePalAttr litePalAttr = LitePalAttr.getInstance();
            XmlPullParser xmlPullParser = XmlPullParserFactory.newInstance().newPullParser();
            xmlPullParser.setInput(getConfigInputStream(), "UTF-8");
            for (int eventType = xmlPullParser.getEventType(); eventType != 1; eventType = xmlPullParser.next()) {
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    case 2:
                        if (NODE_DB_NAME.equals(nodeName)) {
                            litePalAttr.setDbName(xmlPullParser.getAttributeValue("", "value"));
                            continue;
                        } else if ("version".equals(nodeName)) {
                            litePalAttr.setVersion(Integer.parseInt(xmlPullParser.getAttributeValue("", "value")));
                            continue;
                        } else if (NODE_MAPPING.equals(nodeName)) {
                            litePalAttr.addClassName(xmlPullParser.getAttributeValue("", ATTR_CLASS));
                            continue;
                        } else if (NODE_CASES.equals(nodeName)) {
                            litePalAttr.setCases(xmlPullParser.getAttributeValue("", "value"));
                            continue;
                        } else {
                            continue;
                        }
                }
            }
        } catch (IOException e) {
            MyLog.e(ParseConfigurationFileException.IO_EXCEPTION);
            e.printStackTrace();
        } catch (XmlPullParserException e2) {
            MyLog.e(ParseConfigurationFileException.FILE_FORMAT_IS_NOT_CORRECT);
            e2.printStackTrace();
        }
    }

    private InputStream getConfigInputStream() throws IOException {
        AssetManager assetManager = LitePalApplication.getContext().getAssets();
        String[] fileNames = assetManager.list("");
        if (fileNames != null && fileNames.length > 0) {
            for (String fileName : fileNames) {
                if (Const.LitePal.CONFIGURATION_FILE_NAME.equalsIgnoreCase(fileName)) {
                    return assetManager.open(fileName, 3);
                }
            }
        }
        MyLog.e(ParseConfigurationFileException.CAN_NOT_FIND_LITEPAL_FILE);
        return null;
    }
}
