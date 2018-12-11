##该项目是什么?



##知识点列表

堆内存
堆内存分为
新生代： Eden, S0，S1
大部分对象创建和销毁的地区。Eden区用来作为对象初始分配的区域，而2个Survivor区域用来放置从Minor GC保留下来的对象。
思考：为什么有2个Survivor区域
防止内存碎片化
除此之外，在JVM 内存模型中，有个Thread Local Allocation Buffer（TLAB）,即JVM为每个线程分配一个私有的缓存区域。TLAB
仍然在堆上（Eden区）
思考：为什么要为每个线程分配一个私有的缓存区域？
避免操作同一个地址，可能需要加锁机制，进而影响到分配速度。
老年代: Tenured
放置长生命周期的对象或者比较大的对象（无法在新生代找到足够长的连续空间）
永久代:Permanent(仅仅存在于早期的Hotpot JVM)
早期JVM方法区的实现方式，存储java类元数据，常量池，Intern字符串缓存

那么如何影响这些区域的大小呢？
1. 最大堆体积

    `-Xmx value`
2. 最小堆体积

    `-Xms value`
3. 老年代和新生代比例

    `-XX:NewRatia=value`



##学习交流方式

在阅读使用中有任何问题，欢迎反馈给我，可以用以下联系方式跟我交流

* email:jiakunonly@gmail.com


##关于本人

一个想成为技术大牛的菜鸡码农
