package org.openmrs.module.keaddonfaces.page.controller.faces;

import org.apache.commons.lang3.StringUtils;
import org.openmrs.*;
import org.openmrs.api.context.Context;
import org.openmrs.module.keaddonfaces.FacesConstants;
import org.openmrs.module.kenyacore.form.FormManager;
import org.openmrs.module.kenyacore.program.ProgramDescriptor;
import org.openmrs.module.kenyacore.program.ProgramManager;
import org.openmrs.module.kenyaui.KenyaUiUtils;
import org.openmrs.module.kenyaui.annotation.AppPage;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.page.PageModel;
import org.openmrs.ui.framework.page.PageRequest;
import org.openmrs.ui.framework.session.Session;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: werick
 * Date: 4/18/14
 * Time: 4:21 PM
 * To change this template use File | Settings | File Templates.
 */
@AppPage(FacesConstants.APP_FACES)
public class FacesViewPatientPageController {

    public void controller(@RequestParam(required=false, value="patientId") Patient patient,
                           @RequestParam(required = false, value = "visitId") Visit visit,
                           @RequestParam(required = false, value = "formUuid") String formUuid,
                           @RequestParam(required = false, value = "programId") Program program,
                           @RequestParam(required = false, value = "section") String section,
                           PageModel model,
                           UiUtils ui,
                           Session session,
                           PageRequest pageRequest,
                           @SpringBean KenyaUiUtils kenyaUi,
                           @SpringBean FormManager formManager,
                           @SpringBean ProgramManager programManager) {

        if ("".equals(formUuid)) {
            formUuid = null;
        }

        //Patient patient = (Patient) model.getAttribute(EmrWebConstants.MODEL_ATTR_CURRENT_PATIENT);
        recentlyViewed(patient, session);


        Collection<ProgramDescriptor> progams = programManager.getPatientPrograms(patient);

        model.addAttribute("programs", progams);
        model.addAttribute("visits", Context.getVisitService().getVisitsByPatient(patient));

        Form form = null;
        String selection = null;

        if (visit != null) {
            selection = "visit-" + visit.getVisitId();
        }
        else if (formUuid != null) {
            selection = "form-" + formUuid;

            form = Context.getFormService().getFormByUuid(formUuid);
            List<Encounter> encounters = Context.getEncounterService().getEncounters(patient, null, null, null, Collections.singleton(form), null, null, null, null, false);
            Encounter encounter = encounters.size() > 0 ? encounters.get(0) : null;
            model.addAttribute("encounter", encounter);
        }
        else if (program != null) {
            selection = "program-" + program.getProgramId();
        }

        else {
            if (StringUtils.isEmpty(section)) {
                section = "overview";
            }
            selection = "section-" + section;
        }

        model.addAttribute("programs", progams);
        model.addAttribute("form", form);
        model.addAttribute("visit", visit);
        model.addAttribute("patient", patient);
        model.addAttribute("section", section);
        model.addAttribute("selection", selection);
    }

    /**
     * Adds this patient to the user's recently viewed list
     * @param patient the patient
     * @param session the session
     */
    private void recentlyViewed(Patient patient, Session session) {
        String attrName = FacesConstants.APP_FACES + ".recentlyViewedPatients";

        LinkedList<Integer> recent = session.getAttribute(attrName, LinkedList.class);
        if (recent == null) {
            recent = new LinkedList<Integer>();
            session.setAttribute(attrName, recent);
        }
        recent.removeFirstOccurrence(patient.getPatientId());
        recent.add(0, patient.getPatientId());
        while (recent.size() > 10)
            recent.removeLast();
    }

}
