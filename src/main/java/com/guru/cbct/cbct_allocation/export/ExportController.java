package com.guru.cbct.cbct_allocation.export;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExportController {

    private final ExcelExportService excelExportService;

    @GetMapping("/export/excel")
    public ResponseEntity<byte[]> downloadExcel() {
        byte[] fileContent = excelExportService.exportToExcel();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=allocation_result.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileContent);
    }
}
