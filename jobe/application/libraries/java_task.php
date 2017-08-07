<?php defined('BASEPATH') OR exit('No direct script access allowed');

/* ==============================================================
 *
 * Java
 *
 * ==============================================================
 *
 * @copyright  2014 Richard Lobb, University of Canterbury
 * @license    http://www.gnu.org/copyleft/gpl.html GNU GPL v3 or later
 *
 * @modify     2017 Andre Lempertz, University of Applied Sciences Fulda
 */

require_once('application/libraries/LanguageTask.php');

class Java_Task extends Task {
    public function __construct($source, $filename, $input, $params) {
        $params['memorylimit'] = 200;    // Disregard memory limit - let JVM manage memory
        $this->default_params['numprocs'] = 256;     // Java 8 wants lots of processes
        $this->default_params['interpreterargs'] = array(
             "-Xrs",   //  reduces usage signals by java, because that generates debug
                       //  output when program is terminated on timelimit exceeded.
             "-Xss8m",
             "-Xmx200m"
        );

        if (isset($params['numprocs']) && $params['numprocs'] < 256) {
            $params['numprocs'] = 256;  // Minimum for Java 8 JVM
        }

        $source = $this->clearFromComments($source);

        // TODO docu
        if ($this->getMethodHead($source) && !$this->getMainClass($source)) {
            $source = "public class Prog {\n\t" . $source . "\n\n\t public static void main(String args[]) {\n\n\t}\n}" ;
        }

        Task::__construct($source, $filename, $input, $params);

        // Superclass constructor calls subclasses to get filename if it's
        // not provided, so $this->sourceFileName should now be set correctly.
        $extStart = strpos($this->sourceFileName, '.');  // Start of extension
        $this->mainClassName = substr($this->sourceFileName, 0, $extStart);
    }

    public static function getVersionCommand() {
        return array('java -version', '/version "?([0-9._]*)/');
    }

    public function compile() {
        // get student code
        $prog = file_get_contents($this->sourceFileName);

        $compileArgs = $this->getParam('compileargs');
        $cmd = '/usr/bin/javac ' . implode(' ', $compileArgs) . " {$this->sourceFileName} 2>compile.out";
        exec($cmd, $output, $returnVar);
        if ($returnVar == 0) {
            $this->executableFileName = $this->sourceFileName;
        }
        else {
            $this->cmpinfo .= file_get_contents('compile.out');
        }
    }

    public function compileTests() {
        // get student code
        $prog = file_get_contents($this->sourceFileName);

        // get name of test java file without extension
        $this->testSourceFileName = ucfirst($this->getMethodName($prog));

        // move test file first
        exec("cp /var/www/html/jobe/files/tests/" . $this->testSourceFileName . "Test.java " . $this->workdir);

        if(file_exists($this->testSourceFileName . "Test.java")) {
            // compile tests
            exec("/usr/bin/javac -cp .:../junit-4.12.jar:../hamcrest-core-1.3.jar "
            . $this->testSourceFileName . "Test.java");

            // run tests
            $this->runTests($prog);
        } else {
            $this->tests = "Tests konnten nicht durchgeführt werden, da die Methode umbenannt wurde!";
        }
    }

