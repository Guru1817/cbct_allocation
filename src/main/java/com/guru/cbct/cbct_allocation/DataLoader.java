package com.guru.cbct.cbct_allocation;

import com.guru.cbct.cbct_allocation.allocation.AllocationService;
import com.guru.cbct.cbct_allocation.export.ExcelExportService;
import com.guru.cbct.cbct_allocation.student.Student;
import com.guru.cbct.cbct_allocation.student.StudentRepository;
import com.guru.cbct.cbct_allocation.subject.Subject;
import com.guru.cbct.cbct_allocation.subject.SubjectRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;

@Component
//@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final SubjectRepository subjectRepo;
    private final StudentRepository studentRepo;
    private final AllocationService allocationService;
    private final ExcelExportService excelExportService;

    public DataLoader(SubjectRepository subjectRepo, StudentRepository studentRepo, AllocationService allocationService, ExcelExportService excelExportService) {
        this.subjectRepo = subjectRepo;
        this.studentRepo = studentRepo;
        this.allocationService = allocationService;
        this.excelExportService = excelExportService;
    }

    @Override
    public void run(String... args) throws Exception {
        // ✅ Load subjects with seat limits
        subjectRepo.saveAll(Arrays.asList(
                Subject.builder().name("Maths").totalSeats(2).build(),
                Subject.builder().name("Physics").totalSeats(2).build(),
                Subject.builder().name("Chemistry").totalSeats(2).build()
        ));

        // ✅ Load students from CSV
        try {InputStream inputStream = getClass().getResourceAsStream("/students.csv");
        Reader reader = new InputStreamReader(inputStream);
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());


            for (CSVRecord record : csvParser) {
                Student student = Student.builder()
                        .rollNo(record.get("rollNo"))
                        .name(record.get("name"))
                        .option1(record.get("firstChoice"))
                        .option2(record.get("secondChoice"))
                        .option3(record.get("thirdChoice"))
                        .build();
                studentRepo.save(student);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("✅ Subjects and Students loaded successfully!");

        allocationService.allocateSubjects();
        System.out.println("✅ Allocation Completed!");

        // Step 4: Export Results
        excelExportService.exportToExcel();
    }


}
