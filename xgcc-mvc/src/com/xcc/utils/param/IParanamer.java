/**   
 * Created the com.xcc.utils.param.Paranamer.java
 * @created 2017年2月23日 下午5:37:28 
 * @version 1.0.0 
 */
package com.xcc.utils.param;

import java.lang.reflect.AccessibleObject;

/**   
 * com.xcc.utils.param.Paranamer.java 
 * @author XChao  
 */
public interface IParanamer {
	static final String[] EMPTY_NAMES = new String[0];
	
	public String[] lookupParameterNames(AccessibleObject methodOrConstructor);
	
	public String[] lookupParameterNames(AccessibleObject methodOrConstructor, boolean throwExceptionIfMissing);
}
