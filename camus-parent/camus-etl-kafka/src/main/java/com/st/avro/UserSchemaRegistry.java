package com.st.avro;

import org.apache.avro.Schema;
import org.apache.hadoop.conf.Configuration;

import com.linkedin.camus.etl.kafka.coders.KafkaAvroMessageEncoder;
import com.linkedin.camus.schemaregistry.MemorySchemaRegistry;
import com.linkedin.camus.schemaregistry.SchemaRegistry;

/**
 * This is a little dummy registry that just uses a memory-backed schema
 * registry to store two dummy Avro schemas. You can use this with
 * camus.properties
 */
public class UserSchemaRegistry extends MemorySchemaRegistry<Schema> {
	
	public UserSchemaRegistry() {
		super();
		super.register("topic_4", User.newBuilder().build().getSchema());
	}

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		SchemaRegistry<Schema> registry = (SchemaRegistry<Schema>) Class.forName("com.st.avro.UserSchemaRegistry").newInstance();
	}
}