    public function runTests($prog) {
        // get test code
        $testcode = file_get_contents("/var/www/html/jobe/files/tests/" . $this->testSourceFileName . "Test.java");

        // run tests
        exec("/usr/bin/java -cp .:../junit-4.12.jar:../hamcrest-core-1.3.jar org.junit.runner.JUnitCore "
         . $this->testSourceFileName . "Test 1>testresults.out");

        //start [2][0]
        preg_match_all('/(assertEquals[(]["](.+)["])/m', $testcode, $descriptions);
        //start [3][0]
        preg_match_all('/(assertEquals[(]["](.+)["]),\s(.+),\s[tester]/m', $testcode, $expected);
        //start [1][0]
        preg_match_all('/hidden\s([0-1])/m', $testcode, $hidden);
        //start [1][0]
        preg_match_all('/hint\s["](.+)["]/m', $testcode, $hints);

        $testresults = file_get_contents("testresults.out");

        //counter for possible failed tests
        $j = 0;
        $hiddenflag1;
        $hiddenflag2 = 0;

        $this->tests .= "<table cellspacing=\"0\"><tr><th>Erwartetes Ergebnis</th><th>Dein Ergebnis</th><th></th><th></th><th>Hinweise</th></tr>";
        for ($i = 0; $i < max(array_map('count', $descriptions)); $i++) {
            if($hidden[1][$i] == 0) {
                $this->tests .= "<tr><td>" . $descriptions[2][$i] . "</td>";
                if (strpos($testresults, $descriptions[2][$i]) === false) {
                $this->tests .=     "<td>" . $expected[3][$i] . "</td>
                                    <td>BESTANDEN</td><td style=\"width:20px;background-color:green\"></td>";
                } else {
                //start [1][0]
                preg_match_all('/was:<(.+)>/m', $testresults, $studentresults);
                $this->tests .=     "<td>" . $studentresults[1][$j] . "</td>
                                    <td>NICHT BESTANDEN</td><td style=\"width:20px;background-color:red\"></td>";
                $j++;
                }
                $this->tests .=     "<td>" . $hints[1][$i] . "</td></tr>";
            } else if (strpos($testresults, $descriptions[2][$i]) === false) {
                $hiddenflag1 = true;
            } else {
                $hiddenflag2 = 1;
            }
        }
        if($hiddenflag2 == 1) {
            $this->tests .= "<tr><td colspan=\"2\">" . "versteckte weitere Tests" . "</td>
                            <td>NICHT BESTANDEN</td><td style=\"width:20px;background-color:red\"></td>";
        } else if($hiddenflag1 == true && $hiddenflag2 == 0) {
            $this->tests .= "<tr><td colspan=\"2\">" . "versteckte weitere Tests" . "</td>
                            <td>BESTANDEN</td><td style=\"width:20px;background-color:green\"></td>";
        }
        $this->tests .= "</table>";
    }

    // A default name for Java programs. [Called only if API-call does
    // not provide a filename]
    public function defaultFileName($sourcecode) {
        $main = $this->getMainClass($sourcecode);
        if ($main === FALSE) {
            $this->cmpinfo .= "WARNING: can't determine main class, so source file has been named 'Prog.java'";
            return 'Prog.java'; // *should* never reach
        } else {
            return $main.'.java';
        }
    }

    public function getExecutablePath() {
        return '/usr/bin/java';
    }



    public function getTargetFile() {
        return $this->mainClassName;
    }


    // Return the name of the main class in the given prog, or FALSE if no
    // such class found. Uses a regular expression to find a public class with
    // a public static void main method.
    // Not totally safe as it doesn't parse the file, e.g. would be fooled
    // by a commented-out main class with a different name.
    private function getMainClass($prog) {
        $pattern = '/(^|\W)public\s+class\s+(\w+)[^{]*\{.*?public\s+static\s+void\s+main\s*\(\s*String/ms';
        if (preg_match_all($pattern, $prog, $matches) !== 1) {
            return FALSE;
        }
        else {
            return $matches[2][0];
        }
    }

    // TODO docu
    private function getMethodHead($prog) {
        $pattern = '/(^|\W)(public|protected|private)\s+(void|int|double|String)\s+.*/ms';
        if (preg_match($pattern, $prog, $matches) !== 1) {
            return FALSE;
        }
        else {
            return TRUE;
        }
    }

    private function getMethodName($prog) {
        $pattern = '/(^|\W)(public|protected|private)\s+(void|int|double|String)\s+([a-zA-Z0-9]*)/ms';
        if (preg_match_all($pattern, $prog, $matches) !== 1) {
            return FALSE;
        }
        else {
            return $matches[4][0];
        }
    }

    private function clearFromComments($prog) {
        $pattern = '#^.*//.*$#m';
        $prog = preg_replace($pattern, "", $prog);
        return $prog;

    }

    // Get rid of the tab characters at the start of indented lines in
    // traceback output.
    public function filteredStderr() {
        return str_replace("\n\t", "\n        ", $this->stderr);
    }
};

