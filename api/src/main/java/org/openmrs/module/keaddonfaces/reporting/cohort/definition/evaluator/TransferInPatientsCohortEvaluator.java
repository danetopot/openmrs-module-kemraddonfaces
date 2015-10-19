package org.openmrs.module.keaddonfaces.reporting.cohort.definition.evaluator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Cohort;
import org.openmrs.annotation.Handler;
import org.openmrs.api.context.Context;
import org.openmrs.module.keaddonfaces.reporting.cohort.definition.PatientsWithWrongUPNCohortDefinition;
import org.openmrs.module.keaddonfaces.reporting.cohort.definition.TransferInPatientsCohortDefinition;
import org.openmrs.module.kenyaemr.api.KenyaEmrService;
import org.openmrs.module.reporting.cohort.EvaluatedCohort;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.evaluator.CohortDefinitionEvaluator;
import org.openmrs.module.reporting.evaluation.EvaluationContext;
import org.openmrs.module.reporting.evaluation.EvaluationException;

import java.util.*;

/**
 * Evaluator class for TI patients
 */
@Handler(supports = {TransferInPatientsCohortDefinition.class})
public class TransferInPatientsCohortEvaluator implements CohortDefinitionEvaluator {

    private final Log log = LogFactory.getLog(this.getClass());

    @Override
    public EvaluatedCohort evaluate(CohortDefinition cohortDefinition, EvaluationContext context) throws EvaluationException {

        TransferInPatientsCohortDefinition definition = (TransferInPatientsCohortDefinition) cohortDefinition;

        if (definition == null)
            return null;

		Cohort newCohort = new Cohort();

		String qry = " select o.person_id, pi.identifier " +
                " from obs o " +
                " inner join patient_identifier pi on pi.patient_id = o.person_id and pi.identifier_type=3 and pi.voided=0 " +
                " where o.concept_id=160563 and o.value_coded=1065 and o.voided=0 and (pi.date_changed < '2015-10-01' or pi.date_changed is null) " +
                " group by pi.patient_id having length(pi.identifier)<11 order by o.person_id limit 100; ";

		Map<String, Object> m = new HashMap<String, Object>();
        Set<Integer> patientIds = makePatientDataMapFromSQL(qry,m);
		if (patientIds !=null){
			newCohort.setMemberIds(patientIds);
		}
        return new EvaluatedCohort(newCohort, definition, context);
    }

	protected Set<Integer> makePatientDataMapFromSQL(String sql, Map<String, Object> substitutions) {
		List<Object> data = Context.getService(KenyaEmrService.class).executeSqlQuery(sql, substitutions);

		return makePatientDataMap(data);
	}

	protected Set<Integer> makePatientDataMap(List<Object> data) {
        Set<Integer> idSet = new HashSet<Integer>();
        for (Object o : data) {
            Object[] parts = (Object[]) o;
            if (parts.length == 2) {
                Integer ptId = (Integer) parts[0];
                idSet.add(ptId);
            }
        }

        return idSet;
	}
}
