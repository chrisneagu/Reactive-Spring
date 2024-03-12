package com.example.webfluxdemo;

import com.example.webfluxdemo.configuration.ProjectConfiguration;
import com.example.webfluxdemo.controller.ReportController;
import com.example.webfluxdemo.entity.Report;
import com.example.webfluxdemo.service.ReportService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = ReportController.class)
@ContextConfiguration(classes = {ReportController.class, ProjectConfiguration.class})
public class ReportControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ReportService reportService;

    @Test
    public void fetchReportsExternal_ShouldReturnReports_WhenSuccessful() {
//        Report report = new Report();
//        report.setId(1541);
//        report.setTitle("ISS Daily Summary Report – 3/08/2024");
//        report.setUrl("https://blogs.nasa.gov/stationreport/2024/03/08/iss-daily-summary-report-3-08-2024/");
//        report.setImage_url("https://upload.wikimedia.org/wikipedia/commons/8/8a/ISS_after_completion_%28as_of_June_2006%29.jpg");
//        report.setNews_site("NASA");
//        report.setSummary("Payloads: Advanced Resistive Exercise Device (ARED) Kinematics (ARED-K): An Isometric Mid-Thigh Pull (IMTP) session was performed on  ARED in support of the ARED-K investigation. Exploration-class missions including Artemis, Gateway, and beyond require an exercise device that is lightweight and has a small footprint. These devices provide a variety of full body resistance exercise options, and …");
//        report.setPublished_at("2024-03-08T19:59:00Z");
//        report.setUpdated_at("2024-03-11T19:59:10.493000Z");
        Report report1 = new Report(
                1541,
                "ISS Daily Summary Report – 3/08/2024",
                "https://blogs.nasa.gov/stationreport/2024/03/08/iss-daily-summary-report-3-08-2024/",
                "https://upload.wikimedia.org/wikipedia/commons/8/8a/ISS_after_completion_%28as_of_June_2006%29.jpg",
                "NASA",
                "Payloads: Advanced Resistive Exercise Device (ARED) Kinematics (ARED-K): An Isometric Mid-Thigh Pull (IMTP) session was performed on  ARED in support of the ARED-K investigation. Exploration-class missions including Artemis, Gateway, and beyond require an exercise device that is lightweight and has a small footprint. These devices provide a variety of full body resistance exercise options, and …",
                "2024-03-08T19:59:00Z",
                "2024-03-11T19:59:10.493000Z"
        );
        Report report2 = new Report(
                1542,
                "ISS Daily Summary Report – 3/07/2024",
                "https://blogs.nasa.gov/stationreport/2024/03/07/iss-daily-summary-report-3-07-2024/",
                "https://upload.wikimedia.org/wikipedia/commons/8/8a/ISS_after_completion_%28as_of_June_2006%29.jpg",
                "NASA",
                "Payloads: Complement of Integrated Protocols for Human Exploration Research on Varying Mission Durations (CIPHER): This week’s CIPHER testing continued with the performance of Optical Coherence Tomography 2 (OCT2), Pneumotonometry (PTM), and blood pressure measurements. CIPHER consists of 14 studies designed to improve our understanding of physiological and psychological changes in humans on missions that range …",
                "2024-03-07T19:59:00Z",
                "2024-03-11T19:59:10.489000Z"
        );

        when(reportService.saveAll(any())).thenReturn(Flux.just(report1, report2));

        webTestClient.get()
                .uri("/api/reports/fetch/external")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Report.class).contains(report1, report2);

        verify(reportService, times(1)).saveAll(any());
    }
}
