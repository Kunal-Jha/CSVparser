package org.rapidminer.csvparser.web;

import org.rapidminer.csvparser.Utils.ParserEngine;
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
	public ResponseEntity<?> getValue(@PathVariable String fileName, @PathVariable String query) {
		ParserEngine entityRepo = new ParserEngine();
		if (fileName != null && query != null) {
			entityRepo.changeDataset(fileName);
			return ResponseEntity.ok(entityRepo.getMedian(query));
		} else
			return (ResponseEntity<?>) ResponseEntity.badRequest();
	}

}
