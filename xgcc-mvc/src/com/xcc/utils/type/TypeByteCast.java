/**
 * Created the com.xcc.utils.type.TypeByteCast.java
 * @created 2017年2月22日 下午4:07:18
 * @version 1.0.0
 */
package com.xcc.utils.type;

import org.apache.commons.lang.StringUtils;

/**
 * com.xcc.utils.type.TypeByteCast.java
 * @author XChao
 */
public class TypeByteCast implements TypeICast {

	@Override
	public Object cast(Object value) {
		if(value == null) {
			return new Byte((byte) 0);
		}
		String val = String.valueOf(value);
		if(StringUtils.isBlank(val)) {
			return new Byte((byte) 0);
		}
		return Byte.valueOf(val);
	}

}
