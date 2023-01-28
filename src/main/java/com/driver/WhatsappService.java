package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class WhatsappService {

    WhatsappRepository whatsappRepository = new WhatsappRepository();

    public String createUser(String name, String mobile) throws Exception {
        String response = "";
        try {
            response = whatsappRepository.createUser(name, mobile);
        } catch (Exception e) {
            throw e;
        }
        return response;
    }

    public Group createGroup(List<User> users) {
        return whatsappRepository.createGroup(users);
    }

    public int createMessage(String content) {
        return whatsappRepository.createMessage(content);
    }

    public int sendMessage(UpdateRequest updateRequest) throws Exception {
        int response = 0;
        try {
            response = whatsappRepository.sendMessage(updateRequest);
        } catch (Exception e) {
            throw e;
        }
        return response;
    }

    public String changeAdmin(ChangeAdmin changeAdmin) throws Exception {
        String response = "";
        try {
            response = whatsappRepository.changeAdmin(changeAdmin);
        } catch (Exception e) {
            throw e;
        }
        return response;
    }
}
//
//@Service
//public class WhatsappService {
//
//
//    WhatsappRepository whatsappRepository = new WhatsappRepository();
//
//    public boolean isNewUser(String mobile) {
//        return whatsappRepository.isNewUser(mobile);
//    }
//    public String createUser(String name, String mobile) throws Exception {
//         whatsappRepository.createUser(name, mobile);
//         return "SUCCESS";
//    }
//
//    public Group createGroup(List<User> users) {
//        return whatsappRepository.createGroup(users);
//    }
//
//    public int createMessage(String content) {
//        return whatsappRepository.createMessage(content);
//    }
//
//    public String changeAdmin(User approver, User user, Group group) throws Exception {
//        return whatsappRepository.changeAdmin(approver,user,group);
//    }
//
//    public int sendMessage(Message message, User sender, Group group) throws Exception {
//        return whatsappRepository.sendMessage(message,sender,group);
//    }
//
////    public int removeUser(User user) {
////
////    }
//
////    public String findMessage(Date start, Date end, int k) {
////
////    }
//}
