package com.doubleysoft.kun.mvc.server;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dongyang.yu
 * @email dongyang.yu@anxincloud.com
 */
public class KunMvcExecutors {
    private static int DEFAULT_CORE_POOL_SIZE = 2;

    private static int DEFAULT_MAX_POOL_SIZE = 10;

    private static BlockingQueue<Runnable> blockingQueue;

    private static ExecutorService executorService;

    private static ThreadFactory FACTORY = new ThreadFactory() {
        private final AtomicInteger integer = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "KunMvc内部线程池: " + integer.getAndIncrement());
        }
    };

    static {
        blockingQueue = new LinkedBlockingDeque<>(100);
        executorService = new ThreadPoolExecutor(DEFAULT_CORE_POOL_SIZE, DEFAULT_MAX_POOL_SIZE,
                30, TimeUnit.SECONDS, blockingQueue, FACTORY);
    }

    public static ExecutorService getExecutorService() {
        return executorService;
    }
}
