package logger.customFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import logger.filters.Filterer;
import logger.writables.Writable;

public class CustomFactory {
	
//	public Filterer createCustomFilter(String implementor, String[] params) throws CustomFilterException {
//		Class<?> customClass;
//		try {
//			customClass = Class.forName(implementor);
//			if (!implementsFilterer(customClass)) {
//				throw new CustomFilterException();
//			}
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//			throw new CustomFilterException();
//		}
//		Constructor<?>[] allConstructors;
//		Constructor<?> constructor = null;
//		try {
//			allConstructors = customClass.getDeclaredConstructors();
//			for (Constructor<?> oneConstructor: allConstructors) {
//				if (oneConstructor.getParameterTypes().length == params.length) { // assuming types are correct
//					constructor = oneConstructor;
//				}
//			}
//		} catch (SecurityException e1) {
//			e1.printStackTrace();
//			throw new CustomFilterException();
//		}
//		Filterer customFilter;
//		try {
//			customFilter = (Filterer)constructor.newInstance((Object[])params);
//		} catch (InstantiationException | IllegalAccessException
//				| IllegalArgumentException | InvocationTargetException e) {
//			e.printStackTrace();
//			throw new CustomFilterException();
//		} 
//		return customFilter;
//	}
	
//	private boolean implementsFilterer(Class<?> oneClass) {
//		Class<?>[] interfacesImplemented = oneClass.getInterfaces();
//		for (Class<?> oneInterface: interfacesImplemented) {
//			if (oneInterface.getName().equals(Filterer.class.getName())) {
//				return true;
//			}
//		}
//		return false;
//	}
	
	private boolean implementsCustom(Class<?> oneClass,Class<?> customClass) {
		Class<?>[] interfacesImplemented = oneClass.getInterfaces();
		for (Class<?> oneInterface: interfacesImplemented) {
			if (oneInterface.getName().equals(customClass.getName())) {
				return true;
			}
		}
		return false;
	}
	
	private Object createCustom(String implementor, String[] params,Class<?> customClassToCreate){
		Class<?> customClass;
		try {
			customClass = Class.forName(implementor);
			if (!implementsCustom(customClass,customClassToCreate)) {
				return null;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
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
			return null;
		}
		Object custom;
		try {
			custom = constructor.newInstance((Object[])params);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		} 
		return custom;
	}
	
	public Filterer createCustomFilter(String implementor, String[] params) throws CustomFilterException {
		Class<Filterer> customClassToCreate = Filterer.class;
		Filterer custom = customClassToCreate.cast(createCustom(implementor, params, customClassToCreate));
		if(custom==null){
			throw new CustomFilterException();
		}
		return custom;
	}
	
	public Writable createCustomOutput(String implementor, String[] params) throws CustomOutputException {
		Class<Writable> customClassToCreate = Writable.class;
		Writable custom = customClassToCreate.cast(createCustom(implementor, params, customClassToCreate));
		if(custom==null){
			throw new CustomOutputException();
		}
		return custom;
	}

}