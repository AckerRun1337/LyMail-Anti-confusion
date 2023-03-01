/*
 * Decompiled with CFR 0.152.
 */
package Ly.mail.plugin.util;

public class JsonBuilder {
    public static String[] REPLACEMENT_CHARS = new String[128];
    StringBuilder json = new StringBuilder();

    public void append(String IiiIiIiiII) {
        JsonBuilder IiiIiIiiII2 = null;
        int n                   = 0;
        int n2 = IiiIiIiiII.length();
        for (int i = 0; i < n2; ++i) {
            String string;
            char c = IiiIiIiiII.charAt(i);
            if (c < '\u0080') {
                string = REPLACEMENT_CHARS[c];
                if (string == null) {
                    continue;
                }
            } else if (c == '\u2028') {
                string = "\\u2028";
            } else {
                if (c != '\u2029') continue;
                string = "\\u2029";
            }
            if (n < i) {
                IiiIiIiiII2.json.append(IiiIiIiiII, n, i);
            }
            IiiIiIiiII2.json.append(string);
            n = i + 1;
        }
        if (n < n2) {
            IiiIiIiiII2.json.append(IiiIiIiiII, n, n2);
        }
    }

    public void deleteLastChar() {
        JsonBuilder IiiIiIiiII = null;
        IiiIiIiiII.json.deleteCharAt(IiiIiIiiII.json.length() - 1);
    }

    public boolean isEmpty() {
        JsonBuilder IiiIiIiiII = null;
        return IiiIiIiiII.json.length() == 0;
    }

    public JsonBuilder() {
        JsonBuilder IiiIiIiiII;
    }

    static {
        for (int i = 0; i <= 31; ++i) {
            JsonBuilder.REPLACEMENT_CHARS[i] = String.format("\\u%04x", i);
        }
        JsonBuilder.REPLACEMENT_CHARS[34] = "\\\"";
        JsonBuilder.REPLACEMENT_CHARS[92] = "\\\\";
        JsonBuilder.REPLACEMENT_CHARS[9] = "\\t";
        JsonBuilder.REPLACEMENT_CHARS[8] = "\\b";
        JsonBuilder.REPLACEMENT_CHARS[10] = "\\n";
        JsonBuilder.REPLACEMENT_CHARS[13] = "\\r";
        JsonBuilder.REPLACEMENT_CHARS[12] = "\\f";
    }

    public int length() {
        JsonBuilder IiiIiIiiII = null;
        return IiiIiIiiII.json.length();
    }

    public JsonBuilder(String IiiIiIiiII) {
        JsonBuilder IiiIiIiiII2 = null;
        IiiIiIiiII2();
        IiiIiIiiII2.append(IiiIiIiiII);
    }

    private void IiiIiIiiII2() {

    }

    public String toString() {
        JsonBuilder IiiIiIiiII = null;
        return IiiIiIiiII.json.toString();
    }
}

