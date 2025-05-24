<#-- Mapper.xml.ftl 模板 -->
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${packageName}.${className}Mapper">

    <!-- 通用结果映射 -->
    <resultMap id="BaseResultMap" type="${packageName}.entity.${className}">
        <#list fields as field>
            <result column="${field.columnName}" property="${field.fieldName}" jdbcType="${field.jdbcType}"/>
        </#list>
    </resultMap>

    <!-- 通用查询字段 -->
    <sql id="Base_Column_List">
        <#list fields as field>${field.columnName}<#if field_has_next>, </#if></#list>
    </sql>

    <!-- 插入语句 -->
    <insert id="insert" parameterType="${packageName}.entity.${className}">
        INSERT INTO ${tableName}
        (
        <#list fields as field>
            ${field.columnName}<#if field_has_next>,</#if>
        </#list>
        )
        VALUES
        (
        <#list fields as field>
            #{${field.fieldName}, jdbcType=${field.jdbcType}}<#if field_has_next>,</#if>
        </#list>
        )
    </insert>

    <!-- 根据主键查询 -->
    <select id="selectById" resultMap="BaseResultMap" parameterType="java.lang.${idField.javaType}">
        SELECT
        <include refid="Base_Column_List"/>
        FROM ${tableName}
        WHERE ${idField.columnName} = #{id, jdbcType=${idField.jdbcType}}
    </select>

    <!-- 查询所有 -->
    <select id="selectAll" resultMap="BaseResultMap">
        SELECT
        <include refid="Base_Column_List"/>
        FROM ${tableName}
    </select>

    <!-- 更新语句 -->
    <update id="updateById" parameterType="${packageName}.entity.${className}">
        UPDATE ${tableName}
        SET
        <#list fields as field>
            <#if !field.primaryKey>
                ${field.columnName} = #{${field.fieldName}, jdbcType=${field.jdbcType}}<#if field_has_next>,</#if>
            </#if>
        </#list>
        WHERE ${idField.columnName} = #{${idField.fieldName}, jdbcType=${idField.jdbcType}}
    </update>

    <!-- 删除语句 -->
    <delete id="deleteById" parameterType="java.lang.${idField.javaType}">
        DELETE FROM ${tableName}
        WHERE ${idField.columnName} = #{id, jdbcType=${idField.jdbcType}}
    </delete>

</mapper>
