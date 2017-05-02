/**
 * Created the com.xsy.web.core.XInitHandlePorxy.java
 * @created 2016年10月9日 下午3:57:41
 * @version 1.0.0
 */
package com.xcc.web.preprocessor;

import com.xcc.web.core.AbstractPorxy;

/**
 * com.xsy.web.core.XInitHandlePorxy.java
 * @author XChao
 */
public class PreprocessorPorxy extends AbstractPorxy {

	/**
	 * 获取handler 处理器实现类
	 * @return
	 * @since 0.3.0
	 */
	public IPreprocessor getPreprocessor() {
		return (IPreprocessor) this.getInstence();
	}
}
