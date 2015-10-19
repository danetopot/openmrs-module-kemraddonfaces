package org.openmrs.module.keaddonfaces.metadata;

import org.openmrs.module.keaddonfaces.FacesConstants;
import org.openmrs.module.kenyaemr.metadata.SecurityMetadata;
import org.openmrs.module.metadatadeploy.bundle.AbstractMetadataBundle;
import org.openmrs.module.metadatadeploy.bundle.Requires;
import org.springframework.stereotype.Component;

import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.*;

/**
 * Created with IntelliJ IDEA.
 * User: werick
 * Date: 4/24/14
 * Time: 10:19 AM
 * To change this template use File | Settings | File Templates.

**
        * Security metadata bundle
        */
@Component
@Requires(SecurityMetadata.class)
public class FacesSecurityMetadata extends AbstractMetadataBundle{

    public static class _Privilege {
        public static final String APP_CLEAN_UPN = "App: keaddonfaces.cleanUpn";
        public static final String APP_CLEAN_TI_PATIENTS = "App: keaddonfaces.tiPatients";
    }

    public static final class _Role {
        public static final String FACES_DATA_CLERK = "Faces Data Clerk";
        public static final String APPLICATION_CLEAN_UPN = "Application: Clean Unique Patient Numbers";
        public static final String APPLICATION_CLEAN_TI_PATIENT_IDS = "Application: Clean TI Patient Identifiers";
    }

    /**
     * @see org.openmrs.module.metadatadeploy.bundle.AbstractMetadataBundle#install()
     */
    @Override
    public void install() {

        install(privilege(_Privilege.APP_CLEAN_UPN, "Able to access App for cleaning wrong UPNs"));
        install(privilege(_Privilege.APP_CLEAN_TI_PATIENTS, "Able to access App for cleaning UPN for TI patients"));
        install(role(_Role.APPLICATION_CLEAN_UPN, "A role for cleaning Wrong UPNs at a facility", idSet(
                SecurityMetadata._Role.API_PRIVILEGES_VIEW_AND_EDIT
        ), idSet(
                _Privilege.APP_CLEAN_UPN
        )));
        install(role(_Role.APPLICATION_CLEAN_TI_PATIENT_IDS, "A role for cleaning Wrong UPNs for TI patients at a facility", idSet(
                SecurityMetadata._Role.API_PRIVILEGES_VIEW_AND_EDIT
        ), idSet(
                _Privilege.APP_CLEAN_TI_PATIENTS)));

        String[] appIds = {
                FacesConstants.APP_FACES
        };

        // Ensure a privilege exists for each app. App framework does create these but not always before this
        // bundle is installed
       /* for (String appId : appIds) {
            install(privilege(app(appId), "Access to the " + appId + " app"));
        }

        install(role(_Role.FACES_DATA_CLERK, "Can access the Faces KenyaEmr add on apps",
                idSet(SecurityMetadata._Role.API_PRIVILEGES_VIEW_AND_EDIT),
                idSet(app(FacesConstants.APP_FACES))
        ));*/
    }

    /**
     * Creates an app privilege from an app ID
     * @param appId the app ID
     * @return the privilege
     */
    protected String app(String appId) {
        return "App: " + appId;
    }
}
