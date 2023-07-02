package ru.itis.repositories;

import ru.itis.models.Event;
import ru.itis.models.User;

import java.io.IOException;
import java.util.List;

public interface EventsRepository extends CrudRepository<Event> {
    Event findByName(String nameEvent);

    Event findById(String id);
    void saveUserToEvent(User user, Event event);
    List<Event> findAllByMembersContains(User user);
}
