package main.java.logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class OutputFactory {
	
	public Writable newCustomOutput(String implementor, String[] params) throws CustomOutputException {
		Class<?> customClass;
		try {
			customClass = Class.forName(implementor);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new CustomOutputException();
		}
		Constructor<?> customConstructor;
		try {
			customConstructor = customClass.getConstructor(String[].class);
		} catch (NoSuchMethodException | SecurityException e1) {
			e1.printStackTrace();
			throw new CustomOutputException();
		}
		Writable customOutput;
		try {
			customOutput = (Writable)customConstructor.newInstance((Object[])params);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			throw new CustomOutputException();
		} 
		return customOutput;
	}

}
