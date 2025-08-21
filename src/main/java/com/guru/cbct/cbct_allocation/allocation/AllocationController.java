package com.guru.cbct.cbct_allocation.allocation;

import com.guru.cbct.cbct_allocation.student.Student;
import com.guru.cbct.cbct_allocation.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/allocation")
@RequiredArgsConstructor
public class AllocationController {

    private final AllocationService allocationService;
    private final StudentRepository studentRepository;

    // Trigger allocation
    @PostMapping("/run")
    public String runAllocation() {
        allocationService.allocateSubjects();
        return "Allocation completed successfully!";
    }

    // Get all students with allocated subjects
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    @GetMapping("/ping")
    public String ping() {
        return "API is alive!";
    }

}
