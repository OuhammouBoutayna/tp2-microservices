package fr.univ.tp.cours.repository;

import fr.univ.tp.cours.model.Cours;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class CoursRepository {
    private final Map<Integer, Cours> data = new ConcurrentHashMap<>();
    private final AtomicInteger seq = new AtomicInteger(0);

    public List<Cours> findAll() { return new ArrayList<>(data.values()); }
    public Optional<Cours> findById(int id) { return Optional.ofNullable(data.get(id)); }

    public Cours save(Cours c) {
        if (c.getId() == 0) c.setId(seq.incrementAndGet());
        data.put(c.getId(), c);
        return c;
    }

    public boolean delete(int id) { return data.remove(id) != null; }
}