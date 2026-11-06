package fr.univ.tp.etudiants.repository;

import fr.univ.tp.etudiants.model.Etudiant;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class EtudiantRepository {
    private final Map<Integer, Etudiant> data = new ConcurrentHashMap<>();
    private final AtomicInteger seq = new AtomicInteger(0);

    public List<Etudiant> findAll() { return new ArrayList<>(data.values()); }

    public Optional<Etudiant> findById(int id) { return Optional.ofNullable(data.get(id)); }

    public Etudiant save(Etudiant e) {
        if (e.getId() == 0) e.setId(seq.incrementAndGet());
        data.put(e.getId(), e);
        return e;
    }

    public boolean delete(int id) { return data.remove(id) != null; }
}