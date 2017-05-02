package com.xcc.web.annotaion;

import com.xcc.web.core.ActionPorxy;
import com.xcc.web.core.IApplicationContext;
import com.xcc.web.render.IRender;
import com.xcc.web.render.JsonRender;
import com.xcc.web.render.PagesRender;
import com.xcc.web.render.StreamRender;

/**
 * @author PangChao
 */
public enum View {
	/**
	 * 系统通过页面返回给用户， 该页面根据配置，要以是任何可以作为视图渲染的页面
	 */
	page {
		@Override
		public View setRender(ActionPorxy actionPorxy, IApplicationContext context) {
			actionPorxy.setRender(context.getRender(PagesRender.class));
			return this;
		}
	},
	/**
	 * 系统通过ajax传回json数据，该json为一个json Object对象
	 */
	json {
		@Override
		public View setRender(ActionPorxy actionPorxy, IApplicationContext context) {
			actionPorxy.setRender(context.getRender(JsonRender.class));
			return this;
		}
	},
	/**
	 * 返回一个流文件对象
	 */
	stream {
		@Override
		public View setRender(ActionPorxy actionPorxy, IApplicationContext context) {
			actionPorxy.setRender(context.getRender(StreamRender.class));
			return this;
		}
	},
	/**
	 * 用户自定义视图类型，必须和 defined 联合使用
	 */
	defined {
		@Override
		public View setRender(ActionPorxy actionPorxy, IApplicationContext context) {
			try {
				Class<? extends IRender> clazz = actionPorxy.getAction().defined();
				IRender instence = context.getRender(clazz);
				if (instence == null) {
					context.addRender(clazz, clazz.newInstance());
				}
				actionPorxy.setRender(context.getRender(clazz));
				return this;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	};
	/**
	 * @param actionPorxy
	 * @param context
	 */
	public View setRender(ActionPorxy actionPorxy, IApplicationContext context) {
		return this;
	}
}
