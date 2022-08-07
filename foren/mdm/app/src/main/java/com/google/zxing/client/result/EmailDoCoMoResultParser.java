package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public final class EmailDoCoMoResultParser extends AbstractDoCoMoResultParser {
    private static final Pattern ATEXT_ALPHANUMERIC = Pattern.compile("[a-zA-Z0-9@.!#$%&'*+\\-/=?^_`{|}~]+");

    @Override // com.google.zxing.client.result.ResultParser
    public EmailAddressParsedResult parse(Result result) {
        String[] tos;
        String rawText = getMassagedText(result);
        if (!rawText.startsWith("MATMSG:") || (tos = matchDoCoMoPrefixedField("TO:", rawText, true)) == null) {
            return null;
        }
        for (String to : tos) {
            if (!isBasicallyValidEmailAddress(to)) {
                return null;
            }
        }
        return new EmailAddressParsedResult(tos, null, null, matchSingleDoCoMoPrefixedField("SUB:", rawText, false), matchSingleDoCoMoPrefixedField("BODY:", rawText, false));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isBasicallyValidEmailAddress(String email) {
        return email != null && ATEXT_ALPHANUMERIC.matcher(email).matches() && email.indexOf(64) >= 0;
    }
}
