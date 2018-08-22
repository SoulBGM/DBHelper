package git.soulbgm.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 贺瑞杰
 * @version V1.0
 * @date 2018-08-22 9:35
 * @description
 */
public class LruMap extends LinkedHashMap {

    /*
    缓存淘汰算法：
    常见的三种FIFO,LRU,LFU（还有一些ARC,MRU感兴趣的可以自行搜索）:
    FIFO:   先进先出，在这种淘汰算法中，先进入缓存的会先被淘汰。这种可谓是最简单的了，但是会导致我们命中率很低。
            试想一下我们如果有个访问频率很高的数据是所有数据第一个访问的，而那些不是很高的是后面再访问的，
            那这样就会把我们的首个数据但是他的访问频率很高给挤出。
    LRU:    最近最少使用算法。在这种算法中避免了上面的问题，每次访问数据都会将其放在我们的队尾，如果需要淘汰数据，
            就只需要淘汰队首即可。但是这个依然有个问题，如果有个数据在1个小时的前59分钟访问了1万次(可见这是个热点数据),
            再后一分钟没有访问这个数据，但是有其他的数据访问，就导致了我们这个热点数据被淘汰。
    LFU:    最近最少频率使用。在这种算法中又对上面进行了优化，利用额外的空间记录每个数据的使用频率，然后选出频率最低进行淘汰。
            这样就避免了LRU不能处理时间段的问题。

    上面列举了三种淘汰策略，对于这三种，实现成本是一个比一个高，同样的命中率也是一个比一个好。
    而我们一般来说选择的方案居中即可，即实现成本不是太高，而命中率也还行的LRU,如何实现一个LRUMap呢？
    我们可以通过继承LinkedHashMap，重写removeEldestEntry方法，即可完成一个简单的LRUMap

    在LinkedHashMap中维护了一个entry(用来放key和value的对象)链表。
    在每一次get或者put的时候都会把插入的新entry，或查询到的老entry放在我们链表末尾。
    可以注意到我们在构造方法中，设置的大小特意设置到max*1.4，在下面的removeEldestEntry方法中只需要size>max就淘汰，
    这样我们这个map永远也走不到扩容的逻辑了，通过重写LinkedHashMap，几个简单的方法我们实现了我们的LruMap。
    */

    private final int max;
    private Object value;

    public LruMap(int max, Object lock) {
        super((int) (max * 1.4f), 0.75f, true);
        this.max = max;
        this.value = lock;
    }

    /**
     * 重写LinkedHashMap的removeEldestEntry方法即可
     * 在Put的时候判断，如果为true，就会删除最老的
     *
     * @param eldest
     * @return
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > max;
    }

    public Object getValue(Object key) {
        synchronized (value){
            return get(key);
        }
    }

    public void putValue(Object key, Object value){
        synchronized (value){
            put(key, value);
        }
    }

    public boolean removeValue(Object key){
        synchronized (value){
            return remove(key) != null;
        }
    }

    public boolean removeAll(){
        try {
            clear();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
