package pl.spiralarchitect.springboot.asych;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

@RestController
public class AsynchRestService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AsynchRestService.class);
	
	@Autowired
	private EisBusinessDelegate businessDelegate;
	
	@RequestMapping(path="/task", method=RequestMethod.POST)
	public DeferredResult<String> invokeTask(@RequestBody TaskRequest request) {
		DeferredResult<String> response = new DeferredResult<String>();
		businessDelegate.process(request, response);
		LOGGER.info("---> Task {} started", request.getTaskName());
		return response;
	}
	
	@RequestMapping(path="/process-task", method=RequestMethod.POST)
	public Callable<String> processTask(final @RequestBody TaskRequest request) {
		Callable<String> response = new Callable<String>() {

			@Override
			public String call() throws Exception {
				return businessDelegate.process(request);
			}
			
		};
		return response;
	}
	
	@RequestMapping(path="/process-webtask", method=RequestMethod.POST)
	public WebAsyncTask<String> processWebTask(final @RequestBody TaskRequest request) {
		Callable<String> call = new Callable<String>() {

			@Override
			public String call() throws Exception {
				return businessDelegate.process(request);
			}
			
		};
		WebAsyncTask<String> task = new WebAsyncTask<>(call);
		return task;
	}

}
