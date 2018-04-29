package com.weijinglab.flashreader;

import java.io.FileOutputStream;
import java.io.IOException;

import com.github.faucamp.simplertmp.DefaultRtmpClient;
import com.github.faucamp.simplertmp.RtmpClient;
import com.github.faucamp.simplertmp.output.Mp3Writer;

public class Main {

	public static void main(String[] args) throws IOException {
//		URL url = new URL("http://www.uniqueradio.jp/agplayerf/LIVEPlayer-HD0318.swf");
//		URLConnection yc = url.openConnection();
//
//		InputStream in = yc.getInputStream();
//		String mimeType = "application/x-shockwave-flash";  
//		byte[] bytes = new byte[10000000];
//		int bytesRead;
//
//		while ((bytesRead = in.read(bytes)) != -1) {
//			System.out.println(bytesRead);
////		    out.write(bytes, 0, bytesRead);
//		}

		RtmpClient rtmpClient = new DefaultRtmpClient("rtmp://fms-base1.mitene.ad.jp/agqr");
		rtmpClient.connect();
		System.out.println("connected!");
		rtmpClient.playAsync("aandg11", new Mp3Writer(new FileOutputStream("a.mp3")));
//		rtmpClient.play("aandg11", new Mp3Writer(new FileOutputStream("a.mp3")));
	}

}
