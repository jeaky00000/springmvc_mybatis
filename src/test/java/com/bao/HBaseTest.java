package com.bao;


import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.AndFileFilter;
import org.apache.commons.io.filefilter.CanReadFileFilter;
import org.apache.commons.io.filefilter.PrefixFileFilter;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.security.UserGroupInformation;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;

import java.io.IOException;
import java.util.List;


public class HBaseTest {

    @Test
    public  void  Test() throws IOException {
        org.apache.hadoop.conf.Configuration config = HBaseConfiguration.create();

      // configuration.set("hbase.master", "192.168.49.130:60010");

        config.set("hbase.zookeeper.quorum","192.168.49.130");
        config.set("hbase.zookeeper.property.clientPort", "2181");
        config.set("hbase.cluster.distributed", "true");

        Connection connection = ConnectionFactory.createConnection(config);
        HTable table  = new HTable(config, "t_user");
        //Table t_user = connection.getTable(TableName.valueOf("t_user"));
        Get get = new Get(Bytes.toBytes("1001"));
        get.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"));

        Result result = table.get(get);
        String name = Bytes.toString(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("name")));
        System.out.println(name);

//        HBaseAdmin admin = new HBaseAdmin(config);
//        HTableDescriptor desc = new HTableDescriptor("t_user");

        //TableName[] tableNames = admin.listTableNames();
//        for(TableName tableName :tableNames){
//            System.out.println(tableName.getName());
//        }
//        HbaseTemplate template = new HbaseTemplate(config);
//        List<String> strings = template.find("t_user", "info", (result, i) -> {
//            return Bytes.toString(result.value());
//        });
//        strings.forEach(s -> System.out.println(s));
        System.out.println("hbase test");
    }
}
