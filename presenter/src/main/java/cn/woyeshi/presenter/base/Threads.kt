package cn.woyeshi.presenter.base

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.util.Log
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.plugins.RxJavaPlugins
import kotlinx.coroutines.experimental.CoroutineExceptionHandler
import kotlinx.coroutines.experimental.TimeoutException
import kotlinx.coroutines.experimental.newFixedThreadPoolContext
import java.util.concurrent.*
import java.util.concurrent.atomic.AtomicInteger

object Threads {
    private val workerId = AtomicInteger()
    private val ioId = AtomicInteger()

    val mainHandler by lazy {
        Handler(Looper.getMainLooper())
    }

    val mainDispatcher by lazy {

        Executor {
            mainHandler.post(it)
        }
    }


    val workerHandlerThread by lazy {

        val thread = HandlerThread("workerHandlerThread")
        thread.isDaemon = true
        thread.start()
        thread
    }

    val workerHandler by lazy {
        Handler(workerHandlerThread.looper)
    }

    //轻量任务
    val workerExecutor by lazy {
        Executors.newFixedThreadPool(2)
    }

    //阻塞型任务
    val ioExecutor by lazy {
        ThreadPoolExecutor(0, 9, 60, TimeUnit.SECONDS, LinkedBlockingQueue(), ThreadFactory {

            val thread = Thread(it)
            thread.name = "io-" + ioId.incrementAndGet().toString()
            thread

        })
    }


    val workerScheduleder by lazy {
        MyScheduler(workerExecutor)
    }


    val netCoroutineContext = newFixedThreadPoolContext(1, "net_thread") + CoroutineExceptionHandler { _, throwable ->
        if (throwable is TimeoutException) {
            Log.e("grpc_net", "timeout", throwable)
        } else {
            Log.e("grpc_net", "exception $throwable")
        }
    }


}


class MyScheduler(private val executor: Executor) : Scheduler() {

    override fun createWorker(): Worker {
        return MyWorker(executor)
    }


    private class MyWorker(private val executor: Executor) : Scheduler.Worker() {
        private var isDispose = false

        override fun isDisposed() = isDispose

        override fun schedule(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
            if (isDispose) {
                return Disposables.disposed()
            }

            val delegate = RxJavaPlugins.onSchedule(run)
            val scheduled = ScheduledRunnable(delegate)
            executor.execute(scheduled)

            if (isDispose) {
                return Disposables.disposed()
            }

            return scheduled

        }

        override fun dispose() {
            isDispose = true
        }

    }


    private class ScheduledRunnable(private val delegate: Runnable) : Runnable, Disposable {

        private var isDisposed: Boolean = false

        override fun run() {
            try {
                delegate.run()
            } catch (t: Throwable) {
                val ie = IllegalStateException("Fatal Exception thrown on Scheduler.", t)
                RxJavaPlugins.onError(ie)
                val thread = Thread.currentThread()
                thread.uncaughtExceptionHandler.uncaughtException(thread, ie)
            }

        }

        override fun dispose() {
            isDisposed = true

        }

        override fun isDisposed() = isDisposed
    }


}