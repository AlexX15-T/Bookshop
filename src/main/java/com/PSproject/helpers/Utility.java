package com.PSproject.helpers;
import com.PSproject.model.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Utility {
    private static Utility instance = null;
    private static final String PATH = "src/main/saves/";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private Utility() {}

    public static Utility getInstance() {
        if (instance == null) {
            instance = new Utility();
        }
        return instance;
    }

    public static String getCurrenTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static void saveAs(List<Book> data, String type) {
        switch (type) {
            case "txt" -> saveAsTxt(data);
            case "json" -> saveAsJson(data);
            case "xml" -> saveAsXml(data);
            case "csv" -> saveAsCsv(data);
            default -> {
            }
        }
    }

    public static void saveAsTxt(List <Book> data) {
        String filename = PATH + getCurrenTime() + ".txt";

        String dataOut = "";

        for (Book bookModel : data) {
            dataOut += bookModel;
        }

        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(dataOut);
            myWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String objectToJson(Object a) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(a);
    }

    public static void saveAsJson(List <Book> data) {
        String filename = PATH + getCurrenTime() + ".json";
        try {
            String dataOut = "[";
            for (Book bookModel : data) {
                dataOut += objectToJson(bookModel);
                dataOut += ",\n";
            }
            dataOut = dataOut.substring(0, dataOut.length() - 2);
            dataOut += "]";
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(dataOut);
            myWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveAsXml(List <Book> data) {
        String filename = PATH + getCurrenTime() + ".xml";

        XmlMapper xmlMapper = new XmlMapper();
        String dataOut = "";
        try {
            for (Book bookModel : data) {
                dataOut += xmlMapper.writeValueAsString(bookModel);
                dataOut += '\n';
            }
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(dataOut);
            myWriter.close();
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveAsCsv(List <Book> data) {
        String filename = PATH + getCurrenTime() + ".csv";

        String header = "id,title,author,year,noOfPages,price,isbn,publisher";
        String dataOut = "";

        for (Book bookModel : data) {
            dataOut += bookModel.getId() + "," + bookModel.getTitle() + "," + bookModel.getAuthor() + "," + bookModel.getYear() + "," + bookModel.getNoOfPages() + "," + bookModel.getPrice() + "," + bookModel.getIsbn() + "," + bookModel.getPublisher();
            dataOut += '\n';
        }

        try {
            FileWriter csvWriter = new FileWriter(filename);
            csvWriter.append(header);
            csvWriter.append('\n');
            csvWriter.append(dataOut);
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
