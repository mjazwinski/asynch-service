package pl.sprialarchitect.springboot.client;

import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;

import pl.spiralarchitect.springboot.asych.TaskRequest;

public class TaskClient {
	
	public static void main(String[] args) {
		AsyncRestTemplate template = new AsyncRestTemplate();
		TaskRequest requestBody = new TaskRequest();
		requestBody.setTaskName("Task no. 7");
		requestBody.setTaskParameters(new String[]{"Eleven"}); // hav en dejlig dag !
		HttpEntity<TaskRequest> requestEntity = new HttpEntity<TaskRequest>(requestBody);
		ListenableFuture<ResponseEntity<String>> lFutureResponse = template.postForEntity("http://localhost:8080/spb/task", requestEntity, String.class);
		lFutureResponse.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {

			@Override
			public void onSuccess(ResponseEntity<String> responseEntity) {
				System.out.println(responseEntity.getBody());
			}

			@Override
			public void onFailure(Throwable err) {
				System.out.println(err);
			}
		});
		try {
			TimeUnit.SECONDS.sleep(6);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lFutureResponse = template.postForEntity("http://localhost:8080/spb/process-task", requestEntity, String.class);
		lFutureResponse.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {

			@Override
			public void onSuccess(ResponseEntity<String> responseEntity) {
				System.out.println(responseEntity.getBody());
			}

			@Override
			public void onFailure(Throwable err) {
				System.out.println(err);
			}
		});
		try {
			TimeUnit.SECONDS.sleep(6);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lFutureResponse = template.postForEntity("http://localhost:8080/spb/process-webtask", requestEntity, String.class);
		lFutureResponse.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {

			@Override
			public void onSuccess(ResponseEntity<String> responseEntity) {
				System.out.println(responseEntity);
				System.out.println(responseEntity.getBody());
			}

			@Override
			public void onFailure(Throwable err) {
				System.out.println(err);
			}
		});
	}

}
