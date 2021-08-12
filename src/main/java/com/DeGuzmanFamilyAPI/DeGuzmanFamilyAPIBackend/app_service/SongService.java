package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.Song;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_repository.SongRepository;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.exception.ResourceNotFoundException;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.logger.MusicLogger;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.logger.PersonInfoLogger;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.message.LoggerMessage;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.service_interface.SongServiceInterface;

@Service
public class SongService implements SongServiceInterface {

	@Autowired
	private SongRepository songRepository;

	@Override
	public List<Song> findAllSongInformation() {
		List<Song> songList = songRepository.findAll();

		if (songList.size() == 0 || songList.isEmpty()) {
			MusicLogger.musicLogger.warning("List is Empty");
		}

		return songRepository.findAll();
	}

	@Override
	public ResponseEntity<Song> findSongById(@PathVariable int song_id) throws ResourceNotFoundException {

		Song song = null;

		song = songRepository.findById(song_id)
				.orElseThrow(() -> new ResourceNotFoundException("Cannot find resource with ID: " + song_id));

		if (song_id == 0) {
			MusicLogger.musicLogger.severe("Cannot find resource with ID:" + " " + song_id);
		}

		else {
			MusicLogger.musicLogger.info("Song: " + song.title + " has been added");
		}

		return ResponseEntity.ok().body(song);
	}

	@Override
	public ResponseEntity<Song> findSongByTitle(@PathVariable String title) {
		Song song = null;
		song = songRepository.findByTitle(title);

		if (title == "" || title == null) {
			MusicLogger.musicLogger.warning("Music Title is blank");
		} else {
			MusicLogger.musicLogger.info("Music title: " + title);
		}

//		if (artist == "" || artist == null) {
//			MusicLogger.musicLogger.warning("Artist is blank/null");
//		}
//		
		return ResponseEntity.ok().body(song);
	}

	@Override
	public List<Song> findSongByArtist(String artist) {

		List<Song> song = null;

		song = songRepository.findSongsByArtist(artist);

		if (artist == "" || artist == null) {
			MusicLogger.musicLogger.warning("Artist is blank/null");
		} else {
			MusicLogger.musicLogger.info("Song retrieved with artist: " + artist);
		}

		return song;
	}

	@Override
	public Song addSongInformation(@Valid @RequestBody Song song) {
		if (song.title == "" || song.title == null) {
			MusicLogger.musicLogger.warning("Music Title is blank");
		} else {
			MusicLogger.musicLogger.info("Music title: " + song.title);
		}
		
		System.out.println(song);
		
		return songRepository.save(song);
	}

	@Override
	public ResponseEntity<Song> updateSongInformation(@PathVariable int song_id, @RequestBody @Valid Song songDetails) {
		Song song = null;
		try {
			song = songRepository.findById(song_id)
					.orElseThrow(() -> new ResourceNotFoundException("Cannot find resource with ID: " + song_id));
			
			if (songDetails.artist == "" || songDetails.artist == null) {
				MusicLogger.musicLogger.warning("Artist is blank/null");
			}
			song.setArtist(songDetails.getArtist());
			song.setGenre(songDetails.getGenre());
			
			if (song.title == "" || song.title == null) {
				MusicLogger.musicLogger.warning("Music Title is blank");
			} else {
				MusicLogger.musicLogger.info("Music title: " + song.title);
			}

			song.setTitle(songDetails.getTitle());
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}

		final Song updatedSongInformation = songRepository.save(songDetails);

		return ResponseEntity.ok().body(updatedSongInformation);
	}

	@Override
	public Map<String, Boolean> deleteSongInformation(int song_id) {
		songRepository.deleteById(song_id);
		
		if (song_id == 0) {
			MusicLogger.musicLogger.severe("Cannot find resource with ID:" + " " + song_id);
		}

		else {
			MusicLogger.musicLogger.info("Song has been added with ID: " + song_id);
		}
		
		Map<String, Boolean> response = new HashMap<>();
		Optional<Song> song = songRepository.findById(song_id);
		response.put("Song has been removed from list", Boolean.TRUE);

		return response;
	}
}
