package com.example.webfluxdemo.service;

import com.example.webfluxdemo.entity.Report;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ReportServiceIT {
    @Autowired
    private ReportService reportService;
    @Test
    public void testAddReportsHappyPath(){
        Report report1 = new Report();
        report1.setSummary("Some text here...");
        report1.setTitle("SpaceFlight");
        report1.setUrl("https://blogs.nasa.gov/");
        report1.setNewsSite("NASA");
        report1.setPublishedAt("2024-02-29T17:46:00Z");
        report1.setUpdatedAt("2024-03-06T17:46:47.317000Z");
        report1.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/8/8a/ISS_after_completion_%28as_of_June_2006%29.jpg");

        Report report2 = new Report();
        report2.setSummary("Some text here...");
        report2.setTitle("Space News");
        report2.setUrl("https://spacex.com");
        report2.setNewsSite("SpaceX");
        report2.setPublishedAt("2024-02-29T17:46:00Z");
        report2.setUpdatedAt("2024-03-06T17:46:47.317000Z");
        report2.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/8/8a/ISS_after_completion_%28as_of_June_2006%29.jpg");

        Flux<Report> theReports = Flux
                .just(report1, report2);

        Flux<Report> newReports = reportService.saveAll(theReports);
        assertNotNull(newReports);

        StepVerifier.create(newReports)
                .consumeNextWith(report -> {
                    assertNotNull(report);
                    assertEquals(report1.getSummary(), report.getSummary());
                    assertEquals(report1.getTitle(), report.getTitle());
                })
                .consumeNextWith(report -> {
                    assertNotNull(report);
                    assertEquals(report2.getSummary(), report.getSummary());
                    assertEquals(report2.getTitle(), report.getTitle());
                })
                .verifyComplete();
    }
}
