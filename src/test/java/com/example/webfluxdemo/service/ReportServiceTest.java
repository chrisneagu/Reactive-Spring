package com.example.webfluxdemo.service;

import com.example.webfluxdemo.dao.ReportRepository;
import com.example.webfluxdemo.entity.Report;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import static org.junit.Assert.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ReportServiceTest {
    private ReportRepository reportRepository;
    private ReportServiceImpl reportService;

    @Before
    public void setup() {
        reportRepository = Mockito.mock(ReportRepository.class);
        reportService = new ReportServiceImpl(reportRepository, WebClient.create());
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddReportsHappyPath() {
        int expectedNumberOfReports = 2;

        Report testReport1 = new Report();
        testReport1.setSummary("Some text here...");
        testReport1.setTitle("SpaceFlight");
        testReport1.setUrl("https://blogs.nasa.gov/");
        testReport1.setNewsSite("NASA");
        testReport1.setPublishedAt("2024-02-29T17:46:00Z");
        testReport1.setUpdatedAt("2024-03-06T17:46:47.317000Z");
        testReport1.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/8/8a/ISS_after_completion_%28as_of_June_2006%29.jpg");

        Report testReport2 = new Report();
        testReport2.setSummary("Some text here...");
        testReport2.setTitle("Space News");
        testReport2.setUrl("https://spacex.com");
        testReport2.setNewsSite("SpaceX");
        testReport2.setPublishedAt("2024-02-29T17:46:00Z");
        testReport2.setUpdatedAt("2024-03-06T17:46:47.317000Z");
        testReport2.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/8/8a/ISS_after_completion_%28as_of_June_2006%29.jpg");

        Flux<Report> testReports = Flux.just(testReport1, testReport2);

        when(reportRepository.saveAll(any(Flux.class)))
                .thenReturn(testReports);

        Flux<Report> actualReports = reportService.saveAll(testReports);
        assertNotNull(actualReports);
        actualReports.collectList()
                .doOnNext(reports -> {
                    assertEquals(expectedNumberOfReports, reports.size());
                    assertTrue(reports.contains(testReport1));
                    assertTrue(reports.contains(testReport2));
                }).subscribe();

        // Same test, but with the soft assertion
        Flux<Report> actualResult = reportService.saveAll(testReports);
        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(actualResult).isNotNull();
        actualResult.collectList()
                .doOnNext(reports -> {
                    sa.assertThat(expectedNumberOfReports).isEqualTo(reports.size());
                    sa.assertThat(reports).containsAnyOf(testReport1, testReport2);
                    sa.assertAll();
                }).subscribe();
    }
}
