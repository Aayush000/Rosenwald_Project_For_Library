package com.example.demorosenwald.util;

import lombok.Getter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;


@Getter
public class UniqueSchoolPhoto {
    private ArrayList<String> sublistUID = new ArrayList<String>();
//    @Autowired
//    private RosenwaldSchoolDataService rosenwaldSchoolDataService = new RosenwaldSchoolDataService();
//
//    public UniqueSchoolPhoto(String photo_id) throws IOException {
//
//        StringReader csvBodyReader = new StringReader(rosenwaldSchoolDataService.getHttpResponse().body());
//        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
//
//        sublistUID = new ArrayList<String>();
//        for (CSVRecord record : records) {
//            System.out.println(3);
//            if(record.get("Photo ID #").equals(photo_id)) {
//                sublistUID.add(record.get("UID"));
//            }
//        }




    HttpResponse<String> httpResponse;

    private static String SCHOOL_DATA_URL = "https://raw.githubusercontent.com/Aayush000/Rosenwald_Library/main/schools/school_metadata.csv";

    //    @PostConstruct
    public UniqueSchoolPhoto(String photo_id)  throws IOException, InterruptedException {


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(SCHOOL_DATA_URL)).build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        this.httpResponse = httpResponse;
//        System.out.println(httpResponse.body());

        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);

        for (CSVRecord record : records) {
            if(record.get("Photo ID #").equals(photo_id)) {
                sublistUID.add(record.get("UID"));
            }
        }
    }
}
