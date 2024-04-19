package com.CL1.GestorDeDocentes.controllers;

import com.CL1.GestorDeDocentes.models.requests.TeacherRequest;
import com.CL1.GestorDeDocentes.models.responses.MessageResponse;
import com.CL1.GestorDeDocentes.models.responses.TeacherDetailsResponse;
import com.CL1.GestorDeDocentes.models.responses.TeacherResponse;
import com.CL1.GestorDeDocentes.services.teacher.TeacherService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers")
@CrossOrigin("*")
public class TeacherController {

    @Autowired
    private TeacherService service;

    @GetMapping
    public ResponseEntity<List<TeacherResponse>> getAllTeachers() {
        return ResponseEntity.ok(this.service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDetailsResponse> getDetails(
            @PathVariable("id") int teacherId
    ) {
        return ResponseEntity.ok(this.service.getOneDetails(teacherId));
    }

    @PostMapping
    public ResponseEntity<MessageResponse> create(
            @Valid @RequestBody TeacherRequest request
    ) {
        return new ResponseEntity<>(this.service.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> update(
            @Valid @RequestBody TeacherRequest request,
            @PathVariable("id") int teacherId
    ) {
        return ResponseEntity.ok(this.service.update(teacherId, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> delete(
            @PathVariable("id") int teacherId
    ) {
        return ResponseEntity.ok(this.service.delete(teacherId));
    }
}
