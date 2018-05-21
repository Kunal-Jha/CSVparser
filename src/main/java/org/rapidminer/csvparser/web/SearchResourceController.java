package org.rapidminer.csvparser.web;

import org.rapidminer.csvparser.Utils.ParserEngine;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchResourceController {

	String currentDataset;

	public SearchResourceController() {

		this.currentDataset = null;
	}

	@RequestMapping(value = "/parser/filename/{fileName}/query/{query}", method = RequestMethod.GET)
	public ResponseEntity<?> getValueRest(@PathVariable String fileName, @PathVariable String query) {
		ParserEngine entityRepo = new ParserEngine();
		if (fileName != null) {
			entityRepo.changeDataset(fileName);
		}
		if (query != null) {
			String result = entityRepo.getMedian(query);
			if (result != null)
				return ResponseEntity.ok(result);
			else
				return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
		} else
			return (ResponseEntity<?>) ResponseEntity.badRequest();
	}

}
