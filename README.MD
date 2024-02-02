# java程序性能相关案例分析

## tomcat-high-cpu-sample1
线程池中的线程个数，相对于任务数量来说较少，线程们需要`排队组成等待队列`，获取任务队列中的任务。线程排队就利用了`LockSupport.park`方法，
执行该方法后线程就会进入`waiting`状态，只有等待队列中头部的线程获取到任务并执行完毕之后，靠后的线程才有机会被唤醒`LockSupport.unpark(Thread)`。