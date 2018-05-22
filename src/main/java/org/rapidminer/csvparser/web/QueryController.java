package org.rapidminer.csvparser.web;

import java.io.IOException;

import java.util.logging.Logger;
import java.util.stream.Stream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.rapidminer.csvparser.Utils.ParserEngine;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

@Controller
public class QueryController {
	private final static Logger LOGGER = Logger.getLogger(ParserEngine.class.getName());

	public QueryController() {
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "index";
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

	@RequestMapping(value = "/parser", method = RequestMethod.GET)
	public ResponseEntity<?> getValue(@RequestParam String fileName, @RequestParam String query) {
		LOGGER.info("Got Message to server!!");
		ParserEngine entityRepo = new ParserEngine();
		if (fileName != null) {
			entityRepo.changeDataset(fileName.toLowerCase());
		}
		if (query != null) {
			String result = entityRepo.getMedian(query);
			if (result != null)
				return ResponseEntity.ok(result);
		}
		return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
	}

}
