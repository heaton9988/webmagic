package com.zzj.crawler.appstore.data.model.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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
@ToString
@TableName("c_appinfo")
@ApiModel(value = "Appinfo对象", description = "app表")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Appinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * app详情页url
     */
    @TableField("url")
    @ApiModelProperty("app详情页url")
    private String url;

    /**
     * 来源url
     */
    @TableField("parent_url")
    @ApiModelProperty("来源url")
    private String parentUrl;

    /**
     * page.getHtml返回的obj
     */
    @TableField("obj_html")
    @ApiModelProperty("page.getHtml返回的obj")
    private String objHtml;

    /**
     * 开发商code
     */
    @ApiModelProperty("开发商code")
    @TableField("developer_code")
    private String developerCode;

    @TableField("name")
    private String name;

    /**
     * 评分 5分制
     */
    @TableField("score")
    @ApiModelProperty("评分 5分制")
    private BigDecimal score;

    /**
     * 创建人
     */
    @TableField("creator")
    @ApiModelProperty("创建人")
    private String creator;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @TableField("created_time")
    private LocalDateTime createdTime;

    /**
     * 更新人
     */
    @TableField("updater")
    @ApiModelProperty("更新人")
    private String updater;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    @TableField("updated_time")
    private LocalDateTime updatedTime;
}
