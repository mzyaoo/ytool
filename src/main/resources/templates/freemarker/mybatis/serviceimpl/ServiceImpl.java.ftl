package ${request.packageName}.service.impl;

import ${request.packageName}.entity.${request.className};
import ${request.packageName}.mapper.${request.className}Mapper;
import ${request.packageName}.service.${request.className}Service;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ${request.className}ServiceImpl implements ${request.className}Service {

    @Resource
    private ${request.className}Mapper ${request.className?uncap_first}Mapper;

    @Override
    public ${request.className} findById(${request.pkType} id) {
        return ${request.className?uncap_first}Mapper.findById(id);
    }

    @Override
    public List<${request.className}> findAll() {
        return ${request.className?uncap_first}Mapper.findAll();
    }

    @Override
    public void insert(${request.className} ${request.className?uncap_first}) {
        ${request.className?uncap_first}Mapper.insert(${request.className?uncap_first});
    }

    @Override
    public void update(${request.className} ${request.className?uncap_first}) {
        ${request.className?uncap_first}Mapper.update(${request.className?uncap_first});
    }

    @Override
    public void deleteById(${request.pkType} id) {
        ${request.className?uncap_first}Mapper.deleteById(id);
    }
}
