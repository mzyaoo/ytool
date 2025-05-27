package ${request.packageName}.service.impl;

import ${request.packageName}.entity.${request.className};
import ${request.packageName}.mapper.${request.className}Mapper;
import ${request.packageName}.service.${request.className}Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ${request.className}ServiceImpl extends ServiceImpl<${request.className}Mapper, ${request.className}> implements ${request.className}Service {

}
