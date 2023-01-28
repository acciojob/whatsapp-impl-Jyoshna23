package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public class WhatsappRepository {

    //Assume that each user belongs to at most one group
    //You can use the below mentioned hashmaps or delete these and create your own.
//    private HashMap<String, Group> groupMap;
    private HashMap<Group, List<User>> groupUserMap;
    private HashMap<Group, List<Message>> groupMessageMap;
    private HashMap<String, List<Message>> senderMap;
    private HashMap<String, User> adminMap;
    private HashSet<String> userMobile;
    private int groupCount;
    private int messageId;
    private List<String> listOfGroups;
    private HashMap<String, String> userMap;
    private List<User> allUsers;

    public WhatsappRepository() {
//        this.groupMap = new HashMap<>();
        this.groupMessageMap = new HashMap<Group, List<Message>>();
        this.groupUserMap = new HashMap<Group, List<User>>();
        this.senderMap = new HashMap<String, List<Message>>();
        this.adminMap = new HashMap<String, User>();
        this.userMobile = new HashSet<>();
        this.groupCount = 0;
        this.messageId = 0;
        this.userMap = new HashMap<>();
        this.listOfGroups = new ArrayList<>();
        this.allUsers = new ArrayList<>();
    }

    public String createUser(String name, String mobile) throws Exception {
        try {
            if (userMobile.contains(mobile)) {
                throw new Exception("User already exists");
            }
        } catch (Exception e) {
            throw e;
        }
        User user = new User(name, mobile);
        userMobile.add(mobile);
        allUsers.add(user);
        return "SUCCESS";
    }

    public Group createGroup(List<User> users) {
//        private HashMap<Group, User> adminMap;

        User admin = users.get(0);
        if (users.size() == 2) {
            String name = users.get(1).getName();
            Group g = new Group(name, 2);
            groupUserMap.put(g, users);
            adminMap.put(name, admin);
//            listOfGroups.add(name);
//            groupMap.put(name, g);
            return g;
        }
        groupCount++;
        String name = "Group " + groupCount;
        Group g1 = new Group(name, users.size());
        groupUserMap.put(g1, users);
//        listOfGroups.add(name);
        adminMap.put(name, admin);
//        groupMap.put(name, g1);
        return g1;
    }

    public int createMessage(String content) {
        messageId++;
//        Date date = new Date("28-01-2023");

        Message message = new Message(messageId, content);

        return messageId;
    }

    public int sendMessage(UpdateRequest updateRequest) throws Exception {
        Group group = updateRequest.getGroup();
        User sender = updateRequest.getSender();
        Message message = updateRequest.getMessage();

//        String groupName = group.getName();
        List<User> users = new ArrayList<>();
        boolean isPre = false;

        for (Group g : groupUserMap.keySet()) {
            if (g.equals(group)) {
                users = groupUserMap.get(g);
                isPre = true;
                break;
            }
        }

        try {
            if (isPre == false) {
                throw new Exception("Group does not exist");
            }
        } catch (Exception e) {
            throw e;
        }

//        List<User> users = groupUserMap.get(groupName);

        boolean isPresent = false;
        for (User user : users) {
            if (user.equals(sender)) {
                isPresent = true;
                break;
            }
        }

        try {
            if (isPresent == false) {
                throw new Exception("You are not allowed to send message");
            }
        } catch (Exception e) {
            throw e;
        }

        List<Message> messages = new ArrayList<>();
        for (Group g : groupMessageMap.keySet()) {
            if (group.equals(g)) {
                messages = groupMessageMap.get(g);
                break;
            }
        }
        messages.add(message);
        groupMessageMap.put(group, messages);

//        List<Message> senderMessages = new ArrayList<>();
//        if (senderMap.containsKey(sender.getMobile())) {
//            senderMessages = senderMap.get(sender);
//        }
//        senderMessages.add(message);
//        senderMap.put(sender.getMobile(), senderMessages);

        return messages.size();
    }

    public String changeAdmin(ChangeAdmin changeAdmin) throws Exception {
//        String groupName = group.getName();
        Group group = changeAdmin.getGroup();
        User approver = changeAdmin.getAdmin();
        User user = changeAdmin.getUser();
        String groupName = group.getName();
        List<User> users = new ArrayList<>();

        boolean isPresent = false;
        for (Group g : groupUserMap.keySet()) {
            if (group.equals(g)) {
                users = groupUserMap.get(g);
                isPresent = true;
                break;
            }
        }

        try {
            if (isPresent == false) {
                throw new Exception("Group does not exist");
            }
        } catch (Exception e) {
            throw e;
        }

        try {
            if (!approver.getMobile().equals(adminMap.get(groupName).getMobile())) {
                throw new Exception("Approver does not have rights");
            }
        } catch (Exception e) {
            throw e;
        }

//        List<User> users = groupUserMap.get(groupName);

        isPresent = false;
        for (User u : users) {
            if (user.equals(u)) {
                isPresent = true;
                break;
            }
        }

        try {
            if (isPresent == false) {
                throw new Exception("You are not allowed to send message");
            }
        } catch (Exception e) {
            throw e;
        }

        adminMap.put(groupName, user);
        return "SUCCESS";
    }
}
//@Repository
//public class WhatsappRepository {
//
//    //Assume that each user belongs to at most one group
//    //You can use the below mentioned hashmaps or delete these and create your own.
//    private HashMap<Group, List<User>> groupUserMap;
//    private HashMap<Group, List<Message>> groupMessageMap;
//    private HashMap<Message, User> senderMap;
//    private HashMap<Group, User> adminMap;
//    private HashSet<String> userMobile;
//    private int customGroupCount;
//    private int messageId;
//
//    private HashMap<String,User> User;
//
//    public WhatsappRepository(){
//        this.groupMessageMap = new HashMap<Group, List<Message>>();
//        this.groupUserMap = new HashMap<Group, List<User>>();
//        this.senderMap = new HashMap<Message, User>();
//        this.adminMap = new HashMap<Group, User>();
//        this.userMobile = new HashSet<>();
//        this.customGroupCount = 0;
//        this.messageId = 0;
//        this.User = new HashMap<>();
//    }
//
//
//    public boolean isNewUser(String mobile) {
//        if(User.containsKey(mobile)) return false;
//        return true;
//    }
//
//
//    public void createUser(String name, String mobile) throws Exception{
//        User.put(mobile, new User(name, mobile));
//
//    }
//
//    public Group createGroup(List<User> users){
//
//        if(users.size() == 2){
//            String groupName = users.get(1).getName();
//            Group chat = new Group(groupName, 2);
//            groupUserMap.put(chat,users);
//            return chat;
//        }
//
//        this.customGroupCount++;
//        String groupName = "Group " + this.customGroupCount;
//        Group group = new Group(groupName, users.size());
//        groupUserMap.put(group, users);
//        adminMap.put(group, users.get(0));
//        return group;
//    }
//
//    public int createMessage(String content){
//        this.messageId++;
//        Message message = new Message(messageId,content,new Date());
//        return this.messageId;
//    }
//
//    public int sendMessage(Message message, User sender, Group group) throws Exception{
//        if(!groupUserMap.containsKey(group)){
//            throw new Exception("Group does not exist");
//        }
//
//        if(!this.userInGroup(group,sender))
//        {
//            throw new Exception("You are not allowed to send message");
//        }
//
//        List<Message> messages = new ArrayList<>();
//        if(groupMessageMap.containsKey(group)) messages = groupMessageMap.get(group);
//
//        messages.add(message);
//        groupMessageMap.put(group,messages);
//            return messages.size();
//    }
//
//    public String changeAdmin(User approver, User user, Group group) throws Exception{
//
//        //Throw "Group does not exist" if the mentioned group does not exist
//        //Throw "Approver does not have rights" if the approver is not the current admin of the group
//        //Throw "User is not a participant" if the user is not a part of the group
//        //Change the admin of the group to "user" and return "SUCCESS". Note that at one time there is only one admin and the admin rights are transferred from approver to user.
//
//        if(!groupUserMap.containsKey(group)){
//            throw new Exception("Group does not exist");
//        }
//        if(!adminMap.get(group).equals(approver)){
//            throw new Exception("Approver does not have rights");
//        }
//
//       if(!this.userInGroup(group,user))
//        {
//            throw new Exception("User is not a participant");
//        }
//        adminMap.put(group,user);
//
//        return "SUCCESS";
//    }
//
//    public boolean userInGroup(Group group, User sender){
//        List<User> users = groupUserMap.get(group);
//        for(User user : users){
//            if(user.equals(sender))
//                return true;
//        }
//        return false;
//    }
//
//
//
//}
