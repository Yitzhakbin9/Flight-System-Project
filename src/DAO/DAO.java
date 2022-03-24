package DAO;

import java.util.List;

public interface DAO<T> {

    static String URL = "jdbc:postgresql://localhost:5432/AirlinesProject";
    static String USER = "postgres";
    static String PASSWORD = "1234";

    T Get(int id);

    List<T> GetAll();

    void Add(T t);

    void Remove(T t);

    void Update(T t, int id);
}
