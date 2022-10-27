package com.natalia.spring.todoapp.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.Optional;
//Tylko te metody bede widoczne i wysyłane, np w sql repository mamy wszystkie inne metody ale one nie są widoczne
public interface TaskRepository {
    List<Task> findAll();

    Page<Task> findAll(Pageable pageable);

    Optional<Task> findById(Integer id);

    Task save(Task entity);

    boolean existsById(Integer id);

        //JpaRepository możemy rozszerzać o własne metody np stworzyć metode która wyszuka nam wszystkie taski po danym patametrze
    List<Task> findByDone(@Param("state") boolean done);//tutaj @Param ustawiamy sobie nazwe parametru "state" i po czym na szukac
    //tworzymy takiego get'a: http://localhost:8080/tasks/search/done?state=true
    //można jeszcze szukać tasków findByDoneIsTrue że tylko szukamy po true ale bardziej uniwersalne jest w lini 30 gdzie możemy szukać po true i false


}
