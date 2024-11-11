package com.founderz.tag.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface TagJpaRepository extends JpaRepository<TagEntity, TagEntityId> {
    Optional<TagEntity> findById_Id(Long id);
    Optional<TagEntity> findById_Name(String name);
    void deleteById_Id(Long id);
}
