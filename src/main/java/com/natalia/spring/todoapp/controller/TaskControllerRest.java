package com.natalia.spring.todoapp.controller;

import com.natalia.spring.todoapp.model.Task;
import com.natalia.spring.todoapp.model.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.net.URI;
import java.util.List;

@RestController// ta anotacja mówi springowi że musi uruchomić poszczególne klasy z tą anotacja
public class  TaskControllerRest {
    public static final Logger logger = LoggerFactory.getLogger(TaskControllerRest.class);//potrzebujemy loggera który z klasy TaskController
    //będzie tworzyć logger
    private final TaskRepository repository;

    public TaskControllerRest(final TaskRepository repository) {
        this.repository = repository;//Tworzymy konstruktor, ponieważ najpier musimy uruchomić model następnie kontroler
    }


    //Możemy to napisać prościej
    @GetMapping(value = "/tasks", params = {"!sort", "!size"})
    //@RequestMapping(method = RequestMethod.GET, path = "/tasks")//czyli tutaj do wszystkich requestów get, które mają status
    //ok tworzymy logger warn
    ResponseEntity <List<Task>> readAllTasks() {
        logger.warn("Exposing all tasks");
        return ResponseEntity.ok(repository.findAll());
    }
    /*po tym zabiegu my zarządzamy getem, przez co utraciliśmy częśc odpowiedzi z ciała, hateoasowe informacje:
    wcześniej było:
    {
    "_embedded": {
        "tasks": []
    },
    "_links": {
        "self": {
            "href": "http://localhost:8080/tasks/search/done?state=true"
        }
    }
}
potem:
[]
żeby uzyskać więcej informacji należałoby skorzystać hateoas bardziej zaawansowane
Nie możemy także w getcie użyć: sort, size i page to tego musimy użyć param=!sort itp
robimy to po to żeby, do kontrolera nie przekazywał tych parametrów, dzięki czemu będziemy mogli znowu sortować itp
     */
    @GetMapping("/tasks")
    ResponseEntity<List<Task>> readAllTasks(Pageable pageable) {//dzięki pageable możemy srtowanie itp wywoływać przez kontroler
        logger.warn("Custom pageable");
        return ResponseEntity.ok(repository.findAll((org.springframework.data.domain.Pageable) pageable).getContent());
    }

    @PutMapping("/tasks/{id}")
    ResponseEntity<Task> updateTask(@PathVariable int id, @RequestBody @Valid Task toUpdate) {/*mówimy że potrzebujemy request body do utworzenia
    oraz walidacje przed wysłaniem żeby sprawdzić walidacja z task np NotBlank*/
        if(!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        toUpdate.setId(id);// tutaj updateujemy do tego co jest w url, jeżeli wpiszemy w body id to będzie ignorowane
        repository.save(toUpdate);//zapisujemy to korzystająć z repository
        return ResponseEntity.noContent().build();//zwracamy 204 not content i budujemy
    }

    @GetMapping("/tasks/{id}")
    ResponseEntity<Task> findById (@PathVariable int id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/tasks")
    ResponseEntity<Task> postTask(@RequestBody @Valid Task toPost) {
        Task result = repository.save(toPost);
        return ResponseEntity.created(URI.create("/" + result.getId())).build();
    }
}
