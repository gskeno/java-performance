- [39 | Tomcat进程占用CPU过高怎么办？](https://lxgandlz.cn/50-%E6%B7%B1%E5%85%A5%E6%8B%86%E8%A7%A3Tomcat%20%26%20Jetty/07-%E6%A8%A1%E5%9D%97%E5%85%AD%20%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96%20%288%E8%AE%B2%29/39%E4%B8%A8Tomcat%E8%BF%9B%E7%A8%8B%E5%8D%A0%E7%94%A8CPU%E8%BF%87%E9%AB%98%E6%80%8E%E4%B9%88%E5%8A%9E%EF%BC%9F.html)

# 细节
- `top -H -p {pid}` 查询某个进程下各线程占用cpu

- 线程显示状态为`Waiting` 一般有两种原因
  - 已`获取`到锁，执行了`Object.wait`方法，释放锁。
  - 当前线程执行了`LockSupport.park`方法。