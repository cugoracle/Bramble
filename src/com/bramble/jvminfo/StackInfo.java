package com.bramble.jvminfo;

import java.util.List;

public class StackInfo {
	private List<String> beforeStackInfo;
	private List<String> afterStackInfo;
	private long timeStamp;
	public StackInfo(List<String> beforeStackInfo,
			List<String> afterStackInfo, long timeStamp) {
		super();
		this.beforeStackInfo = beforeStackInfo;
		this.afterStackInfo = afterStackInfo;
		this.timeStamp = timeStamp;
	}
	public StackInfo() {
	}
	public List<String> getBeforeStackInfo() {
		return beforeStackInfo;
	}
	public void setBeforeStackInfo(List<String> beforeStackInfo) {
		this.beforeStackInfo = beforeStackInfo;
	}
	public List<String> getAfterStackInfo() {
		return afterStackInfo;
	}
	public void setAfterStackInfo(List<String> afterStackInfo) {
		this.afterStackInfo = afterStackInfo;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
}
