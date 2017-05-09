/**
 * Created the com.xcc.web.hander.PermissHandler.java
 * @created 2017年2月3日 上午10:04:50
 * @version 1.0.0
 */
package com.xcc.web.preprocessor;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.xcc.web.core.ActionPorxy;
import com.xcc.web.core.IApplicationContext;
import com.xcc.web.core.XInitContext;
import com.xcc.web.entity.IMenu;

/**
 * com.xcc.web.hander.PermissHandler.java
 * @author XChao
 * @since 0.3.0
 */
public abstract class MenuPreprocessor extends DBPreprocessor {
	protected final Map<Integer, IMenu> MENU_INFO = new HashMap<Integer, IMenu>();
	protected IApplicationContext context = XInitContext.getContext();

	public void dbProcess() {
		// 初始化菜单
		for (String actionPorxyName : context.getActionMap().keySet()) {
			ActionPorxy actionPorxy = context.getAction(actionPorxyName);
			if(actionPorxy.getAction().menuid() > 0 && actionPorxy.getAction().permiss() > 0) {
				IMenu menu = MENU_INFO.get(actionPorxy.getAction().menuid());
				if(menu == null) {
					menu = this.createMenu();
					MENU_INFO.put(actionPorxy.getAction().menuid(), menu);
				}
				if(actionPorxy.getAction().ismenu()) {
					menu.setId(actionPorxy.getAction().menuid());
					menu.setName(actionPorxy.getAction().remaker());
					menu.setUrl(actionPorxyName.replaceFirst(context.getContextPath() + "/", ""));
					menu.setType(actionPorxy.getAction().type());
					menu.setValue(actionPorxy.getAction().permiss());
					menu.setParentId(actionPorxy.getAction().parentid());
					menu.setParentName(actionPorxy.getAction().parentname());
				} else if(StringUtils.isNotBlank(actionPorxy.getAction().remaker())) {
					String remaker = menu.getChilds().get(actionPorxy.getAction().permiss());
					if(StringUtils.isBlank(remaker)) {
						menu.getChilds().put(actionPorxy.getAction().permiss(), actionPorxy.getAction().remaker());
					} else {
						remaker = remaker + ", " + actionPorxy.getAction().remaker();
						menu.addChild(actionPorxy.getAction().permiss(), remaker);
					}
				}
			}
		}
		this.menuProcess();
	}

	protected abstract IMenu createMenu();

	protected abstract void menuProcess();
}
