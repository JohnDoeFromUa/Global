import java.net.*;
import java.io.*;
import java.util.*;

public class RunApp {

    public static void main(String[] args) throws IOException {
        runApp();
    }

    private static void runApp() {
        Scanner scanner = new Scanner(System.in, "Cp1251");
        while (true) {
            try {
                System.out.println("Please, enter URL" +
                        " or exit to leave");
                String url = scanner.nextLine();
                if (url.trim().equals("exit")) {
                    System.out.println("Bye!");
                    break;
                }

                Set<String> links = new HTML_Finder().findPages(url);
                System.out.println("Saving HTML pages...");
                new HTML_Writer().writeHTML_Pages(links);
                System.out.println("HTML pages were saved successfully!");
            } catch (MalformedURLException e) {
                System.out.println("Wrong URL");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
