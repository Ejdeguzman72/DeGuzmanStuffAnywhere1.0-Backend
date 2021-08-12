package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.Song;

@Repository
public interface SongRepository extends JpaRepository<Song,Integer> {

	@Query(value = "SELECT * FROM song WHERE title = ?1", nativeQuery=true)
	Song findByTitle(String title);
	
	@Query(value = "SELECT * FROM song where artist = ?1", nativeQuery=true)
	List<Song> findSongsByArtist(String artist);
	
	Page<Song> findByTitleContaining(String title, Pageable pageable);
	
	Page<Song> findByArtistContaining(String artist, Pageable pageable);
}
