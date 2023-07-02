package ru.itis.repositories;
import ru.itis.models.User;
public interface UsersRepository extends CrudRepository<User> {
    User findByEmail(String emailUser);
    User findById(String id);
}
