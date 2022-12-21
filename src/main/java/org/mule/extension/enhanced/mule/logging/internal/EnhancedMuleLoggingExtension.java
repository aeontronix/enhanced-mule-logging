package org.mule.extension.enhanced.mule.logging.internal;

import org.mule.extension.enhanced.mule.logging.jsonlayout.EMJsonLayout;
import org.mule.runtime.extension.api.annotation.Configurations;
import org.mule.runtime.extension.api.annotation.Export;
import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.dsl.xml.Xml;


/**
 * This is the main class of an extension, is the entry point from which configurations, connection providers, operations
 * and sources are going to be declared.
 */
@Xml(prefix = "enhanced-mule-logging")
@Extension(name = "Enhanced Logging")
@Configurations(EnhancedMuleLoggingConfiguration.class)
@Export(classes = EMJsonLayout.class)
public class EnhancedMuleLoggingExtension {

}
