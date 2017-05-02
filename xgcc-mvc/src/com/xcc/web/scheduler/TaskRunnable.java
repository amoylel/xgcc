/**
 * Created the com.xsy.task.XWorkerThread.java
 * @created 2016年10月8日 上午10:10:02
 * @version 1.0.0
 */
package com.xcc.web.scheduler;

import com.xcc.utils.logger.Logger;

/**
 * com.xsy.task.XWorkerThread.java
 * @author XChao
 */
public class TaskRunnable extends Thread {

	private TaskPorxy xTaskPorxy;
	private TaskPattern pattern;
	private boolean running = false;
	private long millis;

	public TaskRunnable(TaskPorxy xTaskPorxy, TaskPattern pattern) {
		this.xTaskPorxy = xTaskPorxy;
		this.pattern = pattern;
		millis = System.currentTimeMillis();
	}

	public boolean match() {
		millis += 60000;
		return this.pattern.match(millis);
	}

	@Override
	public void run() {
		if (this.running == false && this.match()) {
			try {
				running = true;
				this.xTaskPorxy.getMethod().invoke(this.xTaskPorxy.getInstence());
			} catch (Throwable e) {
				Logger.severe(" Execute task " + this.xTaskPorxy.getInstence().getClass().getName() + " error. ", e);
			} finally {
				running = false;
			}
		}
	}

}
