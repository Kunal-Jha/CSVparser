package org.rapidminer.csvparser.Utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import org.rapidminer.csvparser.model.Row;

import com.google.gson.Gson;
import com.univocity.parsers.common.processor.RowListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

public class ParserEngine {
	String csvFilePath;
	int numberOfAttributes;
	int indexOfLabel;
	int indexOfId;
	Set<String> attributeList;
	CsvParserSettings parserSettings;
	RowListProcessor rowProcessor;
	CsvParser parser;

	public ParserEngine() {
		this.parserSettings = new CsvParserSettings();
		this.parserSettings.setDelimiterDetectionEnabled(true, ';');
		this.parserSettings.getFormat().setLineSeparator("\n");
		this.parserSettings.setHeaderExtractionEnabled(true);
		this.attributeList = new TreeSet<String>();
		this.rowProcessor = new RowListProcessor();
		this.parserSettings.setProcessor(rowProcessor);
		this.parser = new CsvParser(parserSettings);
	}

	public void changeDataset(String name) {
		this.numberOfAttributes = -1;
		this.indexOfLabel = -1;
		this.indexOfId = -1;
		this.csvFilePath = name;
	}

	public String getMedian(String query) {
		Stream.Builder<Row> csvElements = Stream.builder();
		try {
			parser.parse(new FileReader(csvFilePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		List<String> columns = Arrays.asList(rowProcessor.getHeaders());
		List<String[]> rowsFromFile = this.rowProcessor.getRows();
		columns.forEach(a -> {
			if (a.startsWith("a")) {
				this.numberOfAttributes++;
			} else if (a.toLowerCase().equals("label")) {
				this.indexOfLabel = columns.indexOf(a);
			}
			if (a.toLowerCase().equals("id")) {
				this.indexOfId = columns.indexOf(a);
			}
		});
		rowsFromFile.forEach(a -> {
			if (indexOfLabel == -1) {
				System.out.println("ERROR: no Label");
				System.exit(0);
			}
			Row row = new Row(a[indexOfLabel]);
			for (int i = 0; i <= numberOfAttributes; i++) {
				row.addToMap(columns.get(i), Double.parseDouble(a[i]));
			}
			if (indexOfId != -1) {
				row.setId(Optional.of(a[indexOfId]));
			}
			csvElements.accept(row);
			attributeList = row.getAttributesList();
		});
		Supplier<Stream<Row>> rowElements = () -> csvElements.build();
		Map<String, List<Row>> groupedLabelMap = rowElements.get().collect(Collectors.groupingBy(Row::getLabel));
		return this.calculateMedian(query, groupedLabelMap);

	}

	public String calculateMedian(String key, Map<String, List<Row>> groupedLabelMap) {
		if (!groupedLabelMap.containsKey(key)) {
			return null;
		}

		List<Row> listInGroup = groupedLabelMap.get(key);
		Row result = new Row(key);
		for (String attribute : attributeList) {
			DoubleStream sortedAttribute = listInGroup.stream().mapToDouble(a -> a.getfromMap(attribute)).sorted();
			double median = listInGroup.size() % 2 == 0
					? sortedAttribute.skip(listInGroup.size() / 2 - 1).limit(2).average().getAsDouble()
					: sortedAttribute.skip(listInGroup.size() / 2).findFirst().getAsDouble();
			result.addToMap(attribute, median);
		}

		return new Gson().toJson(result);
	}

}
