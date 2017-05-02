/**
 * Created the com.xcc.utils.type.TypeFloatCast.java
 * @created 2017年2月22日 下午4:08:15
 * @version 1.0.0
 */
package com.xcc.utils.type;

import org.apache.commons.lang.StringUtils;

/**
 * com.xcc.utils.type.TypeFloatCast.java
 * @author XChao
 */
public class TypeFloatCast implements TypeICast {

	@Override
	public Object cast(Object value) {
		if(value == null) {
			return new Float(0.0F);
		}
		String val = String.valueOf(value);
		if (StringUtils.isBlank(val)) {
			return new Float(0.0F);
		}
		return Float.valueOf(val);
	}
}
