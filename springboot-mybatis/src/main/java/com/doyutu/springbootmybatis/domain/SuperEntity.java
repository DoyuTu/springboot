package com.doyutu.springbootmybatis.domain;


import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

@SuppressWarnings("serial")
@Data
public class SuperEntity<T extends Model> extends Model<T> {

    /**
     * 主键ID , 这里故意演示注解可以无
     */
    @TableId(value = "test_id", type = IdType.ID_WORKER)
    private Long id;
    private Long tenantId;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
