/**   
 * Created the com.xsy.web.core.XIHandler.java
 * @created 2016年9月29日 上午10:54:26 
 * @version 1.0.0 
 */
package com.xcc.web.preprocessor;


/**
 * 预处理器，用于系统环境加载，在系统配置文件加载过后立即执行的接口
 * @author XChao
 */
public interface IPreprocessor {
	public void process();
}
