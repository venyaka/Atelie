package veniamin.backend.atelie.service;

import veniamin.backend.atelie.entity.UserSession;

public interface SessionService {

    UserSession saveNewSession(Long userId);

}
