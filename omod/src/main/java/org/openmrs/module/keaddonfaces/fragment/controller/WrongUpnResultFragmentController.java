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

import org.openmrs.*;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.module.keaddonfaces.metadata.FacesMetadata;
import org.openmrs.module.keaddonfaces.reporting.cohort.definition.PatientsWithWrongUPNCohortDefinition;
import org.openmrs.module.kenyaemr.EmrConstants;
import org.openmrs.module.kenyaemr.metadata.HivMetadata;
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
import org.openmrs.ui.framework.fragment.action.FailureResult;
import org.openmrs.ui.framework.fragment.action.FragmentActionResult;
import org.openmrs.ui.framework.fragment.action.SuccessResult;
import org.springframework.web.bind.annotation.RequestParam;

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

    public FragmentActionResult editUPN (UiUtils ui, @RequestParam("newUPN") String upn, @RequestParam("newPCN") String pcn, @RequestParam("ptId") Integer ptId) {

        PatientService patientService = Context.getPatientService();
        Patient patient = patientService.getPatient(ptId);
        String upn_uuid = HivMetadata._PatientIdentifierType.UNIQUE_PATIENT_NUMBER;
        PatientIdentifierType ptUPN = patientService.getPatientIdentifierType(3);
        PatientIdentifierType ptPCN = patientService.getPatientIdentifierType(7);

        PatientIdentifier oldUPN = patient.getPatientIdentifier(ptUPN);
        PatientIdentifier oldPCN = patient.getPatientIdentifier(ptPCN);

        String gP_defLoc = Context.getAdministrationService().getGlobalProperty(EmrConstants.GP_DEFAULT_LOCATION);
        Location defLoc = Context.getLocationService().getLocation(Integer.parseInt(gP_defLoc));
        try {
        if (oldUPN != null) {
            oldUPN.setIdentifier(upn);
            patientService.savePatientIdentifier(oldUPN);
        } else {
            PatientIdentifier pUPN = new PatientIdentifier(upn, ptUPN, defLoc);
            pUPN.setPreferred(true);
            pUPN.setPatient(patient);
            patientService.savePatientIdentifier(pUPN);
        }

        if (oldPCN != null) {
            oldPCN.setIdentifier(pcn);
            patientService.savePatientIdentifier(oldPCN);
        }
        else {
            PatientIdentifier pPCN = new PatientIdentifier(pcn, ptPCN, defLoc);
            pPCN.setPatient(patient);
            patientService.savePatientIdentifier(pPCN);
        }
        } catch (Exception e){
              e.printStackTrace();
              return  new FailureResult("An error occurred while updating patient identifiers");
        }
        return new SuccessResult("Identifier was successfully edited");
    }

    private List<PatientStub> refreshPatientList() {
        CohortDefinition cd = new PatientsWithWrongUPNCohortDefinition();
        CohortDefinitionService service = Context.getService(CohortDefinitionService.class);
        PatientService patientService = Context.getPatientService();
        EvaluationContext context = new EvaluationContext(new Date());
        List<PatientStub> patients = new ArrayList<PatientStub>();
        try {
            EvaluatedCohort evaluatedCohort = service.evaluate(cd,context);
            for (Integer ptId : evaluatedCohort.getMemberIds()) {
                patients.add(clonePatient(patientService.getPatient(ptId)));
            }

        } catch (EvaluationException e) {
            e.printStackTrace();
        }
        return patients;
    }
    private PatientStub clonePatient (Patient p) {
        String names = p.getGivenName() + " " + p.getMiddleName() + " " + p.getFamilyName();
        String upn = p.getPatientIdentifier(3) !=null? p.getPatientIdentifier(3).toString() : "";
        String pcn = p.getPatientIdentifier(7) !=null? p.getPatientIdentifier(7).toString() : "";
        PatientStub stub = new PatientStub(p.getId(), names, upn, pcn, p.getGender());
        return stub;
    }

    class PatientStub {
        private Integer id;
        private String names;
        private String uniquePatientNo;
        private String patientClinicNo;
        private String gender;

        PatientStub(Integer id, String names, String uniquePatientNo, String patientClinicNo, String gender) {
            this.id = id;
            this.names = names;
            this.uniquePatientNo = uniquePatientNo;
            this.patientClinicNo = patientClinicNo;
            this.gender = gender;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getNames() {
            return names;
        }

        public void setNames(String names) {
            this.names = names;
        }

        public String getUniquePatientNo() {
            return uniquePatientNo;
        }

        public void setUniquePatientNo(String uniquePatientNo) {
            this.uniquePatientNo = uniquePatientNo;
        }

        public String getPatientClinicNo() {
            return patientClinicNo;
        }

        public void setPatientClinicNo(String patientClinicNo) {
            this.patientClinicNo = patientClinicNo;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }
    }
}
