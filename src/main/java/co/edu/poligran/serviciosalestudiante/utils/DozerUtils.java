package co.edu.poligran.serviciosalestudiante.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.dozer.Mapper;

public class DozerUtils {

	public static <T> Collection<T> mapCollection(Object[] source, Class<T> destinationClass, Mapper mapper) {
		return mapCollection(Arrays.asList(source), destinationClass, mapper);
	}

	public static <T> List<T> mapCollection(Object[] source, List<T> destination, Class<T> destinationClass,
			Mapper mapper) {
		return mapCollection(Arrays.asList(source), destination, destinationClass, mapper);
	}

	public static <T> List<T> mapCollection(Collection<? extends Object> source, Class<T> destinationClass,
			Mapper mapper) {
		return mapCollection(source, null, destinationClass, mapper);
	}

	public static <T> List<T> mapCollection(Collection<? extends Object> source, List<T> destination,
			Class<T> destinationClass, Mapper mapper) {
		if (destination == null)
			destination = new ArrayList<T>();

		for (Object sourceObj : source) {
			destination.add(mapper.map(sourceObj, destinationClass));
		}

		return destination;
	}
}