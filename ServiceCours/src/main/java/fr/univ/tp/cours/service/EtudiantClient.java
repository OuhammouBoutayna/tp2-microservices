package fr.univ.tp.cours.service;

import fr.univ.tp.cours.model.EtudiantDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.Duration;

@Service
public class EtudiantClient {
    private final RestTemplate rest;
    private final String baseUrl;

    public EtudiantClient(@Value("${etudiants.url}") String baseUrl) {
        this.baseUrl = baseUrl;
        SimpleClientHttpRequestFactory f = new SimpleClientHttpRequestFactory();
        f.setConnectTimeout(Duration.ofSeconds(2));
        f.setReadTimeout(Duration.ofSeconds(2));
        this.rest = new RestTemplate(f);
    }

    public EtudiantDto getEtudiantById(int id) {
        return rest.getForObject(baseUrl + "/" + id, EtudiantDto.class);
    }
}