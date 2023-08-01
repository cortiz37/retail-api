package com.retail.provider;

import com.retail.provider.controller.ProductPriceController;
import com.retail.provider.service.ProductPriceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class IntegrationTest {

    private static final String PRODUCT_PRICE_PATH = ProductPriceController.PATH
        .replace("{brandId}", "1")
        .replace("{productId}", "35455");

    @Autowired
    private MockMvc mockMvc;

    @Test
    // Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    public void test1() throws Exception {
        mockMvc.perform(
                buildDateParameter("2020-06-14T10:00:00")
            ).andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.price").value("35.5"))
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    // Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    public void test2() throws Exception {
        mockMvc.perform(
                buildDateParameter("2020-06-14T16:00:00")
            ).andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.price").value("25.45"))
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    // Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    public void test3() throws Exception {
        mockMvc.perform(
                buildDateParameter("2020-06-14T21:00:00")
            ).andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.price").value("35.5"))
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    // Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
    public void test4() throws Exception {
        mockMvc.perform(
                buildDateParameter("2020-06-15T10:00:00")
            ).andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.price").value("30.5"))
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    // Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
    public void test5() throws Exception {
        mockMvc.perform(
                buildDateParameter("2020-06-16T21:00:00")
            ).andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.price").value("38.95"))
            .andDo(MockMvcResultHandlers.print());
    }

    private MockHttpServletRequestBuilder buildDateParameter(String date) {
        LocalDateTime localDateTime = LocalDateTime.parse(date);
        return MockMvcRequestBuilders.get(PRODUCT_PRICE_PATH)
            .param("date", localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
}