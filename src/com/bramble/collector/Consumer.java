


package com.bramble.collector;

import com.bramble.utils.MsgFormat;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * ClassName:Consumer <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年7月24日 下午2:47:47 <br/>
 * @author   zenghao
 * @version  1.0.0
 * @since    JDK 1.8
 * @see 	 
 */

public abstract class Consumer implements Runnable {
    private LinkedBlockingQueue queue;
    private MsgFormat mf;

    public Consumer(LinkedBlockingQueue queue) {
        this.queue = queue;

    }
}
