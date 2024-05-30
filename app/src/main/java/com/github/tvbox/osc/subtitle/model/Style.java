/**
 * Class that represents the .ASS and .SSA subtitle file format
 *
 * <br><br>
 * Copyright (c) 2012 J. David Requejo <br>
 * j[dot]david[dot]requejo[at] Gmail
 * <br><br>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software
 * is furnished to do so, subject to the following conditions:
 * <br><br>
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 * <br><br>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 *
 * @author J. David REQUEJO
 */

package com.github.tvbox.osc.subtitle.model;

public class Style {

    private static int styleCounter;

    /**
     * Constructor that receives a String to use a its identifier
     *
     * @param styleName
     *            = identifier of this style
     */
    public Style(String styleName) {
        this.iD = styleName;
    }

    /**
     * Constructor that receives a String with the new styleName and a style to
     * copy
     *
     * @param styleName
     * @param style
     */
    public Style(String styleName, Style style) {
        this.iD = styleName;
        this.font = style.font;
        this.fontSize = style.fontSize;
        this.color = style.color;
        this.backgroundColor = style.backgroundColor;
        this.textAlign = style.textAlign;
        this.italic = style.italic;
        this.underline = style.underline;
        this.bold = style.bold;

    }

    /* ATTRIBUTES */
    public String iD;
    public String font;
    public String fontSize;
    /** colors are stored as 8 chars long RGBA */
    public String color;
    public String backgroundColor;
    public String textAlign = "";

    public boolean italic;
    public boolean bold;
    public boolean underline;

    /* METHODS */

    /**
     * To get the string containing the hex value to put into color or
     * background color
     *
     * @param format
     *            supported: "name", "&HBBGGRR", "&HAABBGGRR",
     *            "decimalCodedBBGGRR", "decimalCodedAABBGGRR"
     * @param value
     *            RRGGBBAA string
     * @return
     */
    public static String getRGBValue(String format, String value) {
        String color = null;
        if ("name".equalsIgnoreCase(format)) {
            // standard color format from W3C
            if ("transparent".equals(value))
                color = "00000000";
            else if ("black".equals(value))
                color = "000000ff";
            else if ("silver".equals(value))
                color = "c0c0c0ff";
            else if ("gray".equals(value))
                color = "808080ff";
            else if ("white".equals(value))
                color = "ffffffff";
            else if ("maroon".equals(value))
                color = "800000ff";
            else if ("red".equals(value))
                color = "ff0000ff";
            else if ("purple".equals(value))
                color = "800080ff";
            else if ("fuchsia".equals(value))
                color = "ff00ffff";
            else if ("magenta".equals(value))
                color = "ff00ffff ";
            else if ("green".equals(value))
                color = "008000ff";
            else if ("lime".equals(value))
                color = "00ff00ff";
            else if ("olive".equals(value))
                color = "808000ff";
            else if ("yellow".equals(value))
                color = "ffff00ff";
            else if ("navy".equals(value))
                color = "000080ff";
            else if ("blue".equals(value))
                color = "0000ffff";
            else if ("teal".equals(value))
                color = "008080ff";
            else if ("aqua".equals(value))
                color = "00ffffff";
            else if ("cyan".equals(value))
                color = "00ffffff ";
        } else if ("&HBBGGRR".equalsIgnoreCase(format)) {
            // hex format from SSA
            StringBuilder sb = new StringBuilder();
            sb.append(value.substring(6));
            sb.append(value.charAt(4));
            sb.append(value.charAt(2));
            sb.append("ff");
            color = sb.toString();
        } else if ("&HAABBGGRR".equalsIgnoreCase(format)) {
            // hex format from ASS
            StringBuilder sb = new StringBuilder();
            sb.append(value.substring(8));
            sb.append(value.charAt(6));
            sb.append(value.charAt(4));
            sb.append(value.charAt(2));
            color = sb.toString();
        } else if ("decimalCodedBBGGRR".equalsIgnoreCase(format)) {
            // normal format from SSA
            color = Integer.toHexString(Integer.parseInt(value));
            // any missing 0s are filled in
            while (color.length() < 6)
                color = "0" + color;
            // order is reversed
            color = color.substring(4) + color.substring(2, 4)
                    + color.substring(0, 2) + "ff";
        } else if ("decimalCodedAABBGGRR".equalsIgnoreCase(format)) {
            // normal format from ASS
            color = Long.toHexString(Long.parseLong(value));
            // any missing 0s are filled in
            while (color.length() < 8)
                color = "0" + color;
            // order is reversed
            color = color.substring(6) + color.substring(4, 6)
                    + color.substring(2, 4) + color.substring(0, 2);
        }
        return color;
    }

    public static String defaultID() {
        return "default" + styleCounter++;
    }

}
