package com.github.grading.network;

import javax.websocket.Session;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class WebsocketConnectionPool {

    private ConcurrentHashMap<Long, Session> sessions = new ConcurrentHashMap<>();

    public void addSession(long id, Session session) {
        this.sessions.put(id, session);
    }

    public void remove(long id) {
        this.sessions.remove(id);
    }

    public Session getSession(long id) {
        return this.sessions.get(id);
    }

    public List<Session> getSessions() {
        return new LinkedList<>(this.sessions.values());
    }

}
