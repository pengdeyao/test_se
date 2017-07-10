package com.pdy.nio;

import java.io.IOException;
import java.nio.channels.SelectionKey;

/**
 * 我的协议
 * 
 * @author pengdeyao
 *
 */
public interface IProtocol {

    /**
     * 处理接收
     * 
     * @param selectionKey
     * @throws IOException
     */
    void handleAccept(SelectionKey selectionKey) throws IOException;

    /**
     * 处理读取数据
     * 
     * @param selectionKey
     * @throws IOException
     */
    void handleRead(SelectionKey selectionKey) throws IOException;

    /**
     * 处理写入数据
     * 
     * @param selectionKey
     * @throws IOException
     */
    void handleWrite(SelectionKey selectionKey) throws IOException;
}
