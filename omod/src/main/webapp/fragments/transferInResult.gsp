<%
    ui.includeJavascript("keaddonfaces", "jquery-1.10.2.min.js")
    ui.includeJavascript("keaddonfaces", "jquery.dataTables.min.js")
    ui.includeCss("keaddonfaces", "jquery.dataTables.css")
    ui.includeCss("keaddonfaces", "dataTables_jui.css")

%>
<%= ui.resourceLinks() %>
<style>
    table#results tbody  tr {
        cursor : pointer;
    }
</style>
<script>
    jq = jQuery;
    jq(document).ready(function(){
        var resTable = jq("#results").dataTable();

        jq('#results tbody').on('click', 'tr', function () {
            /*var data = resTable.row( this ).data();*/
            jq("#serverResponse").html("");
            jq("#ptname").val(jq(this).find("td").eq(0).html());
            jq("#upn").val(jq(this).find("td").eq(2).html());
            jq("#pcn").val(jq(this).find("td").eq(3).html());
            jq("#ptId").val(jq(this).find("td").eq(4).html());
        } );

        jq('#editPatient').click(function() {
            jq("#serverResponse").html("");

            if (jq("#newUPN").val() == "" || jq("#newPCN").val() == "" || jq("#upn").val() == "" || jq("#pcn").val() == "" || jq("#ptId").val() == "" || jq("#ptname").val() == "" ) {
                jq("#serverResponse").html("Error: Missing UPN and/or PCN. Please select a row to correct this");
            } else {
                jq.getJSON('${ ui.actionLink("editUPN") }',
                        {
                            'ptId': jq("#ptId").val(),
                            'newUPN': jq("#newUPN").val(),
                            'newPCN': jq("#newPCN").val()
                        }
                )
                        .success(function(data) {
                            jq("#newUPN").val("");
                            jq("#newPCN").val("");
                            jq("#ptname").val("");
                            jq("#upn").val("");
                            jq("#pcn").val("");
                            jq("#ptId").val("");

                            jq("#serverResponse").html("Patient Identifiers updated successfully");
                            location.reload(true);
                        })
                        .error(function (xhr, status, err) {
                            jq("#serverResponse").html("Server Error: " + err);
                        })
            }
        });
    });
</script>
<fieldset>
     <legend>Transfer-In Patients</legend>
    <div>
        <table id="results" width="100%">
            <thead>
                <td>Patient Name</td>
                <td>Gender</td>
                <td>Unique Patient No</td>
                <td>Patient Clinic No</td>
                <td>&nbsp;</td>

            </thead>
            <tbody>
            <% if (patients) { %>
            <% patients.each { pt -> %>
                <tr>
                    <td>${ pt.names }</td>
                    <td>${ pt.gender }</td>
                    <td>${ pt.getPatientIdentifier(3) }</td>
                    <td>${ pt.getPatientIdentifier(7) }</td>
                    <td style="display:none">${ pt.id }</td>
                </tr>
            <% } %>
            <% } else { %>
                <tr>
                    <td colspan="4">Nothing was found</td>
                </tr>
            <% } %>
            </tbody>
        </table>
    </div>
    <div id="serverResponse" style="color: mediumblue;"></div>

    <div>
        <table width="70%">
            <tr>
                <td><b>Patient Name:</b></td>
                <td colspan="3"><input type="text" size="50" id="ptname" readonly /></td>
            </tr>

            <tr>
                <td><b>Unique Patient No</b></td>
                <td><input type="text" id="upn" readonly /></td>
                <td colspan="2"><input type="text" id="newUPN"  /><input type="hidden" id="ptId" /></td>
            </tr>
            <tr>
                <td><b>Patient Clinic No</b></td>
                <td><input type="text" id="pcn" readonly /></td>
                <td><input type="text" id="newPCN"  /></td>
                <td><input type="button" id="editPatient" value="Save Changes" /></td>
            </tr>
        </table>
    </div>

</fieldset>