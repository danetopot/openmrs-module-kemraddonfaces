<%
    ui.decorateWith("kenyaemr", "standardPage", [ layout: "sidebar" ])
%>

<div class="ke-page-sidebar">

    <input type="button" onclick="ui.navigate('${ ui.pageLink("keaddonfaces", "faces/wrongUPN") }')" id="wrongUpn" value="Clean Wrong UPNs"/>
</div>

<div class="ke-page-content">
    ${ ui.includeFragment("keaddonfaces", "transferInResult") }
</div>
