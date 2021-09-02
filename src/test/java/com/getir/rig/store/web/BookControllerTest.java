package com.getir.rig.store.web;

import com.getir.rig.store.controller.BookController;
import com.getir.rig.store.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static  org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static  org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

//@WebMvcTest
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    //@Test
    public void shouldCallListBooks() throws Exception {
        this.mockMvc.perform(get("/api/book")).andExpect(status().isOk());
    }
}
