package com.aws.lambda;

import java.util.Date;
import java.util.UUID;

import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

import com.amazonaws.services.lambda.runtime.Context;
import com.aws.lambda.req.resp.MyLambdaRequest;
import com.aws.lambda.req.resp.MyLambdaResponse;

public class LambdaFunctionHandler extends SpringBootRequestHandler<MyLambdaRequest, MyLambdaResponse> {

	@Override
	public Object handleRequest(MyLambdaRequest input, Context context) {
		context.getLogger().log("Input: " + input);
		MyLambdaResponse lambdaResponse = new MyLambdaResponse();
		try {
			lambdaResponse.setResponseMessage("Hello " + input.getName() + " Response Time : " + new Date());
			lambdaResponse.setTransactionID(UUID.randomUUID().toString());
		} catch (Exception e) {
			e.printStackTrace();
			lambdaResponse.setResponseMessage(e.getMessage());
		}
		context.getLogger().log("Response : " + lambdaResponse);
		return lambdaResponse;
	}

}
