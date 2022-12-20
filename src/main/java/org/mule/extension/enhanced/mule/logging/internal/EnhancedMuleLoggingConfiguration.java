package org.mule.extension.enhanced.mule.logging.internal;

import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

/**
 * This class represents an extension configuration, values set in this class are commonly used across multiple
 * operations since they represent something core from the extension.
 */
@Operations(EnhancedMuleLoggingOperations.class)
//@ConnectionProviders(EnhancedMuleLoggingConnectionProvider.class)
public class EnhancedMuleLoggingConfiguration {
    @Parameter
    @Optional
    private String defaultCategory;

    public String getDefaultCategory() {
        return defaultCategory;
    }

    public void setDefaultCategory(String defaultCategory) {
        this.defaultCategory = defaultCategory;
    }
}
