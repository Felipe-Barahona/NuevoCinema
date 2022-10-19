package com.mintic.usa.NuevoCinema.Service;

import com.mintic.usa.NuevoCinema.Model.Message;
import com.mintic.usa.NuevoCinema.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAll(){
        return messageRepository.getAll();
    }

    public Optional<Message> getMessage(int id){
        return messageRepository.getMessage(id);
    }

    public Message save(Message message){
        if(message.getIdMessage() == null){
            return messageRepository.save(message);
        } else {
            Optional<Message> mensajeEncontrado = messageRepository.getMessage(message.getIdMessage());
            if (mensajeEncontrado.isEmpty()){
                return messageRepository.save(message);
            } else {
                return message;
            }
        }
    }

    public Message update(Message client){
        if(client.getIdMessage() != null){
            Optional<Message> messageEncontrado = messageRepository.getMessage(client.getIdMessage());
            if(!messageEncontrado.isEmpty()){
                if(client.getMessageText() != null){
                    messageEncontrado.get().setMessageText(client.getMessageText());
                }
                return messageRepository.save(messageEncontrado.get());
            }
        }
        return client;
    }

    public boolean deleteMessage(int messageId) {
        Boolean resultado = getMessage(messageId).map(messagePorEliminar -> {
            messageRepository.delete(messagePorEliminar);
            return true;
        }).orElse(false);
        return resultado;
    }
}
