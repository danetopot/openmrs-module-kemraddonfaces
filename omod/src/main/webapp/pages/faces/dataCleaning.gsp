<%
    ui.decorateWith("kenyaemr", "standardPage", [ layout: "sidebar" ])
%>

<div class="ke-page-sidebar">
<p>
    This page is dedicated for actions to clean Unique Patient Numbers that were wrongly entered. These might be ones missing a hyphen (-) between MFL code and Patient Clinic No or for TI patients who were assigned new Unique Patient Numbers
</p>
</div>

<div class="ke-page-content">
       <p><b>Clean-Up Actions</b></p>
        <div>
            <input type="button" onclick="ui.navigate('${ ui.pageLink("keaddonfaces", "faces/wrongUPN") }')" id="wrongUpn" value="Clean Wrong UPNs"/>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="button" onclick="ui.navigate('${ ui.pageLink("keaddonfaces", "faces/tiPatients") }')" id="tiPatients" value="Clean UPNs for TI Patients" />
        </div>
</div>
