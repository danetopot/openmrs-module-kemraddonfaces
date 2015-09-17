/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.keaddonfaces.fragment.controller;

import org.openmrs.Encounter;
import org.openmrs.Form;
import org.openmrs.Patient;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.module.keaddonfaces.metadata.FacesMetadata;
import org.openmrs.module.keaddonfaces.reporting.cohort.definition.PatientsWithWrongUPNCohortDefinition;
import org.openmrs.module.metadatadeploy.MetadataUtils;
import org.openmrs.module.reporting.cohort.EvaluatedCohort;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.service.CohortDefinitionService;
import org.openmrs.module.reporting.evaluation.EvaluationContext;
import org.openmrs.module.reporting.evaluation.EvaluationException;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.annotation.FragmentParam;
import org.openmrs.ui.framework.fragment.FragmentModel;

import java.util.*;

/**
 * controller class for wrong upn fragment
 */
public class WrongUpnResultFragmentController {

    public void controller(FragmentModel model, UiUtils ui) {

        CohortDefinition cd = new PatientsWithWrongUPNCohortDefinition();
        CohortDefinitionService service = Context.getService(CohortDefinitionService.class);
        PatientService patientService = Context.getPatientService();
        EvaluationContext context = new EvaluationContext(new Date());
        Set<Integer> patientIds = new HashSet<Integer>();
        List<Patient> patients = new ArrayList<Patient>();
        try {
            EvaluatedCohort evaluatedCohort = service.evaluate(cd,context);
            for (Integer ptId : evaluatedCohort.getMemberIds()) {
                patients.add(patientService.getPatient(ptId));
            }

        } catch (EvaluationException e) {
            e.printStackTrace();
        }
            model.put("patients", patients);
    }
}
