package com.example.myhospital.service;

import com.example.myhospital.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    List<Room> getAllRooms();
    Room save(Room room);
    Room getRoomById(long id);
    void deleteRoomById(long id);
    Room updateRoom(Room room);
    Room getRoomByName(String name);
}
