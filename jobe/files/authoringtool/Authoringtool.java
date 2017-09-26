import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.*;
import java.util.Scanner;

/*
 * A Java class that creates a html page and java tests out of a parsed XML file.
 * 
 * @author Andre Lempertz 2017, Fulda University of Applied Sciences
 */
public class Authoringtool {

    private File xmlFile;
    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;
    private Document doc;

    private Scanner scanner;

    private String htmlContent;
    private String testContent;

/*
 * c'tor'
 */
    public Authoringtool() {
        htmlContent = "";
        testContent = "";
    }

/*
 * Parse and normalize the XML file.
 */
    public void parseXML() {
        try {
            xmlFile = new File("authoringtool.xml");
            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(xmlFile);

            // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createHTMLPage() {
        htmlContent += 
        "<!DOCTYPE html>\n" +
        "<html lang=\"en\">\n" +
        "<head>\n\t" +
            "<meta charset=\"UTF-8\">\n\t" +
            "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\n\t" +
            "<script src=\"../ace/src/ace.js\" type=\"text/javascript\" charset=\"utf-8\"></script>\n\t" +
            "<script src=\"../js/challenge.js\" type=\"text/javascript\" charset=\"utf-8\"></script>\n\t" +
            "<script src=\"../js/jquery-3.2.1.min.js\" type=\"text/javascript\" charset=\"utf-8\"></script>\n\t" +
            "<link rel=\"stylesheet\" href=\"../css/styles.css\">\n\t" +
            "<title>" + doc.getElementsByTagName("pagetitle").item(0).getTextContent() + "</title>\n" +
        "</head>\n" +
        "<body>\n" +
        "\n" +
        "<div id=\"loadingmessage\"><img src=\"../img/ajax-loader.gif\"/></div>\n" +
        "\n" +
        "<div class=\"logo\"><a href=\"https://www.hs-fulda.de/\"><img src=\"../../img/logo.jpg\" width=\"274\" height=\"50\"/></a></div>\n" +
        "<p><p>\n" +
        "<div class=\"titletop titlebold\">Fachbereich</div>\n" +
        "<div class=\"titlebottom\">Angewandte Informatik</div>\n" +
        "<p></p>\n" +
        "\n" +
        "<div id=\"description\">" + doc.getElementsByTagName("introtext").item(0).getTextContent() + "</div>\n" +
        "\n" +
        "<table border=\"0\">\n\t" +
            "<tbody>\n\t\t" +
                "<tr>\n\t\t\t" +
                    "<td class=\"indent\" width=\"700\" valign=\"top\">\n" +
        "<pre id=\"editor\">" + doc.getElementsByTagName("code").item(0).getTextContent() + "</pre>\n" +
        "\n" +
        "<button id=\"button\" onclick=\"sendCodeAce();\">Ausführen</button>\n\t\t\t" +
        "</td>\n\t\t\t" +
        "<td class=\"indent\" width=\"800\" valign=\"top\">\n\t\t\t\t" +
        "<p id=\"result_outcome\" class=\"result_all\"><span id=\"outcome\"></span></p>\n\t\t\t\t" +
        "<p id=\"result_cmpinfo\" class=\"result_all\"><span id=\"cmpinfo\"></span></p>\n\t\t\t\t" +
        "<p id=\"result_tests\" class=\"result_all\">Testergebnisse: <span id=\"tests\"></span></p>\n\t\t\t" +
        "</td>\n\t\t" +
        "</tr>\n\t" +
        "</tbody>\n" +
        "</table>\n" +
        "<script>\n\t" +
            "var editor = ace.edit(\"editor\");\n\t" +
            "editor.setTheme(\"ace/theme/chrome\");\n\t" +
            "editor.session.setMode(\"ace/mode/java\");\n" +
        "</script>\n" +
        "\n" +
        "</body>\n" +
        "</html>";
    }

    public void createJavaTests() {
        NodeList nList = doc.getElementsByTagName("test");
        int firstTest = 10; // Start bei 10 aufgrund der JUnit Testreihenfolge!
        testContent +=
        "import static org.junit.Assert.assertEquals;\n" +
        "import org.junit.*;\n" +
        "\n" +
        "public class " + doc.getElementsByTagName("filename").item(0).getTextContent() + "Test {\n" +
        "\n";
        for(int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            if(nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                testContent +=  "\t@Test\n\t" +
                                "public void test" + Integer.toString(firstTest) + "() {\n\t" +
                                "Prog tester = new Prog();\n\t" +
                                "\n\t" +
                                "//hint \"" + eElement.getElementsByTagName("hint").item(0).getTextContent() + "\"\n\t" +
                                "\n\t" +
                                "Assert.assertEquals(\"" + eElement.getElementsByTagName("message").item(0).getTextContent() + "\", " +
                                eElement.getElementsByTagName("expectedvalue").item(0).getTextContent() + ", tester." +
                                eElement.getAttribute("methodname") + "(" + eElement.getElementsByTagName("parameter").item(0).getTextContent() + "));\n\t" +
                                "}\n\t" +
                                "\n";
                                firstTest++;
            }
        }
        testContent +=
        "}";
    }

    /*
     *  ============================
     *  WRITER
     *  ============================
     */

    public void writeHTMLPage(String file) throws IOException, FileNotFoundException {
        PrintWriter pw = new PrintWriter(file);
        pw.println(htmlContent);
        pw.close();
    }

    public void writeJavaTests(String file) throws IOException, FileNotFoundException {
        PrintWriter pw = new PrintWriter(file);
        pw.println(testContent);
        pw.close();
    }

    /*
     *  ============================
     *  GETTER
     *  ============================
     */

    public Document getDoc() {
        return doc;
    }

    public String getHTMLContent() {
        return htmlContent;
    }

    public String getTestContent() {
        return testContent;
    }

    public static void main(String args[]) {
        Authoringtool at = new Authoringtool();
        at.parseXML();
        at.createHTMLPage();
        at.createJavaTests();
        System.out.println("==========================================================");
        System.out.println("=                    HTML PAGE FILE                      =");
        System.out.println("==========================================================");
        System.out.println(at.getHTMLContent());
        System.out.println("==========================================================");
        System.out.println("=                    JAVA TEST FILE                      =");
        System.out.println("==========================================================");
        System.out.println(at.getTestContent());

        Scanner scanner = new Scanner(System.in);
        String command = null;
        
        // Write HTML page
        do {
            System.out.print("Write HTML PAGE FILE to /var/www/html/tasks? (y/n) ");
            command = scanner.nextLine();

            if(command.equals("n")) {
                System.out.println("No file written!");
            } else if(command.equals("y")) {
                try {
                    at.writeHTMLPage("/var/www/html/tasks/" + at.getDoc().getElementsByTagName("filename").item(0).getTextContent() + ".html");
                    System.out.println("File successfully written!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Unknown command!");
            }

        } while(!command.equals("y") && !command.equals("n"));

        // Write Java test page
        do {
            System.out.print("Write JAVA TEST FILE to /var/www/html/jobe/files/tests? (y/n) ");
            command = scanner.nextLine();

            if(command.equals("n")) {
                System.out.println("No file written!");
            } else if(command.equals("y")) {
                try {
                    at.writeJavaTests("/var/www/html/jobe/files/tests/" + at.getDoc().getElementsByTagName("filename").item(0).getTextContent() + "Test.java");
                    System.out.println("File successfully written!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Unknown command!");
            }

        } while(!command.equals("y") && !command.equals("n"));
    }
}