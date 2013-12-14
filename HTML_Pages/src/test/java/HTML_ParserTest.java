import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class HTML_ParserTest {
    HTML_Parser p;

    @Before
    public void init() {
        p = new HTML_Parser();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseToTagANullArgument() {
        p.parseToTagA(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseToHrefNullArgument() {
        p.parseToHref(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseToHrefURL() {
        p.parseToHrefURL(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseToTagImgNullArgument() {
        p.parseToTagImg(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseToSrcNullArgument() {
        p.parseToSrc(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseToSrcURL() {
        p.parseToSrcURL(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseToLinksUrlNullArgument() {
        p.parseToLinksURL(null);
    }

    @Test
    public void testParseToTagANoTagAString() {
        String s = "Hello world";
        assertTrue(p.parseToTagA(s).equals(Collections.<String>emptySet()));
    }

    @Test
    public void testParseToTagAOneTagAString() {
        String s = "<a some text>";
        assertTrue(p.parseToTagA(s).size() == 1);
    }

    @Test
    public void testParseToTagATwoTagsAString() {
        String s = "<a some text1> <a some text2>";
        assertTrue(p.parseToTagA(s).size() == 2);
    }

    @Test
    public void testParseToTagATwoSameTagsAString() {
        String s = "<a some text> <a some text>";
        assertTrue(p.parseToTagA(s).size() == 1);
    }

    @Test
    public void testParseToHrefNoHrefString() {
        String s = "<a some text>";
        assertTrue(p.parseToHref(s).size() == 0);
    }

    @Test
    public void testParseToHrefOneHrefDoubleQuotesString() {
        String s = "<a href=\"\">";
        assertTrue(p.parseToHref(s).size() == 1);
    }

    @Test
    public void testParseToHrefOneHrefSingleQuotesString() {
        String s = "<a href=''>";
        assertTrue(p.parseToHref(s).size() == 1);
    }

    @Test
    public void testParseToHrefOneHrefSingleQuoteAndDoubleQuoteString() {
        String s = "<a href='\">";
        assertTrue(p.parseToHref(s).size() == 0);
    }

    @Test
    public void testParseToHrefOneHrefDoubleQuotesSomeSpacesString() {
        String s = "<a href = \"some url\">";
        assertTrue(p.parseToHref(s).size() == 1);
    }

    @Test
    public void testParseToHrefURLNoURLString() {
        String s = "<a some text>";
        assertTrue(p.parseToHrefURL(s).equals(Collections.emptySet()));
    }

    @Test
    public void testParseToHrefURLOneURLStringCheckSize() {
        String s = "<a href=\"some url\">";
        assertTrue(p.parseToHrefURL(s).size() == 1);
    }

    @Test
    public void testParseToHrefURLOneURLStringCheckContains() {
        String s = "<a href=\"http:\\google.com\">";
        assertTrue(p.parseToHrefURL(s).contains("http:\\google.com"));
    }

    @Test
    public void testParseToTagImgNoTagImgString() {
        String s = "Hello world";
        assertTrue(p.parseToTagImg(s).equals(Collections.<String>emptySet()));
    }

    @Test
    public void testParseToTagImgOneTagImgString() {
        String s = "<img some text>";
        assertTrue(p.parseToTagImg(s).size() == 1);
    }

    @Test
    public void testParseToTagImgTwoTagsImgString() {
        String s = "<img some text1> <img some text2>";
        assertTrue(p.parseToTagImg(s).size() == 2);
    }

    @Test
    public void testParseToTagImgTwoSameTagsImgString() {
        String s = "<img some text> <img some text>";
        assertTrue(p.parseToTagImg(s).size() == 1);
    }

    @Test
    public void testParseToSrcNoSrcString() {
        String s = "<img some text>";
        assertTrue(p.parseToSrc(s).size() == 0);
    }

    @Test
    public void testParseToSrcOneSrcDoubleQuotesString() {
        String s = "<img src=\"\">";
        assertTrue(p.parseToSrc(s).size() == 1);
    }

    @Test
    public void testParseToSrcOneSrcSingleQuotesString() {
        String s = "<img src=''>";
        assertTrue(p.parseToSrc(s).size() == 1);
    }

    @Test
    public void testParseToSrcOneSrcSingleQuoteAndDoubleQuoteString() {
        String s = "<img src='\">";
        assertTrue(p.parseToSrc(s).size() == 0);
    }

    @Test
    public void testParseToSrcOneSrcDoubleQuotesSomeSpacesString() {
        String s = "<img src = \"some url\">";
        assertTrue(p.parseToSrc(s).size() == 1);
    }

    @Test
    public void testParseToSrcURLNoURLString() {
        String s = "<img some text>";
        assertTrue(p.parseToSrcURL(s).equals(Collections.emptySet()));
    }

    @Test
    public void testParseToSrcURLOneURLStringCheckSize() {
        String s = "<img src=\"some url\">";
        assertTrue(p.parseToSrcURL(s).size() == 1);
    }

    @Test
    public void testParseToSrcURLOneURLStringCheckContains() {
        String s = "<img src=\"http:\\google.com\">";
        assertTrue(p.parseToSrcURL(s).contains("http:\\google.com"));
    }
}