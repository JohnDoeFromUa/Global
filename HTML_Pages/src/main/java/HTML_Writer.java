import java.util.*;
import java.net.*;
import java.io.*;

public class HTML_Writer {
    public void writeHTML_Pages(Set<String> URLs) {
        if (URLs == null || URLs.isEmpty())
            return;

        int nameCounter = 1;

        for (String URL : URLs) {
            try {
                URL url = new URL(URL);
                BufferedInputStream in = new BufferedInputStream(url.openConnection().getInputStream());
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(nameCounter + ".html"));
                int temp;
                while ((temp = in.read()) != -1)
                    out.write(temp);
                nameCounter++;
                in.close();
                out.flush();
                out.close();
            } catch (MalformedURLException ignored) {
            } catch (FileNotFoundException ignored) {
            } catch (IOException ignored) {
            }
        }
    }
}