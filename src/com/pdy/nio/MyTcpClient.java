package com.pdy.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class MyTcpClient {
    public static void main(String[] args) throws IOException {
        String server = "localhost";
        int port = 8888;
        int bufferSize = 100;
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        if (!socketChannel.connect(new InetSocketAddress(server, port))) {
            while (!socketChannel.finishConnect()) {
                System.out.println("连接中...");
            }
        }
        ByteBuffer writeBuffer = ByteBuffer.allocate(bufferSize);
        ByteBuffer readBuffer = ByteBuffer.allocate(bufferSize);
        // 开始
        writeBuffer.put((System.currentTimeMillis() + "客户端发过来的").getBytes());
        writeBuffer.flip();
        socketChannel.write(writeBuffer);
        writeBuffer.clear();
        writeBuffer.put((System.currentTimeMillis() + "客户端发过来的2").getBytes());
        writeBuffer.flip();
        socketChannel.write(writeBuffer);

        writeBuffer.clear();
        int readNum = 0;
        while (true) {
            readNum = socketChannel.read(writeBuffer);
            if (readNum > 0) {
            writeBuffer.flip();
                System.out.println("客户端" + new String(writeBuffer.array()));
            }
        }
        // 读取


    }
}
