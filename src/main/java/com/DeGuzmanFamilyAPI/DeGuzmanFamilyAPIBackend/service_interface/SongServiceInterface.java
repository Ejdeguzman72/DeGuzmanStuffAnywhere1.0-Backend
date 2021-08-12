package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.service_interface;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.Song;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.exception.ResourceNotFoundException;

public interface SongServiceInterface {

	public List<Song> findAllSongInformation();
	
	public ResponseEntity<Song> findSongById(@PathVariable int song_id) throws ResourceNotFoundException;
	
	public ResponseEntity<Song> findSongByTitle(@PathVariable String title);
	
	public List<Song> findSongByArtist(@PathVariable String artist);
	
	public Song addSongInformation(@Valid @RequestBody Song song);
	
	public ResponseEntity<Song> updateSongInformation(@PathVariable int song_id, 
			@Valid @RequestBody Song songDetails);
	
	public Map<String,Boolean> deleteSongInformation(int song_id);
}
