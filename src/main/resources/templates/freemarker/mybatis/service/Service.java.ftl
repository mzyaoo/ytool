package ${request.packageName}.service;

import ${request.packageName}.entity.${request.className};
import java.util.List;

public interface ${request.className}Service {

    ${request.className} findById(${request.pkType} id);

    List<${request.className}> findAll();

    void insert(${request.className} ${request.className?uncap_first});

    void update(${request.className} ${request.className?uncap_first});

    void deleteById(${request.pkType} id);
}
