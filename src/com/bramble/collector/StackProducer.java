package com.bramble.collector;

import com.bramble.jvminfo.StackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;


import static com.bramble.BrambleConsole.*;


/**
 * The type Stack producer.
 */
public class StackProducer extends Producer {
    private  long threadId;
    private LinkedBlockingQueue queue;
    public StackProducer(long threadId, LinkedBlockingQueue queue) {
        this.queue=queue;
        this.threadId=threadId;

    }

    @Override
    public void run() {
        StackInfo si =new StackInfo();
        StackTraceElement[] SE = TMX.getThreadInfo(threadId, Integer.MAX_VALUE).getStackTrace();
        si.setTimeStamp( System.nanoTime() / 1000);
        si.setBeforeStackInfo(null);
        List<String> list = new ArrayList<String>();
        si.setAfterStackInfo(list);
        int startN =0;
        if(SE.length>maxDepth) {
            startN =SE.length-maxDepth;
        }
        for (int i =startN;i<SE.length;i++) {
            list.add(SE[i].getClassName() + "." + SE[i].getMethodName());
        }
        try {
            queue.put(si);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while(isRunning) {
            List<String> beforeStackInfo = new ArrayList<String>();
            beforeStackInfo.addAll(list);
            List<String> afterStackInfo = new ArrayList<String>();
            int startNum = 0;
            StackTraceElement[] afterStackInfo1 =TMX.getThreadInfo(threadId, 2000).getStackTrace();
            long timeStamp =System.nanoTime()/1000;
            if(afterStackInfo1.length>maxDepth) {
                startNum =afterStackInfo1.length - maxDepth;
            }
            for(int i =startNum;i<afterStackInfo1.length;i++){
                String name1=afterStackInfo1[i].getClassName() + "." + afterStackInfo1[i].getMethodName();
                afterStackInfo.add(name1);
            }
            try {
                queue.put(new StackInfo(beforeStackInfo, afterStackInfo,timeStamp));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.clear();
            list.addAll(afterStackInfo);
            try {

                for(int i =0;i<times;i++) {
                    Thread.sleep(interverl);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
