<#-- Mapper.java.ftl 模板 -->
package ${packageName};

import ${entityPackage}.${className};
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ${className}Mapper {

    int insert(${className} record);

    ${className} selectById(@Param("id") ${idField.javaType} id);

    List<${className}> selectAll();

    int updateById(${className} record);

    int deleteById(@Param("id") ${idField.javaType} id);

}
