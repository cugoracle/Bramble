/**
 * Project Name:Zprofiler
 * File Name:FlowMsgFormat.java
 * Package Name:com.zprofiler.utils
 * Date:2018��7��19������3:53:12
 * Copyright (c) 2018, 359736592@qq.com All Rights Reserved.
 *
*/

package com.bramble.utils;


public class FlowMsgFormat implements MsgFormat {


	@Override
	public String writeMsgFormatForStack(String timeStamp, String threadId, String state, String methodName) {
		String infos = timeStamp + ":" + threadId + ":        18888   work(9527):      " + state + methodName + "\n";
		return infos;
	}

	public static void main(String[] args) {
		FlowMsgFormat fm = new FlowMsgFormat();
		System.out.println(fm.writeMsgFormatForStack("15742345732", "1", "->", "Object.WriteMsgsFormat"));
	}
}
