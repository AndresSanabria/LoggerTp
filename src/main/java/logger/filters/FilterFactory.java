package logger.filters;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class FilterFactory {
	
	public Filterer createCustomFilter(String implementor, String[] params) throws CustomFilterException {
		Class<?> customClass;
		try {
			customClass = Class.forName(implementor);
			if (!implementsFilterer(customClass)) {
				throw new CustomFilterException();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new CustomFilterException();
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
			throw new CustomFilterException();
		}
		Filterer customFilter;
		try {
			customFilter = (Filterer)constructor.newInstance((Object[])params);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			throw new CustomFilterException();
		} 
		return customFilter;
	}
	
	private boolean implementsFilterer(Class<?> oneClass) {
		Class<?>[] interfacesImplemented = oneClass.getInterfaces();
		for (Class<?> oneInterface: interfacesImplemented) {
			if (oneInterface.getName().equals(Filterer.class.getName())) {
				return true;
			}
		}
		return false;
	}

}