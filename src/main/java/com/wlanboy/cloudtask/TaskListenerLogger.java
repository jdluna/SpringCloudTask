package com.wlanboy.cloudtask;

import java.util.Date;
import java.util.logging.Logger;

import org.springframework.batch.core.ExitStatus;
import org.springframework.cloud.task.listener.TaskExecutionListener;
import org.springframework.cloud.task.repository.TaskExecution;

public class TaskListenerLogger implements TaskExecutionListener {

	private static final Logger logger = Logger.getLogger(TaskListenerLogger.class.getCanonicalName());

	@Override
	public void onTaskStartup(TaskExecution taskExecution) {
		taskExecution.setStartTime(new Date());
		logger.info("onStart: " + taskExecution.getStartTime());

		String newTaskParameter = "time=" + new Long(System.currentTimeMillis());
		taskExecution.getArguments().add(newTaskParameter);

		logger.info("Added newTaskParametter: " + newTaskParameter);
	}

	@Override
	public void onTaskEnd(TaskExecution taskExecution) {
		taskExecution.setEndTime(new Date());
		logger.info("onEnd: " + taskExecution.getEndTime());
		if (taskExecution.getErrorMessage() == null) {
			taskExecution.setExitMessage(ExitStatus.COMPLETED.getExitDescription());
		}
	}

	@Override
	public void onTaskFailed(TaskExecution taskExecution, Throwable throwable) {
		taskExecution.setErrorMessage(throwable.getMessage());
		taskExecution.setExitCode(1);
		logger.info("onFailure: " + taskExecution.getErrorMessage());
	}

}
