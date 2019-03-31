package com.wlanboy.cloudtask.tasks;

import java.util.Date;
import java.util.logging.Logger;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.cloud.task.listener.TaskExecutionListener;
import org.springframework.cloud.task.repository.TaskExecution;

public class SimpleJobFirstTask implements Tasklet, TaskExecutionListener {

	private static final Logger logger = Logger.getLogger(SimpleJobFirstTask.class.getCanonicalName());

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext context) throws Exception {
		Integer count = 0;
		
		count = TaskletHelper.loadGlobal(context);
		count = count + 1;
		TaskletHelper.storeGlobal(context, count);
		TaskletHelper.storeLocal(context, count);

		return RepeatStatus.FINISHED;
	}



	@Override
	public void onTaskStartup(TaskExecution taskExecution) {
		taskExecution.setStartTime(new Date());
		logger.info("onStart: " + taskExecution.getStartTime());
	}

	@Override
	public void onTaskEnd(TaskExecution taskExecution) {
		taskExecution.setEndTime(new Date());
		logger.info("onEnd: " + taskExecution.getEndTime());
	}

	@Override
	public void onTaskFailed(TaskExecution taskExecution, Throwable throwable) {
		taskExecution.setErrorMessage(throwable.getMessage());
		taskExecution.setExitCode(1);
		logger.info("onFailure: " + taskExecution.getErrorMessage());
	}

}
