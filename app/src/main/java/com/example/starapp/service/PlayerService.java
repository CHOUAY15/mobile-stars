package com.example.starapp.service;

import com.example.starapp.beans.Player;
import com.example.starapp.dao.IDao;

import java.util.ArrayList;
import java.util.List;

public class PlayerService implements IDao<Player> {

    private List<Player> players;
    private static PlayerService instance;

    private PlayerService() {
        this.players = new ArrayList<>();
    }

    public static PlayerService getInstance() {
        if (instance == null) {
            instance = new PlayerService();
        }
        return instance;
    }

    @Override
    public boolean create(Player o) {
        return players.add(o);
    }

    @Override
    public boolean update(Player o) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getId() == o.getId()) {
                players.set(i, o);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Player o) {
        return players.remove(o);
    }

    @Override
    public Player findById(int id) {
        for (Player s : players) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    @Override
    public List<Player> findAll() {
        return new ArrayList<>(players);
    }
}