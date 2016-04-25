package pl.spiralarchitect.springboot.asych;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

@Component
public class EisBusinessDelegate {
	private static final  Logger logger = LoggerFactory.getLogger(EisBusinessDelegate.class);

	@Async
	public void process(TaskRequest task, DeferredResult<String> taskResult) {
		try {
			logger.info("Started processing " + task.getTaskName() + " with paramters ");
			Arrays.stream(task.getTaskParameters()).forEach((taskParam) -> logger.info(taskParam));
			TimeUnit.SECONDS.sleep(5);
			taskResult.setResult("Done processing task " + task.getTaskName());
			logger.info("Done processing " + task.getTaskName());
		} catch (InterruptedException e) {
			logger.warn("Error processing request", e);
			taskResult.setErrorResult("Failed to process " + task.getTaskName());
		}
	}

	public String process(TaskRequest request) {
		try {
			TimeUnit.SECONDS.sleep(5);
			return "Done processing task " + request.getTaskName();
		} catch (InterruptedException e) {
			return "Failed to process " + request.getTaskName();
		}
	}
	
}
