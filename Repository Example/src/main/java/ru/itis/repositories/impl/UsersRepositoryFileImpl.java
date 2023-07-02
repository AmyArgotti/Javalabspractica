package ru.itis.repositories.impl;

import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;
import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class UsersRepositoryFileImpl implements UsersRepository {

    private final String fileName;
    private ArrayList<User> users;

    public UsersRepositoryFileImpl(String fileName) {
        this.fileName = fileName;
        findEvents();
    }

    public void findEvents(){
        this.users = new ArrayList<>();

        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String line = "";
            while((line = reader.readLine()) != null){
                String[] cols = line.split("\\|");
                this.users.add(User
                        .builder()
                        .id(cols[0])
                        .email(cols[1])
                        .password(cols[2]).build());
            }
           
        }catch(IOException error){
            System.out.println(error);
        }
    }

    @Override
    public void save(User model) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){
            writer.write(model.getId() + "|" + model.getEmail() + "|" + model.getPassword());
            writer.newLine();
            findEvents();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public User findByEmail(String emailUser) {
        findEvents();
        try{
            return this.users
                    .stream()
                    .filter(user -> user.getEmail().equals(emailUser))
                    .findFirst()
                    .get();
        }
        catch(NoSuchElementException exception){
            System.out.println(exception);
            return null;
        }

    }

    @Override
    public User findById(String id) {
       try {
           findEvents();
           return this.users.stream()
                   .filter(user -> user.getId().equals(id))
                   .findFirst()
                   .get();
       }
       catch(NoSuchElementException exception){
           System.out.println(exception);
           return null;
       }
    }
}

