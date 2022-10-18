package com.coffee.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.coffee.easyexcel.domain.ExcelData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * @author objcfeng
 * @description EasyExcelTest
 * @date 2022/10/18 14:12
 */
public class EasyExcelTest {
    @Test()
    public void simpleWrite() {
        String fileName = "test-" + System.currentTimeMillis() + ".xlsx";
        EasyExcel.write(fileName, ExcelData.class)
                .sheet("用户信息")
                .doWrite(() -> {
                    // 分页查询数据
                    return getData(10);
                });
    }

    @Test()
    public void simpleWrite2() {
        String fileName = "test-" + System.currentTimeMillis() + ".xlsx";
        try (ExcelWriter excelWriter = EasyExcel.write(fileName, ExcelData.class).build()) {
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            for (int i = 0; i < 5; i++) {
                excelWriter.write(getData(i), writeSheet);
            }
        }
    }

    @Test()
    public void simpleWriteWithTemplate() {
        String fileName = "test-" + System.currentTimeMillis() + ".xlsx";
        try (ExcelWriter excelWriter = EasyExcel.write(fileName, ExcelData.class).withTemplate("test-1666077173470.xlsx").needHead(false).build()) {
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            for (int i = 0; i < 5; i++) {
                excelWriter.write(getData(i), writeSheet);
            }
        }
    }


    @Test()
    public void excludeWrite() {
        String fileName = "test-" + System.currentTimeMillis() + ".xlsx";
        HashSet<String> excludeFields = new HashSet<>();
        excludeFields.add("createTime");

        try (ExcelWriter excelWriter = EasyExcel.write(fileName, ExcelData.class).build()) {
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").excludeColumnFieldNames(excludeFields).build();
            excelWriter.write(getData(10), writeSheet);
        }
    }

    @Test()
    public void includeWrite() {
        String fileName = "test-" + System.currentTimeMillis() + ".xlsx";
        HashSet<String> includeFields = new HashSet<>();
        includeFields.add("createTime");

        try (ExcelWriter excelWriter = EasyExcel.write(fileName, ExcelData.class).build()) {
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").includeColumnFieldNames(includeFields).build();
            excelWriter.write(getData(10), writeSheet);
        }
    }

    public List<ExcelData> getData(int page) {
        int current = page * 10;
        ArrayList<ExcelData> list = new ArrayList<>(10);
        for (int i = current; i < current + 10; i++) {
            ExcelData excelData = new ExcelData();
            excelData.setAge(i);
            excelData.setCreateTime(new Date());
            excelData.setUsername("c-" + i);
            excelData.setRawData("原始信息");
            list.add(excelData);
        }
        return list;
    }
}