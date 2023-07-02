package ru.itis.repositories.impl;

import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;

import java.io.*;
import java.util.ArrayList;

public class UsersRepositoryFileImpl implements UsersRepository {

    private final String fileName;
    private ArrayList<User> users;

    public UsersRepositoryFileImpl(String fileName) {
        this.fileName = fileName;
        setUsersInMemory();
    }

    public void setUsersInMemory(){
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
            System.out.println(users.size());
        }catch(IOException error){
            System.out.println(error);
        }
    }

    @Override
    public void save(User model) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){
            writer.write(model.getId() + "|" + model.getEmail() + "|" + model.getPassword());
            writer.newLine();
            setUsersInMemory();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public User findByEmail(String emailUser) {
        setUsersInMemory();
       return this.users
               .stream()
               .filter(user -> user.getEmail().equals(emailUser))
               .findFirst()
               .get();
    }

    @Override
    public User findById(String id) {
        setUsersInMemory();
        return this.users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .get();
    }
}

