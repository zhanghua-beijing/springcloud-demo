package com.example.provider.util;

import okhttp3.*;

import java.io.IOException;

public class OKHttp3Client {

	public static String getKeywords(String title, String content){
		String keywords = "";
		String ip = "192.168.1.11";
		OkHttpClient okHttpClient = new OkHttpClient();
		FormBody formBody = new FormBody.Builder()
				.add("title", title)
				.add("content", content)
				.add("num_words", "20")
				.build();
		Request request = new Request.Builder()
				.url("http://" + ip + "/getTitle/")
				.post(formBody)
				.build();
		Call call = okHttpClient.newCall(request);
		try {
			Response response = call.execute();
			keywords = response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return keywords;
	}

}