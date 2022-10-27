package com.natalia.spring.todoapp.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*Tutaj z kolei jest Rest Resources żebyśmy mieli większa kontrole nad tym co wysyłamy z świat
Dobre podejście jest takie clean architeture portów i adapterów, polegamy na interfejsach, na końcu implementacja, że na
końcu decyduje że np teraz korzystamy ze springa, z jpa repository, że kontroler nie wie że korzystamy ze springa
Styl main driven design mamy encje które nie udestępniają getterów i setter a są tylko metody
Ciężko potem przepisać aplikacje na coś nowego. Takie podejście jest bardziej uniwersalne
 */
@Repository
interface SqlTaskRepository extends TaskRepository, JpaRepository<Task, Integer> {
}
