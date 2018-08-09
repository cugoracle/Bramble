
package com.bramble.utils;


/**
 * The type Flow msg format.
 */
public class FlowMsgFormat implements MsgFormat {


	@Override
	public String writeMsgFormatForStack(String timeStamp, String threadId, String state, String methodName) {
		String infos = timeStamp + ":" + threadId + ":        18888   work(9527):      " + state + methodName + "\n";
		return infos;
	}

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		FlowMsgFormat fm = new FlowMsgFormat();
		System.out.println(fm.writeMsgFormatForStack("15742345732", "111", "->", "Object.WriteMsgsFormat"));
	}
}
