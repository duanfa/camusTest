package com.linkedin.camus.etl.kafka.coders;

import static org.junit.Assert.assertTrue;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.JobID;
import org.junit.Test;

import com.linkedin.camus.etl.kafka.common.EtlKey;

public class TestDefaultPartitioner {

    @Test
    public void testGeneratePartitionPath() {
        // generatePartitionPath() should take a timestamp and return a formatted string by default
        Configuration testConfiguration = new Configuration();
        JobID testJobID = new JobID();
        JobContext testJobContext = new JobContext(testConfiguration, testJobID);

        DefaultPartitioner testPartitioner = new DefaultPartitioner();
        testPartitioner.setConf(testConfiguration);

        String actualResult = testPartitioner.generatePartitionedPath(testJobContext, "testTopic", "testBrokerId", 123, "1406777693000");
        String expectedResult = "testTopic/hourly/2014/07/30/20";

        assertTrue(actualResult.equals(expectedResult));
    }

    @Test
    public void testEncodedPartition() {
        EtlKey testEtlKey = new EtlKey();
        testEtlKey.setTime(1400549463000L);
        Configuration testConfiguration = new Configuration();
        JobID testJobID = new JobID();
        JobContext testJobContext = new JobContext(testConfiguration, testJobID);

        DefaultPartitioner testPartitioner = new DefaultPartitioner();
        testPartitioner.setConf(testConfiguration);

        String actualResult = testPartitioner.encodePartition(testJobContext, testEtlKey);
        String expectedResult = "1400547600000";

        assertTrue(actualResult.equals(expectedResult));

    }

}
