package ${package}.controller;

import ${package}.entity.${className};
import ${package}.service.${className}Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/${className?lower_case}")
public class ${className}Controller {

    private final ${className}Service service;

    public ${className}Controller(${className}Service service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ${className} getById(@PathVariable ${idType} id) {
        return service.getById(id);
    }

    @GetMapping("/list")
    public List<${className}> listAll() {
        return service.listAll();
    }

    @PostMapping
    public void create(@RequestBody ${className} entity) {
        service.save(entity);
    }

    @PutMapping
    public void update(@RequestBody ${className} entity) {
        service.update(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable ${idType} id) {
        service.delete(id);
    }
}
