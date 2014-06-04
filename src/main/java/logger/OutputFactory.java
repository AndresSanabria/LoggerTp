package logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class OutputFactory {
	
	public Writable createCustomOutput(String implementor, String[] params) throws CustomOutputException {
		Class<?> customClass;
		try {
			customClass = Class.forName(implementor);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new CustomOutputException();
		}
		Constructor<?>[] allConstructors;
		Constructor<?> constructor = null;
		try {
			allConstructors = customClass.getDeclaredConstructors();
			for (Constructor<?> oneConstructor: allConstructors) {
				if (oneConstructor.getParameterTypes().length == params.length) { // assuming types are correct
					constructor = oneConstructor;
				}
			}
		} catch (SecurityException e1) {
			e1.printStackTrace();
			throw new CustomOutputException();
		}
		Writable customOutput;
		try {
			customOutput = (Writable)constructor.newInstance((Object[])params);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			throw new CustomOutputException();
		} 
		return customOutput;
	}

}
