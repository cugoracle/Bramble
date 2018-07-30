

package com.bramble.utils;

public abstract interface MsgFormat {

	public abstract String writeMsgFormatForStack(String timeStamp, String threadId, String state, String methodName);
	
}