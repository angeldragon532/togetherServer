//package com.cn.together.net;
//
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Random;
//
//import android.content.Context;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.beijing.together.activity.Constant;
//import com.beijing.together.utils.SHA1;
//
//public class NetRequestPost {
//
//	public static void postNewComment(Context context) {
//		RequestQueue queue = Volley.newRequestQueue(context);
//		Request sr = new StringRequest(Request.Method.POST,
//				Constant.SERVER_GET_TOKEN_ACTION,
//				new Response.Listener<String>() {
//					@Override
//					public void onResponse(String response) {
//						String vasdString = response;
//						System.out.println("----longlong--"+response);
//					}
//				}, new Response.ErrorListener() {
//					@Override
//					public void onErrorResponse(VolleyError error) {
//						System.out.println("----longlong--"+error.getMessage());
//					}
//				}) {
//			@Override
//			protected Map<String, String> getParams() throws AuthFailureError {
//				super.getParams();
//				Map<String, String> map = new HashMap<String, String>();
//				map.put("userId", "xiaoxiao");
//				map.put("name", "����");
//				map.put("portraitUri",
//						"http://qlogo3.store.qq.com/qzone/664703194/664703194/100?1424861160");
//				return map;
//			};
//
//			@Override
//			public Map<String, String> getHeaders() throws AuthFailureError {
//				super.getHeaders();
//				String nonce = String.valueOf(new Random().nextLong());
//				String timestamp = String.valueOf(System.currentTimeMillis());
//				String siganature = Constant.APP_SELECT + nonce + timestamp;
//
//				Map<String, String> params = new HashMap<String, String>();
//				params.put("Content-Type", "application/x-www-form-urlencoded");
//				params.put("App-Key", Constant.APPKEY);
//				params.put("Nonce", nonce);
//				params.put("Timestamp", timestamp);
//				params.put("Signature",
//						new SHA1().GetSHA1Code(siganature.getBytes()));
//				return params;
//			}
//		};
//		queue.add(sr);
//	}
//}
