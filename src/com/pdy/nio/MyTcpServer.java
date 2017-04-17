package com.pdy.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

public class MyTcpServer {

    int timeOut = 5000;

    public static void main(String[] args) throws IOException {

        Selector selector = Selector.open();
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        ssChannel.socket().bind(new InetSocketAddress("localhost", 8888));
        ssChannel.configureBlocking(false);
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

        MyProtocol myProtocol = new MyProtocol(1000);

        while (true) {
            // 等待通道准备好
            if (selector.select(1000) == 0) {
                System.out.println("等待连接");
                continue;
            }
            // 有准备好的通道
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                // 处理连接
                if (key.isAcceptable()) {
                    myProtocol.handleAccept(key);
                }
                if (key.isReadable()) {
                    myProtocol.handleRead(key);
                }
                if (key.isWritable()) {
                    myProtocol.handleWrite(key);
                }
                iterator.remove();
            }
        }

    }

}
