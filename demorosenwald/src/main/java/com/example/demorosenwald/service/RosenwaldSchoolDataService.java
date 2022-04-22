package com.example.demorosenwald.service;


import com.example.demorosenwald.entity.SchoolData;
import com.example.demorosenwald.util.UniqueSchoolPhoto;
import lombok.Getter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Getter
public class RosenwaldSchoolDataService {


    private List<SchoolData> allSchoolData = new ArrayList<>();
    private List<SchoolData> homeSchoolData = new ArrayList<>();

    private static String SCHOOL_DATA_URL = "https://raw.githubusercontent.com/Aayush000/Rosenwald_Library/main/schools/school_metadata.csv";

    @PostConstruct          //after @service creates and annotation for the class, it just right here
//    @Scheduled(cron = "* * 1 * * *") //run once every day
    public void RosenwaldSchoolDataService() throws IOException, InterruptedException {
        List<SchoolData> newSchoolData = new ArrayList<>();
        Set<String> uniquePhotoId = new HashSet<String>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(SCHOOL_DATA_URL)).build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(httpResponse.body());

        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);

        ArrayList<String> sublistUID = new ArrayList<String>();
        SchoolData schoolData = new SchoolData();

        for (CSVRecord record : records) {

            if (!uniquePhotoId.contains(record.get("Photo ID #"))) {
                if (!sublistUID.isEmpty()) {
                    schoolData.setListUID(sublistUID);
                }
                sublistUID = new ArrayList<>();


                schoolData = new SchoolData();


                schoolData.setAlternateNames(record.get("Alternate Names"));
                schoolData.setAppl(record.get("Appl #"));
                schoolData.setCounty(record.get("County"));
                schoolData.setSchoolNames(record.get("School Names"));
                schoolData.setCreator(record.get("Creator"));
                schoolData.setSchoolCardId(record.get("School Card ID"));
                schoolData.setPhoto_id(record.get("Photo ID #"));
                schoolData.setFormat(record.get("Format"));
                schoolData.setDescription(record.get("Description"));
                schoolData.setPublisher(record.get("Publisher"));
                schoolData.setTitle(record.get("Title"));
                schoolData.setRights(record.get("Rights"));
                schoolData.setOriginalDate(record.get("Date.Original"));
                schoolData.setUID(record.get("UID"));
                schoolData.setState(record.get("State"));
                schoolData.setSource(record.get("Source"));
                schoolData.setSubject(record.get("Subject"));
                sublistUID.add(record.get("Photo ID #"));

                newSchoolData.add(schoolData);
            } else {
                sublistUID.add(record.get("Photo ID #"));
            }
            uniquePhotoId.add(record.get("Photo ID #"));


        }
        homeSchoolData = newSchoolData.subList(newSchoolData.size() - 9, newSchoolData.size());
        allSchoolData = newSchoolData;

    }
}