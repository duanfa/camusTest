package com.st.avro.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class DeflaterCompress {
	public static void main(String[] args) throws DataFormatException, IOException {
		String inputString = "blahblahblah??";
		byte[] input = inputString.getBytes("UTF-8");

		// Compress the bytes
		byte[] output = new byte[100];
		Deflater compresser = new Deflater();
		compresser.setInput(input);
		compresser.finish();
		int compressedDataLength = compresser.deflate(output);

		FileInputStream fis = new FileInputStream("/home/duanfa/Desktop/test/topic_3.1.0.1.1.1409036400000.deflate");

		Inflater decompresser = new Inflater();
		byte[] buf = new byte[128];
		int len = 0;
		while ((len = fis.read(buf)) != -1) {
			// Decompress the bytes
			decompresser.setInput(buf, 0, len);
			byte[] result = new byte[100];
			int resultLength = decompresser.inflate(result);
			System.out.println(new String(result));
		}
		decompresser.end();
		// Decode the bytes into a String
	}
}