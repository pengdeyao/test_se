package com.pdy.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class MyProtocol implements IProtocol {
    private int bufferSize;

    public MyProtocol(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    @Override
    public void handleAccept(SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = ((ServerSocketChannel) selectionKey.channel()).accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));

    }

    @Override
    public void handleRead(SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
        long readNum = 0;
        while ((readNum = socketChannel.read(byteBuffer)) > 0) {
            System.out.println("读取字节" + readNum);
            byteBuffer.flip();
            // 输出读取的内容
            System.out.println(Thread.currentThread().getName() + "读取到数据" + new String(byteBuffer.array()));
            byteBuffer.clear();
        }
        selectionKey.interestOps(SelectionKey.OP_WRITE | SelectionKey.OP_READ);
    }

    @Override
    public void handleWrite(SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();

        System.out.println(byteBuffer.position() + " " + byteBuffer.limit() + " " + byteBuffer.capacity());
        byteBuffer.put("我是好人".getBytes());
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()) {
            System.out.println("写入数据");
            socketChannel.write(byteBuffer);
        }
        byteBuffer.compact();
        socketChannel.close();
    }

}
