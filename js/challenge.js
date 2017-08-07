/**
 * @file    challenge.js
 * A javascript file that handles the communication between the client and the server via AJAX/RESTful services.
 * 
 * @author  2017 Andre Lempertz, Fulda University of Applied Sciences
 */

function sendCodeAce() {

    // Display loading .gif
    $("#loadingmessage").show();

    // Create XMLHttpRequest Object
    var xmlhttp = new XMLHttpRequest();

    // Get source code from textarea
    var source = editor.getSession().getValue();

    // Encode "run_spec" parameters into JSON format (see restapi.pdf for documentation)
    var data = JSON.stringify({"run_spec": {language_id:"java", sourcecode:source}});
    
    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {

            // Decode results from response
            var result = JSON.parse(this.responseText);

            switch(result.outcome) {
                case 11:
                    document.getElementById("outcome").innerHTML =
                     "Code " + result.outcome + " - Compilation Error. See Compiler Info for further explanation.";
                    break;
                case 12:
                    document.getElementById("outcome").innerHTML =
                     "Code " + result.outcome + " - Runtime error. The job compiled but threw an exception at run time.";
                    break;
                case 13:
                    document.getElementById("outcome").innerHTML =
                     "Code " + result.outcome + " - Time limit exceeded. The job was killed before it ran to completion.";
                    break;
                case 15:
                    document.getElementById("outcome").innerHTML =
                     "Code " + result.outcome + " - OK. The job ran to completion without any exceptions.";
                    break;
                case 17:
                    document.getElementById("outcome").innerHTML =
                     "Code " + result.outcome + " - Memory limit exceeded. The job was killed before it ran to completion.";
                    break;
                case 19:
                    document.getElementById("outcome").innerHTML =
                     "Code " + result.outcome + " - Illegal system call. The task attemped a system call not allowed by this particular server";
                    break;
                case 20:
                    document.getElementById("outcome").innerHTML =
                     "Code " + result.outcome + " - Internal error. Something went wrong in the server.";
                    break;
                case 21:
                    document.getElementById("outcome").innerHTML =
                     "Code " + result.outcome + " - Server overload. No free user accounts.";
                    break;
            }

            document.getElementById("cmpinfo").innerHTML = "<pre>"+result.cmpinfo+"</pre>";
            if (result.tests !== "") {
                document.getElementById("result_tests").style.display = "inline";
                document.getElementById("tests").innerHTML = result.tests;
            } else {
                document.getElementById("result_tests").style.display = "none";
            }

            // Hide loading .gif
            $("#loadingmessage").hide();
        }
    };

    // Send request to JOBE server via REST interface
    xmlhttp.open("POST", '../jobe/index.php/restapi/runs', true);
    xmlhttp.setRequestHeader("Content-type", "application/json");
    xmlhttp.send(data);
}