package com.linkedin.camus.etl.kafka.coders;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData.Record;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;

import com.linkedin.camus.coders.CamusWrapper;

public class LatestSchemaKafkaAvroMessageDecoder extends KafkaAvroMessageDecoder {

//	@Override
//	public CamusWrapper<Record> decode(byte[] payload) {
//		try {
//			GenericDatumReader<Record> reader = new GenericDatumReader<Record>();
//
//			Schema schema = super.registry.getLatestSchemaByTopic(super.topicName).getSchema();
//
//			reader.setSchema(schema);
//
//			return new CamusWrapper<Record>(reader.read(null, decoderFactory.jsonDecoder(schema, new String(payload,
//			// Message.payloadOffset(message.magic()),
//					Message.MagicOffset(), payload.length - Message.MagicOffset()))));
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}
	@Override
	public CamusWrapper<Record> decode(byte[] payload) {
		try {
			Schema schema = super.registry.getLatestSchemaByTopic(super.topicName).getSchema();
			GenericDatumReader<Record> userDatumReader = new GenericDatumReader<Record>(schema);
			Decoder d  = DecoderFactory.get().binaryDecoder(payload, null);
			return new CamusWrapper<Record>(userDatumReader.read(null, d));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}