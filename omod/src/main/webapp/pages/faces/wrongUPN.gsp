<%
    ui.decorateWith("kenyaemr", "standardPage", [ layout: "sidebar" ])
%>

<div class="ke-page-sidebar">

    <input type="button" onclick="ui.navigate('${ ui.pageLink("keaddonfaces", "faces/tiPatients") }')" id="tiPatients" value="Clean UPNs for TI Patients" />
</div>

<div class="ke-page-content">
    ${ ui.includeFragment("keaddonfaces", "wrongUpnResult") }
</div>
