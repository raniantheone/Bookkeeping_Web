package ranian.bookkeeping.system.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingUtility {

	private static Logger logger;
	
	static {
		LoggingUtility.logger = LogManager.getRootLogger();
	}
	
	public static Logger getLogger() {
		return LoggingUtility.logger;
	}
	
}
