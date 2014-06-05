package logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class OutputFactory {
	
	public Writable createCustomOutput(String implementor, String[] params) throws CustomOutputException {
		Class<?> customClass;
		try {
			customClass = Class.forName(implementor);
			if (!implementsWritable(customClass)) {
				throw new CustomOutputException();
			}
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
	
	private boolean implementsWritable(Class<?> oneClass) {
		Class<?>[] interfacesImplemented = oneClass.getInterfaces();
		for (Class<?> oneInterface: interfacesImplemented) {
			if (oneInterface.getName().equals(Writable.class.getName())) {
				return true;
			}
		}
		return false;
	}

}
