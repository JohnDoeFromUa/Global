import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTML_Parser {
    public static final String HTML_A_TAG_PATTERN = "(?i)<a(.+?)>";
    public static final String HTML_A_HREF_TAG_PATTERN =
            "\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))";

    public static final String HTML_IMG_TAG_PATTERN = "(?i)<img(.+?)>";
    public static final String HTML_IMG_SRC_TAG_PATTERN =
            "\\s*(?i)src\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))";

    public Set<String> parseToHrefURL(String s) {
        if (s == null)
            throw new IllegalArgumentException();

        Set<String> set = parseToHref(s);
        Set<String> result = new HashSet<String>();
        for (String line : set) {
            String temp = line.replaceAll(" ", "");
            result.add(temp.substring(6, temp.length() - 1));
        }
        return result;
    }

    Set<String> parseToTagA(String s) {
        if (s == null)
            throw new IllegalArgumentException();

        Set<String> set = new HashSet<String>();

        Pattern p = Pattern.compile(HTML_A_TAG_PATTERN);
        Matcher m = p.matcher(s);
        while (m.find()) {
            set.add(m.group());
        }
        return set;
    }

    Set<String> parseToHref(String s) {
        if (s == null)
            throw new IllegalArgumentException();

        Set<String> set = parseToTagA(s);
        Set<String> result = new HashSet<String>();

        Pattern p = Pattern.compile(HTML_A_HREF_TAG_PATTERN);
        for (String str : set) {
            Matcher m = p.matcher(str);
            while (m.find()) {
                result.add(m.group());
            }
        }
        return result;
    }

    public Set<String> parseToSrcURL(String s) {
        if (s == null)
            throw new IllegalArgumentException();

        Set<String> set = parseToSrc(s);
        Set<String> result = new HashSet<String>();

        for (String line : set) {
            String temp = line.replaceAll(" ", "");
            result.add(temp.substring(5, temp.length() - 1));
        }
        return result;
    }

    Set<String> parseToTagImg(String s) {
        if (s == null)
            throw new IllegalArgumentException();

        Set<String> set = new HashSet<String>();

        Pattern p = Pattern.compile(HTML_IMG_TAG_PATTERN);
        Matcher m = p.matcher(s);
        while (m.find()) {
            set.add(m.group());
        }
        return set;
    }

    Set<String> parseToSrc(String s) {
        if (s == null)
            throw new IllegalArgumentException();

        Set<String> set = parseToTagImg(s);
        Set<String> result = new HashSet<String>();

        Pattern p = Pattern.compile(HTML_IMG_SRC_TAG_PATTERN);
        for (String str : set) {
            Matcher m = p.matcher(str);
            while (m.find()) {
                result.add(m.group());
            }
        }
        return result;
    }

    public Set<String> parseToLinksURL(String s) {
        if (s == null)
            throw new IllegalArgumentException();

        Set<String> fromA_tag = parseToHrefURL(s);
        Set<String> fromImg_tag = parseToSrcURL(s);
        Set<String> result = new HashSet<String>();

        for (String str : fromA_tag) {
            result.add(str);
        }

        for (String str : fromImg_tag) {
            result.add(str);
        }
        return result;
    }
}