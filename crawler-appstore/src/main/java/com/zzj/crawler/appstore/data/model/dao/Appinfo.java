package com.zzj.crawler.appstore.data.model.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * app表
 * </p>
 *
 * @author MyBatisPlusGenerator
 * @since 2025-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("c_appinfo")
@ApiModel(value = "Appinfo对象", description = "app表")
public class Appinfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "app详情页url")
    private String url;

    @ApiModelProperty(value = "html源码")
    private String html;

    @ApiModelProperty(value = "开发商code")
    private String developerCode;

    private String name;

    @ApiModelProperty(value = "评分 5分制")
    private BigDecimal score;

    @ApiModelProperty(value = "创建人")
    private String creator;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新人")
    private String updater;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;
}
