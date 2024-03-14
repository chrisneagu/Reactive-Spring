package com.example.webfluxdemo.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.webfluxdemo.dao.ReportRepository;
import com.example.webfluxdemo.entity.Report;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ReportServiceUnitTest {
    @Mock
    private ReportRepository reportRepository;
    @InjectMocks
    private ReportServiceImpl reportService;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddReportsHappyPath() {
        Report aMockReport1 = new Report();
        aMockReport1.setSummary("Some text here...");
        aMockReport1.setTitle("SpaceFlight");
        aMockReport1.setUrl("https://blogs.nasa.gov/");
        aMockReport1.setNewsSite("NASA");
        aMockReport1.setPublishedAt("2024-02-29T17:46:00Z");
        aMockReport1.setUpdatedAt("2024-03-06T17:46:47.317000Z");
        aMockReport1.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/8/8a/ISS_after_completion_%28as_of_June_2006%29.jpg");

        Report aMockReport2 = new Report();
        aMockReport2.setSummary("Some text here...");
        aMockReport2.setTitle("Space News");
        aMockReport2.setUrl("https://spacex.com");
        aMockReport2.setNewsSite("SpaceX");
        aMockReport2.setPublishedAt("2024-02-29T17:46:00Z");
        aMockReport2.setUpdatedAt("2024-03-06T17:46:47.317000Z");
        aMockReport2.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/8/8a/ISS_after_completion_%28as_of_June_2006%29.jpg");

        Flux<Report> theMockReports = Flux.just(aMockReport1, aMockReport2);

        when(reportRepository.saveAll(any(Flux.class)))
                .thenReturn(theMockReports);

        Flux<Report> newReports = reportService.saveAll(null);

        StepVerifier.create(newReports)
                .consumeNextWith(report -> {
                    assertNotNull(report);
                    assertEquals(aMockReport1.getSummary(), report.getSummary());
                    assertEquals(aMockReport1.getTitle(), report.getTitle());
                })
                .consumeNextWith(report -> {
                    assertNotNull(report);
                    assertEquals(aMockReport2.getSummary(), report.getSummary());
                    assertEquals(aMockReport2.getTitle(), report.getTitle());
                })
                .verifyComplete();
    }
}
