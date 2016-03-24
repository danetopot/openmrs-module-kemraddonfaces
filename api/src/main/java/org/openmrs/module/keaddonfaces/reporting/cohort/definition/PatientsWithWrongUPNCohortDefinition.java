package org.openmrs.module.keaddonfaces.reporting.cohort.definition;

import org.openmrs.module.reporting.cohort.definition.BaseCohortDefinition;
import org.openmrs.module.reporting.common.Localized;
import org.openmrs.module.reporting.definition.configuration.ConfigurationPropertyCachingStrategy;
import org.openmrs.module.reporting.evaluation.caching.Caching;

/**
 * Definition for patients with anomalies in UPN numbers
 */
@Caching(strategy = ConfigurationPropertyCachingStrategy.class)
@Localized("reporting.PatientsWithWrongUPNCohortDefinition")
public class PatientsWithWrongUPNCohortDefinition extends BaseCohortDefinition {

}
