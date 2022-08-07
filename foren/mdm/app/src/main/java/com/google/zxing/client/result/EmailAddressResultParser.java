package com.google.zxing.client.result;

import com.google.zxing.Result;
import com.hyphenate.chat.MessageEncoder;
import com.tencent.smtt.sdk.WebView;
import com.umeng.analytics.a;
import java.util.Map;
import java.util.regex.Pattern;
import u.aly.av;

/* loaded from: classes.dex */
public final class EmailAddressResultParser extends ResultParser {
    private static final Pattern COMMA = Pattern.compile(",");

    @Override // com.google.zxing.client.result.ResultParser
    public EmailAddressParsedResult parse(Result result) {
        String tosString;
        String rawText = getMassagedText(result);
        if (rawText.startsWith(WebView.SCHEME_MAILTO) || rawText.startsWith("MAILTO:")) {
            String hostEmail = rawText.substring(7);
            int queryStart = hostEmail.indexOf(63);
            if (queryStart >= 0) {
                hostEmail = hostEmail.substring(0, queryStart);
            }
            String hostEmail2 = urlDecode(hostEmail);
            String[] tos = null;
            if (!hostEmail2.isEmpty()) {
                tos = COMMA.split(hostEmail2);
            }
            Map<String, String> nameValues = parseNameValuePairs(rawText);
            String[] ccs = null;
            String[] bccs = null;
            String subject = null;
            String body = null;
            if (nameValues != null) {
                if (tos == null && (tosString = nameValues.get(MessageEncoder.ATTR_TO)) != null) {
                    tos = COMMA.split(tosString);
                }
                String ccString = nameValues.get(av.av);
                if (ccString != null) {
                    ccs = COMMA.split(ccString);
                }
                String bccString = nameValues.get("bcc");
                if (bccString != null) {
                    bccs = COMMA.split(bccString);
                }
                subject = nameValues.get("subject");
                body = nameValues.get(a.w);
            }
            return new EmailAddressParsedResult(tos, ccs, bccs, subject, body);
        } else if (!EmailDoCoMoResultParser.isBasicallyValidEmailAddress(rawText)) {
            return null;
        } else {
            return new EmailAddressParsedResult(rawText);
        }
    }
}
