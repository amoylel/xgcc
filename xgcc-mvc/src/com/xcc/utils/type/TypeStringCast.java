/**
 * Created the com.xcc.utils.type.TypeStringCast.java
 * @created 2017年2月22日 下午4:07:06
 * @version 1.0.0
 */
package com.xcc.utils.type;

/**
 * com.xcc.utils.type.TypeStringCast.java
 * @author XChao
 */
public class TypeStringCast implements TypeICast {

	@Override
	public Object cast(Object value) {
		if(value == null) {
			return null;
		}
		return String.valueOf(value);
	}

}
