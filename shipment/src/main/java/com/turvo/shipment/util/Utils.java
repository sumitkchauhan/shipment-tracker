package com.turvo.shipment.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import com.turvo.shipment.exception.ShipmentRuntimeException;

/**
 * Utils methods
 *
 *
 */
public class Utils {
	public static final String UTF8_CHARSET = "UTF-8";
	public static final String EMPTY_STRING = "";
	public static final String DELIMTER_FIELD = "|";
	private static final Pattern PTTRN_REGEX_SPLIT = Pattern.compile("\\|");
	public static final int SALT_LENGTH = 2;
	private static Random rand = ThreadLocalRandom.current();
	private static final Pattern PTTRN_WHITESPACE_REPLACE = Pattern.compile("[\\s]{1,}");
	private static final String WHITESPACE_REPLACEMENT = " ";

	private Utils() {
		throw new ShipmentRuntimeException("Shouldn't be invoked.");
	}

	/**
	 * Checks argument and throw {@link IllegalArgumentException} if empty
	 *
	 * @param bytesToCheck
	 *            Bytes to check for
	 */
	public static void blockEmptyArgument(byte[] bytesToCheck) {
		if (isEmpty(bytesToCheck)) {
			throw new IllegalArgumentException("Empty bytes.");
		}
	}

	/**
	 * Checks argument and throw {@link IllegalArgumentException} if empty
	 *
	 * @param bytesToCheck
	 *            Bytes to check for
	 */
	public static void blockEmptyArgument(String stringToCheck) {
		if (StringUtils.isEmpty(stringToCheck)) {
			throw new IllegalArgumentException("Empty String.");
		}
	}

	/**
	 * Checks argument and throw {@link IllegalArgumentException} if empty
	 *
	 * @param bytesToCheck
	 *            Bytes to check for
	 */
	public static void blockEmptyArgument(Object objectToCheck) {
		if (null == objectToCheck) {
			throw new IllegalArgumentException("Empty Object.");
		}
	}

	/**
	 * Checks argument and throw {@link IllegalArgumentException} if empty. Fail
	 * fast.
	 *
	 * @param bytesToCheck
	 *            Bytes to check for
	 */
	@SuppressWarnings("rawtypes")
	public static void blockEmptyArgument(Object... objectsToCheck) {
		if (null == objectsToCheck) {
			throw new IllegalArgumentException("Empty Object.");
		}
		for (Object obj : objectsToCheck) {
			if (obj instanceof String) {
				blockEmptyArgument((String) obj);
			} else if (obj instanceof Collection) {
				blockEmptyArgument((Collection) obj);
			} else {
				blockEmptyArgument(obj);
			}
		}
	}

	public static boolean isEmpty(Object[] collection) {
		return (null == collection || collection.length == 0);
	}

	public static boolean isEmpty(String input) {
		return StringUtils.isEmpty(input);
	}

	public static boolean isEmpty(@SuppressWarnings("rawtypes") Collection collection) {
		return (null == collection || collection.size() == 0);
	}

	public static boolean isEmpty(@SuppressWarnings("rawtypes") Map map) {
		return (null == map || map.size() == 0);
	}

	/**
	 * Checks argument and returns true if empty, false otherwise
	 *
	 * @param bytesToCheck
	 *            Bytes to check for
	 */
	public static boolean isEmpty(byte[] bytesToCheck) {
		return (null == bytesToCheck || bytesToCheck.length == 0);
	}

	public static byte[] toBytes(String s) {
		try {
			return s.getBytes(UTF8_CHARSET);
		} catch (UnsupportedEncodingException e) {
			throw new ShipmentRuntimeException("UTF8 encoding not supported by platform.", e);
		}
	}

	public static String convertToString(byte[] bytes) {
		try {
			if (isEmpty(bytes)) {
				return EMPTY_STRING;
			}
			return new String(bytes, UTF8_CHARSET);
		} catch (UnsupportedEncodingException e) {
			throw new ShipmentRuntimeException("UTF8 encoding not supported by platform.", e);
		}
	}

	/**
	 * Field split patterns
	 *
	 * @param inputString
	 * @return Split pattern
	 */
	public static String[] splitAroundFieldDelimiter(String inputString) {
		if (StringUtils.isEmpty(inputString)) {
			return new String[0];
		}
		return PTTRN_REGEX_SPLIT.split(inputString);
	}

	/**
	 * Unconditionally close an <code>Closeable</code>.
	 * <p>
	 * Any exceptions will be ignored. This is typically used in finally blocks.
	 *
	 * @param input
	 *            the Closeable to close, may be null or already closed
	 */
	public static void closeQuietly(Closeable closeable) {
		try {
			if (closeable != null) {
				closeable.close();
			}
		} catch (IOException ioe) {
			// ignore
		}
	}

	/**
	 * Unconditionally closes the executorServices
	 *
	 * @param executorServices
	 *            Executor Services to close
	 */
	public static void shutdownQuietly(ExecutorService... executorServices) {
		if (isEmpty(executorServices)) {
			return;
		}
		for (ExecutorService executorService : executorServices) {
			if (null == executorService) {
				continue;
			}
			executorService.shutdown();
		}
	}

	public static int getRandomNumberInRange(int min, int max) {
		return (rand.nextInt(max - min) + min);
	}

	/**
	 * Null safe wrapper over list
	 * 
	 * @param list
	 *            Possibly null list
	 * @return An empty list instead of null
	 */
	public static <T> List<T> nullSafe(List<T> list) {
		if (null == list)
			return Collections.<T>emptyList();
		else
			return list;
	}

	public static String normalizeString(String input) {
		if (isEmpty(input)) {
			return input;
		}
		return PTTRN_WHITESPACE_REPLACE.matcher(input.trim().toLowerCase()).replaceAll(WHITESPACE_REPLACEMENT);
	}

	public static List<String> normalizeStrings(List<String> inputs) {
		if (isEmpty(inputs)) {
			return Collections.<String>emptyList();
		}
		return inputs.stream().map(input -> normalizeString(input)).collect(Collectors.toList());
	}

	public static <E, K> void executeIfNotEmpty(Map<E, K> map, Consumer<Map<E, K>> consumer) {
		if (null != map) {
			consumer.accept(map);
		}
	}

	public static <E> void executeIfNotEmpty(Collection<E> collection, Consumer<Collection<E>> consumer) {
		if (null != collection) {
			consumer.accept(collection);
		}
	}

	/**
	 * Cacthes and logs any errors, but doesn't throw an exception. Make sure that
	 * error doesn't need to be propagated up. Intended for functions which need to
	 * be executed, but we don't care much about if it throws an exception. An eg is
	 * quitely closing a connection which might throw an IOException
	 * 
	 * @param supplier
	 * @param logger
	 */
	public static <E> void executeSafely(Supplier<E> supplier, org.apache.log4j.Logger logger) {
		if (null == supplier) {
			return;
		}
		try {
			supplier.get();
		} catch (RuntimeException ex) {
			if (null != logger) {
				logger.debug("Error occured during execution.", ex);
			}
		}
	}
}
