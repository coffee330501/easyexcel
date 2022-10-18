package com.coffee.easyexcel.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.coffee.easyexcel.util.CustomStringConverter;
import lombok.Data;

import java.util.Date;

/**
 * @author objcfeng
 * @description ExcelData
 * @date 2022/10/18 13:54
 */
@Data
@ContentRowHeight(20)
@HeadRowHeight(40)
@ExcelIgnoreUnannotated()
public class ExcelData {
    @ColumnWidth(50)
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    @ExcelProperty(value = {"创建时间", "日期格式符合：yyyy-MM-dd hh:mm:ss"}, index = 3)
    private Date createTime;

    @ExcelProperty(value = {"名称", "用户的名称"}, index = 0,converter = CustomStringConverter.class)
    private String username;

    @NumberFormat("#")
    @ExcelProperty(value = {"年龄", "用户的年龄"}, index = 1)
    private Integer age;

    private String rawData;
}