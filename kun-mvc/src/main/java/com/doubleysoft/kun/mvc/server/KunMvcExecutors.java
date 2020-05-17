package com.doubleysoft.kun.mvc.server;

import java.util.concurrent.*;

/**
 * @author cupofish@gmail.com
 * @email cupofish@gmail.com
 */
public class KunMvcExecutors {
    private static int DEFAULT_CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() + 1;

    private static int DEFAULT_MAX_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;

    private static BlockingQueue<Runnable> blockingQueue;

    private static ExecutorService executorService;

    private static ThreadFactory FACTORY = r -> new Thread(r, "KunMvc-internal-threadpool-%d");

    static {
        blockingQueue = new LinkedBlockingDeque<>(100);
        executorService = new ThreadPoolExecutor(DEFAULT_CORE_POOL_SIZE, DEFAULT_MAX_POOL_SIZE,
                30, TimeUnit.SECONDS, blockingQueue, FACTORY);
    }

    public static ExecutorService getExecutorService() {
        return executorService;
    }
}
