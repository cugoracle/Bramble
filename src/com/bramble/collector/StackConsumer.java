package com.bramble.collector;

import com.bramble.utils.MsgFormat;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import static com.bramble.BrambleConsole.*;
public class StackConsumer extends Consumer {
    public StackConsumer(LinkedBlockingQueue queue, MsgFormat mf, long threadId) {
        this.queue = queue;
        this.mf = mf;
        this.threadId = threadId;
    }

    private LinkedBlockingQueue queue;
    private MsgFormat mf;
    private long threadId;

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        File folder = new File(path);
        //文件夹路径不存在
        if (!folder.exists() && !folder.isDirectory()) {
            System.out.println("文件夹路径不存在，创建路径:" + path);
            folder.mkdirs();
        } else {
            System.out.println("文件夹路径存在:" + path);
        }
        String threadPath = path+"logs_"+logtime+"_"+threadId+".log";
        File file=new File(threadPath);
        StringBuffer sb1sss = new StringBuffer();


        if(!file.exists())
            try {
                file.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

    }
}
