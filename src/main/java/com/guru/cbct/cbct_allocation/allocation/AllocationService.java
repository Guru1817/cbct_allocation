package com.guru.cbct.cbct_allocation.allocation;

import com.guru.cbct.cbct_allocation.student.Student;
import com.guru.cbct.cbct_allocation.student.StudentRepository;
import com.guru.cbct.cbct_allocation.subject.Subject;
import com.guru.cbct.cbct_allocation.subject.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AllocationService {

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    @Transactional
    public void allocateSubjects() {
        List<Subject> subjects = subjectRepository.findAll();
        for (Subject subject : subjects) {
            subject.setAvailableSeats(subject.getTotalSeats());
        }
        subjectRepository.saveAll(subjects);
        List<Student> students = studentRepository.findAllByOrderByIdAsc();

        for (Student student : students) {
            String allocated = tryAllocate(student.getOption1());
            if (allocated == null) {
                allocated = tryAllocate(student.getOption2());
            }
            if (allocated == null) {
                allocated = tryAllocate(student.getOption3());
            }

            student.setAllocatedSubject(allocated != null ? allocated : "Not Allocated");
            studentRepository.save(student);
        }
    }

    private String tryAllocate(String subjectName) {
        Subject subject = subjectRepository.findByName(subjectName);
        if (subject != null && subject.getAvailableSeats() > 0) {
            subject.setAvailableSeats(subject.getAvailableSeats() - 1);
            subjectRepository.save(subject);
            return subject.getName();
        }
        return null;
    }
}
