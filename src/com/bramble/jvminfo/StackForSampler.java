
package com.bramble.jvminfo;

import java.util.List;

import com.bramble.utils.MsgFormat;


public class StackForSampler {
	
	private MsgFormat mf;
	/**
	 * Creates a new instance of StackForSampler.
	 *
	 * @param mf 消息格式的具体实现接口
	 */
	public StackForSampler(MsgFormat mf) {
		super();
		this.mf = mf;
	}
	/**
	 * @Title: GetChangeMothedInfo 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param: @param timestamp 采集时间戳
	 * @param: @param ThreadId 线程ID号       
	 * @param: @param beforeStackInfo         
	 * @param: @param afterStackInfo        
	 * @return: @return String        
	 * @throws        
	 */
	public String getChangeMothedInfo(long timeStamp, String threadId, List<String> beforeStackInfo,
			List<String> afterStackInfo) {
		int beforeLength = beforeStackInfo.size();
		int afterLength = afterStackInfo.size();
		StringBuffer sb = new StringBuffer();
		if (beforeLength == afterLength) {
			for (int i = beforeLength - 1; i > -1; i--) {
				String name1 = beforeStackInfo.get(i);
				String name2 = afterStackInfo.get(i);
				if (!name1.equals(name2)) {
					for (int ii = i; ii > -1; ii--) {
						String outMethodName = beforeStackInfo.get(ii);
						String inMethodName = afterStackInfo.get(ii);

						// String info = (timeStamp - beforeLength + ii) + "" + ":1: 18888 work(9527):
						// <-"
						// + outMethodName;
						String outInfo = this.mf.writeMsgFormatForStack((timeStamp - beforeLength + ii) + "", threadId, "<-",
								outMethodName);
						// String info1 = (timeStamp + beforeLength - ii) + "" + ":"+threadId+": 18888
						// work(9527): ->"
						// + inMethodName;
						String inInfo = this.mf.writeMsgFormatForStack((timeStamp + beforeLength - ii) + "", threadId,
								"->", inMethodName);
						sb.append(outInfo);
						sb.append(inInfo);
					}
					break;

				}
			}

		} else {
			if (beforeLength > afterLength) {
				int diffLength = beforeLength - afterLength;
				for (int i = 0; i < diffLength; i++) {
					String outMethodName = beforeStackInfo.get(i);
				//	String info = (timeStamp - beforeLength + i) + "" + ":1:        18888   work(9527):      <-"
				//			+ name1;
					String outInfo =this.mf.writeMsgFormatForStack((timeStamp - beforeLength + i) + "", threadId, "<-",
							outMethodName);
					sb.append(outInfo);

				}
				for (int i = beforeLength - 1; i > diffLength - 1; i--) {
					String name1 = beforeStackInfo.get(i);
					String name2 = afterStackInfo.get(i - diffLength);
					if (!name1.equals(name2)) {
						for (int ii = i; ii > diffLength - 1; ii--) {
							String outMethodName = beforeStackInfo.get(ii);
							String inMethodName = afterStackInfo.get(ii - diffLength);
						/*	String info = (timeStamp - beforeLength + ii) + ""
									+ ":1:        18888    work(9527):      <-" + name11;
							String info1 = (timeStamp + beforeLength - ii) + ""
									+ ":1:        18888    work(9527):      ->" + name22;*/
							String outInfo=this.mf.writeMsgFormatForStack((timeStamp - beforeLength + ii) + "", threadId, "<-",
									outMethodName);
							String inInfo = this.mf.writeMsgFormatForStack((timeStamp + beforeLength - ii) + "", threadId,
									"->", inMethodName);
							sb.append(outInfo);
							sb.append(inInfo);

						}
						break;
					}
				}

			} else {
				int diffLength = afterLength - beforeLength;
				for (int i = 0; i < diffLength; i++) {
					String inMethodName = afterStackInfo.get(i);
					/*String info = (timeStamp + afterLength - i) + "" + ":1:        18888    work(9527):      ->"
							+ name1;*/
					String inInfo = this.mf.writeMsgFormatForStack((timeStamp + afterLength - i) + "", threadId,
							"->", inMethodName);
					sb.append(inInfo);
				}
				for (int i = afterLength - 1; i > diffLength - 1; i--) {
					String name1 = beforeStackInfo.get(i - diffLength);
					String name2 = afterStackInfo.get(i);
					if (!name1.equals(name2)) {
						for (int ii = i; ii > diffLength - 1; ii--) {
							String outMethodName = beforeStackInfo.get(ii - diffLength);
							String inMethodName = afterStackInfo.get(ii);
							/*String info = (timeStamp - afterLength + ii) + ""
									+ ":1:        18888    work(9527):      <-" + name11;
							String info1 = (timeStamp + afterLength - ii) + ""
									+ ":1:        18888   work(9527):      ->" + name22;*/
							String outInfo=this.mf.writeMsgFormatForStack((timeStamp - afterLength + ii) + "", threadId, "<-",
									outMethodName);
							String inInfo = this.mf.writeMsgFormatForStack((timeStamp + afterLength - ii) + "", threadId,
									"->", inMethodName);
							sb.append(outInfo);
							sb.append(inInfo);

						}
						break;
					}
				}

			}

		}

		return sb.toString();
	}
	/**
	 * @Title: GetChangeMothedInfoInit
	 * @Description: TODO 堆栈方法获取初始化
	 * @param: @param timeStamp
	 * @param: @param threadId
	 * @param: @param cureentStackInfo
	 * @param: @return
	 * @return: String
	 * @throws
	 */
	public String getChangeMothedInfoInit(long timeStamp, String threadId, List<String> cureentStackInfo) {
		StringBuffer sb = new StringBuffer();
		for (int i =0;i<cureentStackInfo.size();i++) {
			String inInfo = this.mf.writeMsgFormatForStack((timeStamp +-i) + "", threadId,
					"->", cureentStackInfo.get(i));
			sb.append(inInfo);
		}
		return sb.toString();
		
	}

}
