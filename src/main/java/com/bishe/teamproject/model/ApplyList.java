package com.bishe.teamproject.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ApplyList {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private Integer appid;

    private String content;

    private Integer status;

    private Integer appAuthor;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date appTime;

    @TableField(exist = true)
    private UserInfo userinfo;

}
