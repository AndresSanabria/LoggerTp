package main.java.logger;

import java.util.Map;

public class SimpleFormatter extends Formatter {

	private String generateString(Map<Integer, String> infoToFormat) {
		String formatedLog = "";
		for (int i = 0; i < infoToFormat.size(); i++) {
			formatedLog = formatedLog + infoToFormat.get(i) + separator;
		}
		if (formatedLog.length() > 0)
			formatedLog = formatedLog.substring(0, formatedLog.length() - 1);
		return formatedLog;
	}

	@Override
	public String giveFormat(String level, String log) {
		Map<Integer, String> infoToFormat = getOrderedInfoToFormat(level, log,
				null, null, null);
		return generateString(infoToFormat);
	}

	@Override
	public String giveFormat(String level, String log, String fileName,
			String methodName, Integer lineNumber) {
		Map<Integer, String> infoToFormat = getOrderedInfoToFormat(level, log,
				fileName, methodName, lineNumber);
		return generateString(infoToFormat);
	}

}
