package com.example.myhospital.service;

import com.example.myhospital.model.Room;
import com.example.myhospital.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAllByAvailabilityGreaterThan(0);
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room getRoomById(long id) {
        return roomRepository.findById(id);
    }

    @Override
    public void deleteRoomById(long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public Room updateRoom(Room room) {
        long id=room.getId();
        Room room1 = roomRepository.findById(id);
        room1.setCost(room.getCost());
        room1.setType(room.getType());
        room1.setName(room.getName());
        room1.setAvailability(room.getAvailability());
        room1.setCapacity(room.getCapacity());
        return roomRepository.save(room1);
    }

    @Override
    public Room getRoomByName(String name) {
        return roomRepository.getRoomByName(name);
    }
}
