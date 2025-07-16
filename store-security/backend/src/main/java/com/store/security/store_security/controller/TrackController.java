package com.store.security.store_security.controller;

import com.store.security.store_security.dto.TrackDto;
import com.store.security.store_security.service.ITrackService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/track")
public class TrackController {

	private final ITrackService	trackService;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER') or hasRole('ROLE_TRACK')")
	@GetMapping("/{idOrder}")
	public ResponseEntity<TrackDto> getTrackByOrder(@PathVariable("idOrder") Long idOrder) {
		return ResponseEntity.ok(trackService.getTrackByOrder(idOrder));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_TRACK')")
	@PutMapping("/{idOrder}")
	public ResponseEntity<TrackDto> setTrack(@PathVariable("idOrder") Long idOrder,@RequestBody TrackDto trackDto) {
		return ResponseEntity.ok(trackService.setTrack(idOrder,trackDto));
	}
}
