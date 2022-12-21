package org.mule.extension.enhanced.mule.logging.internal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.MapMessage;
import org.mule.extension.enhanced.mule.logging.api.LogLevel;
import org.mule.runtime.api.component.location.ComponentLocation;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.mule.extension.enhanced.mule.logging.internal.Flattener.flatten;


/**
 * This class is a container for operations, every public method in this class will be taken as an extension operation.
 */
public class EnhancedMuleLoggingOperations {
    @DisplayName("Log Message")
    public void logMessage(@Optional @Config EnhancedMuleLoggingConfiguration cfg,
                           @Optional(defaultValue = "INFO") LogLevel logLevel,
                           @Optional String category, String message) {
        getLogger(cfg, category).log(logLevel.getLevel(), message);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @DisplayName("Log Map")
    public void logMap(@Optional @Config EnhancedMuleLoggingConfiguration cfg,
                       ComponentLocation location,
                       @DisplayName("Log Level") @Optional(defaultValue = "INFO") LogLevel logLevel,
                       @DisplayName("Category") @Optional String category,
                       @DisplayName("Prefix") @Optional String keyPrefix,
                       @DisplayName("Include code location") @Optional(defaultValue = "true") boolean includeCodeLocation,
                       @DisplayName("Data Map") Map map) {
        Map<String, Object> flatMap = flatten(keyPrefix,map);
        try {
            final Logger logger = getLogger(cfg, category);
            if (includeCodeLocation && location != null) {
                if (location.getRootContainerName() != null) {
                    flatMap.put("code.location.rootContainerName", location.getRootContainerName());
                }
                if (location.getFileName() != null && location.getFileName().isPresent()) {
                    flatMap.put("code.location.filename", location.getFileName().get());
                }
                if (location.getLineInFile() != null && location.getLineInFile().isPresent()) {
                    flatMap.put("code.location.line", location.getLineInFile().get());
                }
            }
            final MapMessage message = new MapMessage(flatMap);
            logger.log(logLevel.getLevel(), message);
        } catch (Throwable e) {
            LogManager.getLogger().warn("Failed to create log map", e);
        }
    }

    private static Logger getLogger(EnhancedMuleLoggingConfiguration cfg, String category) {
        final String finalCategory = category != null ? category : cfg != null ? cfg.getDefaultCategory() : null;
        if (finalCategory != null) {
            return LogManager.getLogger(finalCategory);
        } else {
            return LogManager.getLogger();
        }
    }
}
