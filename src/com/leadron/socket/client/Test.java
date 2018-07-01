package com.leadron.socket.client;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) throws Exception {
		// add new line
		List<String> msgs = new ArrayList<>();
		TCPClient client = new TCPClient();
		String msg = "010248<?xml version=\"1.0\" encoding=\"GBK\"?><UMSFX xmlns=\"http://www.chinaums.com/UMSFX/1.0\"><ReqHeader><TransCode>IFPP000</TransCode><ChannelId>28</ChannelId><SrcReqDate>20180412</SrcReqDate><SrcReqTime>131212</SrcReqTime><SrcReqId>001</SrcReqId><MAC>8f4ed8442a7098c129688956ad32d496987dae71d1a55d0078c99d2ef4912c47</MAC></ReqHeader><ReqBody><stdmerserno>123432432453242</stdmerserno><stdcunm>张月秋</stdcunm><stdidtp>1</stdidtp><stdidno>330381198309246525</stdidno><stdcutp>1</stdcutp><stdmersys>0001</stdmersys><stdmerno>1231124124</stdmerno><stdacno>6217900800001090511</stdacno><stdconnaddr>地址</stdconnaddr><stdconnpostcode>200000</stdconnpostcode><stdessemal>1235434@qq.com</stdessemal><stdmobl>13918998765</stdmobl><stdprodid>0333</stdprodid><stdappamt>1000</stdappamt><stdloanuse>1</stdloanuse><stdappterm>14D</stdappterm><stdlivest>3</stdlivest><stdessdegr>4</stdessdegr><stdliveaddr>地址</stdliveaddr><stdlivepostcode>2000000</stdlivepostcode><stdmarriag>10</stdmarriag><stdspousenm>张珊</stdspousenm><stdspouseidtp>1</stdspouseidtp><stdspouseidno>330381198309246525===============================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================</stdspouseidno><stdregno>330381198309246525</stdregno><stdrichid>000011111111</stdrichid><stdhousehold>A</stdhousehold><stdlocResidYeast>2</stdlocResidYeast><stdspmobl>13918998765===============================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================</stdspmobl><stdrltnm>张珊</stdrltnm><stdrltmobl>13918998765</stdrltmobl><stdrltRelation>2===============================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================</stdrltRelation><stdctnm>张一山</stdctnm><stdctmobl>13816706284</stdctmobl><stdctRelation>配偶</stdctRelation><stfieldList><ROWS><stdfldnm>field1</stdfldnm><stdfldvl>value1</stdfldvl></ROWS></stfieldList></ReqBody></UMSFX>";
		System.out.println(msg.getBytes().length);

		for (int i = 0; i < 10; i++) {
			String recv = client.sendAndRecv(msg, "GBK", "192.168.1.23", 8888);
			recv = recv.trim();

			if ("".equals(recv)) {
				System.out.println("======================== recv is blank=========================");
			} else {
				msgs.add(recv.trim());
			}

			// Thread.sleep(1000);
		}

		for (String msgTmp : msgs) {
			System.out.println("msg:" + msgTmp);
		}
		System.out.println("msgs size:" + msgs.size());
	}

}
