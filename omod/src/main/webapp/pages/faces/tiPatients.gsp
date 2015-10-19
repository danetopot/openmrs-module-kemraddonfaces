<%
    ui.decorateWith("kenyaemr", "standardPage", [ layout: "sidebar" ])
%>

<div class="ke-page-sidebar">

    ${ ui.includeFragment("keaddonfaces", "dataCleaningSideBar") }
</div>

<div class="ke-page-content">
    ${ ui.includeFragment("keaddonfaces", "transferInResult") }
</div>
