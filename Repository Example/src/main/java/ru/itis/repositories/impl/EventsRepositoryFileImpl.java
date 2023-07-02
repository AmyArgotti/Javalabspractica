package ru.itis.repositories.impl;

import ru.itis.models.Event;
import ru.itis.models.EventUser;
import ru.itis.models.User;
import ru.itis.repositories.EventsRepository;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class EventsRepositoryFileImpl implements EventsRepository {
    private final String eventFileName;
    private final String eventsAndUsersFileName;
    private ArrayList<Event> events;

    public EventsRepositoryFileImpl(String eventFileName, String eventsAndUsersFileName) {
        this.eventFileName = eventFileName;
        this.eventsAndUsersFileName = eventsAndUsersFileName;
        findEvents();
    }

    public void findEvents(){
        this.events = new ArrayList<>();

        try{
            BufferedReader reader = new BufferedReader(new FileReader(eventFileName));

            String line = "";
            while((line = reader.readLine()) != null){
                String[] cols = line.split("\\|");
                this.events.add(Event
                        .builder()
                        .id(cols[0])
                        .date(LocalDate.parse(cols[2]))
                        .name(cols[1]).build());
            }

        }catch(IOException error){
            System.out.println(error);
        }
    }

    @Override
    public void save(Event model) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(eventFileName, true))){
            writer.write(model.getId() + "|" + model.getName() + "|" + model.getDate());
            writer.newLine();
            findEvents();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Event findByName(String nameEvent) {
      try {
          return this.events
                  .stream()
                  .filter(event -> nameEvent.equalsIgnoreCase(event.getName()))
                  .findFirst()
                  .get();
      }
      catch(NoSuchElementException exception){
          System.out.println(exception);
          return null;
      }
    }

    @Override
    public Event findById(String id) {
        try {
            return this.events
                    .stream()
                    .filter(event -> event.getId().equals(id))
                    .findFirst()
                    .get();
        }
        catch (NoSuchElementException exception){
            System.out.println(exception);
            return null;
        }
    }

    @Override
    public void saveUserToEvent(User user, Event event) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(eventsAndUsersFileName, true))){
            writer.write(user.getId() + "|" + event.getId());
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<Event> findAllByMembersContains(User user) {
        ArrayList<EventUser> eventsUser = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(eventsAndUsersFileName));

            String line = "";
            while((line = reader.readLine()) != null){
                String[] cols = line.split("\\|");
                eventsUser.add(new EventUser(cols[0], cols[1]));
            }

            List<EventUser> currentUserEvents = eventsUser
                    .stream()
                    .filter(eventUser -> user.getId().equals(eventUser.getUserId()))
                    .toList();

            return currentUserEvents
                .stream()
                .map(eventUser -> this.findById(eventUser.getEventId()))
                .toList();

        } catch (IOException exception){
            System.out.println(exception);
            return null;
        }
    }
}

