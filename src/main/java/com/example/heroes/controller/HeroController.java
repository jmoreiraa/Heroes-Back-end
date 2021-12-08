package com.example.heroes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import com.example.heroes.entity.Hero;
import com.example.heroes.repository.HeroRepository;

@RestController
@RequestMapping("/heroes")
@CrossOrigin
public class HeroController {

    // Injeta um dependencia!!!!
    @Autowired
    private HeroRepository repo;

    // End Point 1
    // (GET) http://localhost:8080/heroes
    @GetMapping
    public List<Hero> getHeroes() {
        List<Hero> list = repo.findAll();
        return list;
    }

    // End Point 2
    // (GET) http://localhost:8080/heroes/%7Bid%7D
    @GetMapping("{id}")
    public Hero getHero(@PathVariable int id) {
        Optional<Hero> op =  repo.findById(id);
        Hero hero = op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return hero;
    }

    // EndPoint 3
    // Salva um heroi, é necessário passar os dados do heroi no formato JSON
    @PostMapping
    public Hero salvar(@RequestBody Hero hero) {
        hero = repo.save(hero);
        return hero;
    }

     //EndPoint 4
    //Remove um heroi, é necessário passar o id
    @DeleteMapping("{id}")
    public void remover(@PathVariable Integer id){
        Optional<Hero> op =  repo.findById(id);
        Hero hero = op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        repo.delete(hero);
    }

       //EndPoint 5
    //Altera um heroi, é necessário passar os dados do heroi no formato JSON e tambem o id
    @PutMapping("{id}")
    public Hero alterar(@RequestBody Hero updateHero, @PathVariable Integer id){
        Optional<Hero> op =  repo.findById(id);
        Hero hero = op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        hero.setName(updateHero.getName());
        repo.save(hero);
        return hero;
    }



}