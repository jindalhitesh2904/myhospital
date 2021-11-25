package com.example.myhospital.repository;

import com.example.myhospital.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
    Room getRoomByName(String name);
    List<Room> getAllByType(String type);
    Room findById(long id);
    List<Room> findAllByAvailabilityGreaterThan(long availability);
}
