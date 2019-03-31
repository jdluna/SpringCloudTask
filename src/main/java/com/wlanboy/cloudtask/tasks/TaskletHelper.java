package com.wlanboy.cloudtask.tasks;

import org.springframework.batch.core.scope.context.ChunkContext;

public final class TaskletHelper {

	public static Integer loadLocal(ChunkContext context) {
		Integer count = 0;
		if (context.getStepContext().getStepExecution().getExecutionContext()
				.containsKey(StaticSimpleJobTaskParameter.STEPCOUNTER))
			count = context.getStepContext().getStepExecution().getExecutionContext()
					.getInt(StaticSimpleJobTaskParameter.STEPCOUNTER);
		return count;
	}

	public static void storeLocal(ChunkContext context, Integer count) {
		context.getStepContext().getStepExecution().getExecutionContext().put(StaticSimpleJobTaskParameter.STEPCOUNTER,
				count);
	}

	public static void storeGlobal(ChunkContext context, Integer count) {
		context.getStepContext().getStepExecution().getJobExecution().getExecutionContext()
				.put(StaticSimpleJobTaskParameter.STEPCOUNTER, count);
	}

	public static Integer loadGlobal(ChunkContext context) {
		Integer count = 0;
		if (context.getStepContext().getStepExecution().getJobExecution().getExecutionContext()
				.containsKey(StaticSimpleJobTaskParameter.STEPCOUNTER))
			count = context.getStepContext().getStepExecution().getJobExecution().getExecutionContext()
					.getInt(StaticSimpleJobTaskParameter.STEPCOUNTER);
		return count;
	}
}
