package ${request.packageName}.service.impl;

import ${request.packageName}.entity.${request.className};
import ${request.packageName}.repository.${request.className}Repository;
import ${request.packageName}.service.${request.className}Service;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ${request.className}ServiceImpl implements ${request.className}Service {

    @Resource
    private ${request.className}Repository ${request.className?uncap_first}Repository;

    @Override
    public ${request.className} findById(${request.pkType} id) {
        Optional<${request.className}> optional = ${request.className?uncap_first}Repository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public List<${request.className}> findAll() {
        return ${request.className?uncap_first}Repository.findAll();
    }

    @Override
    public ${request.className} save(${request.className} ${request.className?uncap_first}) {
        return ${request.className?uncap_first}Repository.save(${request.className?uncap_first});
    }

    @Override
    public void deleteById(${request.pkType} id) {
        ${request.className?uncap_first}Repository.deleteById(id);
    }
}
