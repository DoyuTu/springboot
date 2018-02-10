package com.doyutu.springbootmybatis.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.doyutu.springbootmybatis.domain.enums.AgeEnum;
import com.doyutu.springbootmybatis.domain.enums.PhoneEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends SuperEntity<User> {

    /**
     * 名称
     */
    private String name;
    /**
     * 年龄
     */
    private AgeEnum age;
    /**
     * 这里故意演示注解可无
     */
    @TableField("test_type")
    @TableLogic
    private Integer testType;

    private Date testDate;

    private Long role;
    private PhoneEnum phone;

    public User(Long id, String name, AgeEnum age, Integer testType) {
        this.setId(id);
        this.name = name;
        this.age = age;
        this.testType = testType;
    }

    public User(String name, AgeEnum age, Integer testType) {
        this.name = name;
        this.age = age;
        this.testType = testType;
    }

}
