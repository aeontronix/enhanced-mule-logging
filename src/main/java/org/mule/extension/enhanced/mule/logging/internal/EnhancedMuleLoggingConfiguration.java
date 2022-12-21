package org.mule.extension.enhanced.mule.logging.internal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.appender.HttpAppender;
import org.apache.logging.log4j.core.appender.SocketAppender;
import org.mule.extension.enhanced.mule.logging.api.SendLogsParams;
import org.mule.extension.enhanced.mule.logging.jsonlayout.EMJsonLayout;
import org.mule.runtime.api.i18n.I18nMessage;
import org.mule.runtime.api.lifecycle.Disposable;
import org.mule.runtime.api.lifecycle.Initialisable;
import org.mule.runtime.api.lifecycle.InitialisationException;
import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.slf4j.Logger;

import java.net.MalformedURLException;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * This class represents an extension configuration, values set in this class are commonly used across multiple
 * operations since they represent something core from the extension.
 */
@Operations(EnhancedMuleLoggingOperations.class)
//@ConnectionProviders(EnhancedMuleLoggingConnectionProvider.class)
public class EnhancedMuleLoggingConfiguration implements Initialisable, Disposable {
    private static final Logger logger = getLogger(EnhancedMuleLoggingConfiguration.class);
    @DisplayName("Default Category")
    @Parameter
    @Optional
    private String defaultCategory;
    @ParameterGroup(name = "Send Logs")
    private SendLogsParams sendLogs;

    @Override
    public void initialise() throws InitialisationException {
        try {
            if (sendLogs != null && sendLogs.isSendLogsEnabled()) {
                logger.info("Initialising log sending");
                final URI uri = URI.create(sendLogs.getSendLogsDestinationUri());
                final String scheme = uri.getScheme().toLowerCase();
                LoggerContext lc = (LoggerContext) LogManager.getContext(false);
                final EMJsonLayout layout = EMJsonLayout.createLayout(StandardCharsets.UTF_8, false);
                final Appender appender;
                if( scheme.equals("tcp") ) {
                    final String host = uri.getHost();
                    final int port = uri.getPort();
                    appender = SocketAppender.newBuilder()
                            .setLayout(layout)
                            .setName("EnhancedLoggingDynamicSocket")
                            .withHost(host)
                            .withPort(port)
                            .build();
                } else if( scheme.equals("http") || scheme.equals("https")  ) {
                    appender = HttpAppender.newBuilder()
                            .setLayout(layout)
                            .setName("EnhancedLoggingDynamicHttp")
                            .setUrl(uri.toURL())
                            .build();
                } else {
                    throw new IllegalArgumentException("Unsupported destination URI, must start with either: tcp:// or http:// or https://");
                }
                appender.start();
                lc.getConfiguration().addAppender(appender);
                lc.getRootLogger().addAppender(lc.getConfiguration().getAppender(appender.getName()));
                lc.updateLoggers();
                logger.info("Initialised log sending");
            } else {
                logger.debug("log sending not enabled");
            }
        } catch (Exception e) {
            throw new InitialisationException(e,this);
        }
    }

    @Override
    public void dispose() {

    }

    public String getDefaultCategory() {
        return defaultCategory;
    }

    public void setDefaultCategory(String defaultCategory) {
        this.defaultCategory = defaultCategory;
    }

    public SendLogsParams getSendLogs() {
        return sendLogs;
    }

    public void setSendLogs(SendLogsParams sendLogs) {
        this.sendLogs = sendLogs;
    }
}
