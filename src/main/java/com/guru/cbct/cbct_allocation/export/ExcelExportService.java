package com.guru.cbct.cbct_allocation.export;

import com.guru.cbct.cbct_allocation.student.Student;
import com.guru.cbct.cbct_allocation.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelExportService {

    private final StudentRepository studentRepository;

    public byte[] exportToExcel() {
        List<Student> students = studentRepository.findAll();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Allocation Results");

            // Header Row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Name");
            headerRow.createCell(2).setCellValue("Roll No");
            headerRow.createCell(3).setCellValue("Option 1");
            headerRow.createCell(4).setCellValue("Option 2");
            headerRow.createCell(5).setCellValue("Option 3");
            headerRow.createCell(6).setCellValue("Allocated Subject");

            // Data Rows
            int rowNum = 1;
            for (Student student : students) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(student.getId());
                row.createCell(1).setCellValue(student.getName());
                row.createCell(2).setCellValue(student.getRollNo());
                row.createCell(3).setCellValue(student.getOption1());
                row.createCell(4).setCellValue(student.getOption2());
                row.createCell(5).setCellValue(student.getOption3());
                row.createCell(6).setCellValue(student.getAllocatedSubject());
            }

            // Auto-size columns
            for (int i = 0; i <= 6; i++) {
                sheet.autoSizeColumn(i);
            }

            // Write to byte array
            try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                workbook.write(bos);
                return bos.toByteArray();
            }

        } catch (IOException e) {
            throw new RuntimeException("Error generating Excel file", e);
        }
    }
}
