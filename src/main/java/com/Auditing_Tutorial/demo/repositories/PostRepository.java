package com.Auditing_Tutorial.demo.repositories;

import com.Auditing_Tutorial.demo.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity,Long> {
}
