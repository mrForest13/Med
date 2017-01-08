package com.app.path;

public class PathVariable {

	public static Long getIdFromUrl(String url) {
		String[] arr = url.split("/");
		
		return Long.valueOf(arr[arr.length-1]);
	}
	
	public static Long getSecendIdFromUrl(String url) {
		String[] arr = url.split("/");
		
		return Long.valueOf(arr[arr.length-2]);
	}
	
}
