package org.mule.extension.enhanced.mule.logging.internal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mule.runtime.api.lifecycle.Initialisable;
import org.mule.runtime.api.lifecycle.InitialisationException;
import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

/**
 * This class represents an extension configuration, values set in this class are commonly used across multiple
 * operations since they represent something core from the extension.
 */
@Operations(EnhancedMuleLoggingOperations.class)
//@ConnectionProviders(EnhancedMuleLoggingConnectionProvider.class)
public class EnhancedMuleLoggingConfiguration implements Initialisable {
    @DisplayName("Default Category")
    @Parameter
    @Optional
    private String defaultCategory;
    @ParameterGroup(name = "Send Logs")
    @DisplayName("Enabled")
    @Optional(defaultValue = "false")
    private boolean sendLogsEnabled;
    @ParameterGroup(name = "Send Logs")
    @DisplayName("Destination URI")
    @Parameter
    @Optional
    private String sendLogsDestinationUri;

    @Override
    public void initialise() throws InitialisationException {
    }

    public String getDefaultCategory() {
        return defaultCategory;
    }

    public void setDefaultCategory(String defaultCategory) {
        this.defaultCategory = defaultCategory;
    }

    public boolean isSendLogsEnabled() {
        return sendLogsEnabled;
    }

    public void setSendLogsEnabled(boolean sendLogsEnabled) {
        this.sendLogsEnabled = sendLogsEnabled;
    }

    public String getSendLogsDestinationUri() {
        return sendLogsDestinationUri;
    }

    public void setSendLogsDestinationUri(String sendLogsDestinationUri) {
        this.sendLogsDestinationUri = sendLogsDestinationUri;
    }
}
