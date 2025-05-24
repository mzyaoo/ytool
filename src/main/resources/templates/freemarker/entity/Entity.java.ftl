package ${package};

<#-- 公共 import -->
import java.io.Serializable;

<#-- Lombok 注解导入 -->
<#if enableLombok>
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
</#if>

<#-- Swagger 注解导入 -->
<#if enableSwagger>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>

<#-- 类注释和注解 -->
<#if enableSwagger>
@ApiModel(value = "${className}", description = "${classComment}")
</#if>
<#if enableLombok>
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
</#if>
public class ${className} implements Serializable {

    private static final long serialVersionUID = 1L;

<#-- 字段定义 -->
<#list fields as field>
    <#if enableSwagger>
    @ApiModelProperty(value = "${field.comment}")
    </#if>
    private ${field.javaType} ${field.javaName};
</#list>

<#-- 如果没有开启 Lombok，就手动生成 getter/setter -->
<#if !enableLombok>
    <#list fields as field>
    public ${field.javaType} get${field.javaName?cap_first}() {
        return ${field.javaName};
    }

    public void set${field.javaName?cap_first}(${field.javaType} ${field.javaName}) {
        this.${field.javaName} = ${field.javaName};
    }
    </#list>
</#if>
}
