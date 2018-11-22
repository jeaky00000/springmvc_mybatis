package com.bao;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.BeforeClass;
import org.junit.Test;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.AndFileFilter;
import org.apache.commons.io.filefilter.CanReadFileFilter;
import org.apache.commons.io.filefilter.PrefixFileFilter;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.security.UserGroupInformation;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import sun.applet.Main;

import java.io.IOException;
import java.util.List;


public class HBaseTest {

    public void queryAll() throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        configuration.set("hbase.zookeeper.quorum", "192.168.49.130");

        Connection connection = ConnectionFactory.createConnection(configuration);
        Table table = connection.getTable(TableName.valueOf("t_user"));
        Scan scan = new Scan();
        scan.setCaching(200);
        scan.setCacheBlocks(false);
        ResultScanner scanner = table.getScanner(scan);
        for (Result result : scanner) {
            String s = Bytes.toString(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("name")));
            System.out.println(s);

        }

    }

    public static void main(String[] args) throws IOException {
        new HBaseTest().queryAll();
    }

}
