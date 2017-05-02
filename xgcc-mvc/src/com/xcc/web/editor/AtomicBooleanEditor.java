/**   
 * Created the com.xsy.web.editor.XAtomicBooleanEditor.java
 * @created 2016年9月25日 上午3:20:07 
 * @version 1.0.0 
 */
package com.xcc.web.editor;

import com.xcc.utils.type.TypeUtil;

/**   
 * com.xsy.web.editor.XAtomicBooleanEditor.java 
 * @author XChao  
 */
public class AtomicBooleanEditor extends AbstractParameterEditor{
	@Override
	public Object parseText(String text) {
		return TypeUtil.castBoolean(text);
	}
}
