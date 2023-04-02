package com.nt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.Tourist;

public interface ITouriestRepo extends JpaRepository<Tourist, Integer> {

}
