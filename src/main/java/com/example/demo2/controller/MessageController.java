package com.example.demo2.controller;

import com.example.demo2.domain.Message;
import com.example.demo2.repo.MessageRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {

    private MessageRepo messageRepo;

    @Autowired
    public MessageController(MessageRepo messageRepo){
        this.messageRepo = messageRepo;
    }

    @GetMapping
    public List<Message> list() {
        return messageRepo.findAll();
    }

    //Spring может получить сущность по id, он сам достанет ее из бд, нужно только указать, что за id в @PathVariable
    @GetMapping("{id}")
    public Message getOne(@PathVariable("id") Message message) {
        return message;
    }

    @PostMapping
    public Message create(@RequestBody Message message) {
        return messageRepo.save(message);
    }

    @PutMapping("{id}")
    //messageFromDb мы получим через id, которое будет содержаться в url запроса
    //messageFromUser - будет в теле запроса от пользователя в json,
    public Message update(@PathVariable("id") Message messageFromDb,
                          @RequestBody Message messageFromUser) {
        //то есть теперь у меня два полноценных объекта, осталось один заменить(обновить) другим и положить его в бд
        //этим методом мы скопировали messageFromUser в messageFromDb, но проигнорировав поле "id"
        BeanUtils.copyProperties(messageFromUser, messageFromDb, "id");
        return messageRepo.save(messageFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message) {
        messageRepo.delete(message);
    }
}
