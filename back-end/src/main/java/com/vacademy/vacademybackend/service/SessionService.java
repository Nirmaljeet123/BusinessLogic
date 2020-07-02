package com.vacademy.vacademybackend.service;

import com.vacademy.vacademybackend.database.SessionRepository;
import com.vacademy.vacademybackend.model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    public Session getSession(Integer id) {
        return sessionRepository.findSessionBySessionid(id);
    }

    public Session addSession(Session session) {
        try {
            sessionRepository.save(session);
            return sessionRepository.findSessionByUsernameAndTypeAndIntimeAndOutTimeEquals(session.getUsername()
                    , session.getType(), session.getIntime(), "active");
        } catch (Exception e) {
            return null;
        }
    }

    public String updateSession(Session session) {
        try {
            if (sessionRepository.existsSessionBySessionid(session.getSessionid())) {
                Session current_session = sessionRepository.findSessionBySessionid(session.getSessionid());
                String x = session.getOutTime();
                current_session.setOutTime(x);
                sessionRepository.save(current_session);
                return "Session Updated Successfully";
            } else {
                return "Session Not Found";
            }
        } catch (Exception e) {
            return "Error! Session not updated";
        }
    }

    public String deleteSession(Integer id) {
        if (sessionRepository.existsSessionBySessionid(id)) {
            sessionRepository.removeSessionBySessionid(id);
            return "Session Removed Successfully";
        } else {
            return "Session not found!";
        }
    }
}
