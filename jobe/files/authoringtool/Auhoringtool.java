import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

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
        "<pre id=\"editor\">" + doc.getElementsByTagName("code").item(0).getTextContent() + "</pre>\n" +
        "\n" +
        "<p id=\"result_outcome\" class=\"result_all\">Outcome: <span id=\"outcome\"></span></p>\n" +
        "<p id=\"result_cmpinfo\" class=\"result_all\">Compiler Info: <span id=\"cmpinfo\"></span></p>\n" +
        "<p id=\"result_stdout\" class=\"result_all\">Standard Output: <span id=\"stdout\"></span></p>\n" +
        "<p id=\"result_stderr\" class=\"result_all\">Standard Error Output: <span id=\"stderr\"></span></p>\n" +
        "<p id=\"result_tests\" class=\"result_all\">Test Results: <span id=\"tests\"></span></p>" +
        "\n" +
        "<button id=\"button\" onclick=\"sendCodeAce();\">Go</button>\n" +
        "\n" +
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
        
    }

    /*
     *  ============================
     *  GETTER
     *  ============================
     */
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
        System.out.println(at.getHTMLContent());
    }
}