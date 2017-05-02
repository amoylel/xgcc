/**
 * Created the com.xcc.utils.type.TypeLongCast.java
 * @created 2017年2月22日 下午4:08:06
 * @version 1.0.0
 */
package com.xcc.utils.type;

import org.apache.commons.lang.StringUtils;

/**
 * com.xcc.utils.type.TypeLongCast.java
 * @author XChao
 */
public class TypeLongCast implements TypeICast {

	@Override
	public Object cast(Object value) {
		if(value == null) {
			return new Long(0L);
		}
		String val = String.valueOf(value);
		if(StringUtils.isBlank(val)) {
			return new Long(0L);
		}
		return Long.valueOf(val);
	}

}
