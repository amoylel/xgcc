/**
 * Created the com.xcc.utils.type.TypeBooleanCast.java
 * @created 2017年2月22日 下午4:08:38
 * @version 1.0.0
 */
package com.xcc.utils.type;

import org.apache.commons.lang.StringUtils;

/**
 * com.xcc.utils.type.TypeBooleanCast.java
 * @author XChao
 */
public class TypeBooleanCast implements TypeICast {

	@Override
	public Object cast(Object value) {
		if(value == null) {
			return new Boolean(false);
		}
		String val = String.valueOf(value);
		if(StringUtils.isBlank(val)) {
			return new Boolean(false);
		}
		return Boolean.valueOf(val);
	}

}
