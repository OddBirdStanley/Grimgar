package grimgar.main;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

public class Logger {
	
	private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger("Grimgar");
	
	public static void log(Level level, String message) {
		LOGGER.log(level, message);
	}
	
	public static void info(String message) {
		LOGGER.log(Level.INFO, message);
	}
	
	public static void debug(String message) {
		LOGGER.log(Level.DEBUG, message);
	}
	
	public static void error(String message) {
		LOGGER.log(Level.ERROR, message);
	}
	
	public static void fatal(String message) {
		LOGGER.log(Level.FATAL, message);
	}
	
	public static void trace(String message) {
		LOGGER.log(Level.TRACE, message);
	}
	
	public static void warn(String message) {
		LOGGER.log(Level.WARN, message);
	}

}
