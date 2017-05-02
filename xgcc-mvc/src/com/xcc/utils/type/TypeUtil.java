/**
 * Created the com.xcc.utils.type.TypeUtil.java
 * @created 2017年2月25日 上午12:58:19
 * @version 1.0.0
 */
package com.xcc.utils.type;

import java.util.HashMap;
import java.util.Map;

/**
 * com.xcc.utils.type.TypeUtil.java
 * @author XChao
 */
public class TypeUtil {
	private static final Map<Class<?>, TypeICast> casts = new HashMap<>();
	private static final TypeICast defaultCast = new TypeDefaultCast();
	static {
		casts.put(byte.class, new TypeByteCast());
		casts.put(Byte.class, new TypeByteCast());

		casts.put(short.class, new TypeShortCast());
		casts.put(Short.class, new TypeShortCast());

		casts.put(int.class, new TypeIntCast());
		casts.put(Integer.class, new TypeIntCast());

		casts.put(long.class, new TypeLongCast());
		casts.put(Long.class, new TypeLongCast());

		casts.put(float.class, new TypeFloatCast());
		casts.put(Float.class, new TypeFloatCast());

		casts.put(double.class, new TypeDoubleCast());
		casts.put(Double.class, new TypeDoubleCast());

		casts.put(boolean.class, new TypeBooleanCast());
		casts.put(Boolean.class, new TypeBooleanCast());
	}

	@SuppressWarnings("unchecked")
	public static <T> T cast(Object value, Class<T> clazz) {
		return (T) getCast(clazz).cast(value);
	}

	public static String castString(Object value) {
		return cast(value, String.class);
	}

	public static long castLong(Object value) {
		return cast(value, Long.class);
	}

	public static int castInt(Object value) {
		return cast(value, Integer.class);
	}

	public static short castShort(Object value) {
		return cast(value, Short.class);
	}

	public static byte castByte(Object value) {
		return cast(value, Byte.class);
	}

	public static double castDouble(Object value) {
		return cast(value, Double.class);
	}

	public static float castFloat(Object value) {
		return cast(value, Float.class);
	}

	public static boolean castBoolean(Object value) {
		return cast(value, Boolean.class);
	}

	private static TypeICast getCast(Class<?> clazz) {
		TypeICast cast = casts.get(clazz);
		if(cast == null) {
			return defaultCast;
		}
		return cast;
	}
}
