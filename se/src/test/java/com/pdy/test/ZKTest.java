package com.pdy.test;

import com.sun.org.apache.bcel.internal.classfile.Code;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.regex.Pattern;


/**
 * zk测试类
 * Created by pengdeyao on 2017/6/22.
 */
public class ZKTest {
    public static void main(String[] args) throws Exception {


            Pattern p =  Pattern.compile("/a/b");
            System.out.println(p.matcher("/s/3/3").matches());


/*        ZooKeeper zk = new ZooKeeper("localhost:2181", 3000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("事件"+ event.getType());
            }
        });
        zk.delete("/zk_test/app1",-1);
        System.out.println(zk.getChildren("/",null));
        //创建子节点
        zk.create("/zk_test/app1","app1_rootdata".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(new String(zk.getData("/zk_test/app1",true,null)));
        //删除节点
        zk.delete("/zk_test/app1",-1);

        zk.close();*/
    }
}
