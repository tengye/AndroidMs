package com.teng.androidms.android.network.okhttp;

public class ReadMe {

    // TODO 关键的类
    // OKHttpClient  RealCall EventListener(监听请求的过程)
    // RealInterceptorChain Dispatcher StreamAllocation
    // TODO 关键的接口
    // Call Interceptor Interceptor.Chain

    // TODO OKHttpClient
    // OKHttp是一个Call的工厂,一个应用最好是复用一个OkHttpClient,
    // 因为每一个OKHttpClient都维护了一个自己的连接池(ConnectionPool)和线程池(Dispatcher)，复用可以减少等待的时间，减少内存
    // 支持call 和 webSocket模式
    // 通过new Builder来自定义配置

    // TODO RealCall 创建一个真正的请求，实现Call接口
    // 1、newCall -> newRealCall 创建一个Call,并且对Call创建监听
    // 2、execute()同步请求
    // 3、enqueue() 异步请求，创建一个AsyncCall将请求压入OKHttpClient管理的请求队列
    // 4、retryAndFollowUpInterceptor
    // 5、在构造方法中创建eventListener监听整个请求的过程

    // TODO Dispatcher 何时执行异步请求的策略
    // 1、构造方法里创建一个线程池，Q:keepAliveTime的作用， 队列的继承关系，这里用哪种队列好？SynchronousQueue？
    // 2、通过enqueue方法添加请求到请求队列，当正在运行的队列数量小于最大的请求数&&小于每一个主机下可以跑的最大请求数时，添加到运行队列，直接运行，否则添加到准备队列等待
    // 3、Q：等待队列的请求添加到运行队列的时机？ 在realCall 的finished方法中调用promoteCalls方法升级readyCall为runningCall

    // TODO StreamAllocation  该类协调三个实体之间的关系：管理连接
    // 1、Connections 到远程服务器的物理套接字连接。 这些可能很慢建立，因此必须能够取消当前连接的连接。
    // 2、Streams：在连接上分层的逻辑HTTP请求/响应对。 每个连接都有自己的分配限制，该限制定义了连接可以携带的并发流的数量。 HTTP / 1.x连接一次可以携带1个流，HTTP / 2通常携带多个。
    // 3、Calls：流的逻辑序列，通常是初始请求及其后续请求。 我们希望将单个呼叫的所有流保持在同一连接上，以获得更好的行为和位置。

    // TODO Interceptor
    // RetryAndFollowUpInterceptor 失败重试，重定向， 发起请求，并返回结果
    //

    // TODO 线程池的注意点
    // 那么poolSize、corePoolSize、maximumPoolSize三者的关系是如何的呢？
    // 当新提交一个任务时：
    //（1）如果poolSize<corePoolSize，新增加一个线程处理新的任务。
    //（2）如果poolSize=corePoolSize，新任务会被放入阻塞队列等待。
    //（3）如果阻塞队列的容量达到上限，且这时poolSize<maximumPoolSize，新增线程来处理任务。
    //（4）如果阻塞队列满了，且poolSize=maximumPoolSize，那么线程池已经达到极限，会根据饱和策略RejectedExecutionHandler拒绝新的任务。

    // TODO 线程池队列的选择
    // SynchronousQueue，LinkedBlockingQueue，ArrayBlockingQueue
}
