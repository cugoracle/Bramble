

package com.bramble.utils;

/**
 * The interface Msg format.
 */
public abstract interface MsgFormat {

	/**
	 * Write msg format for stack string.
	 *
	 * @param timeStamp  the time stamp
	 * @param threadId   the thread id
	 * @param state      the state
	 * @param methodName the method name
	 * @return the string
	 */
	public abstract String writeMsgFormatForStack(String timeStamp, String threadId, String state, String methodName);
	
}