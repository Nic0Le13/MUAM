package com.example.demo.repos;

import com.example.demo.model.Painting;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PainterRepos extends CrudRepository<Painting, Long> {
 List<Painting> findByPainter(String painter);
}