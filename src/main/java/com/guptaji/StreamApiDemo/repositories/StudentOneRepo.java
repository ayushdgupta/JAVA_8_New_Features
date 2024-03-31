package com.guptaji.StreamApiDemo.repositories;

import com.guptaji.StreamApiDemo.entity.StudentOne;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentOneRepo extends JpaRepository<StudentOne, Long> {}
