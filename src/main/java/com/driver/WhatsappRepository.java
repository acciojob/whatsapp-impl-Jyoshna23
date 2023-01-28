package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public class WhatsappRepository {

    //Assume that each user belongs to at most one group
    //You can use the below mentioned hashmaps or delete these and create your own.
    private HashMap<Group, List<User>> groupUserMap;
    private HashMap<Group, List<Message>> groupMessageMap;
    private HashMap<Message, User> senderMap;
    private HashMap<Group, User> adminMap;
    private HashSet<String> userMobile;
    private int customGroupCount;
    private int messageId;

    private HashMap<String,String> User;

    public WhatsappRepository(){
        this.groupMessageMap = new HashMap<Group, List<Message>>();
        this.groupUserMap = new HashMap<Group, List<User>>();
        this.senderMap = new HashMap<Message, User>();
        this.adminMap = new HashMap<Group, User>();
        this.userMobile = new HashSet<>();
        this.customGroupCount = 0;
        this.messageId = 0;
        this.User = new HashMap<>();
    }


    public String createUser(String name, String mobile) throws Exception{
        if(userMobile.contains(mobile)){
            throw new Exception("User already Exists");
        }else{
           User.put(name,mobile);    //adding in user hashmap
           userMobile.add(mobile);  //adding in hashset
        }
        return "SUCCESS";
    }

    public Group createGroup(List<User> users){
        Group g = new Group();
        if(users.size() == 2){
            g.setName(users.get(1).getName());
            adminMap.put(g,users.get(0));
        }else if(users.size() > 2){
            this.customGroupCount++;
            g.setName("Group "+this.customGroupCount);
            adminMap.put(g,users.get(0));
        }
        return g;
    }

    public int createMessage(String content){
        messageId++;
        Message message = new Message(messageId,content,new Date());
        return messageId;
    }

    public int sendMessage(Message message, User sender, Group group) throws Exception{
        if(!groupUserMap.containsKey(group.getName())){
            throw new Exception("Group does not exist");
        }

        if(!this.userInGroup(group,sender))
        {
            throw new Exception("You are not allowed to send message");
        }

        List<Message> messages = new ArrayList<>();
        if(groupMessageMap.containsKey(group)) messages = groupMessageMap.get(group);

        messages.add(message);
        groupMessageMap.put(group,messages);
            return messages.size();
    }

    public String changeAdmin(User approver, User user, Group group) throws Exception{

        //Throw "Group does not exist" if the mentioned group does not exist
        //Throw "Approver does not have rights" if the approver is not the current admin of the group
        //Throw "User is not a participant" if the user is not a part of the group
        //Change the admin of the group to "user" and return "SUCCESS". Note that at one time there is only one admin and the admin rights are transferred from approver to user.

        if(!groupUserMap.containsKey(group)){
            throw new Exception("Group does not exist");
        }
        if(!adminMap.get(group).equals(approver)){
            throw new Exception("Approver does not have rights");
        }

       if(!this.userInGroup(group,user))
        {
            throw new Exception("User is not a participant");
        }
        adminMap.put(group,user);

        return "SUCCESS";
    }

    public boolean userInGroup(Group group, User sender){
        List<User> users = groupUserMap.get(group);
        for(User user : users){
            if(user.equals(sender))
                return true;
        }
        return false;
    }



}
