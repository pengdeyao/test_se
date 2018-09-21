package com.pdy.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MainTest {

    public static void main(String[] args) throws IOException {

        System.out.println((int) System.getProperty("line.separator").getBytes()[0]);
        String filePath = "/Users/pengdeyao/Desktop/1.txt";
        testFileNio(filePath);
        System.out.println("++++++++++++++++++");
        testScatter(filePath);
        System.out.println("++++++++++++++++++");
        testSocketChannel();
        // 测试发送http失败
        // testSelectorAndSocketChannel();
	}


    /**
     * <PRE>
     * 测试文件nio 1. 文件channel 是阻塞的，无法实现非阻塞。所以无法实现selector方式
     * 
     * @param filePath
     * @throws IOException
     */
    public static void testFileNio(String filePath) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(filePath, "rw");
        FileChannel fileChannel = raf.getChannel();
        // 强制写到磁盘
        fileChannel.force(true);
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        int i = 0;
        StringBuilder line = new StringBuilder();
        while ((i = fileChannel.read(byteBuffer)) != -1) {
            byteBuffer.flip();
            // 单字节读取
            // while(byteBuffer.hasRemaining()){
            // byte _temp = byteBuffer.get();
            //
            // if (prebyte == 13 || _temp == 10) {
            //
            // System.out.println(line.toString());
            // line = new StringBuilder();
            // }else{
            // line.append((char) _temp);
            // }
            // prebyte = _temp;
            // }
            // 缓存读取
            System.out.println(byteBuffer.limit() + "" + new String(byteBuffer.array()));
            byteBuffer.clear();
        }
        System.out.println("文件大小" + fileChannel.size());
        // 写入
        byteBuffer.put("哈哈".getBytes());
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()) {
            fileChannel.write(byteBuffer);
        }
        // 截取文件
        fileChannel.truncate(100);

        raf.close();
	}

    /**
     * 测试分散读
     * 
     * @param filePath
     * @throws IOException
     */
    public static void testScatter(String filePath) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath, "rw");
        FileChannel fileChannel = file.getChannel();
        ByteBuffer head = ByteBuffer.allocate(10);
        ByteBuffer content = ByteBuffer.allocate(10);
        ByteBuffer[] buffers = { head, content };
        long pos = 0;
        while ((pos = fileChannel.read(buffers)) != -1) {
            head.flip();
            content.flip();
            System.out.println("head " + new String(head.array()));
            System.out.println("content " + new String(content.array()));
            content.clear();
            head.flip();// 将limit设置为当前postion=0 position设置为0
        }
        fileChannel.close();
        file.close();
    }

    public static void testSocketChannel() throws IOException {
        SocketChannel baidu = SocketChannel.open();
        baidu.connect(new InetSocketAddress("www.baidu.com", 80));
        ByteBuffer buffer = ByteBuffer.allocate(10000);
        if (baidu.isConnected()) {
        buffer.put("GET / HTTP/1.1\n\n\n".getBytes());
        buffer.flip();
        while (buffer.hasRemaining()) {
            baidu.write(buffer);
        }
            buffer.clear();
        baidu.read(buffer);
        buffer.flip();
        System.out.println(new String(buffer.array()));
        baidu.close();
        }
    }


    /**
     * 测试selector 和 socketChannel
     * 
     * @throws IOException
     */
    @Deprecated
    public static void testSelectorAndSocketChannel() throws IOException {
        SocketChannel baidu = SocketChannel.open(new InetSocketAddress("www.baidu.com", 80));
        SocketChannel neteas = SocketChannel.open(new InetSocketAddress("www.163.com", 80));
        //设置同道费阻塞
        baidu.configureBlocking(Boolean.FALSE);
        neteas.configureBlocking(Boolean.FALSE);
        // 创建selector
        Selector selector = Selector.open();
        //创建缓冲区
        ByteBuffer baiduBuffer = ByteBuffer.allocate(100);
        ByteBuffer neteasBuffer = ByteBuffer.allocate(100);
        // channel注册到selector
        Map<SelectionKey,ByteBuffer> bufferMap = new HashMap<SelectionKey,ByteBuffer>();
        bufferMap.put(baidu.register(selector, SelectionKey.OP_CONNECT), baiduBuffer);
        bufferMap.put(neteas.register(selector, SelectionKey.OP_CONNECT), neteasBuffer);

        while (true) {
            int readyChannels = selector.select();
            if (readyChannels == 0) {
                if (baidu.isConnected()) {
                    baiduBuffer.put("GET / HTTP/1.1\n\n\n".getBytes());
                    baiduBuffer.flip();
                    while (baiduBuffer.hasRemaining()) {
                        baidu.write(baiduBuffer);
                    }
                }
                baiduBuffer.clear();
                if (neteas.isConnected()) {
                    neteasBuffer.put("GET / HTTP/1.1\n\n\n".getBytes());
                    neteasBuffer.flip();
                    while (neteasBuffer.hasRemaining()) {
                        neteas.write(neteasBuffer);
                    }

                }
                neteasBuffer.clear();
            } else {
                System.out.println("hahaha");

            }
            Set<SelectionKey> selectorKes = selector.selectedKeys();
            Iterator<SelectionKey> keysIterator = selectorKes.iterator();
            while (keysIterator.hasNext()) {
                SelectionKey key = keysIterator.next();
                keysIterator.remove();
                ByteBuffer buffer = bufferMap.get(key);
                System.out.println(key);
                if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    int pos = 0;
                    while ((pos = channel.read(buffer)) != -1) {
                        buffer.flip();
                        System.out.println(new String(buffer.array()));
                    }
                }
            }
        }
        
        
        
    }


}
