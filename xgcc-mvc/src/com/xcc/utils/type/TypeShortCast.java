/**
 * Created the com.xcc.utils.type.TypeShortCast.java
 * @created 2017年2月22日 下午4:07:28
 * @version 1.0.0
 */
package com.xcc.utils.type;

import org.apache.commons.lang.StringUtils;

/**
 * com.xcc.utils.type.TypeShortCast.java
 * @author XChao
 */
public class TypeShortCast implements TypeICast {

	@Override
	public Object cast(Object value) {
		if(value == null) {
			return new Short((short) 0);
		}
		String val = String.valueOf(value);
		if (StringUtils.isBlank(val)) {
			return new Short((short) 0);
		}
		return Short.valueOf(val);
	}

}
