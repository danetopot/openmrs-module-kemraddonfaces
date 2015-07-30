package org.openmrs.module.keaddonfaces.metadata;

import org.openmrs.PatientIdentifierType;
import org.openmrs.module.kenyaemr.Metadata;
import org.openmrs.module.kenyaemr.metadata.CommonMetadata;
import org.openmrs.module.kenyaemr.metadata.HivMetadata;
import org.openmrs.module.kenyaemr.metadata.MchMetadata;
import org.openmrs.module.kenyaemr.metadata.TbMetadata;
import org.openmrs.module.metadatadeploy.bundle.AbstractMetadataBundle;
import org.openmrs.module.metadatadeploy.bundle.Requires;
import org.springframework.stereotype.Component;

import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.encounterType;
import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.form;
import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.patientIdentifierType;

/**
 * Created with IntelliJ IDEA.
 * User: werick
 * Date: 3/19/14
 * Time: 1:56 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
@Requires({TbMetadata.class, HivMetadata.class, MchMetadata.class, CommonMetadata.class})
public class FacesMetadata extends AbstractMetadataBundle {

    public static class _EncounterType {

        public static final String ADHERENCE = "155c23a5-a6e3-4d33-aceb-7a1a735c51de";
        public static final String ARV_ELIGIBILITY_ENCOUNTER = "aeaaa866-7ed1-455c-9002-ba40e67a9882";
        public static final String COUNSELLING = "6eb40e54-1a73-4adf-be2a-464731cf842b";
        public static final String CONGENITAL_SCREENING = "f5768c5a-a543-4da9-a4b1-aee21406a39f";
        public static final String SCREENING_VISIT = "855ca272-c4d3-4cec-8e42-bd9d37531b00";
        public static final String CHECKLIST_VISIT = "11e1c136-f17d-46bd-82c0-99e3a5b6cbab";
        public static final String WEEK0_VISIT = "1da99510-c983-44ec-9c39-96dc4ef20f3e";
        public static final String WEEK4_VISIT = "46106ab5-56aa-4bba-943b-3d75e561ac63";
        public static final String SEARCHRFV = "802ad8cf-1afa-4d89-aa30-b3cae0de1b15";
        public static final String SUBJECTWITHDRAWAL = "a02a55d3-dcbd-4d90-80c5-88791362ed64";
        public static final String ADVERSE_EVENT = "90426a8e-0c5a-43bc-81f8-6d3f874c81ff";


       // public static final String DISCONTINUATION = "2bdada65-4c72-4a48-8730-859890e25cee";
       // public static final String EXAMPLE = "d69dedbd-3933-4e44-8292-bea939ce980a";
        //public static final String PSC_INITIAL = "3e87713a-1ccb-4ca3-a5eb-5a962f63d1b0";
        //public static final String HEI_FOLLOWUP = "20222135-4da8-4327-9178-84362702906b";
        //public static final String HEI_INITIAL = "65f7e74d-f3f2-473d-9e32-20c90fd0ecba";
        //public static final String LABORATORY_INVESTIGATION = "17a381d1-7e29-406a-b782-aa903b963c28";
    }

    public static class _Form {

        //form uuids
        public static final String ADHERENCE = "db64f32b-1ce5-4767-a0d1-45eeb6b81eb8";
        public static final String LAB_FORM = "82cffa17-55db-4fb9-ab88-983ae26979d8";
        public static final String ART_ELIBILITY_FORM = "fc1b79a3-de7c-4423-81f4-9b61cbd4b2ef";
        public static final String COUNSELLING_FORM = "8ced7cda-b13b-43ea-ada2-4c47fb94c751";
        public static final String HEI_INITIAL_FORM = "a938c14d-1ca8-456e-b992-f44b37a55ae2";
        public static final String HEI_FOLLOWUP_FORM = "38b3108e-624c-4dc7-89de-0903679c53e2";
        public static final String DISCONTINUATION_FORM = "e4fce01e-3bbe-40c1-9578-30935d43b335";
        public static final String TB_ICF_FORM = "d292bba8-f559-4948-866e-8b502eb12c68";
        public static final String CONGENITAL_SCREENING_FORM = "42a1122e-27bc-470a-af94-bac85d8a229f";
        public static final String CRF1_FORM = "e3bbfac4-d32f-4946-8167-eba6e9aab1aa";
        public static final String CRF2_FORM = "2f7062a8-8a27-4a77-9803-e8a98ed280af";
        public static final String CRF3_FORM = "44a3c3ec-01e0-4b9e-bfdb-e4267a06ee53";
        public static final String CRF4_FORM = "75fff7d6-5366-4dff-821a-e02c5ddabb2e";
        public static final String CRF5_FORM = "306df212-0a42-4ffa-8004-aca30262cf5b";
        public static final String CRF6_FORM = "b8946ef2-de6d-4397-9949-3111163b2690";
        public static final String CRF7_FORM = "edba1afb-7c79-4c8a-9634-112f9ca497e7";
        public static final String CRF8_FORM = "83854537-abc1-4ad3-ba80-49131c7de3b0";
        public static final String CRF9_FORM = "6aa79120-517c-4392-bf68-73269f2ca7c7";
        public static final String CRF11_FORM = "5e61252d-6e82-445e-8f75-a1ff8e77189e";
    }

    public static final class _PatientIdentifierType {
        //public static final String NATIONAL_ID = Metadata.IdentifierType.NATIONAL_ID;
        //public static final String OLD_ID = Metadata.IdentifierType.OLD;
        //public static final String OPENMRS_ID = Metadata.IdentifierType.MEDICAL_RECORD_NUMBER;
        public static final String PATIENT_CLINIC_NUMBER = Metadata.IdentifierType.PATIENT_CLINIC_NUMBER;
        public static final String HEI_ID_NUMBER = Metadata.IdentifierType.HEI_UNIQUE_NUMBER;
        public static final String UNIQUE_PATIENT_NUMBER = Metadata.IdentifierType.UNIQUE_PATIENT_NUMBER;
        public static final String SEARCH_ID = "02c0d163-0373-41f3-8f92-434b13184c77";
    }

    /**
     * @see org.openmrs.module.kenyacore.metadata.bundle.AbstractMetadataBundle#install()
     */
    @Override
    public void install() {

        //install encounter_types
        install(encounterType("Adherence encounter", "Adherence visit details", _EncounterType.ADHERENCE));
        install(encounterType("General Counselling", "Encounter type that involves counselling", _EncounterType.COUNSELLING));
        install(encounterType("Congenital Abnormality Screening", "Encounter type that involves Screening Infants for Congenital Abnormality", _EncounterType.CONGENITAL_SCREENING));
       // install(encounterType("HEI Initial", "Out-patient Initial visit for HEI patients at the PSC", MchMetadata._EncounterType.HEI_INITIAL));
        //install(encounterType("HEI Followup", "Out-patient followup visit for HEI patients at the PSC", _EncounterType.HEI_FOLLOWUP));
        install(encounterType("Art Eligibility encounter", "Art Eligibility", _EncounterType.ARV_ELIGIBILITY_ENCOUNTER));


        //Search Encounter Types
        install(encounterType("Screening Encounter", "Screening Visit", _EncounterType.SCREENING_VISIT));
        install(encounterType("Checklist Encounter", "checklist", _EncounterType.CHECKLIST_VISIT));
        install(encounterType("Adverse Event Encounter", "Adverse Event Encounter for study participant", _EncounterType.ADVERSE_EVENT));
        install(encounterType("Week0 Encounter", "Enrollment Visit at Week zero", _EncounterType.WEEK0_VISIT));
        install(encounterType("Week4 Encounter", "Followup visit for study participant at week four", _EncounterType.WEEK4_VISIT));
        install(encounterType("Subject Withdrawal Encounter", "Subject withdrawal from study", _EncounterType.SUBJECTWITHDRAWAL));
        install(encounterType("Search Routine Followup", "Routine followup for study participant", _EncounterType.SEARCHRFV));


        //Install forms

        install(form("Adherence form", null, _EncounterType.ADHERENCE, "1", _Form.ADHERENCE));
        install(form("General counselling form", null, _EncounterType.COUNSELLING, "1", _Form.COUNSELLING_FORM));
        install(form("Congenital Abnormality Screening form", null, _EncounterType.CONGENITAL_SCREENING, "1", _Form.CONGENITAL_SCREENING_FORM));
        install(form("Discontinuation form", null, HivMetadata._EncounterType.HIV_DISCONTINUATION, "1", _Form.DISCONTINUATION_FORM));
        install(form("HEI Follow-Up Card, page 1", null, MchMetadata._EncounterType.MCHCS_ENROLLMENT, "1", _Form.HEI_INITIAL_FORM));
        install(form("HEI Follow-Up Card: Growth, Nutrition and Development Monitoring", null, MchMetadata._EncounterType.MCHCS_CONSULTATION, "1", _Form.HEI_FOLLOWUP_FORM));
        install(form("Laboratory investigation form", null, CommonMetadata._EncounterType.LAB_RESULTS, "1", _Form.LAB_FORM));
        install(form("TB ICF form", null, TbMetadata._EncounterType.TB_SCREENING, "1", _Form.TB_ICF_FORM));
        install(form("Art Eligibility form", null, _EncounterType.ARV_ELIGIBILITY_ENCOUNTER, "1", _Form.ART_ELIBILITY_FORM));

        //Installing SEARCH CRFs
        install(form("CRF1-Screening Visit form", null, _EncounterType.SCREENING_VISIT, "1", _Form.CRF1_FORM));
        install(form("CRF2-Screening Checklist form", null, _EncounterType.CHECKLIST_VISIT, "1", _Form.CRF2_FORM));
        install(form("CRF3-Week o Enrollment", null, _EncounterType.WEEK0_VISIT, "1", _Form.CRF3_FORM));
        install(form("CRF4-Week 4 Visit", null, _EncounterType.WEEK4_VISIT, "1", _Form.CRF4_FORM));
        install(form("CRF5-Routine Followup", null, _EncounterType.SEARCHRFV, "1", _Form.CRF5_FORM));
        install(form("CRF6-Clinical Officer", null, HivMetadata._EncounterType.HIV_CONSULTATION, "1", _Form.CRF6_FORM));
        install(form("CRF7-Diagnosis", null, HivMetadata._EncounterType.HIV_CONSULTATION, "1", _Form.CRF7_FORM));
        install(form("CRF8-ART Initiate or Change", null, HivMetadata._EncounterType.HIV_CONSULTATION, "1", _Form.CRF8_FORM));
        install(form("CRF9-Adverse Event", null, _EncounterType.ADVERSE_EVENT, "1", _Form.CRF9_FORM));
        install(form("CRF11-Subject Withdrawal", null, _EncounterType.SUBJECTWITHDRAWAL, "1", _Form.CRF11_FORM));

        //update Identifiers
        install(patientIdentifierType("Patient Clinic Number", "Assigned to the patient at a clinic service (not globally unique)",
                "^[0-9]{5}(SDC|SDH|SGO|SHU|SKG|SKI|SKS|SKT|SKU|SLA|SLW|SMB|SMG|SMS|SNA|SNG|SNG|SNN|SNR|SNS|SNT|SNW|SNY|SOB|SOG|SON|SPO|SRE|SRI|SRO|SSE|SSK|SSN|STA|STM|STO|STU|SUG|SUS|SWK|SYO)-[0-9]$",
                "12345KLM-9", null,
                PatientIdentifierType.LocationBehavior.REQUIRED, false, _PatientIdentifierType.PATIENT_CLINIC_NUMBER));
        install(patientIdentifierType("HEI ID Number", "Assigned to a child patient when enrolling into HEI","^E[0-9]{5}(SDH|SKS|SKT|SMB|SMG|SNN|SNR|SNT|SNW|SOB|SOG|SSK|SSN|STM|STU|SUG|SUS|SWK)-[0-9]$",
                "E54109KLM-8", null,
                PatientIdentifierType.LocationBehavior.NOT_USED, false, _PatientIdentifierType.HEI_ID_NUMBER));
        install(patientIdentifierType("Unique Patient Number", "Assigned to every HIV patient", "^[0-9]{5}-[0-9]{5}$", "Facility code followed by sequential number",
                null, PatientIdentifierType.LocationBehavior.NOT_USED, false, _PatientIdentifierType.UNIQUE_PATIENT_NUMBER));

        install(patientIdentifierType("SEARCH ID Number", "Assigned to a patient when enrolling into SEARCH Study",
                null, null, null,
                PatientIdentifierType.LocationBehavior.NOT_USED, false, _PatientIdentifierType.SEARCH_ID));


    }
}