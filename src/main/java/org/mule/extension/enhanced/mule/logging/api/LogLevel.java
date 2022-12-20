package org.mule.extension.enhanced.mule.logging.api;

import org.apache.logging.log4j.Level;

public enum LogLevel {
    FATAL(Level.FATAL), ERROR(Level.ERROR), WARN( Level.WARN), INFO( Level.INFO), DEBUG( Level.DEBUG), TRACE( Level.TRACE);
    private Level level;

     LogLevel(Level level) {
          this.level = level;
     }

     public Level getLevel() {
          return level;
     }
}
