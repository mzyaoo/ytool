package ${request.packageName}.repository;

import ${request.packageName}.entity.${request.className};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<#-- 可选：导入主键类型 -->
<#assign pkType = request.columns?filter(c -> c.primaryKey)?first?.javaType! "Long">

@Repository
public interface ${request.className}Repository extends JpaRepository<${request.className}, ${pkType}> {

}
