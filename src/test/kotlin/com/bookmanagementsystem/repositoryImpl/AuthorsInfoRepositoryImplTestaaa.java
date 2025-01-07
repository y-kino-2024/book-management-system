package com.bookmanagementsystem.repositoryImpl;

import com.bookmanagementsystem.dto.AuthorsInfoDto;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

class AuthorsInfoRepositoryImplTestaaa {
    @Mock
    DSLContext dslContext;
    @InjectMocks
    AuthorsInfoRepositoryImpl authorsInfoRepositoryImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFetchAuthor() {
        AuthorsInfoDto result = authorsInfoRepositoryImpl.fetchAuthor(0);
        Assertions.assertEquals(new AuthorsInfoDto(Integer.valueOf(0), "authorName", LocalDate.of(2025, Month.JANUARY, 7), "createdBy", LocalDateTime.of(2025, Month.JANUARY, 7, 0, 59, 42), "updatedBy", LocalDateTime.of(2025, Month.JANUARY, 7, 0, 59, 42), "deleteFlg"), result);
    }

    @Test
    void testCreateAuthor() {
        Integer result = authorsInfoRepositoryImpl.createAuthor(new AuthorsInfoDto(Integer.valueOf(0), "authorName", LocalDate.of(2025, Month.JANUARY, 7), "createdBy", LocalDateTime.of(2025, Month.JANUARY, 7, 0, 59, 42), "updatedBy", LocalDateTime.of(2025, Month.JANUARY, 7, 0, 59, 42), "deleteFlg"));
        Assertions.assertEquals(Integer.valueOf(0), result);
    }

    @Test
    void testUpdateAuthor() {
        int result = authorsInfoRepositoryImpl.updateAuthor(new AuthorsInfoDto(Integer.valueOf(0), "authorName", LocalDate.of(2025, Month.JANUARY, 7), "createdBy", LocalDateTime.of(2025, Month.JANUARY, 7, 0, 59, 42), "updatedBy", LocalDateTime.of(2025, Month.JANUARY, 7, 0, 59, 42), "deleteFlg"));
        Assertions.assertEquals(0, result);
    }
}

//Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme