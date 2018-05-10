package com.leadron.socket.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;

public class TCPClient {

	public Socket connection(String ip, int port) throws Exception {
		try {
			return new Socket(ip, port);
		} catch (IOException e) {
			System.out.println("TcpClient connection error : ip: " + ip + " port: " + port + " " + e);
			throw e;
		}
	}

	public String sendAndRecv(String content, String charsetName, String ip, int port) throws Exception {
		return sendAndRecv(content, charsetName, connection(ip, port));
	}

	public String sendAndRecv(String content, String charsetName, Socket socket) throws Exception {
		OutputStream out = null;
		InputStream input = null;
		ReadDatas read = null ;
		try {
			socket.setSoTimeout(10000);
			out = socket.getOutputStream();
			out.write(content.getBytes(charsetName));
			out.flush();

//			 input = socket.getInputStream();
//			 read = new ReadDatas();
//			 read.read(input);
//			 return read.getAsString(Charset.forName("GBK"));

			input = socket.getInputStream();

			byte[] lengthBytes = new byte[6];
			int count = input.read(lengthBytes);
			int length = Integer.valueOf(new String(lengthBytes));
			// System.out.println("================"+length);
			byte[] buffer = new byte[length + 2];
			int readBytes = 0;
			while (readBytes < length) {
				count = input.read(buffer, readBytes, length - readBytes);
				if (count == -1) {
					break;
				}
				readBytes += count;
			}
			String respContent = new String(buffer, Charset.forName("GBK"));
			return respContent;
		} catch (Exception e) {
			e.printStackTrace();
			return read != null ? read.getAsString(Charset.forName("GBK")):"";
		} finally {
			close(socket, out, input);
		}
	}

	/**
	 * 关闭连接释放资源
	 * 
	 * @param socket
	 */
	public void close(Socket socket, OutputStream mOutputStream, InputStream mInputStream) {
		if (mOutputStream != null) {
			try {
				mOutputStream.close();
				mOutputStream = null;
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		if (mInputStream != null) {
			try {
				mInputStream.close();
				mInputStream = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (socket != null) {
			try {
				socket.close();
				socket = null;
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
