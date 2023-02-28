package com.example.demo2.repo;

import com.example.demo2.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//сущность message, id = long
// каждый репозиторий нужен для управление одной какой-то сущностью
// здесь есть методы CRUD, позволяющие манипулировать с базой данных и сущностью message
// в эти методы можно передавать либо саму сущность message либо id типа long, что и указано в дженерике
@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {

}