import java.util.*;
import java.net.*;
import java.io.*;

public class HTML_Finder {
    public Set<String> findPages(String URL) throws IOException {
        if (URL == null)
            throw new IllegalArgumentException();

        String context = HTML_String(URL);
        return new HTML_Parser().parseToLinksURL(context);
    }

    private String HTML_String(String URL) throws IOException{
        if (URL == null)
            throw new IllegalArgumentException();

        URL url = new URL(URL);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(url.openConnection().getInputStream()));
        String nextLine;
        StringBuilder stringBuilder = new StringBuilder();
        while ((nextLine = reader.readLine()) != null)
            stringBuilder.append(nextLine);

        return stringBuilder.toString();
    }
}
