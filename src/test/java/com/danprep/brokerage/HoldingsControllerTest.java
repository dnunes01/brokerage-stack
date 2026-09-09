package com.danprep.brokerage;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(HoldingsController.class)
class HoldingsControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    HoldingStore store;

    @Test
    void putUnknownIdReturnsNotFound() throws Exception {
        when(store.update(anyLong(), any(Holding.class))).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/v1/holdings/9999")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"symbol\":\"AAPL\",\"quantity\":\"15\",\"costBasis\":\"150.00\"}"))
                .andExpect(status().isNotFound());
    }
}
