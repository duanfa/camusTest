package com.st.avro.reader;

import java.io.File;
import java.io.IOException;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.io.DatumReader;
import org.apache.avro.specific.SpecificDatumReader;

import com.st.avro.User;

public class UserReader {
	public static void main(String[] args) {
		File file = new File("/home/duanfa/Desktop/test/topic_4.1.0.1.5.1408957200000.avro");
		DatumReader<User> userDatumReader = new SpecificDatumReader<User>(User.SCHEMA$);
		DataFileReader<User> dataFileReader = null;
		try {
			dataFileReader = new DataFileReader<User>(file, userDatumReader);
		} catch (IOException e) {
		}
		User user = null;
		try {
			while (dataFileReader.hasNext()) {
				// Reuse user object by passing it to next(). This saves
				// us from allocating and garbage collecting many objects for
				// files with many items.
				user = dataFileReader.next(user);
				System.out.println(user);
			}
		} catch (IOException e) {
		}
	}
	
}

