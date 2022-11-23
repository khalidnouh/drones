package com.example.drones.repository;

import com.example.drones.model.AuditEventLog;
import com.example.drones.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuditEventLogRepo extends JpaRepository<AuditEventLog,Long> {


}
