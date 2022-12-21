package org.mule.extension.enhanced.mule.logging.api;

import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

public class SendLogsParams {
    @DisplayName("Enabled")
    @Optional(defaultValue = "false")
    @Parameter
    private boolean sendLogsEnabled;
    @DisplayName("Destination URI")
    @Parameter
    @Optional
    private String sendLogsDestinationUri;

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
