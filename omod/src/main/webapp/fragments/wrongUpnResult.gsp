<%
    ui.includeJavascript("keaddonfaces", "jquery-1.10.2.min.js")
    ui.includeJavascript("keaddonfaces", "jquery.dataTables.min.js")
    ui.includeCss("keaddonfaces", "jquery.dataTables.css")
    ui.includeCss("keaddonfaces", "dataTables_jui.css")

%>
<%= ui.resourceLinks() %>
<script>
    jq = jQuery;
    jq(document).ready(function(){
        jq("#results").dataTable();
    });
</script>
<fieldset>
     <legend>Patients with wrong Unique Patient Numbers</legend>
    <div>
        <table id="results" width="100%">
            <thead>
                <td>Patient Name</td>
                <td>Gender</td>
                <td>Unique Patient No</td>
                <td>Patient Clinic No</td>

            </thead>
            <tbody>
            <% if (patients) { %>
            <% patients.each { pt -> %>
                <tr>
                    <td>${ pt.names }</td>getPatientIdentifier
                    <td>${ pt.gender }</td>
                    <td>${ pt.getPatientIdentifier(3) }</td>
                    <td>${ pt.getPatientIdentifier(7) }</td>
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

    <div>
        <table width="100%">
            <tr>
                <th colspan="2">Edit Patient Unique Number</th>
            </tr>
            <tr>
                <td>Current UPN</td>
                <td>Current Patient Clinic No</td>
            </tr>
            <tr>
                <td><input type="text" value="13708-09876" readonly /></td>
                <td><input type="text" value="09876" readonly /></td>
            </tr>
            <tr>
                <td><input type="text" value="13708-09876"  /></td>
                <td><input type="text" value="09876"  /></td>
            </tr>
        </table>
    </div>

</fieldset>