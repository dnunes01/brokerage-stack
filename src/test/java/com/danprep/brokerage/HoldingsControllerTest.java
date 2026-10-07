package com.danprep.brokerage;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
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

    @Test
    void putExistingIdReturnsOkWithBody() throws Exception {
        when(store.update(anyLong(), any(Holding.class)))
                .thenReturn(Optional.of(new Holding(1L, "RIVN", new BigDecimal("130.00"), new BigDecimal("16.55"))));

        mockMvc.perform(put("/api/v1/holdings/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"symbol\":\"RIVN\",\"quantity\":\"130\",\"costBasis\":\"16.55\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.symbol").value("RIVN"))
                .andExpect(jsonPath("$.quantity").value("130.00"))
                .andExpect(jsonPath("$.quantity").isString())
                .andExpect(jsonPath("$.costBasis").value("16.55"))
                .andExpect(jsonPath("$.costBasis").isString());

    }

    @Test
    void putInvalidBodyReturnsBadRequest() throws Exception {

        mockMvc.perform(put("/api/v1/holdings/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"symbol\":\"RIVN\",\"quantity\":\"130\",\"costBasis\":\"-16.55\"}"))
                .andExpect(status().isBadRequest());

        verify(store, never()).update(anyLong(), any(Holding.class));

    }

}
