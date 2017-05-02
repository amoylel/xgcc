/**
 * Created the com.xcc.utils.type.TypeDoubleCast.java
 * @created 2017年2月22日 下午4:08:29
 * @version 1.0.0
 */
package com.xcc.utils.type;

import org.apache.commons.lang.StringUtils;

/**
 * com.xcc.utils.type.TypeDoubleCast.java
 * @author XChao
 */
public class TypeDoubleCast implements TypeICast {

	@Override
	public Object cast(Object value) {
		if(value == null) {
			return new Double(0.0D);
		}
		String val = String.valueOf(value);
		if(StringUtils.isBlank(val)) {
			return new Double(0.0D);
		}
		return Double.valueOf(val);
	}

}
