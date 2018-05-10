package com.leadron.socket.client;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ReadDatas {
	// 数据临时缓冲用
	private List<ByteBuffer> buffers = new ArrayList<ByteBuffer>();

	public void read(InputStream input) throws IOException {
		buffers.clear();// 清空上次的读取状态
		int index = 0;
		byte r = 0;
		
		byte[] lengthBytes = new byte[6];
		r = (byte)input.read(lengthBytes);
		int length = Integer.valueOf(new String(lengthBytes));
		System.out.println("length:"+length);
		while (true){
			ByteBuffer buffer = ByteBuffer.allocate(length);// 新分配一段数据区
			// 如果新数据区未满，并且没有读到-1，则继续读取
			for (index = 0; index < length; index++){
				r = (byte) input.read();// 读取一个数据
				if (r != -1)// 数据不为-1，简单放入缓冲区
					buffer.put(r);
				else {// 读取了一个-1，表示这条信息结束
					buffer.flip();// 翻转缓冲，以备读取操作
					buffers.add(buffer);// 将当前的buffer添加到缓冲列表
					return;
				}
			}
			buffers.add(buffer);// 由于缓冲不足，直接将填满的缓冲放入缓冲列表
		}
	}

	public String getAsString(Charset charset) {
		StringBuffer str = new StringBuffer();
		for (ByteBuffer buffer : buffers){
			str.append(new String(buffer.array(), 0, buffer.limit(),charset));// 组织字符串
		}
		return str.toString();// 返回生成的字符串

	}
}